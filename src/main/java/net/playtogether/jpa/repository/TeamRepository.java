package net.playtogether.jpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
 
	Collection<Team> findAll();
	
	@Query("SELECT u FROM User u where (u.username LIKE CONCAT(?1, '%') OR u.name LIKE CONCAT(?1, '%'))")
	List<User> findUserByNameOrUsername(String user);

	@Query("SELECT u FROM User u where u.id = ?1")
	User findUserById(Integer id);
	
	@Query("SELECT t FROM Team t where t.championship.id = ?1")
	List<Team> findTeamsByChampionshipId(Integer id);
	
}

