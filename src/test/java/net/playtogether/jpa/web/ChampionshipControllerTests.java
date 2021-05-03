
package net.playtogether.jpa.web;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		Team t2 = new Team();
		t2.setChampionship(this.testChampionship);
		t2.setId(9);
		t2.setName("Equipo9");
		t2.setParticipants(usuarios);
		t2.setUser(u2);
		List<Team> teams2 = new ArrayList<Team>();
		teams2.add(t2);
		this.testChampionship.setTeams(teams2);

		u2.setTeams(teams2);

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

	// Test de consultar torneos
	@Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
	void listChampionships() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	// Test de crear torneo
	@Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
	void createChampionship() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/add")
				.param("address", "Polideportivo Ciudad Jardin").param("city", "Sevilla")
				.param("name", "Torneo de ejemplo futbol").param("description", "Descripción sencilla del torneo")
				.param("startDate", "2021-09-14").param("finishDate", "2021-09-16").param("sport", "1")
				.param("maxTeams", "8").with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());

	}

	// Test de crear torneo con ciudad con numero
	@Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
	void createChampionshipWithErrors() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/add")
				.param("address", "Polideportivo Ciudad Jardin").param("city", "Sevilla1")
				.param("name", "Torneo de ejemplo futbol").param("description", "Descripción sencilla del torneo")
				.param("startDate", "2021-09-14").param("finishDate", "2021-09-16").param("sport", "1")
				.param("maxTeams", "8").with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	// Test de crear torneo con ciudad con numero
	@Test
	@WithMockUser(username = "user", authorities = { "usuario" }, password = "password")
	void createChampionshipPaying() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/add")
				.param("address", "Polideportivo Ciudad Jardin").param("city", "Sevilla")
				.param("name", "Torneo de ejemplo futbol").param("description", "Descripción sencilla del torneo")
				.param("startDate", "2021-09-14").param("finishDate", "2021-09-16").param("sport", "1")
				.param("maxTeams", "8").with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());

	}

	// Test de crear equipo
	@Test
	@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
	void testInitCreationTeamForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/team/create"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("teams/createOrUpdateTeamForm"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("team"));
	}

	@Test
	@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
	void testPostCreationTeamForm() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/championships/8/team/create").param("name", "Equipo9")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/pay/championship/8?teamName=Equipo9"));

	}

	// Test de indicar resultado de partido
	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void getMatchDetails() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/match/1/result/team1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/listMatch"));

	}

	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void matchAddResult() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/1/match/1/result/team1?search=Usuario1")
						.param("dateTime", "2022-04-05T12:00").param("team1", "1").param("team2", "2")
						.param("puntos1", "5").param("puntos2", "4").param("puntos3", "5").param("puntos4", "4")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/championships/1/matches"));
	}

	// Test de consultar un torneo

//	@Test
	@WithMockUser(username = "user1", authorities = { "usuario" }, password = "password")
	void getChampionship() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("championships/championshipsDetails"));
	}

	// Test de participar torneo
//	@Test
	@WithMockUser(username = "user", authorities = { "usuario1" }, password = "password")
	void initJoin() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/join/9"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/championships/8"));

	}

	// Test de participar torneo con participante ya unido anteriormente
//	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void initJoinParticipantAlreadyJoined() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/join/8"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	// Test de consultar un equipo

//	@Test
	@WithMockUser(value = "user1", authorities = "usuario")
	void getTeam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/1/teams/3"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/championships/1/teams/3"));

	}

	// Test de consultar un equipo negative

	@Test
	void getTeamNegative() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/1/teams/26"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());

	}

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
		User user = this.user;
		Set<Authorities> setAuthorities = new HashSet<Authorities>();
		Authorities authorities = new Authorities();
		authorities.setAuthority("premium");
		authorities.setUser(user);
		setAuthorities.add(authorities);
		user.setAuthorities(setAuthorities);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/teams/8/2/delete"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("teams/teamDetails"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void teamsDetails() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/championships/8/teams/8"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("teams/teamDetails"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchWithNoParameters() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/createOrUpdateMatchForm"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchWithLaterDateThanEndChampionship() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add")
						.param("dateTime", "2060-04-05T12:00").with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/createOrUpdateMatchForm"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchWithPastDate() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add")
						.param("dateTime", "2021-04-30T12:00").with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/createOrUpdateMatchForm"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchWithTeam1EqualsTeam2() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add")
						.param("dateTime", "2021-06-15T12:00").param("team1", "1").param("team2", "1")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/createOrUpdateMatchForm"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchWithTeamNull() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add")
						.param("dateTime", "2021-06-15T12:00").param("team1", "").param("team2", "1")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/createOrUpdateMatchForm"));
	}

	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void postCreationMatchCorrectly() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/sports/1/championships/8/match/add").param("championship", "8")
						.param("dateTime", "2021-06-15T12:00").param("team1", "1").param("team2", "2")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/sports/1/championships/8/matches"));
	}

	//no consigo que salte el catch de la linea 421 
	@Test
	@WithMockUser(username = "user2", authorities = { "usuario" }, password = "password")
	void matchDetailsNotFoundUser() throws Exception {
		Principal principal = new Principal() {

	        @Override
	        public String getName() {
	            return null;
	        }

	    };
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/sports/1/championships/8/match/1/result/1").principal(principal)
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("matches/listMatch"));
	}
}
