package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;


import net.playtogether.jpa.entity.Championship;

import net.playtogether.jpa.repository.ChampionshipRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChampionshipTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ObjectMapper objectMapper;
	  @Autowired
	  private ChampionshipRepository championshipRepository;
	  
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
			.param("maxTeams", "8")
			.with(csrf()))
			.andExpect(status().is3xxRedirection());

	Championship championshipEntity = championshipRepository.findById(8).orElse(null);
	assertThat(championshipEntity.getCity()).isEqualTo("Sevilla");
	}

	//Test de consultar un torneo
	@Test
	void getChampionship() throws Exception{
		this.mockMvc.perform(get("/sports/1/championships/1"))
		.andExpect(status().is2xxSuccessful());
		
		Championship championshipEntity = championshipRepository.findById(1).orElse(null);
		assertThat(championshipEntity.getCity()).isEqualTo("Sevilla");
		
		
	}

}
