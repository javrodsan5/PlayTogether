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

import java.time.LocalDate;
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
import net.playtogether.jpa.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeetingControllerTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private MeetingRepository meetingRepository;
	  
	  @MockBean
	  private MeetingService meetingService;
	  
	  @MockBean
	  private SportService sportService;
	  
	  @MockBean
	  private UserService userService;
	  
	  private Meeting testMeeting1;
	  
	  private Meeting testMeeting2;
	  
	  @BeforeEach
	  void setup() {
		  testMeeting1 = new Meeting();
		  testMeeting1.setId(1);	 
		  testMeeting1.setAddress("Bami");;
		  testMeeting1.setCity("Sevilla");;
		  testMeeting1.setDate(LocalDateTime.of(2021, 06, 12, 12, 00));
		  testMeeting1.setParticipants(new ArrayList<User>());
		  testMeeting1.setDescription("Una partidata");
		  
		  testMeeting2 = new Meeting();
		  testMeeting2.setId(2);	 
		  testMeeting2.setAddress("Calle Antonio Ulloa");;
		  testMeeting2.setCity("Sevilla");;
		  testMeeting2.setDate(LocalDateTime.of(2021, 06, 23, 18, 16));
		  testMeeting2.setParticipants(new ArrayList<User>());
		  testMeeting2.setDescription("Partido de tenis");
		  
		  Sport s = new Sport();
		  SportType st = new SportType();
		  st.setId(1);
		  st.setName("Equipo");
		  s.setChampionships(new ArrayList<Championship>());
		  s.setId(1);
		  s.setMeetings(new ArrayList<Meeting>());
		  s.setName("Tenis");
		  s.setSportType(st);		  
		  
		  User u = new User();
		  u.setId(1);
		  u.setName("Usuario 1");
		  u.setCorreo("correo@correo.com");
		  u.setUsername("user1");
		  u.setPassword("password");
		  u.setBirthdate(LocalDate.of(1999, 3, 16));
		  u.setPhone("123456789");
		  u.setPayment(null);
		  u.setStatistics(null);
		  u.setType(null);
		  u.setTeams(null);
		  u.setMeetings(null);
		  
		  given(this.meetingService.findMeetingById(1)).willReturn(testMeeting1);
		  given(this.sportService.findSportById(1)).willReturn(s);
		  given(this.meetingService.findMeetingById(2)).willReturn(testMeeting2);
		  given(this.userService.findUserById(1)).willReturn(null);
	}
	  
	  //Test de consultar quedadas
	@Test
	void listMeetings() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings"))
		.andExpect(status().is2xxSuccessful());
		
		Collection<Meeting> meetingEntities = meetingRepository.findAll();
		assertThat(meetingEntities.size()).isEqualTo(7);
	
		}
	
	// Test negativo de consultar quedadas
	@Test
	void listMeetingsNegative() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings"))
		.andExpect(status().is2xxSuccessful());
		
		Collection<Meeting> meetingEntities = meetingRepository.findAll();
		assertThat(meetingEntities.size()).isNotEqualTo(4);
	
		}
	  
	 //Test de crear quedada
	@WithMockUser(value = "spring")
	@Test
	void createMeeting() throws Exception {
	
		mockMvc.perform(post("/sports/1/meetings/add").with(csrf())
				.param("address", "Charco la Pava")
				.param("city", "Sevilla")
				.param("date", "2021/06/12 12:00")
				.param("id", "1")
				.param("description", "Cambio de planes"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/sports/1/meetings")); 


	}
	
	 //Test de crear quedada negativo
	@Test
	void createMeetingNegative() throws Exception {
	
	this.mockMvc.perform(post("/sports/1/meetings/add")
			.param("address", "Calle 1")
			.param("city", "Sevilla")
			.param("description", "aaaa")
			.param("date", "2021-01-14 14:14")
			.param("sport", "1")
			.with(csrf()))
			.andExpect(status().is2xxSuccessful());

	}

	//Test de consultar una quedada
	@Test
	void getMeeting() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings/1"))
		.andExpect(status().is2xxSuccessful());
		
		Meeting meetingEntity = meetingRepository.findById(1).orElse(null);
		assertThat(meetingEntity.getCity()).isEqualTo("Sevilla");
		
		
	}
	
	//Test de consultar una quedada negative
	@Test
	void getMeetingNegative() throws Exception{
		this.mockMvc.perform(get("/sports/1/meetings/1"))
		.andExpect(status().is2xxSuccessful());
		
		Meeting meetingEntity = meetingRepository.findById(1).orElse(null);
		assertThat(meetingEntity.getCity()).isNotEqualTo("Huelva");
		
		
	}
	  
	//Test join meeting controller
//		@Test
//		@Transactional
//		void joinMeeting() throws Exception{
//			this.mockMvc.perform(get("/meetings/2/join")).andExpect(status().is2xxSuccessful());
//			
//			
//			Meeting meetingEntity = meetingRepository.findById(2).orElse(null);
//			assertThat(meetingEntity.getParticipants().size()).isEqualTo(1);
//			
//		}
		
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
