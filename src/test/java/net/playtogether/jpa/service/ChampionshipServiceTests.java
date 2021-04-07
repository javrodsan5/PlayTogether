
package net.playtogether.jpa.service;

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
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChampionshipServiceTests {

	@Autowired
	private ChampionshipService	championshipService;

	@Autowired
	private SportService		sportService;

	@Autowired
	private UsuarioService			userService;


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
		Assertions.assertThat(championships.size()).isEqualTo(9);
	}

	// FIND TEAM BY ID
	@Test
	void shouldFindTeamWithCorrectId() throws Exception {
		Team team = this.championshipService.findTeamId(1);
		Assertions.assertThat(team.getName()).isEqualTo("Equipo1");
	}

	// FIND ALL (COLLECTION SPORT)
	@Test
	void shouldFindAllTeams() {
		Collection<Team> listTeams = this.championshipService.listTeams();
		Assertions.assertThat(listTeams.size()).isEqualTo(10);
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
		Usuario user = this.championshipService.findUsersById(3);
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
		Assertions.assertThat(championships.size()).isEqualTo(2);
	}

}
