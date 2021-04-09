
package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvitationServiceTests {

	@Autowired
	private InvitationService	invitationService;
	
	@Autowired
	private ChampionshipService	championshipService;
	
	@Autowired
	private MeetingService meetingService;

	// SAVE INVITATION
	@Test
	public void testSavingInvitationCorrect() {
		Long countBefore = this.invitationService.countInvitations();
		countBefore++;

		Invitation invitation = new Invitation();

		this.invitationService.save(invitation);
		Long countAfter = this.invitationService.countInvitations();
		Assertions.assertThat(countBefore).isEqualTo(countAfter);
	}
	
	// DELETE INVITATION
	@Test
	public void testDeleteInvitationCorrect() {
		Invitation invitation = new Invitation();
		this.invitationService.save(invitation);
		
		Long countBefore = this.invitationService.countInvitations();
		countBefore--;

		this.invitationService.delete(invitation.getId());
		Long countAfter = this.invitationService.countInvitations();
		Assertions.assertThat(countBefore).isEqualTo(countAfter);
	}
	
	// FIND INVITATION BY ID
	@Test
	void shouldFindInvitationWithCorrectId() throws Exception {
		Invitation invitation1 = new Invitation();
		this.invitationService.save(invitation1);
		
		Invitation invitation2 = this.invitationService.findById(invitation1.getId());
		Assertions.assertThat(invitation2.equals(invitation1));
	}

	// FIND CHAMPIONSHIP INVITATION BY USER_NAME
	@Test
	void shouldFindChampionshipInvitationByUserName() throws Exception {
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
		
		Invitation invitation = new Invitation();
		invitation.setReceiver(u);
		
		this.invitationService.save(invitation);
		
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUserName(u.getName());
		Assertions.assertThat(invitations.contains(invitation));

	}
	
	// BOOLEAN USER IS NOT INVITED TO TEAM YET
	@Test
	void shouldValidateUserIsNotInvitedToTeamYet() throws Exception {
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
		
		Team t = new Team();
		this.championshipService.save(t);
		
		Invitation invitation = new Invitation();
		invitation.setTeam(t);
		this.invitationService.save(invitation);

		boolean b = this.invitationService.isNotInvitedYetToChampionshipTeam(t.getId(), u.getId());
		
		Assertions.assertThat(b == true);

	}
	
	
	
	// FIND MEETING INVITATION BY USER_NAME
	@Test
	void shouldFindMeetingInvitationByUserName() throws Exception {
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
		
		Invitation invitation = new Invitation();
		invitation.setReceiver(u);
		
		this.invitationService.save(invitation);
		
		Collection<Invitation> invitations = this.invitationService.findMeetingInvitationsByUserName(u.getName());
		Assertions.assertThat(invitations.contains(invitation));

	}
	
	/*
	// BOOLEAN USER IS NOT INVITED TO MEETING YET
	@Test
	void shouldValidateUserIsNotInvitedToMeetingYet() throws Exception {
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
		
		Meeting m = new Meeting();
		m.setId(1);
		m.setAddress("Bami");
		m.setCity("Sevilla");
		m.setDate(LocalDateTime.of(2021, 06, 12, 12, 00));
		m.setParticipants(new ArrayList<User>());
		m.setDescription("Una partidata");
		m.setNumberOfPlayers(2);
		this.meetingService.save(m);
		
		Invitation invitation = new Invitation();
		invitation.setMeeting(m);
		this.invitationService.save(invitation);

		boolean b = this.invitationService.isNotInvitedYetToMeeting(m.getId(), u.getId());
		
		Assertions.assertThat(b == true);
	}
	*/

}
