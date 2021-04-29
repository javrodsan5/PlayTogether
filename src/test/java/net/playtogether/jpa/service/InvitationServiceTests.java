
package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvitationServiceTests {

	@Autowired
	private InvitationService	invitationService;
	
	@Autowired
	private ChampionshipService	championshipService;

	// SAVE INVITATION
	@Test
	public void testSavingInvitationCorrect() {
		Long countBefore = this.invitationService.countInvitations();
		countBefore++;

		Invitation invitation = new Invitation();

		this.invitationService.save(invitation);
		Long countAfter = this.invitationService.countInvitations();
		assertThat(countBefore).isEqualTo(countAfter);
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
		assertThat(countBefore).isEqualTo(countAfter);
	}
	
	// FIND INVITATION BY ID
	@Test
	void shouldFindInvitationWithCorrectId() throws Exception {
		Invitation invitation1 = new Invitation();
		this.invitationService.save(invitation1);
		
		Invitation invitation2 = this.invitationService.findById(invitation1.getId());
		assertThat(invitation2.equals(invitation1));
	}

	// FIND CHAMPIONSHIP INVITATION BY USER_NAME
	@Test
	void shouldFindChampionshipInvitationByUserName() throws Exception {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("password");
		user.setEnabled(true);
		
		Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario1");
		u.setCorreo("correo@correo.com");
		u.setUser(user);
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
		
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUsername(u.getUser().getUsername());
		assertThat(invitations.contains(invitation));

	}
	
	// BOOLEAN USER IS NOT INVITED TO TEAM YET
	@Test
	void shouldValidateUserIsNotInvitedToTeamYet() throws Exception {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("password");
		user.setEnabled(true);
		
		Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario1");
		u.setCorreo("correo@correo.com");
		u.setUser(user);
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
		
		assertThat(b == true);

	}
	
	
	
	// FIND MEETING INVITATION BY USER_NAME
	@Test
	void shouldFindMeetingInvitationByUserName() throws Exception {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("password");
		user.setEnabled(true);
		
		Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario1");
		u.setCorreo("correo@correo.com");
		u.setUser(user);
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
		
		Collection<Invitation> invitations = this.invitationService.findMeetingInvitationsByUsername(u.getName());
		assertThat(invitations.contains(invitation));

	}

	@Test
	void listInvitationsNotFinishedChamp() throws Exception {
		List<Invitation> l = this.invitationService.listInvitationsNotFinishedChamp("antonio98");
		assertThat(l.size()).isEqualTo(0);
	}

	@Test
	void findMyChampionshipInvitations() throws Exception {
		Collection<Invitation> l = this.invitationService.findMyChampionshipInvitations("antonio98");
		assertThat(l.size()).isEqualTo(0);
	}

	@Test
	void findMyMeetingInvitations() throws Exception {
		Collection<Invitation> l = this.invitationService.findMyMeetingInvitations("antonio98");
		assertThat(l.size()).isEqualTo(1);
	}

	@Test
	void isNotInvitedYetToMeeting() throws Exception {
		Boolean b = this.invitationService.isNotInvitedYetToMeeting(1, 1);
		assertThat(b).isTrue();
	}

}
