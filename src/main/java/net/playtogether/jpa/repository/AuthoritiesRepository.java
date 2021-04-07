package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.Authorities;

public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
