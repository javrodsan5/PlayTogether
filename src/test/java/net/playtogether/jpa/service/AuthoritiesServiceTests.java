package net.playtogether.jpa.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthoritiesServiceTests {
	
	@Autowired
	private AuthoritiesService	authoritiesService;
	
	@Autowired
	private UserService	userService;
		
	//SAVE AUTHORITY
	@Test
	void saveUser() throws Exception {
		Integer contador = this.authoritiesService.findAll().size();
		Authorities authority = new Authorities();
		User user = new User();
		user.setUsername("usuarioPrueba");
		user.setPassword("us3r");
		this.userService.saveUser(user);
		String auth = "usuario";
		authority.setUser(user);
		authority.setAuthority(auth);
		this.authoritiesService.saveAuthorities(authority);
		Integer contadorFin = this.authoritiesService.findAll().size();
		Assertions.assertThat(contador).isNotEqualTo(contadorFin);
	}
		
	//SAVE AUTHORITY 2
	@Test
	void saveUser2() throws Exception {
		Integer contador = this.authoritiesService.findAll().size();
		String username = "marta98";
		String rol = "usuario";

		Authorities a = new Authorities();
		a.setAuthority(rol);
		a.setUser(this.userService.findUserByUsername(username));
		
		this.authoritiesService.save(a);
		Integer contadorFin = this.authoritiesService.findAll().size();
		Assertions.assertThat(contador).isNotEqualTo(contadorFin);
	}

	@Test
	void findByUsername() throws Exception {
		List<Authorities> a = this.authoritiesService.findByUsername("antonio98");
		assertThat(a.size()).isEqualTo(2);
	}

	@Test
	void delete() throws Exception {
		List<Authorities> a = this.authoritiesService.findByUsername("fernando98");
		for(Authorities au : a) {
			this.authoritiesService.delete(au.getId());
			break;
		}

		List<Authorities> a2 = this.authoritiesService.findByUsername("fernando98");
		assertThat(a2.isEmpty()).isEqualTo(false);
	}
}
