package net.playtogether.jpa.web;

import static org.hamcrest.Matchers.hasProperty;

import static org.mockito.BDDMockito.given;

import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.MeetingRepository;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.SportService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeetingTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private MeetingRepository meetingRepository;
	  
	  @MockBean
	  private MeetingService meetingService;
	  
	  @MockBean
	  private SportService sportService;
	  
	  private Meeting testMeeting1;
	  
	  @BeforeEach
	  void setup() {
		  testMeeting1 = new Meeting();
		  testMeeting1.setId(1);	 
		  testMeeting1.setAddress("Bami");;
		  testMeeting1.setCity("Sevilla");;
		  testMeeting1.setDate(LocalDateTime.of(2021, 06, 12, 12, 00));
		  testMeeting1.setParticipants(new ArrayList<User>());
		  testMeeting1.setDescription("Una partidata");
		  
		  Sport s = new Sport();
		  SportType st = new SportType();
		  st.setId(1);
		  st.setName("Equipo");
		  s.setChampionships(new ArrayList<Championship>());
		  s.setId(1);
		  s.setMeetings(new ArrayList<Meeting>());
		  s.setName("Tenis");
		  s.setSportType(st);		  
		  
		  given(this.meetingService.findMeetingById(1)).willReturn(testMeeting1);
	}
	  
	  //Test de consultar quedadas
	@Test
	void listMeetings() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings"))
		.andExpect(status().is2xxSuccessful());
		
		Collection<Meeting> meetingEntities = meetingRepository.findAll();
		assertThat(meetingEntities.size()).isEqualTo(7);
	
		}
	  
	 //Test de crear quedada
	@Test
	void createMeeting() throws Exception {
	
	this.mockMvc.perform(post("/sports/1/meetings/add")
			.param("address", "Calle 1")
			.param("city", "Sevilla")
			.param("description", "aaaa")
			.param("date", "2021-06-14 14:14")
			.param("sport", "1")
			.with(csrf()))
			.andExpect(status().is3xxRedirection());

	Meeting meetingEntity = meetingRepository.findById(8).orElse(null);
	assertThat(meetingEntity.getAddress()).isEqualTo("Calle 1");
	}

	//Test de consultar una quedada
	@Test
	void getMeeting() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings/1"))
		.andExpect(status().is2xxSuccessful());
		
		Meeting meetingEntity = meetingRepository.findById(1).orElse(null);
		assertThat(meetingEntity.getCity()).isEqualTo("Sevilla");
		
		
	}
	  
	//Test join meeting controller
		@Test
		@Transactional
		void joinMeeting() throws Exception{
			this.mockMvc.perform(get("/meetings/2/join")).andExpect(status().is2xxSuccessful());
			
			
			Meeting meetingEntity = meetingRepository.findById(2).orElse(null);
			assertThat(meetingEntity.getParticipants().size()).isEqualTo(1);
			
		}
		
    //Test update meeting controller
		@Test
		@WithMockUser(value="spring")
		void initUpdateMeeting() throws Exception {
			mockMvc.perform(get("/sports/1/meetings/1/edit"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("meeting"))
			.andExpect(model().attribute("meeting", hasProperty("address", is("Bami"))))
			.andExpect(model().attribute("meeting", hasProperty("city", is("Sevilla"))))
			.andExpect(model().attribute("meeting", hasProperty("date", is(LocalDateTime.of(2021, 06, 12, 12, 00)))))
			.andExpect(model().attribute("meeting", hasProperty("description", is("Una partidata"))))
			.andExpect(model().attribute("meeting", hasProperty("id", is(1))))
			.andExpect(model().attribute("meeting", hasProperty("participants", is(testMeeting1.getParticipants()))))
			.andExpect(view().name("meetings/createOrUpdateMeetingForm"));
		}
		
		@WithMockUser(value = "spring")
		@Test
		void testProcessUpdateMeetingFormSuccess() throws Exception {
			mockMvc.perform(post("/sports/1/meetings/1/edit").with(csrf())
					.param("address", "Charco la Pava").param("city", "Sevilla").param("date", "2021/06/12 12:00")
					.param("id", "1").param("description", "Cambio de planes"))
					.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/sports/1/meetings"));
		}
		
		@WithMockUser(value = "spring")
		@Test
		void testProcessUpdateMeetingFormErrors() throws Exception {
			mockMvc.perform(post("/sports/1/meetings/1/edit").with(csrf())
					.param("address", "Charco la Pava").param("city", "Sevilla").param("date", "")
					.param("id", "1").param("description", "Cambio de planes"))
					.andExpect(status().isOk())
					.andExpect(model().attributeHasErrors("meeting"))
					.andExpect(model().attributeHasFieldErrors("meeting", "date"))
					.andExpect(view().name("meetings/createOrUpdateMeetingForm"));
		}
}
