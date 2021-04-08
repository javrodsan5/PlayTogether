package net.playtogether.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.UserRepository;
@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
}