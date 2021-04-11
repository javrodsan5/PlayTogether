
package net.playtogether.jpa.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;


	// FIND USER BY USERNAME
	@Test
	void shouldFindUserWithCorrectUsername() throws Exception {
		User user = this.userService.findUserByUsername("antonio98");
		Assertions.assertThat(user.getUsername()).isEqualTo("antonio98");
	}

	// FIND USER BY USERNAME NEGATIVE
	@Test
	void shouldFindUserWithIncorrectUsername() throws Exception {
		User user = this.userService.findUserByUsername("antonio98");
		Assertions.assertThat(user.getUsername()).isNotEqualTo("fernando98");
	}

	//FIND USER BY ID
	@Test
	void shouldFindUserWithCorrectId() throws Exception {
		User user = this.userService.findUser("antonio98").orElse(null);
		Assertions.assertThat(user.getUsername()).isEqualTo("antonio98");
	}

	//FIND USER BY ID NEGATIVE
	@Test
	void shouldFindUserWithIncorrectId() throws Exception {
		User user = this.userService.findUser("antonio98").orElse(null);
		Assertions.assertThat(user.getUsername()).isNotEqualTo("fernando98");
	}

	//SAVE USER
	@Test
	void saveUser() throws Exception {
		Integer contador = this.userService.findAll().size();
		User user = new User();
		user.setUsername("usuarioPrueba");
		user.setPassword("us3r");
		this.userService.saveUser(user);
		Integer contadorFin = this.userService.findAll().size();
		Assertions.assertThat(contador).isNotEqualTo(contadorFin);
	}

	//FIND ALL USERS
	@Test
	void shouldFindAllUsers() throws Exception {
		Collection<User> users = this.userService.findAll();
		Assertions.assertThat(users.size()).isEqualTo(4);

	}

}
