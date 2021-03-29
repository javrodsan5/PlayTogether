package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.SportService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChampionshipTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChampionshipService championshipService;

	private Championship testChampionship;

	@MockBean
	private SportService sportService;

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

		testChampionship = new Championship();
		testChampionship.setName("Torneo8");
		testChampionship.setCity("Sevilla");
		testChampionship.setDescription("Descripcion del torneo");
		testChampionship.setStartDate(LocalDate.of(2021, 06, 15));
		testChampionship.setFinishDate(LocalDate.of(2021, 06, 25));
		testChampionship.setId(8);
		testChampionship.setMaxTeams(16);
		testChampionship.setMatches(new ArrayList<Match>());
		testChampionship.setSport(s);

		Team t = new Team();
		t.setChampionship(testChampionship);
		t.setId(8);
		t.setName("Equipo8");
		t.setParticipants(new ArrayList<User>());
		List<Team> teams = new ArrayList<Team>();
		teams.add(t);
		testChampionship.setTeams(teams);

		given(this.championshipService.findChampionshipId(8)).willReturn(testChampionship);
		given(this.sportService.findSportById(1)).willReturn(s);
		given(this.championshipService.findTeamId(8)).willReturn(t);

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
	@WithMockUser(value = "spring")

	void createChampionship() throws Exception {

		this.mockMvc.perform(post("/sports/1/championships/add")

				.param("city", "Sevilla").param("description", "aafdfdfaa").param("startDate", "2021/06/14")
				.param("finishDate", "2021/07/14").param("sport", "1").param("maxTeams", "8").with(csrf()))
				.andExpect(status().is3xxRedirection());

	}

	// Test de consultar un torneo
	@Test
	@WithMockUser(value = "spring")
	void getChampionship() throws Exception {
		this.mockMvc.perform(get("/sports/1/championships/8")).andExpect(status().is2xxSuccessful());

	}

	// Test de participar torneo
	@Test
	@WithMockUser(value = "spring")

	void initJoin() throws Exception {
		mockMvc.perform(get("/sports/1/championships/8/join/8")).andExpect(status().is3xxRedirection());

		Team team = championshipService.findTeamId(8);
		assertThat(team.getParticipants().size() == 1);

	}

	// Test de participar torneo con participante ya unido anteriormente
	@Test
	@WithMockUser(value = "spring")

	void initJoinParticipantAlreadyJoined() throws Exception {
		mockMvc.perform(get("/sports/1/championships/8/join/8")).andExpect(status().is3xxRedirection());

		Team team = championshipService.findTeamId(8);
		assertThat(team.getParticipants().size() == 1);

		mockMvc.perform(get("/sports/1/championships/8/join/8")).andExpect(status().is3xxRedirection());

		Team teamAgain = championshipService.findTeamId(8);
		assertThat(teamAgain.getParticipants().size() == 1);

	}

}
