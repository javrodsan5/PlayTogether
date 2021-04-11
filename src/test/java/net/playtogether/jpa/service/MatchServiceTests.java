package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	// FIND MATCH BY ID
	@Test
	void shouldFindMatchWithCorrectId() throws Exception {
		Match match = this.matchService.findMatchById(1);
		assertThat(match.getChampionship().getId()).isEqualTo(1);
	}
	
	/*@Test
	void shouldSaveMatch() throws Exception {
		Championship championship = new  Championship();
	
		championship.setCity("Sevilla");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(this.sportService.findSportById(1));
		championship.setMaxTeams(8);
		championship.setDescription("descripción");
		championship.setUser(new Usuario());
		
		this.championshipService.save(championship);
		
		Team team1 = new Team();
		team1.setChampionship(championship);
		Team team2 = new Team();
		team2.setChampionship(championship);
		
		this.championshipService.save(team1);
		this.championshipService.save(team2);
		
		Match match = new Match();

		match.setChampionship(championship);
		match.setDateTime(LocalDateTime.now());
		match.setTeam1(team1);
		match.setTeam2(team2);
		this.matchService.save(match);
		Match match2 = this.matchService.findMatchById(match.getId());
		assertThat(match.getId()).isEqualTo(match2.getId());
	}*/
	
}
