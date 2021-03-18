package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
 
}
