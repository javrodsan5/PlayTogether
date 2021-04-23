package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MeetingServiceTests {

	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private SportService sportService;

	
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
	 

}
