package net.playtogether.jpa.service;

import java.util.Collection;
import java.util.List;

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
	public Long countInvitations() {
		return this.invitationRepository.count();
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
	
	@Transactional(readOnly = true)
	public Invitation findById(Integer invitationId) {
		return this.invitationRepository.findById(invitationId).orElse(null);
	}

	@Transactional(readOnly = true)
	public Collection<Invitation> findChampionshipInvitationsByUsername(String username) {
		return this.invitationRepository.findChampionshipInvitationsByUsername(username);
	}

	@Transactional(readOnly = true)
	public Boolean isNotInvitedYetToChampionshipTeam(int teamId, Integer receiverId) {
		return this.invitationRepository.isNotInvitedYetToChampionshipTeam(teamId, receiverId);
	}

	
	@Transactional(readOnly = true)
	public Collection<Invitation> findMeetingInvitationsByUsername(String username) {
		return this.invitationRepository.findMeetingInvitationsByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public Boolean isNotInvitedYetToMeeting(int meetingId, Integer receiverId) {
		return this.invitationRepository.isNotInvitedYetToMeeting(meetingId, receiverId);
	}
	
	@Transactional
	public void deleteInvitationsByTeamId(Integer teamId) {
		this.invitationRepository.deleteInvitationsByTeamId(teamId);
		
	}

	@Transactional
	public void deleteInvitationsByMeetingId(Integer meetingId) {
		this.invitationRepository.deleteInvitationsByMeetingId(meetingId);
		
	}

	@Transactional(readOnly = true)
	public Collection<Invitation> findMyMeetingInvitations(String username) {
		return this.invitationRepository.findMyMeetingInvitations(username);
	}

	@Transactional(readOnly = true)
	public Collection<Invitation> findMyChampionshipInvitations(String username) {
		return this.invitationRepository.findMyChampionshipInvitations(username);
	}
	
	@Transactional(readOnly = true)
	public List<Invitation> listInvitationsNotFinishedChamp(String username) {
		return this.invitationRepository.listInvitationsNotFinishedChamp(username);
	}

	public Collection<Invitation> findMyMeetingInvitationsMeeting(String name, Integer meetingId) {
		
		return this.invitationRepository.findMyMeetingInvitationsMeeting(name, meetingId);
	}

	public Collection<Invitation> findMyChampionshipInvitationsTeam(String name, Integer teamId) {
		
		return this.invitationRepository.findMyChampionshipInvitationsTeam(name, teamId);
	}
}
