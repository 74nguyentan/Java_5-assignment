package edu.poly.assignment.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.EntityManagerFactory;

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

import edu.poly.assignment.dtos.StaffDto;
import edu.poly.assignment.model.Depart;
import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;
import edu.poly.assignment.services.RecordService;
import edu.poly.assignment.services.StaffSevice;

@Controller
@RequestMapping("/staffs")
public class StaffController {
	@Autowired
	private StaffSevice staffSevice;
	@Autowired
	private RecordService recordService;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("staffs", staffSevice.findAll());
		return "Staffs/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {

		StaffDto staffDto = new StaffDto();
		model.addAttribute("staffDto", staffDto);

		return "Staffs/addOrEdit";
	}

	@PostMapping("/saveOrUpdate")
	public String save(ModelMap model, @Validated StaffDto staffDto, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("message", "please input all required fields");
			model.addAttribute("staffDto", staffDto);

			return "Staffs/addOrEdit";
		}

		// check update hay add new
		if (staffDto.getId() != null && staffDto.getId() > 0) {

			model.addAttribute("message", "the staff update");
		} else {
			model.addAttribute("message", "new Staff inserted");
		}

		// upload ảnh
		Path path = Paths.get("images/");
		if (staffDto.getImage() != null && !staffDto.getImage().isEmpty()) {
			try (InputStream inp = staffDto.getImage().getInputStream()) {
				Files.copy(inp, path.resolve(staffDto.getImage().getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				String fileName = staffDto.getImage().getOriginalFilename();
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "Error: " + e.getMessage());
			}
		}

		Staff staff = new Staff();
		staff.setId(staffDto.getId());
		staff.setBirthday(staffDto.getBirthday());
		staff.setName(staffDto.getName());
		staff.setGioiTinh(staffDto.getGioiTinh());
		staff.setCapdo(staffDto.getCapdo());
		staff.setEmail(staffDto.getEmail());
		staff.setNote(staffDto.getNote());
		staff.setSalary(staffDto.getSalary());
		staff.setPhone(staffDto.getPhone());
		if (staffDto.getImage() != null && !staffDto.getImage().isEmpty()) {

			staff.setPhoto(staffDto.getImage().getOriginalFilename());
		} else {
			Optional<Staff> s = staffSevice.findById(staff.getId());
			if (s.isPresent()) {
				staff.setPhoto(s.get().getPhoto());
			}
		}
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);

		staffSevice.save(staff);

		staffDto.setId(staff.getId());
		// model.addAttribute("staffDto", staffDto);
		model.addAttribute("staffs", staffSevice.findAll());
		return "Staffs/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
		Optional<Staff> optStaff = staffSevice.findById(id);
		if (optStaff.isPresent()) {
			StaffDto dto = new StaffDto();
			BeanUtils.copyProperties(optStaff.get(), dto);
			dto.setDepartId(optStaff.get().getDepart().getId());

			model.addAttribute("staffDto", dto);
		} else {
			return list(model);
		}
		return "Staffs/addOrEdit";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		staffSevice.deleteById(id);
		return list(model);
	}

	@GetMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<Staff> list = staffSevice.findByNameLikeOrderByName(name);
		model.addAttribute("staffs", list);

		return "Staffs/list";
	}

	@ModelAttribute(name = "departs")
	public List<Depart> getDepart() {

		return staffSevice.findAllDeparts();
	}

	@ModelAttribute(name = "gioiTinh")
	public String[] getGioiTinh() {
		String[] gioiTinh = { "Nam", "Nu" };

		return gioiTinh;
	}

	@ModelAttribute(name = "capdo")
	public int[] getCapdo() {
		int[] capdo = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		return capdo;
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
		int disceple = records.stream().filter(item -> item.getType() == 0).collect(Collectors.toList()).size();
		int achieve = records.stream().filter(item -> item.getType() == 1).collect(Collectors.toList()).size();
		int sum = achieve - disceple  ;
		model.addAttribute("staff", staff);
		model.addAttribute("sum", sum);
		model.addAttribute("disceple", disceple);
		model.addAttribute("achieve", achieve);
		return "Staffs/report";
	}
}
