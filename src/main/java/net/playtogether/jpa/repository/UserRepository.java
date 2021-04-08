package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	@Query("SELECT u FROM User u where u.username=?1")
	User findUserByUsername(String username);

}
