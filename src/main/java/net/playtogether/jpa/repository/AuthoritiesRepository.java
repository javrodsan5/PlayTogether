package net.playtogether.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.Authorities;

public interface AuthoritiesRepository extends  CrudRepository<Authorities, Integer>{
	
	@Query("select a from Authorities a where a.user.username=?1")
	public List<Authorities> findByUsername(String username);
	
	
}
