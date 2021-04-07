package net.playtogether.jpa.service;

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
	private UserService			userService;
	
	// FIND USER BY ID
		@Test
		void shouldFindUserWithCorrectId() throws Exception {
			User user = this.userService.findUserByUsername("usuario1");
			Assertions.assertThat(user.getUsername()).isEqualTo("usuario1");
		}

}
