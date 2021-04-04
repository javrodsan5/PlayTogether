package net.playtogether.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;

@Repository
public interface SportRepository extends CrudRepository<Sport, Integer> {
 
	Collection<Sport> findAll();
	
	@Query("SELECT s FROM Sport s where s.name = ?1")
	Sport findSportByName(String sport);
	
	@Query("select s from Sport s where s.sportType = ?1")
	Collection<Sport> findAllSportsByType(SportType sportType);
	
	@Query("SELECT s FROM Sport s where s.id = ?1")
	Sport findSportById(Integer id);
}
