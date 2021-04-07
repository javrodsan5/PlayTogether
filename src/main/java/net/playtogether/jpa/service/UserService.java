package net.playtogether.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository=userRepository;
	}

	
	@Transactional(readOnly=true)
	public User findUserById(int id){
		return userRepository.findById(id).orElse(null);
	}

	@Transactional
	public User findUserByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
}
