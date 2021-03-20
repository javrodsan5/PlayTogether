package net.playtogether.jpa.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Championship;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Integer> {

	Collection<Championship> findAll();

}
