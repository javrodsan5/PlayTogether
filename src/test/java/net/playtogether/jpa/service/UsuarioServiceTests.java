package net.playtogether.jpa.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioServiceTests {
	
	@Autowired
	private UsuarioService			usuarioService;
	
	@Autowired
	private UserService				userService;
	
	@Autowired
	private UserTypeService			userTypeService;
		
	//FIND USER BY ID
		@Test
		void shouldFindUserWithCorrectId() throws Exception {
			Usuario user = this.usuarioService.findUserById(1);
			Assertions.assertThat(user.getName()).isEqualTo("Antonio");
		}
		
	//FIND USER BY ID NEGATIVE
		@Test
		void shouldFindUserWithIncorrectId() throws Exception {
			Usuario user = this.usuarioService.findUserById(1);
			Assertions.assertThat(user.getName()).isNotEqualTo("usuario2");
		}
		
	//SAVE USER
		@Test
		void saveUser() throws Exception {
			Integer contador = this.usuarioService.findAll().size();
			Usuario usuario = new Usuario();
			usuario.setId(10);
			usuario.setName("usuarioPr");
			usuario.setCorreo("correo@cor.com");
			usuario.setBirthdate(LocalDate.of(1999, 2, 14));
			usuario.setPhone("123456789");	
			User user = new User();
			user.setUsername("usuarioPrueba");
			user.setPassword("us3r");
			this.userService.saveUser(user);
			usuario.setUser(user);
			UserType userType = this.userTypeService.findUserTypeById(1);
			usuario.setType(userType);
			this.usuarioService.saveUsuario(usuario);
			Integer contadorFin = this.usuarioService.findAll().size();
			Assertions.assertThat(contador).isNotEqualTo(contadorFin);
		}
		
}
