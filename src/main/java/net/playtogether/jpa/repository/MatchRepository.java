package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {
 
}
