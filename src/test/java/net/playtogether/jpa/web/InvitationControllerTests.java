
package net.playtogether.jpa.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.InvitationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvitationControllerTests {

	@Autowired
	private MockMvc				mockMvc;

	@MockBean
	private ChampionshipService	championshipService;

	private Championship		testChampionship;
	
	private Invitation		testInvitation;
	
	@MockBean
	private InvitationService		invitationService;


	@BeforeEach
	void setup() {

		Sport s = new Sport();
		SportType st = new SportType();
		st.setId(1);
		st.setName("Equipo");
		s.setChampionships(new ArrayList<Championship>());
		s.setId(1);
		s.setMeetings(new ArrayList<Meeting>());
		s.setName("Tenis");
		s.setSportType(st);
		s.setNumberOfPlayersInTeam(1);

		this.testChampionship = new Championship();
		this.testChampionship.setName("Torneo8");
		this.testChampionship.setCity("Sevilla");
		this.testChampionship.setDescription("Descripcion del torneo");
		this.testChampionship.setStartDate(LocalDate.of(2022, 06, 15));
		this.testChampionship.setFinishDate(LocalDate.of(2022, 06, 25));
		this.testChampionship.setId(8);
		this.testChampionship.setMaxTeams(16);
		this.testChampionship.setMatches(new ArrayList<Match>());
		this.testChampionship.setSport(s);

		User u = new User();
		u.setId(1);
		u.setName("Usuario1");
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
		List<User> users = new ArrayList<>();
		users.add(u);

		Team team1 = new Team();
		team1.setId(1);
		team1.setChampionship(this.testChampionship);
		team1.getParticipants().add(u);
		team1.setTeamSize(2);
		
		Team team2 = new Team();
		team2.setId(2);
		team2.setTeamSize(1);
		team2.setChampionship(this.testChampionship);
		
		
		Championship testChampionship2 = new Championship();
		testChampionship2.setName("Torneo8");
		testChampionship2.setCity("Sevilla");
		testChampionship2.setDescription("Descripcion del torneo");
		testChampionship2.setStartDate(LocalDate.of(2022, 06, 15));
		testChampionship2.setFinishDate(LocalDate.of(2022, 06, 25));
		testChampionship2.setId(8);
		testChampionship2.setMaxTeams(16);
		testChampionship2.setMatches(new ArrayList<Match>());
		testChampionship2.setSport(s);
		
		Team team3 = new Team();
		team3.setId(3);
		team3.setTeamSize(1);
		team3.setChampionship(testChampionship2);
		
		Championship testChampionship3 = new Championship();
		testChampionship3.setName("Torneo8");
		testChampionship3.setCity("Sevilla");
		testChampionship3.setDescription("Descripcion del torneo");
		testChampionship3.setStartDate(LocalDate.of(2020, 06, 15));
		testChampionship3.setFinishDate(LocalDate.of(2020, 06, 25));
		testChampionship3.setId(8);
		testChampionship3.setMaxTeams(16);
		testChampionship3.setMatches(new ArrayList<Match>());
		testChampionship3.setSport(s);

		Team team4 = new Team();
		team4.setId(4);
		team4.setTeamSize(1);
		team4.setChampionship(testChampionship3);
		
		this.testInvitation = new Invitation();
		this.testInvitation.setId(1);
		this.testInvitation.setName("invitation");
		this.testInvitation.setReceiver(u);			// COMPROBAR QUE NO SE LE PUEDE INVITAR A EQUIPO 1
		this.testInvitation.setTeam(team2);    
		
		Invitation invitation2 = new Invitation();
		invitation2.setId(2);
		invitation2.setName("invitation");
		invitation2.setReceiver(u);			// COMPROBAR QUE NO SE LE PUEDE INVITAR A EQUIPO 1
		invitation2.setTeam(team4); 
		
		Collection<Invitation> invitations = new ArrayList<>(Arrays.asList(testInvitation, invitation2));

		BDDMockito.given(this.championshipService.findTeamId(1)).willReturn(team1);
		BDDMockito.given(this.championshipService.findTeamId(2)).willReturn(team2);
		BDDMockito.given(this.championshipService.findTeamId(3)).willReturn(team3);
		BDDMockito.given(this.championshipService.findTeamId(4)).willReturn(team4);
		BDDMockito.given(this.championshipService.findUserByNameOrUsername(u.getName())).willReturn(users);
		BDDMockito.given(this.invitationService.findChampionshipInvitationsByUserName(u.getName())).willReturn(invitations);
		BDDMockito.given(this.invitationService.findChampionshipInvitationsByUserName("Antonio")).willReturn(invitations);   // CAMBIAR CUANDO ESTÉ EL LOGIN IMPLEMENTADO
		BDDMockito.given(this.championshipService.findUsersById(Integer.parseInt(u.getId().toString()))).willReturn(u);
		BDDMockito.given(this.invitationService.isNotInvitedYetToChampionshipTeam(3, u.getId())).willReturn(true);
		BDDMockito.given(this.invitationService.isNotInvitedYetToChampionshipTeam(4, u.getId())).willReturn(true);
		
		BDDMockito.given(this.invitationService.findById(1)).willReturn(testInvitation);
		BDDMockito.given(this.invitationService.findById(2)).willReturn(invitation2);
	}

	
	// Test de mostrar formulario de búsqueda de participantes
	@Test
	@WithMockUser(value = "spring")
	void listChampionships() throws Exception {
		this.mockMvc.perform(get("/invitations/team/1")).andExpect(status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));

	}
	
	// Test de intentar invitar a alguien a un equipo lleno 
	@Test
	@WithMockUser(value = "spring")
	void testSendInvitationTeamIsFull() throws Exception {
		this.mockMvc.perform(get("/invitations/team/1/send_invitation")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));

	}
	
	// Test de intentar invitar a alguien que ya está en el equipo
	@Test
	@WithMockUser(value = "spring")
	void testSendInvitationAlreadyInTeam() throws Exception {
		this.mockMvc.perform(get("/invitations/team/1/send_invitation?search=Usuario1")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));
	}
	
	
	// Test de mandar una invitación correcto
	@Test
	@WithMockUser(value = "spring")
	void testSendInvitationSuccess() throws Exception {
		this.mockMvc.perform(get("/invitations/team/1/send_invitation?search=Usuario2")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));
	}
		
	
	// Test de mandar una invitación de equipo a alguien 
	@Test
	@WithMockUser(value = "spring")
	void testPostSendInvitationSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/invitations/team/3/send_invitation")

			.param("selected_participant", "1")

			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));
		
	}
	
	// Test de mandar una invitación de equipo a alguien que ya está en el equipo
	@Test
	@WithMockUser(value = "spring")
	void testPostSendInvitationUserWasAlreadyInvitedToTeam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/invitations/team/1/send_invitation")

			.param("selected_participant", "1")

			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));
		
	}
	
	// Test de mandar una invitación de equipo a alguien a un torneo finalizado
	@Test
	@WithMockUser(value = "spring")
	void testPostSendInvitationToFinishedChampionship() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/invitations/team/4/send_invitation")

			.param("selected_participant", "1")

			.with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/addParticipantsForm"));
		
	}
	
	
	// Test de listar invitaciones
	@Test
	@WithMockUser(value = "spring")
	void testListInvitations() throws Exception {
		this.mockMvc.perform(get("/invitations/championshipInvitations")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/listInvitations"));
	}
		

	// Test de introducir mal la url de aceptar o rechazar una invitación
	@Test
	@WithMockUser(value = "spring")
	void testIncorrectAcceptInvitation() throws Exception {
		this.mockMvc.perform(get("/invitations/1/?accepted=")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/listInvitations"));
	}
	
	// Test de rechazar una invitación
	@Test
	@WithMockUser(value = "spring")
	void testDeclineInvitation() throws Exception {
		this.mockMvc.perform(get("/invitations/1/?accepted=false")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/listInvitations"));
	}
	
	
	// Test de aceptar invitación
	@Test
	@WithMockUser(value = "spring")
	void testAcceptInvitation() throws Exception {
		this.mockMvc.perform(get("/invitations/1/?accepted=true")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("invitations/listInvitations"));
	}
	
}
