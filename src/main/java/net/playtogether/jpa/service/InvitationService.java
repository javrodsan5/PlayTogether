package net.playtogether.jpa.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.repository.InvitationRepository;

@Service
public class InvitationService {

	private InvitationRepository invitationRepository;


	@Autowired
	public InvitationService(InvitationRepository invitationRepository) {
		this.invitationRepository = invitationRepository;
	}

	@Transactional
	public boolean save(final Invitation invitation) throws DataAccessException {
		this.invitationRepository.save(invitation);
		return true;
	}
	
	@Transactional
	public boolean delete(Integer invitationId) {
		this.invitationRepository.deleteById(invitationId);
		return true;
	}
	
	@Transactional
	public Invitation findById(Integer invitationId) {
		return this.invitationRepository.findById(invitationId).orElse(null);
	}

	@Transactional(readOnly = true)
	public Collection<Invitation> findChampionshipInvitationsByUserName(String name) {
		return this.invitationRepository.findChampionshipInvitationsByUserName(name);
	}

	@Transactional(readOnly = true)
	public Boolean isNotInvitedYetToChampionshipTeam(int teamId, Integer receiverId) {
		return this.invitationRepository.isNotInvitedYetToChampionshipTeam(teamId, receiverId);
	}


	
	@Transactional(readOnly = true)
	public Collection<Invitation> findMeetingInvitationsByUserName(String name) {
		return this.invitationRepository.findMeetingInvitationsByUserName(name);
	}
	
	@Transactional(readOnly = true)
	public Boolean isNotInvitedYetToMeeting(int meetingId, Integer receiverId) {
		return this.invitationRepository.isNotInvitedYetToMeeting(meetingId, receiverId);
	}


	
}
