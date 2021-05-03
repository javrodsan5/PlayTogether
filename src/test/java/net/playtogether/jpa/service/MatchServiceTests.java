package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MatchServiceTests {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private ChampionshipService championshipService;
	
	@Autowired
	private SportService sportService;

	@Autowired
	private UsuarioService usuarioService;

	@BeforeEach
	void addTeam() {
		Championship championship = new  Championship();

		Usuario usuario = this.usuarioService.findUserById(1);
	
		championship.setCity("Sevilla");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(this.sportService.findSportById(1));
		championship.setMaxTeams(8);
		championship.setDescription("descripción");
		championship.setUser(usuario);
		championship.setAddress("address");
		
		this.championshipService.save(championship);
		
		Team team1 = new Team();
		team1.setChampionship(championship);
		Team team2 = new Team();
		team2.setChampionship(championship);
		
		this.championshipService.save(team1);
		this.championshipService.save(team2);
		
		Match match = new Match();

		match.setId(1);
		match.setChampionship(championship);
		match.setDateTime(LocalDateTime.now());
		match.setTeam1(team1);
		match.setTeam2(team2);
		this.matchService.save(match);
	}

	//FIND MATCH BY ID
	@Test
	void shouldFindMatchWithCorrectId() throws Exception {
		Match match = this.matchService.findMatchById(1);
		assertThat(match.getId()).isEqualTo(1);
		assertThat(match.getChampionship().getId()).isEqualTo(15);
	}
	
	@Test
	void shouldSaveMatch() throws Exception {
		
		Championship championship = new  Championship();

		Usuario usuario = this.usuarioService.findUserById(1);
	
		championship.setCity("Sevilla");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(this.sportService.findSportById(1));
		championship.setMaxTeams(8);
		championship.setDescription("descripción");
		championship.setUser(usuario);
		championship.setAddress("address");
		
		this.championshipService.save(championship);
		
		Team team1 = new Team();
		team1.setChampionship(championship);
		Team team2 = new Team();
		team2.setChampionship(championship);
		
		this.championshipService.save(team1);
		this.championshipService.save(team2);
		
		Match match = new Match();

		match.setId(2);
		match.setChampionship(championship);
		match.setDateTime(LocalDateTime.now());
		match.setTeam1(team1);
		match.setTeam2(team2);
		this.matchService.save(match);

		Match match2 = this.matchService.findMatchById(2);
		assertThat(match2).isNotNull();
		assertThat(match2.getId()).isEqualTo(2);
	}

	@Test
	void findAllTest() throws Exception {
		Collection<Match> matchs = this.matchService.listMatches();
		assertThat(matchs.size()).isEqualTo(2);
	}

	@Test
	void findMatchesByChampionshipId() throws Exception {
		Collection<Match> matchs = this.matchService.listMatchesByChampionship(15);
		assertThat(matchs.size()).isEqualTo(0);
	}

	@Test
	void findTeamsByChampionshipId() throws Exception {
		Collection<Team> teams = this.matchService.findTeams(1);
		assertThat(teams.size()).isEqualTo(8);
	}

	@Test
	void findMatchesByTeamId() throws Exception {
		Collection<Match> matchs = this.matchService.findMatchesByTeamId(25);
		assertThat(matchs.size()).isEqualTo(0);
	}
	
}
