package net.playtogether.jpa.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
	Collection<Meeting> findAll();
	
 
}
