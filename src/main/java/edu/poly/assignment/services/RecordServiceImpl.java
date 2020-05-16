package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.bind.v2.util.StackRecorder;

import edu.poly.assignment.model.Depart;
import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;
import edu.poly.assignment.repository.RecordRepository;
import edu.poly.assignment.repository.StaffRepository;

@Service
public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private StaffRepository staffRepository;
	
	
	




	@Override
	public List<Records> findAllByStaff(Staff staff) {
		return recordRepository.findAllByStaff(staff);
	}

	@Override
	public List<Records> findAddRecordByStaff(Staff staff) {
		return recordRepository.findAddRecordByStaff(staff);
	}





	@Override
	public List<Staff> findAllStaff(){
		return(List<Staff>) staffRepository.findAll();
	}
	
	@Override
	public Records save(Records entity) {
		return recordRepository.save(entity);
	}

	@Override
	public List<Records> saveAll(List<Records> entities) {
		return (List<Records>) recordRepository.saveAll(entities);
	}

	@Override
	public Optional<Records> findById(Integer id) {
		return recordRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return recordRepository.existsById(id);
	}

	@Override
	public Iterable<Records> findAll() {
		return recordRepository.findAll();
	}

	@Override
	public List<Records> findAllById(List<Integer> ids) {
		return (List<Records>) recordRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return recordRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		recordRepository.deleteById(id);
	}

	@Override
	public void delete(Records entity) {
		recordRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Records> entities) {
		recordRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		recordRepository.deleteAll();
	}
	
	
}
