package net.playtogether.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Pay;

@Repository
public interface PayRepository extends CrudRepository<Pay, Integer> {

    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.id = (SELECT MAX(p.id) FROM Pay p WHERE p.user.user.username = ?1)")
    public Pay findLastPayByUsername(String username);

    @Query("SELECT p FROM Pay p WHERE p.date = null AND p.user.user.username = ?1")
    public List<Pay> findPaysNotFinishedByUsername(String username);

    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.championship.id = ?2 AND p.date != null")
    public Pay findLastFinishedPayForChampionshipByUsername(String username, Integer championshipId);
    
    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.team.id = ?2 AND p.date != null")
    public Pay findLastFinishedPayForTeamByUsername(String username, Integer teamId);

    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.championship.id = ?2 AND p.date = null")
    public List<Pay> findLastNotFinishedPayForChampionshipByUsername(String username, Integer championshipId);
 
    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.team.id = ?2 AND p.date = null")
    public Pay findLastNotFinishedPayForTeamByUsername(String username, Integer teamId);
    
    @Query("SELECT p FROM Pay p WHERE p.user.user.username = ?1 AND p.payType.id=1 AND p.date != null")
    public List<Pay> findLastPayByUsernamePremium(String username);
    
    List<Pay> findAll();

    @Modifying
    @Query("DELETE FROM Pay p WHERE p.user.id = ?1 AND p.team.id=?2")
	void deleteTeamUser(Integer userId, Integer teamId);
    
}
