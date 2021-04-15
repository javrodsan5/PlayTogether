
package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

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
	private UsuarioService	usuarioService;

	@Autowired
	private UserService		userService;

	@Autowired
	private UserTypeService	userTypeService;


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

	//FIND ALL USUARIOS
	@Test
	void shouldFindAllUsuarios() throws Exception {
		Collection<Usuario> usuarios = this.usuarioService.findAll();
		Assertions.assertThat(usuarios.size()).isEqualTo(4);

	}

	//FIND USUARIO BY NAME
	@Test
	void shouldFindUsuarioWithCorrectId() throws Exception {
		Usuario usuario = this.usuarioService.findByUsername("antonio98");
		Assertions.assertThat(usuario.getName()).isEqualTo("Antonio");
	}

	//FIND USUARIO BY NAME NEGATIVE
	@Test
	void shouldFindUsuarioWithIncorrectId() throws Exception {
		Usuario usuario = this.usuarioService.findByUsername("antonio98");
		Assertions.assertThat(usuario.getName()).isNotEqualTo("Fernando");
	}

	//CHECK CORREO EXISTS
	@Test
	void shouldFindUsuarioWithCorreo() throws Exception {
		Assertions.assertThat(this.usuarioService.checkCorreoExists("antonio@gmail.com")).isEqualTo(true);
	}

	//CHECK CORREO DOES NOT EXISTS
	@Test
	void shouldFindUsuarioWithIncorrectCorreo() throws Exception {
		Assertions.assertThat(this.usuarioService.checkCorreoExists("alberto@gmail.com")).isEqualTo(false);
	}

	//CHECK USERNAME EXISTS
	@Test
	void shouldFindUsuarioWithUsername() throws Exception {
		Assertions.assertThat(this.usuarioService.checkUsernameExists("antonio98")).isEqualTo(true);
	}

	//CHECK USERNAME DOES NOT EXISTS
	@Test
	void shouldFindUsuarioWithIncorrectUsername() throws Exception {
		Assertions.assertThat(this.usuarioService.checkUsernameExists("usuario4")).isEqualTo(false);
	}

	//CHECK PHONE EXISTS
	@Test
	void shouldFindUsuarioWithPhone() throws Exception {
		Assertions.assertThat(this.usuarioService.checkPhoneExists("654893274")).isEqualTo(true);
	}

	//CHECK PHONE DOES NOT EXISTS
	@Test
	void shouldFindUsuarioWithIncorrectPhone() throws Exception {
		Assertions.assertThat(this.usuarioService.checkPhoneExists("458943894")).isEqualTo(false);
	}
	
	//CHECK FIND MEETING BY USER ID AND YEAR
	@Test
	void shouldFindMeetingByIdUserAndYear() throws Exception {
		List<Integer> meetings = this.usuarioService.findMeetingByMonth(1, 2021);
		Assertions.assertThat(meetings.size()).isEqualTo(3);
	}
	
	//CHECK FIND CHAMPIONSHIPS BY USER ID AND YEAR
	@Test
	void shouldFindChampionShipByIdUserAndYear() throws Exception {
		List<Integer> championship = this.usuarioService.findChampionshipByMonth(1, 2021);
		Assertions.assertThat(championship.size()).isEqualTo(6);
	}

}
