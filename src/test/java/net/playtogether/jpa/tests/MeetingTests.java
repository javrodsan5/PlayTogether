package net.playtogether.jpa.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.repository.MeetingRepository;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeetingTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ObjectMapper objectMapper;
	  @Autowired
	  private MeetingRepository meetingRepository;
	  
	//Test de consultar quedadas
//	@Test
//	void listMeetings() throws Exception{
//		this.mockMvc.perform(get("/meetings"))
//		.andExpect(status().is2xxSuccessful());
//		
//		Collection<Meeting> meetingEntities = meetingRepository.findAll();
//		assertThat(meetingEntities.size()).isEqualTo(3);
//	
//		}
//	  
//	 //Test de crear quedada
//	@Test
//	void createMeeting() throws Exception {
//	
//	this.mockMvc.perform(post("/meetings/add")
//			.param("address", "Calle 1")
//			.param("city", "Sevilla")
//			.param("description", "aaaa")
//			.param("date", "2021-06-14 14:14")
//			.param("sport", "1")
//			.with(csrf()))
//			.andExpect(status().is3xxRedirection());
//
//	Meeting meetingEntity = meetingRepository.findById(3).orElse(null);
//	assertThat(meetingEntity.getAddress()).isEqualTo("Calle 1");
//	}
//
//	//Test de consultar una quedada
//	@Test
//	void getMeeting() throws Exception{
//		this.mockMvc.perform(get("/meetings/1"))
//		.andExpect(status().is2xxSuccessful());
//		
//		Meeting meetingEntity = meetingRepository.findById(1).orElse(null);
//		assertThat(meetingEntity.getName()).isEqualTo("Quedada1");
//		
//		
//	}

}
