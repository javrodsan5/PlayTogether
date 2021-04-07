package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	

	private User u;
	
	@BeforeEach
	void setup() {
		u = new User();
		u.setId(1);
		u.setName("Usuario 1");
		u.setCorreo("correo@correo.com");
		u.setUsername("user1");
		u.setPassword("password");
		u.setBirthdate(LocalDate.of(1999, 3, 16));
		u.setPhone("123456789");
		u.setPayment(null);
		u.setStatistics(null);
		u.setType(null);
		u.setTeams(null);
		u.setMeetings(null);
		
		given(this.userService.findUserById(1)).willReturn(u);
	}
	
	
	// Test de consultar un usuario externo
		@Test
		void getUser() throws Exception {
			this.mockMvc.perform(get("/users/1")).andExpect(status().is2xxSuccessful());

			User userEntity = userService.findUserById(1);
			assertThat(userEntity.getUsername()).isEqualTo("user1");

		}

		// Test de consultar un usuario negative
		@Test
		void getUserNegative() throws Exception {
			this.mockMvc.perform(get("/users/1")).andExpect(status().is2xxSuccessful());

			User userEntity =userService.findUserById(1);
			assertThat(userEntity.getUsername()).isNotEqualTo("user2");

		}

}
