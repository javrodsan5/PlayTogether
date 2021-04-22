package net.playtogether.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.ChatMessage;
import net.playtogether.jpa.entity.ChatType;
import net.playtogether.jpa.repository.ChatMessageRepository;
import net.playtogether.jpa.repository.ChatRepository;
import net.playtogether.jpa.repository.ChatTypeRepository;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    private ChatTypeRepository chatTypeRepository;

    private ChatMessageRepository chatMessageRepository;

    public ChatService(ChatRepository chatRepository, ChatTypeRepository chatTypeRepository, ChatMessageRepository chatMessageRepository) {
        this.chatRepository = chatRepository;
        this.chatTypeRepository = chatTypeRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @Transactional
    public ChatType findChatTypeById(Integer id) {
        return this.chatTypeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveChat(Chat chat) {
        this.chatRepository.save(chat);
    }

    @Transactional
    public void saveMessage(ChatMessage message) {
        this.chatMessageRepository.save(message);
    }

    @Transactional
    public Chat findChatById(Integer id) {
        return this.chatRepository.findChatById(id);
    }

    @Transactional
    public Integer findLastMessageId() {
        return this.chatMessageRepository.findLastId();
    }

    @Transactional
    public Integer findChatIdByMeetingId(Integer id) {
        return this.chatRepository.findChatIdByMeetingId(id);
    }

    @Transactional
    public Integer findChatIdByTeam1Id(Integer id) {
        return this.chatRepository.findChatIdByTeam1Id(id);
    }

    @Transactional
    public Integer findIndividualChatIdBetweenTwoUsers(Integer idUser1, Integer idUser2) {
        return this.chatRepository.findIndividualChatIdBetweenTwoUsers(idUser1, idUser2);
    }

    @Transactional
    public void deleteById(Integer id) {
        this.chatRepository.deleteById(id);
    }
    
}
