package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.ChatType;

public interface ChatTypeRepository extends CrudRepository<ChatType, Integer> {
    
}
