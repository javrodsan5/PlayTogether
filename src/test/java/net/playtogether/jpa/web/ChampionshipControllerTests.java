
package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.MatchService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.TeamService;
import net.playtogether.jpa.service.UserService;
import net.playtogether.jpa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChampionshipControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChampionshipService championshipService;

	private Championship testChampionship;

	@MockBean
	private SportService sportService;

	@MockBean
	private MatchService matchService;

	@MockBean
	private TeamService teamService;

	@MockBean
	private UserService userService;

	@MockBean
	private UsuarioService usuarioService;

	private User user;
	
	private User user2;

	@BeforeEach
	void setup() {

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
		this.testChampionship.setName("Torneo8");
		this.testChampionship.setCity("Sevilla");
		this.testChampionship.setDescription("Descripcion del torneo");
		this.testChampionship.setStartDate(LocalDate.of(2021, 06, 15));
		this.testChampionship.setFinishDate(LocalDate.of(2021, 06, 25));
		this.testChampionship.setId(8);
		this.testChampionship.setMaxTeams(16);
		this.testChampionship.setMatches(new ArrayList<Match>());
		this.testChampionship.setSport(s);


		Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario1");
		u.setCorreo("correo@correo.com");
		u.setBirthdate(LocalDate.of(1999, 3, 16));
		u.setPhone("123456789");
		u.setPayment(null);
		u.setStatistics(null);
		u.setType(null);
		u.setTeams(null);
		u.setMeetings(null);
		u.setPuntos(10);

		user = new User();
		user.setUsername("user1");
		user.setPassword("password");
		this.user.setEnabled(true);

		u.setUser(user);

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
		
		user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("password");
		this.user2.setEnabled(true);
		
		usuarios.add(u2);
		
		Team t = new Team();
		t.setChampionship(this.testChampionship);
		t.setId(8);
		t.setName("Equipo8");
		t.setParticipants(usuarios);
		t.setUser(u);
		List<Team> teams = new ArrayList<Team>();
		teams.add(t);
		this.testChampionship.setTeams(teams);
		
		u.setTeams(teams);

		Team team1 = new Team();
		team1.setChampionship(this.testChampionship);
		team1.getParticipants().add(u);
		Team team2 = new Team();
		team2.setChampionship(this.testChampionship);

		Team team3 = new Team();
		t.setChampionship(this.testChampionship);
		t.setId(3);
		team3.setName("lobos");

		Match match = new Match();
		match.setId(1);
		match.setChampionship(this.testChampionship);
		match.setDateTime(LocalDateTime.now());
		match.setTeam1(team1);
		match.setTeam2(team2);

		Match match2 = new Match();
		match.setId(2);
		match.setChampionship(this.testChampionship);
		match.setDateTime(LocalDateTime.now());
		match.setTeam1(t);
		match.setTeam2(team2);
		Collection<Match> matches = new ArrayList<>();
		matches.add(match2);

		BDDMockito.given(this.championshipService.findTeamsByChampionshipId(8)).willReturn(teams);
		BDDMockito.given(this.championshipService.findChampionshipId(8)).willReturn(this.testChampionship);
		BDDMockito.given(this.sportService.findSportById(1)).willReturn(s);
		BDDMockito.given(this.championshipService.findTeamId(8)).willReturn(t);
		BDDMockito.given(this.championshipService.findUserByNameOrUsername("Usuario1")).willReturn(usuarios);
		BDDMockito.given(this.matchService.findMatchById(1)).willReturn(match);
		BDDMockito.given(this.teamService.findTeamById(3)).willReturn(team3);
		BDDMockito.given(this.teamService.findTeamById(8)).willReturn(t);
		BDDMockito.given(this.userService.findUserByUsername("user1")).willReturn(user);
		BDDMockito.given(this.matchService.findMatchesByTeamId(8)).willReturn(matches);
		BDDMockito.given(this.usuarioService.usuarioLogueado(user.getUsername())).willReturn(u);
		BDDMockito.given(this.usuarioService.usuarioLogueado(user2.getUsername())).willReturn(u2);
		BDDMockito.given(this.usuarioService.findUserById(2)).willReturn(u2);

	}

	//	// Test de consultar torneos
	//	@Test
	//	@WithMockUser(value = "spring")
	//	void listChampionships() throws Exception {
	//		this.mockMvc.perform(get("/sports/1/championships")).andExpect(status().is2xxSuccessful());
	//
	//		Collection<Championship> championshipEntities = championshipService.findAllChampionships();
	//		assertThat(championshipEntities.size()).isEqualTo(8);
	//
	//	}

	// Test de crear torneo
	@Test
	@WithMockUser(value = "user1", authorities="usuario")
	void createChampionship() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/add").param("city", "Sevilla").param("description", "aafdfdfaa").param("startDate", "2021/06/14").param("finishDate", "2021/07/14").param("sport", "1").param("maxTeams", "8")
			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is3xxRedirection());

	}

	//Test de crear equipo
	@Test
	@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
	void testInitCreationTeamForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/team/create"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("teams/createOrUpdateTeamForm"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("team"));
	}

	/*@Test
	void testPostCreationTeamForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/championships/8/team/create")

			.param("name", "Equipo9")

			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/invitations/team/null"));


	}*/

	//Test de indicar resultado de partido
	@Test
	@WithMockUser(value = "user1", authorities="usuario")
	void getMatchDetails() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/match/1/result/team1")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("matches/listMatch"));

	}

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
	void matchAddResult() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/1/match/1/result/team1?search=Usuario1")

			.param("dateTime", "2022/04/05 12:00").param("team1", "1").param("team2", "2").param("puntos1", "5").param("puntos2", "4").param("puntos3", "5").param("puntos4", "4")

			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/championships/1/matches"));
	}

	// Test de consultar un torneo
	/*@Test
	@WithMockUser(value = "spring")
	void getChampionship() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/3/championships/8")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}*/

	// Test de participar torneo
//	@Test
//	@WithMockUser(value = "user1", authorities="usuario")
//	void initJoin() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/join/8")).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//		Team team = this.championshipService.findTeamId(8);
//		Assertions.assertThat(team.getParticipants().size() == 1);
//
//	}

	// Test de participar torneo con participante ya unido anteriormente
//	@Test
//	@WithMockUser(value = "user1", authorities="usuario")
//	void initJoinParticipantAlreadyJoined() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/join/8")).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//		Team team = this.championshipService.findTeamId(8);
//		Assertions.assertThat(team.getParticipants().size() == 1);
//
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/join/8")).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//		Team teamAgain = this.championshipService.findTeamId(8);
//		Assertions.assertThat(teamAgain.getParticipants().size() == 1);
//
//	}
	
	// Test de consultar un equipo
		/* @Test
		void getTeam() throws Exception {
			this.mockMvc.perform(get("/championships/1/teams/3")).andExpect(status().is2xxSuccessful());

			Team teamEntity = teamService.findTeamById(3);
			assertThat(teamEntity.getName()).isEqualTo("lobos");

		} */

		// Test de consultar un equipo negative
		/* @Test
		void getTeamNegative() throws Exception {
			this.mockMvc.perform(get("/championships/1/teams/3")).andExpect(status().is2xxSuccessful());

			Team teamEntity = teamService.findTeamById(3);
			assertThat(teamEntity.getName()).isNotEqualTo("tigres");

		} */
		
		// ABANDONAR EQUIPO COMO OWNER
		@Test
		@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
		void ownerLeaveTeam() throws Exception {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/teams/8/leave"))
					.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
					.andExpect(MockMvcResultMatchers.view().name("redirect:/championships/8/teams/8"));
		}
		
		// ABANDONAR EQUIPO COMO USUARIO
			@Test
			@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
			void userLeaveTeam() throws Exception {
				this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/teams/8/leave"))
						.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
						.andExpect(MockMvcResultMatchers.view().name("redirect:/championships/8/teams/8"));
			}
		
		// ELIMINAR JUGADOR SIENDO OWNER
		@Test
		@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
		void deleteTeamPlayer() throws Exception {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/teams/8/2/delete"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("teams/teamDetails"));
		}

}
