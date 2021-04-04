package net.playtogether.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;


@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {
	
	Collection<Match> findAll();
 
	@Query("SELECT lm FROM Match lm WHERE lm.championship.id = ?1")
	Collection<Match> listMatchesByChampionship(int championshipId);
	
	@Query("SELECT t FROM Team t WHERE t.championship.id = ?1")
	Collection<Team> findTeams(int championshipId);
}
