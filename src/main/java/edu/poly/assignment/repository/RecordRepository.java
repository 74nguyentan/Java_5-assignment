package edu.poly.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.poly.assignment.model.Records;
import edu.poly.assignment.model.Staff;

public interface RecordRepository extends CrudRepository<Records, Integer> {
	List<Records> findAddRecordByStaff(Staff staff);
	List<Records> findAllByStaff(Staff staff);
}
