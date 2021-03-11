package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
}
