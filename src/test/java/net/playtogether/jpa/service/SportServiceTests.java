package net.playtogether.jpa.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SportServiceTests {

	@Autowired
	private SportService sportService;

	// FIND ALL (COLLECTION SPORT)
	@Test
	void shouldFindAllSports() {
		Collection<Sport> listSports = this.sportService.findAll();
		assertThat(listSports.size()).isEqualTo(20);
	}

	// FIND SPORT BY NAME
	@Test
	void shouldFindSportWithCorrectName() throws Exception {
		Sport sport2 = this.sportService.findSportByName("Fútbol 11");
		assertThat(sport2.getName()).isEqualTo("Fútbol 11");
	}

	// FIND SPORT BY ID
	@Test
	void shouldFindSportWithCorrectId() throws Exception {
		Sport sport3 = this.sportService.findSportById(3);
		assertThat(sport3.getName()).isEqualTo("Fútbol sala");
	}
	
	// FIND SPORT BY TYPE
		@Test
		void shouldFindSportsWithCorrectType() throws Exception {
			SportType st = new SportType();
			st.setId(2);
			Collection<Sport> listSports = this.sportService.findAllSportsByType(st);
			assertThat(listSports.size()).isEqualTo(11);
		}
}
