package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.UserType;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
 
}
