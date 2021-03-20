package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
 
}
