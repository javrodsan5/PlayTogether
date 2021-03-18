package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Championship;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
 
}
