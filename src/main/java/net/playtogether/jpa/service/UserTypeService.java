package net.playtogether.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.repository.UserTypeRepository;

@Service
public class UserTypeService {
	
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	public UserTypeService(UserTypeRepository userTypeRepository) {
		this.userTypeRepository = userTypeRepository;
	}
	
	@Transactional(readOnly = true)
	public UserType findUserTypeById(Integer id) {
		return userTypeRepository.findById(id).orElse(null);
	}
}
