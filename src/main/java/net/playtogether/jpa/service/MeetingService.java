package net.playtogether.jpa.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.repository.MeetingRepository;

@Service
public class MeetingService {
	
	MeetingRepository meetingRepository;
	
	public MeetingService(MeetingRepository meetingRepository){
		this.meetingRepository=meetingRepository;
	}
	@Transactional()
	public void save(Meeting meeting)  {
		meetingRepository.save(meeting);
	}
	
	@Transactional(readOnly=true)
	public Collection<Meeting> listMeetings(){
		return meetingRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Meeting findMeetingById(int id){
		return meetingRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public Collection<Meeting> listMeetingsBySport(int sportId){
		return meetingRepository.listMeetingsBySport(sportId);
	}

}
