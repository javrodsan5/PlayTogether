package net.playtogether.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
	Collection<Meeting> findAll();
	
	@Query("SELECT lm FROM Meeting lm WHERE lm.sport.id = ?1 AND lm.date>= CURRENT_TIMESTAMP ORDER BY lm.date")
	Collection<Meeting> listMeetingsBySport(int sportId);
	
	@Query("SELECT lm FROM Meeting lm WHERE lm.meetingCreator.id = ?1 and Year(lm.creationDate) = Year(sysdate()) and Month(lm.creationDate) = Month(sysdate())")
	Collection<Meeting> findMeetingThisMonthToUser(int userId);

	@Query("SELECT lm FROM Meeting lm WHERE lm.category.name = ?1 AND lm.date>= CURRENT_TIMESTAMP ORDER BY lm.date")
	List<Meeting> findMeetingsByCategory(String category);

}
