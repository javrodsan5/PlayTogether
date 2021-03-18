package net.playtogether.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.repository.MeetingRepository;

@Service
public class MeetingService {
	
	MeetingRepository meetingRepository;
	
	@Transactional()
	public void save(Meeting meeting)  {
		meetingRepository.save(meeting);
	}

}
