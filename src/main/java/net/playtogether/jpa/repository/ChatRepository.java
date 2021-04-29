package net.playtogether.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.ChatMessage;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    
    @Query("SELECT c FROM Chat c WHERE c.id = ?1")
    public Chat findChatById(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE c.meeting.id = ?1")
    public Integer findChatIdByMeetingId(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE c.team.id = ?1")
    public Integer findChatIdByTeam1Id(Integer id);

    @Query("SELECT c.id FROM Chat c WHERE (c.user1.id = ?1 AND c.user2.id = ?2) OR (c.user1.id = ?2 AND c.user2.id = ?1)")
    public Integer findIndividualChatIdBetweenTwoUsers(Integer idUser1, Integer idUser2);

    @Query("SELECT cm FROM ChatMessage cm WHERE cm.chat.id = ?2 AND cm.usuario.id <> ?1")
	public List<ChatMessage> findNotMineMessages(Integer id, Integer chatId);

    @Query("SELECT cm FROM ChatMessage cm WHERE cm.chat.id = ?2 AND cm.usuario.id = ?1")
	public List<ChatMessage> findOwnMessages(Integer id, Integer chatId);

    @Query("SELECT c FROM Chat c WHERE c.user1.id = ?1 OR c.user2.id = ?1")
    public List<Chat> findMyPrivateChats(Integer id);

    List<Chat> findAll();
}
