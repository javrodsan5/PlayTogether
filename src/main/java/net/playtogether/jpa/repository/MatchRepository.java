package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
 
}
