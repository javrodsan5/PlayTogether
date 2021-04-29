
package net.playtogether.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChampionshipServiceTests {

	@Autowired
	private ChampionshipService	championshipService;

	@Autowired
	private UsuarioService			userService;

  	@Autowired
	private TeamService			teamService;


	// FIND CHAMPIONSHIP BY ID
	@Test
	void shouldFindChampionshipWithCorrectId() throws Exception {
		Championship championship = this.championshipService.findChampionshipId(1);
		Assertions.assertThat(championship.getCity()).isEqualTo("Sevilla");
	}

	// FIND ALL CHAMPIONSHIPS
	@Test
	void shouldFindAllChampionships() throws Exception {
		Collection<Championship> championships = this.championshipService.listChampionship();
		Assertions.assertThat(championships.size()).isEqualTo(15);
		
	}

	// FIND TEAM BY ID
	@Test
	void shouldFindTeamWithCorrectId() throws Exception {
		Team team = this.championshipService.findTeamId(1);
		Assertions.assertThat(team.getName()).isEqualTo("Los Lobos");
	}

	// FIND ALL (COLLECTION SPORT)
	@Test
	void shouldFindAllTeams() {
		Collection<Team> listTeams = this.championshipService.listTeams();
		Assertions.assertThat(listTeams.size()).isEqualTo(39);
	}

	// FIND USER BY NAME OR USERNAME
	@Test
	void shouldFindUserWithCorrectNameOrUsername() throws Exception {
		List<Usuario> user = this.championshipService.findUserByNameOrUsername("Antonio");
		Assertions.assertThat(user.get(0).getName()).isEqualTo("Antonio");
	}

	// FIND USER BY ID
	@Test
	void shouldFindUserWithCorrectId() throws Exception {
		Usuario user = this.championshipService.findUsersById(1);
		Assertions.assertThat(user.getName()).isEqualTo("Antonio");
	}

	// SAVE TEAM
	@Test
	public void testSavingTeamCorrect() {
		Integer countBefore = this.championshipService.countTeams();
		countBefore++;

		List<Usuario> users = new ArrayList<>();
		Usuario user = this.userService.findUserById(1);
		users.add(user);

		Team team = new Team();
		team.setName("aaaa");
		team.setChampionship(this.championshipService.findChampionshipId(1));
		team.setParticipants(users);

		this.championshipService.save(team);
		Integer countAfter = this.championshipService.countTeams();
		Assertions.assertThat(countBefore).isEqualTo(countAfter);
	}

	// FIND ALL CHAMPIONSHIPS BY SPORT
	@Test
	void shouldFindListChampionshipsBySport() throws Exception {
		Collection<Championship> championships = this.championshipService.listChampionshipsBySport(2);
		Assertions.assertThat(championships.size()).isEqualTo(1);
	}
	
	
	 //Test de consultar un team por id
	 @Test
	 void findTeamByIdTest() {
		 Team team = this.teamService.findTeamById(1);
		 assertThat(team.getName()).isEqualTo("Los Lobos");
	 }

	@Test
	void shouldDeleteTeam() {
		List<Usuario> users = new ArrayList<>();
		Usuario user = this.userService.findUserById(1);
		users.add(user);

		Team team = new Team();
		team.setId(60);
		team.setName("aaaa");
		team.setChampionship(this.championshipService.findChampionshipId(1));
		team.setParticipants(users);

		this.championshipService.save(team);
		this.teamService.delete(team);
		assertThat(team).isNotNull();

		Team teamAfter = this.teamService.findTeamById(60);
		assertThat(teamAfter).isNull();
	}

	@Test
	void shouldDeleteTeams() {
		List<Usuario> users = new ArrayList<>();
		Usuario user = this.userService.findUserById(1);
		users.add(user);

		Team team = new Team();
		team.setId(61);
		team.setName("aaaa");
		team.setChampionship(this.championshipService.findChampionshipId(1));
		team.setParticipants(users);
		Team team1 = new Team();
		team.setId(62);
		team1.setName("aaaa");
		team1.setChampionship(this.championshipService.findChampionshipId(1));
		team1.setParticipants(users);

		this.championshipService.save(team);
		this.championshipService.save(team1);
		List<Team> teams = new ArrayList<>();
		teams.add(team); teams.add(team1);
		this.teamService.deleteAll(teams);
		assertThat(teams.size()).isEqualTo(2);

		Team team4After = this.teamService.findTeamById(61);
		Team team5After = this.teamService.findTeamById(62);
		assertThat(team4After).isNull();
		assertThat(team5After).isNull();
	}

	@Test
	void findParticipantsChampionship() throws Exception {
		Collection<Usuario> u = this.championshipService.findParticipantsChampionship(1);
		assertThat(u.size()).isEqualTo(10);
	}

	@Test
	void findTeamsByChampionshipId() throws Exception {
		Collection<Team> u = this.championshipService.findTeamsByChampionshipId(1);
		assertThat(u.size()).isEqualTo(9);
	}

	@Test
	void existeChampionship() throws Exception {
		Boolean u = this.championshipService.existeChampionship(2);
		assertThat(u).isTrue();
	}

	@Test
	void coincideResultados() throws Exception {
		Match m = new Match();
		m.setPuntos1(10); m.setPuntos2(10); m.setPuntos3(10); m.setPuntos4(10);
		Boolean u = this.championshipService.coincideResultados(m);
		assertThat(u).isTrue();

		m.setPuntos2(5);
		Boolean u2 = this.championshipService.coincideResultados(m);
		assertThat(u2).isFalse();
	}

	@Test
	void getGanadorPartido() throws Exception {
		Match m = new Match();
		m.setPuntos1(10); m.setPuntos2(10); m.setPuntos3(20); m.setPuntos4(20);
		Team u = this.championshipService.getGanadorPartido(m);
		assertThat(u).isNull();
	}
}
