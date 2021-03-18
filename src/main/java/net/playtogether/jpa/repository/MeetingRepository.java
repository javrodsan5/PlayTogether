package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
 
}
