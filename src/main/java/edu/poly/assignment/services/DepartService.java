package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.poly.assignment.model.Depart;
import edu.poly.assignment.repository.DepartRepository;

public interface DepartService {

	void deleteAll();

	void deleteAll(List<Depart> entities);

	void delete(Depart entity);

	void deleteById(Integer id);

	long count();

	List<Depart> findAllById(List<Integer> ids);

	Iterable<Depart> findAll();

	boolean existsById(Integer id);

	Optional<Depart> findById(Integer id);

	List<Depart> saveAll(List<Depart> entities);

	Depart save(Depart entity);

	List<Depart> findByNameLikeOrderByName(String name);

}
