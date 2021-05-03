package net.playtogether.jpa.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {

    @Query("SELECT MAX(cm.id) from ChatMessage cm")
    public Integer findLastId();
}
