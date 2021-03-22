package net.playtogether.jpa.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SportServiceTests {

	@Autowired
	protected SportService sportService;

	// FIND ALL (COLLECTION SPORT)
	@Test
	void shouldFindAllSports() {
		Collection<Sport> listSports = this.sportService.findAll();
		assertThat(listSports.size()).isEqualTo(13);
	}

	// FIND SPORT BY NAME
	@Test
	void shouldFindSportWithCorrectName() throws Exception {
		Sport sport2 = this.sportService.findSportByName("Futbol 11");
		assertThat(sport2.getName()).isEqualTo("Futbol 11");
	}

	// FIND SPORT BY ID
	@Test
	void shouldFindSportWithCorrectId() throws Exception {
		Sport sport3 = this.sportService.findSportById(3);
		assertThat(sport3.getName()).isEqualTo("Futbol sala");
	}
	
	// FIND SPORT BY TYPE
		@Test
		void shouldFindSportsWithCorrectType() throws Exception {
			SportType st = new SportType();
			st.setId(2);
			Collection<Sport> listSports = this.sportService.findAllSportsByType(st);
			assertThat(listSports.size()).isEqualTo(4);
		}
}
