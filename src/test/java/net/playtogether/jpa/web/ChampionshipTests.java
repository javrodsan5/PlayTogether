package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.repository.ChampionshipRepository;
import net.playtogether.jpa.service.ChampionshipService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChampionshipTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ChampionshipRepository championshipRepository;
	  @MockBean
	  private ChampionshipService championshipService;
	  
	  private Championship championship;
	  
	  @BeforeEach
	  void setup() {
		  championship = new Championship();
		  championship.setId(1);
		  championship.setCity("Sevilla");
		  championship.setDescription("hola");
		  championship.setStartDate(LocalDate.of(2021, 06, 11));
		  championship.setFinishDate(LocalDate.of(2021, 06, 12));
		  
		  Sport s = new Sport();
		  s.setNumberOfPlayersInTeam(11);
		  SportType st = new SportType();
		  st.setId(1);
		  st.setName("Equipo");
		  s.setChampionships(new ArrayList<Championship>());
		  s.setId(1);
		  s.setMeetings(new ArrayList<Meeting>());
		  s.setName("Tenis");
		  s.setSportType(st);
		  
		  championship.setSport(s);
		  
		  given(this.championshipService.findTeamsByChampionshipId(1)).willReturn(new ArrayList<>());
		  given(this.championshipService.findChampionshipId(1)).willReturn(championship);
	}
	  
	  //Test de consultar torneos
	@Test
	void listChampionships() throws Exception{
		this.mockMvc.perform(get("/sports/1/championships"))
		.andExpect(status().is2xxSuccessful());
		
		Collection<Championship> championshipEntities = championshipRepository.findAll();
		assertThat(championshipEntities.size()).isEqualTo(8);
	
		}
	  
	 //Test de crear torneo
	@Test
	void createChampionship() throws Exception {
	
	this.mockMvc.perform(post("/sports/1/championships/add")
			
			.param("city", "Sevilla")
			.param("description", "aafdfdfaa")
			.param("startDate", "2021/06/14")
			.param("finishDate", "2021/07/14")
			.param("sport", "1")
			.with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/sports/1/championships"));

	}

	//Test de consultar un torneo
	@Test
	void getChampionship() throws Exception{
		this.mockMvc.perform(get("/sports/1/championships/1"))
		.andExpect(status().is2xxSuccessful());
		
		Championship championshipEntity = championshipRepository.findById(1).orElse(null);
		assertThat(championshipEntity.getCity()).isEqualTo("Sevilla");
	}
	
	
	//Test de crear equipo
	@Test
	void testInitCreationTeamForm() throws Exception {
		this.mockMvc.perform(get("/championships/1/team/create"))
		.andExpect(status().isOk())
		.andExpect(view().name("teams/createOrUpdateTeamForm"))
		.andExpect(model().attributeExists("team"));
	}
	
	@Test
	void testPostCreationTeamForm() throws Exception {
		this.mockMvc.perform(post("/championships/1/team/create")
				
				.param("id", "8")
				.param("name", "Equipo8")
				
				
				.with(csrf()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/championships/team/8"));
	}

}
