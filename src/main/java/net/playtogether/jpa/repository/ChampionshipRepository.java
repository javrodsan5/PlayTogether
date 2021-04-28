package net.playtogether.jpa.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Usuario;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Integer> {

	Collection<Championship> findAll();

	@Query("SELECT lm FROM Championship lm WHERE lm.sport.id = ?1 AND lm.finishDate >= CURRENT_TIMESTAMP ORDER BY lm.startDate")
	Collection<Championship> listChampionshipsBySport(int sportId);
	
	@Query("Select t.participants From Team t where t.championship.id = ?1 ")
	Collection<Usuario> findParticipantsChampionship(int championshipId);
}
