package net.playtogether.jpa.service;

import java.util.Collection; 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import net.playtogether.jpa.entity.Championship;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChampionshipServiceTests {

	@Autowired
	private ChampionshipService championshipService;

	
	// FIND CHAMPIONSHIP BY ID
	@Test
	void shouldFindChampionshipWithCorrectId() throws Exception {
		Championship championship = this.championshipService.findChampionshipId(3);
		assertThat(championship.getCity()).isEqualTo("Sevilla");
	}
	
	// FIND ALL CHAMPIONSHIPS
	@Test
	void shouldFindAllChampionships() throws Exception {
		Collection<Championship> championships = this.championshipService.listChampionship();
		assertThat(championships.size()).isEqualTo(8);
	}
	
	
	// FIND ALL CHAMPIONSHIPS BY SPORT
	@Test
	void shouldFindListChampionshipsBySport() throws Exception {
		Collection<Championship> championships = this.championshipService.listChampionshipsBySport(1);
		assertThat(championships.size()).isEqualTo(6);
	}
		
}
