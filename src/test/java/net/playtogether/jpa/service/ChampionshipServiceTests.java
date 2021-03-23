package net.playtogether.jpa.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;

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
	
	
}
