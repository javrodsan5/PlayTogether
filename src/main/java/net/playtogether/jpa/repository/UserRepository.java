package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
