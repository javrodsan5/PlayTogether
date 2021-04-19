package net.playtogether.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Invitation;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Integer> {

	Collection<Invitation> findAll();

	@Query("SELECT i FROM Invitation i WHERE i.id = ?1")
	Collection<Invitation> findInvitationById(int invitationId);
	
	@Query("SELECT i FROM Invitation i WHERE (i.team.id IS NOT NULL AND i.receiver.user.username LIKE ?1)")
	Collection<Invitation> findChampionshipInvitationsByUsername(String username);

	@Query("SELECT COUNT(i) = 0 from Invitation i WHERE (i.team.id = ?1 AND i.receiver.id = ?2)")
	Boolean isNotInvitedYetToChampionshipTeam(int teamId, Integer receiverId);
	
	@Query("SELECT i FROM Invitation i WHERE (i.meeting.id IS NOT NULL AND i.receiver.user.username LIKE ?1)")
	Collection<Invitation> findMeetingInvitationsByUsername(String username);
	
	@Query("SELECT COUNT(i) = 0 from Invitation i WHERE (i.meeting.id = ?1 AND i.receiver.id = ?2)")
	Boolean isNotInvitedYetToMeeting(int meetingId, Integer receiverId);

	@Modifying
	@Query("DELETE FROM Invitation i WHERE i.team.id = ?1")
	void deleteInvitationsByTeamId(Integer teamId);
	
	@Modifying
	@Query("DELETE FROM Invitation i WHERE i.meeting.id = ?1")
	void deleteInvitationsByMeetingId(Integer meetingId);


	
}
