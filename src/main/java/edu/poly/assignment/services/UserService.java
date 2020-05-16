package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;



import edu.poly.assignment.model.User;


public interface UserService {

	void deleteAll();

	void deleteAll(List<User> entities);

	void delete(User entity);

	void deleteById(Long id);

	long count();

	Iterable<User> findAllById(Iterable<Long> ids);

	Iterable<User> findAll();

	boolean existsById(Long id);

	Optional<User> findById(Long id);

	List<User> saveAll(List<User> entities);

	User save(User entity);

	List<User> findByUsername(String username);

	List<User> findByNameLikeOrderByName(String name);

}
