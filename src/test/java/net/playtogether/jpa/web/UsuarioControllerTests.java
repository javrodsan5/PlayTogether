package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.UserService;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UsuarioService usuarioService;

	@MockBean
	private UserTypeService userTypeService;

	private User u;

	private Usuario usuario;

	private Usuario usuario2;

	private User u2;

	private UserType userType;

	private Championship testChampionship;

	@BeforeEach
	void setup() {
		usuario = new Usuario();
		this.usuario.setId(1);
		usuario.setName("usuarioPr");
		usuario.setCorreo("correo@cor.com");
		usuario.setBirthdate(LocalDate.of(1999, 2, 14));
		usuario.setPhone("123456789");
		usuario.setId(1);
		u = new User();
		u.setUsername("user1");
		u.setPassword("password");
		this.u.setEnabled(true);

		usuario.setUser(u);

		userType = new UserType();
		userType.setId(1);
		userType.setName("Basico");

		usuario2 = new Usuario();
		usuario2.setName("usuarioPq");
		usuario2.setCorreo("correo2@cor.com");
		usuario2.setBirthdate(LocalDate.of(1999, 2, 14));
		usuario2.setPhone("123256789");
		usuario2.setId(2);
		u2 = new User();
		u2.setUsername("user2");
		u2.setPassword("password");

		usuario2.setUser(u2);

		Sport s = new Sport();
		SportType st = new SportType();
		st.setId(1);
		st.setName("Equipo");
		s.setChampionships(new ArrayList<Championship>());
		s.setId(1);
		s.setMeetings(new ArrayList<Meeting>());
		s.setName("Tenis");
		s.setSportType(st);
		s.setNumberOfPlayersInTeam(1);

		this.testChampionship = new Championship();
		this.testChampionship.setName("Torneo1");
		this.testChampionship.setCity("Sevilla");
		this.testChampionship.setDescription("Descripcion del torneo");
		this.testChampionship.setStartDate(LocalDate.of(2021, 06, 15));
		this.testChampionship.setFinishDate(LocalDate.of(2021, 06, 25));
		this.testChampionship.setId(1);
		this.testChampionship.setMaxTeams(16);
		this.testChampionship.setMatches(new ArrayList<Match>());
		this.testChampionship.setSport(s);

		Team t = new Team();
		t.setChampionship(this.testChampionship);
		t.setId(1);
		t.setName("Equipo1");
		t.setTeamSize(4);
		t.setChampionship(this.testChampionship);
		List<Team> teams = new ArrayList<Team>();
		teams.add(t);
		this.usuario.setTeams(teams);

		given(this.userService.findUserByUsername("user1")).willReturn(u);
		given(this.userTypeService.findUserTypeById(1)).willReturn(userType);
		given(this.usuarioService.findUserById(1)).willReturn(usuario);
		given(this.usuarioService.findByUsername("user1")).willReturn(usuario);
		given(this.usuarioService.findUserById(2)).willReturn(usuario2);
	}

	// Test de consultar un usuario externo
	@WithMockUser(authorities = "usuario")
	@Test
	void getUser() throws Exception {
		this.mockMvc.perform(get("/usuarios/1")).andExpect(status().is2xxSuccessful());

		Usuario userEntity = usuarioService.findUserById(1);
		assertThat(userEntity.getName()).isEqualTo("usuarioPr");

	}

	// Test de consultar un usuario negative
	@WithMockUser(authorities = "usuario")
	@Test
	void getUserNegative() throws Exception {
		this.mockMvc.perform(get("/usuarios/1")).andExpect(status().is2xxSuccessful());
		Usuario userEntity = usuarioService.findUserById(1);
		assertThat(userEntity.getName()).isNotEqualTo("usuarioPq");

	}

	// Test de consultar un usuario propio
	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	@Test
	void getMyProfile() throws Exception {
		this.mockMvc.perform(get("/myprofile/1")).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("users/userProfile"));

	}

	// Test de consultar un usuario negative
	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	@Test
	void getMyProfileNegative() throws Exception {
		this.mockMvc.perform(get("/myprofile/2")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("error-403"));
	}

	// Test de GetMapping crear usuario
	@WithMockUser(authorities = "usuario")
	@Test
	void getCreateUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/registro"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("users/register"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("usuario"));

	}

	// Test de PostMapping de crear usuario
	@WithMockUser(authorities = "usuario")
	@Test
	void createUser() throws Exception {
		mockMvc.perform(post("/registro").with(csrf()).param("name", "Usuario").param("correo", "usu@usuario.com")
				.param("birthdate", "1999/06/12").param("phone", "666555888").param("user.username", "usuarioPruebas")
				.param("user.password", "Usuar1o")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));

	}

	// Test update meeting controller
	@Test
	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	void initUpdateUser() throws Exception {
		mockMvc.perform(get("/myprofile/1/edit")).andExpect(status().isOk())
				.andExpect(model().attributeExists("usuario"))
				.andExpect(model().attribute("usuario", hasProperty("correo", is("correo@cor.com"))))
				.andExpect(model().attribute("usuario", hasProperty("phone", is("123456789"))))
				.andExpect(model().attribute("usuario", hasProperty("id", is(1))))
				.andExpect(view().name("users/updateUser"));
	}

	// Test update meeting controller
	@Test
	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	void initUpdateUserError() throws Exception {
		this.mockMvc.perform(get("/myprofile/2/edit")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("error-403"));
	}

	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	@Test
	void testProcessUpdateUserFormSuccess() throws Exception {
		mockMvc.perform(post("/myprofile/1/edit").with(csrf()).param("correo", "correo2@cor.com").param("id", "1")
				.param("name", "usuarioPr").param("birthdate", "1999/02/14").param("phone", "123456789")
				.param("user.username", "user1")

		).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/myprofile/1"));
	}

	@WithMockUser(username = "user1", password = "password", authorities = "usuario")
	@Test
	void testProcessUpdateUserFormErrors() throws Exception {
		mockMvc.perform(
				post("/myprofile/1/edit").with(csrf()).param("correo", "").param("id", "1").param("name", "usuarioPr")
						.param("birthdate", "1999/02/14").param("phone", "123456789").param("user.username", "user1"))
				.andExpect(model().attributeHasFieldErrors("usuario", "correo"))
				.andExpect(view().name("users/updateUser"));

	}

	// Test de GetMapping de historial de torneos de un usuario
	@WithMockUser(username = "user1", authorities =

	"usuario", password = "password")
	@Test
	void getChampionshipRecord() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/myprofile/1/championshipsRecord"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("users/championshipRecord"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("championships"));

	}
}
