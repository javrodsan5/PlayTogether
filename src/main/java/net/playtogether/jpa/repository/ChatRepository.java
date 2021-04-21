package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    
    @Query("SELECT c FROM Chat c WHERE c.id = ?1")
    public Chat findChatById(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE c.meeting.id = ?1")
    public Integer findChatIdByMeetingId(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE c.team.id = ?1")
    public Integer findChatIdByTeam1Id(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE (c.user1.id = ?1 AND c.user2.id = ?2) OR (c.user1.id = ?2 AND c.user2.id = ?1)")
    public Integer findIndividualChatIdBetweenTwoUsers(Integer idUser1, Integer idUser2);
}
