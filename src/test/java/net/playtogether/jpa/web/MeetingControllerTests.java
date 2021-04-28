
package net.playtogether.jpa.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.repository.MeetingRepository;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeetingControllerTests {

	@Autowired
	private MockMvc				mockMvc;
	@Autowired
	private MeetingRepository	meetingRepository;

	@MockBean
	private MeetingService		meetingService;

	@MockBean
	private SportService		sportService;

	@MockBean
	private UsuarioService		usuarioService;

	private Meeting				testMeeting1;

	private Meeting				testMeeting2;

	private Meeting				testMeeting3;

	private User				user;

	private User				user2;


	@BeforeEach
	void setup() {
		this.testMeeting1 = new Meeting();
		this.testMeeting1.setId(1);
		this.testMeeting1.setAddress("Bami");
		this.testMeeting1.setCity("Sevilla");
		this.testMeeting1.setDate(LocalDateTime.of(2021, 06, 12, 12, 00));
		this.testMeeting1.setParticipants(new ArrayList<Usuario>());
		this.testMeeting1.setDescription("Una partidata");
		this.testMeeting1.setNumberOfPlayers(2);

		this.testMeeting2 = new Meeting();
		this.testMeeting2.setId(2);
		this.testMeeting2.setAddress("Calle Antonio Ulloa");
		this.testMeeting2.setCity("Sevilla");
		this.testMeeting2.setDate(LocalDateTime.of(2021, 06, 23, 18, 16));
		this.testMeeting2.setParticipants(new ArrayList<Usuario>());
		this.testMeeting2.setDescription("Partido de tenis");

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

		Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario 1");
		u.setCorreo("correo@correo.com");
		u.setBirthdate(LocalDate.of(1999, 3, 16));
		u.setPhone("123456789");
		u.setPuntos(5);
		u.setPayment(null);
		u.setStatistics(null);
		u.setType(null);
		u.setTeams(null);
		u.setMeetings(null);
		u.setPuntos(10);

		this.user = new User();
		this.user.setUsername("user1");
		this.user.setPassword("password");
		Set<Authorities> au = new HashSet<>();
		Authorities aut = new Authorities();
		aut.setId(1);
		aut.setUser(user);
		aut.setAuthority("premium");
		au.add(aut);
		this.user.setAuthorities(au);
		this.user.setEnabled(true);

		u.setUser(this.user);

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(u);

		Usuario u2 = new Usuario();
		u2.setId(2);
		u2.setName("Usuario2");
		u2.setCorreo("correo2@correo.com");
		u2.setBirthdate(LocalDate.of(1999, 2, 16));
		u2.setPhone("123456789");
		u2.setPayment(null);
		u2.setStatistics(null);
		u2.setType(null);
		u2.setTeams(null);
		u2.setMeetings(null);
		u2.setPuntos(10);

		this.user2 = new User();
		this.user2.setUsername("user2");
		this.user2.setPassword("password");
		this.user2.setEnabled(true);

		usuarios.add(u2);

		this.testMeeting3 = new Meeting();
		this.testMeeting3.setId(3);
		this.testMeeting3.setAddress("Bami");
		this.testMeeting3.setCity("Sevilla");
		this.testMeeting3.setDate(LocalDateTime.of(2021, 06, 12, 12, 00));
		this.testMeeting3.setParticipants(usuarios);
		this.testMeeting3.setMeetingCreator(u);
		this.testMeeting3.setDescription("Una partidata");
		this.testMeeting3.setNumberOfPlayers(2);

		BDDMockito.given(this.meetingService.findMeetingById(1)).willReturn(this.testMeeting1);
		BDDMockito.given(this.sportService.findSportById(1)).willReturn(s);
		BDDMockito.given(this.meetingService.findMeetingById(2)).willReturn(this.testMeeting2);
		BDDMockito.given(this.usuarioService.findUserById(1)).willReturn(null);
		BDDMockito.given(this.usuarioService.usuarioLogueado("antonio98")).willReturn(u);
		BDDMockito.given(this.meetingService.findMeetingById(3)).willReturn(this.testMeeting3);
		BDDMockito.given(this.usuarioService.usuarioLogueado(this.user.getUsername())).willReturn(u);
		BDDMockito.given(this.usuarioService.usuarioLogueado(this.user2.getUsername())).willReturn(u2);
		BDDMockito.given(this.usuarioService.findUserById(2)).willReturn(u2);
	}

	// Test de consultar quedadas
	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void listMeetings() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/meetings")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

		Collection<Meeting> meetingEntities = this.meetingRepository.findAll();
		Assertions.assertThat(meetingEntities.size()).isEqualTo(7);

	}

	// Test negativo de consultar quedadas
	@WithMockUser(value = "user1", authorities = "usuario")
	@Test
	void listMeetingsNegative() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/meetings")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

		Collection<Meeting> meetingEntities = this.meetingRepository.findAll();
		Assertions.assertThat(meetingEntities.size()).isNotEqualTo(4);

	}

	// Test de crear quedada

	@Test
	@WithMockUser(username = "antonio98", authorities = "usuario")
	void createMeeting() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/meetings/add").with(SecurityMockMvcRequestPostProcessors.csrf()).param("address", "Charco la Pava").param("city", "Sevilla").param("date", "2021-06-12T12:00").param("id", "1")
			.param("description", "Cambio de planes").param("creationDate", "2021/06/12")).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/meetings"));

	}

	// Test de crear quedada negativo
	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void createMeetingNegative() throws Exception {

		this.mockMvc
			.perform(
				MockMvcRequestBuilders.post("/sports/1/meetings/add").param("address", "Calle 1").param("city", "Sevilla").param("description", "aaaa").param("date", "2021/06/14 14:14").param("sport", "1").with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	// Test join meeting controller
	//	@Test
	//	@WithMockUser(value = "user1", authorities="usuario")
	//	void joinMeeting() throws Exception {
	//		this.mockMvc.perform(get("/meetings/1/join")).andExpect(status().is2xxSuccessful());
	//
	//		Meeting meetingEntity = meetingService.findMeetingById(1);
	//		assertThat(meetingEntity.getParticipants().size()).isEqualTo(1);
	//
	//	}

	// Test join meeting controller
	//	@Test
	//	@WithMockUser(value = "user1", authorities="usuario")
	//	void joinMeetingAlreadyExistsParticipant() throws Exception {
	//		this.mockMvc.perform(get("/meetings/1/join")).andExpect(status().is2xxSuccessful());
	//
	//		Meeting meetingEntity = meetingService.findMeetingById(1);
	//		assertThat(meetingEntity.getParticipants().size()).isEqualTo(1);
	//
	//		this.mockMvc.perform(get("/meetings/1/join")).andExpect(status().is2xxSuccessful());
	//
	//		Meeting meetingEntity2 = meetingService.findMeetingById(1);
	//		assertThat(meetingEntity2.getParticipants().size()).isEqualTo(1);
	//
	//	}

	// Test update meeting controller
	//	@Test
	//	@WithMockUser(value = "user1", authorities="usuario")
	//	void initUpdateMeeting() throws Exception {
	//		mockMvc.perform(get("/sports/1/meetings/1/edit")).andExpect(status().isOk())
	//				.andExpect(model().attributeExists("meeting"))
	//				.andExpect(model().attribute("meeting", hasProperty("address", is("Bami"))))
	//				.andExpect(model().attribute("meeting", hasProperty("city", is("Sevilla"))))
	//				.andExpect(model().attribute("meeting", hasProperty("date", is(LocalDateTime.of(2021, 06, 12, 12, 00)))))
	//				.andExpect(model().attribute("meeting", hasProperty("description", is("Una partidata"))))
	//				.andExpect(model().attribute("meeting", hasProperty("id", is(1))))
	//				.andExpect(model().attribute("meeting", hasProperty("participants", is(testMeeting1.getParticipants()))))
	//				.andExpect(view().name("meetings/updateMeetingForm"));
	//	}

	@WithMockUser(value = "user1", authorities = "usuario")
	@Test
	void testProcessUpdateMeetingFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/meetings/1/edit").with(SecurityMockMvcRequestPostProcessors.csrf()).param("address", "Charco la Pava").param("city", "Sevilla").param("date", "2021-06-12T12:00").param("id", "1")
			.param("description", "Cambio de planes").param("numberOfPlayers", "10")).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/meetings"));
	}

	@WithMockUser(value = "user1", authorities = "usuario")
	@Test
	void testProcessUpdateMeetingFormErrors() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/sports/1/meetings/1/edit").with(SecurityMockMvcRequestPostProcessors.csrf()).param("address", "Charco la Pava").param("city", "Sevilla").param("date", "").param("id", "1").param("description",
				"Cambio de planes").param("numberOfPlayers", "10"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("meeting")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("meeting", "date"))
			.andExpect(MockMvcResultMatchers.view().name("meetings/updateMeetingForm"));
	}

	// ABANDONAR EQUIPO COMO OWNER
	@Test
	@WithMockUser(username = "user1", authorities = {
		"usuario"
	}, password = "password")
	void ownerLeaveTeam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/meetings/3/leave")).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/meetings/3"));
	}

	// ABANDONAR EQUIPO COMO USUARIO
	@Test
	@WithMockUser(username = "user2", authorities = {
		"usuario"
	}, password = "password")
	void userLeaveTeam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/meetings/3/leave")).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/meetings/3"));
	}

	// ELIMINAR JUGADOR SIENDO OWNER
	@Test
	@WithMockUser(username = "user1", authorities = {
		"usuario"
	}, password = "password")
	void deleteMeetingUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/meetings/3/2/delete")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("meetings/meetingDetails"));
	}

}
