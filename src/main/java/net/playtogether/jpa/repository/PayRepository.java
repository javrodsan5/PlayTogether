package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Pay;

@Repository
public interface PayRepository extends CrudRepository<Pay, Integer> {
 
}
