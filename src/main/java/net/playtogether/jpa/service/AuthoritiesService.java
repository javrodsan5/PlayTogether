package net.playtogether.jpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	private UserService userService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository, UserService userService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userService = userService;
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}

	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		Optional<User> user = userService.findUser(username);
		if (user.isPresent()) {
			authority.setUser(user.get());
			authority.setAuthority(role);
			// user.get().getAuthorities().add(authority);
			authoritiesRepository.save(authority);
		} else
			throw new DataAccessException("User '" + username + "' not found!") {
			};
	}

	@Transactional
	public List<Authorities> findAll() {
		return (List<Authorities>) authoritiesRepository.findAll();
	}

	@Transactional
	public List<Authorities> findByUsername(String username) {
		return authoritiesRepository.findByUsername(username);
	}

	@Transactional
	public void save(Authorities au) {
		this.authoritiesRepository.save(au);
		
	}
	
	@Transactional
	public void delete(Authorities au) {
		this.authoritiesRepository.delete(au);
		
	}
	
}
