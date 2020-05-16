package edu.poly.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long>{
	List<Staff> findByNameLikeOrderByName(String name);

	
}
