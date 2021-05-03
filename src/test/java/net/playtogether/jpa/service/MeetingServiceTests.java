package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Meeting;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MeetingServiceTests {

	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private SportService sportService;

	@Autowired
	private UsuarioService usuarioService;

	
	//Test de consultar numero de quedadas
	 @Test
	 void listMeetingTest() {
		 int numMeetings = this.meetingService.listMeetings().size();
		 assertThat(numMeetings).isEqualTo(7);
	 }
	 
	 //Test de consultar una quedada por id
	 @Test
	 void findMeetingByIdTest() {
		 Meeting meeting = this.meetingService.findMeetingById(1);
		 assertThat(meeting.getCity()).isEqualTo("Sevilla");
	 }

	 @Test
	 void findMeetingThisMonthToUser() throws Exception {
		Collection<Meeting> meetings = this.meetingService.findMeetingThisMonthToUser(1);
		assertThat(meetings.size()).isEqualTo(0);
	 }

	 @Test
	 void findListMeetingBySport() throws Exception {
		Collection<Meeting> meetings = this.meetingService.listMeetingsBySport(1);
		assertThat(meetings.size()).isEqualTo(3);
	 }

	 @Test
	 void saveMeeting() throws Exception {
		Meeting meeting = new Meeting();

		meeting.setAddress("address");
		meeting.setCity("city");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDescription("description");
		meeting.setMeetingCreator(this.usuarioService.findUserById(1));
		meeting.setNumberOfPlayers(8);
		meeting.setParticipants(new ArrayList<>());
		meeting.setSport(this.sportService.findSportById(2));

		this.meetingService.save(meeting);

		Meeting m = this.meetingService.findMeetingById(8);
		assertThat(m).isNotNull();
		assertThat(m.getId()).isEqualTo(8);
	 }
	 

}
