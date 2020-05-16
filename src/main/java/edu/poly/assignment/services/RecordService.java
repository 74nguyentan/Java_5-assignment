package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;

import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;

public interface RecordService {

	void deleteAll();

	void deleteAll(List<Records> entities);

	void delete(Records entity);

	void deleteById(Integer id);

	long count();

	List<Records> findAllById(List<Integer> ids);

	Iterable<Records> findAll();

	boolean existsById(Integer id);

	Optional<Records> findById(Integer id);

	List<Records> saveAll(List<Records> entities);

	Records save(Records entity);

	List<Staff> findAllStaff();

	List<Records> findAddRecordByStaff(Staff staff);

	List<Records> findAllByStaff(Staff staff);



	

}
