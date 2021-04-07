package net.playtogether.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;

public interface UserRepository extends CrudRepository<User, String> {
	@Query("SELECT u FROM User u where u.username=?1")
	User findUserByUsername(String username);
}
