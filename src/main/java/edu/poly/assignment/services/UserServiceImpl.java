package edu.poly.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.assignment.model.User;
import edu.poly.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findByNameLikeOrderByName(String name) {
		return userRepository.findByNameLikeOrderByName(name);
	}




	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>) userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> findAllById(Iterable<Long> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

}
