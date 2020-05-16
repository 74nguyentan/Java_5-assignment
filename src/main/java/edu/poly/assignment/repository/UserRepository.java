package edu.poly.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.assignment.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByUsername(String username);
	List<User> findByNameLikeOrderByName(String name);
}
