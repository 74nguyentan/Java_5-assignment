package edu.poly.assignment.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.poly.assignment.dtos.RecordDto;
import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;
import edu.poly.assignment.repository.StaffRepository;
import edu.poly.assignment.services.RecordService;
import edu.poly.assignment.services.StaffSevice;

@Controller
@RequestMapping("/records")
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StaffSevice staffSevice;

	@GetMapping("/add")
	public String add(ModelMap model) {
		
		RecordDto recordDto = new RecordDto();
		model.addAttribute("recordDto" , recordDto);
		
		return "Records/addOrEdit";
	}
	
	@PostMapping("/saveOrUpdate")
	public String save(ModelMap model, @Validated RecordDto recordDto, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("message" ,  "please input all required fields");
			model.addAttribute("recordDto",recordDto);
			return "Records/addOrEdit";	
		}
		if(recordDto.getId() != null && recordDto.getId() > 0 ) {
			model.addAttribute("message","the Record update");
		}
		else {
			model.addAttribute("message","the Record inserted");
		}
		
		Records records = new Records();
		records.setId(recordDto.getId());
		records.setDate(recordDto.getDate());
		records.setReason(recordDto.getReason());
		records.setType(recordDto.getType());

		Staff staff = staffRepository.findById(recordDto.getStaff()).orElseThrow(()
		-> new RuntimeException("Fail! -> Không tìm thấy nhân viên này"));
	
//		Staff staff = new Staff();
//		staff.setId(recordDto.getStaff());
		records.setStaff(staff);
		
		recordService.save(records);
		recordDto.setId(records.getId());
		
		model.addAttribute("records", recordService.findAll());
		return "Records/list";
		
	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(ModelMap model,@PathVariable(name = "id") Integer id) {
		
		Optional<Records> opp = recordService.findById(id);
		if(opp.isPresent()) {
			RecordDto reDto = new RecordDto();
			BeanUtils.copyProperties(opp.get(), reDto);
			reDto.setStaff(opp.get().getStaff().getId());
			model.addAttribute("recordDto",reDto);
		}
		else {
			return list(model);
		}
		return "Records/addOrEdit";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("records", recordService.findAll());
		return "Records/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Integer id) {
		recordService.deleteById(id);
		
		return list(model);
	}
	
	@GetMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "" ) String name) {
//		List<Staff> listt = recordService.findAllStaff();
//		System.out.println(" ----111---- " + listt.get(0));
//		List<Records> list = recordService.findAllByStaff(name);
		return "Records/list";
	}
	

	@ModelAttribute(name = "staffs")
	public List<Staff> getStaff(){
		
		return recordService.findAllStaff();
	}
	
	
	
	@GetMapping("/report/{id}")
	public String report(ModelMap model, @PathVariable(name = "id") Long id) {
//		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
//		Query query = session.createQuery("SELECT r.Staff.id, " + " SUM(case when r.type=1 then 1 else 0 end), "
//				+ " SUM(case when r.type=0 then 1 else 0 end)" + " FROM Records r " + " GROUP BY r.Staff.id");
//
//		List<Object[]> list = ((org.hibernate.query.Query) query).list();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Object[] records = (Object[]) iterator.next();
//			System.out.println("=======" + records[0]);
//			System.out.println("=======" + records[1]);
//			System.out.println("=======" + records[2]);
//
//		}
//		model.addAttribute("arrays", list);
		Staff staff = staffSevice.findById(id).orElseThrow(() -> new RuntimeException("khoong tim thay staff"));
		List<Records> records = recordService.findAddRecordByStaff(staff);
		int disceple = records.stream().filter(item -> item.getType() == 0).collect(Collectors.toList()).size(); // ki luat
		int achieve = records.stream().filter(item -> item.getType() == 1).collect(Collectors.toList()).size();  // thanh tich
		int sum = achieve - disceple ;
		model.addAttribute("staff", staff);
		model.addAttribute("disceple", disceple);
		model.addAttribute("achieve", achieve);
		model.addAttribute("sum", sum);
		return "Records/report";
	}
}
