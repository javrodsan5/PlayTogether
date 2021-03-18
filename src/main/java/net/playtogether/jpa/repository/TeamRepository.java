package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
 
}
