package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;

import edu.poly.assignment.model.Depart;
import edu.poly.assignment.model.Staff;
import edu.poly.assignment.repository.StaffRepository;

public interface StaffSevice {

	void deleteAll();

	void deleteAll(List<Staff>  entities);

	void delete(Staff entity);

	void deleteById(Long id);

	long count();

	Iterable<Staff> findAllById(Iterable<Long> ids);

	Iterable<Staff> findAll();

	boolean existsById(Long id);

	Optional<Staff> findById(Long id);

	List<Staff> saveAll(List<Staff>  entities);

	Staff save(Staff entity);

	List<Depart> findAllDeparts();

	List<Staff> findByNameLikeOrderByName(String name);


}
