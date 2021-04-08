package net.playtogether.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Invitation;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Integer> {

	Collection<Invitation> findAll();

	@Query("SELECT i FROM Invitation i WHERE i.id = ?1")
	Collection<Invitation> findInvitationById(int invitationId);
	
	@Query("SELECT i FROM Invitation i WHERE (i.receiver.name LIKE ?1 AND i.team.id IS NOT NULL)")
	Collection<Invitation> findChampionshipInvitationsByUserName(String name);

	@Query("SELECT COUNT(i) = 0 from Invitation i WHERE (i.team.id = ?1 AND i.receiver.id = ?2)")
	Boolean isNotInvitedYetToChampionshipTeam(int teamId, Integer receiverId);
	
	
	@Query("SELECT i FROM Invitation i WHERE (i.receiver.name LIKE ?1 AND i.meeting.id IS NOT NULL)")
	Collection<Invitation> findMeetingInvitationsByUserName(String name);
	
	@Query("SELECT COUNT(i) = 0 from Invitation i WHERE (i.meeting.id = ?1 AND i.receiver.id = ?2)")
	Boolean isNotInvitedYetToMeeting(int meetingId, Integer receiverId);
	
}
