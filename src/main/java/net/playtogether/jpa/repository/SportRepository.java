package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
 
}
