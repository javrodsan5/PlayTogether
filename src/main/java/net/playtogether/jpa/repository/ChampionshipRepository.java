package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Championship;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Integer> {
 
}
