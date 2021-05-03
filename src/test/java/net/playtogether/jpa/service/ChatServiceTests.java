package net.playtogether.jpa.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.ChatMessage;
import net.playtogether.jpa.entity.ChatType;
import net.playtogether.jpa.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChatServiceTests {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UsuarioService usuarioService;

	// FIND CHAT_TYPE BY ID
	@Test
	void shouldFindChatTypeWithCorrectId() {
		ChatType chatType = this.chatService.findChatTypeById(3);
		assertThat(chatType.getId()).isEqualTo(3);
	}

	// SAVE CHAT
	@Test
	void testSavingChatCorrect() {
		List<Chat> chats = this.chatService.findAll();
		Integer tam1 = chats.size();

		Chat chat = new Chat();
		List<ChatMessage> m = new ArrayList<ChatMessage>();
		ChatMessage cm = new ChatMessage();
		cm.setMessage("HOLA");
		m.add(cm);
		chat.setMessages(m);
		this.chatService.saveChat(chat);

		Integer tam2 = this.chatService.findAll().size();

		Assertions.assertThat(tam2).isEqualTo(tam1 + 1);
	}

	// SAVE CHATMESSAGE
	@Test
	void testSavingChatMessageCorrect() {
		Usuario u1 = this.usuarioService.findUserById(5);
		
		List<ChatMessage> cml = this.chatService.findOwnMessages(5, 32);

		Chat chat = this.chatService.findChatById(32);
		ChatMessage cm = new ChatMessage();
		cm.setMessage("HOLA");
		cm.setId(60);
		cm.setUsuario(u1);
		cm.setChat(chat);
		cm.setDate(LocalDateTime.now());
		cml.add(cm);
		chat.setMessages(cml);
		this.chatService.saveMessage(cm);
		this.chatService.saveChat(chat);
		List<ChatMessage> cml2 = this.chatService.findOwnMessages(5, 32);
		

		Assertions.assertThat(cml2.size()).isEqualTo(cml.size() + 1);
	}

	// FIND CHAT BY ID
	@Test
	void shouldFindChatWithCorrectId() {
		Chat chat = this.chatService.findChatById(3);
		assertThat(chat.getId()).isEqualTo(3);
	}

	// FIND LAST MESSAGE BY ID

	@Test
	void shouldFindLastMessageWithCorrectId() {
		ChatMessage message1 = new ChatMessage();

		message1.setMessage("hola");
		this.chatService.saveMessage(message1);

		Assertions.assertThat(message1.getId().equals(1));
	}

	// FIND CHAT_ID BY MEETING_ID

	@Test
	void shouldFindChatIByMeetingdWithCorrectId() {
		Integer chatId = this.chatService.findChatIdByMeetingId(6);
		Chat chat = this.chatService.findChatById(chatId);
		Assertions.assertThat(chat.getId()).isEqualTo(6);
	}

	// FIND CHAT_ID BY TEAM_ID
	@Test
	void shouldFindChatIdByTeamWithCorrectId() {
		Integer chat = this.chatService.findChatIdByTeam1Id(1);
		Assertions.assertThat(chat).isEqualTo(8);
	}

	// FIND INDIVIDUAL CHAT_ID BETWEEN 2 USERS

	@Test
	void shouldFindIndividualChatBetweenTwoUsersCorrectId() {

		Integer id = this.chatService.findIndividualChatIdBetweenTwoUsers(5, 6);
		Assertions.assertThat(id).isEqualTo(32);
	}

	// DELETE CHAT BY ID

	@Test
	void shouldDeleteChatByCorrectId() {
		List<Chat> lista = this.chatService.findAll();
		Integer tamaño = lista.size();

		Chat c = this.chatService.findChatById(1);
		this.chatService.deleteById(c.getId());

		Integer tamaño2 = this.chatService.findAll().size();

		Assertions.assertThat(tamaño2).isEqualTo(tamaño - 1);

	}
	// FIND OWN MESSAGES

	@Test
	void shouldFindOwnMessage() {

		List<ChatMessage> cm = this.chatService.findOwnMessages(6, 32);

		Assertions.assertThat(cm.size()).isEqualTo(2);
	}

	// FIND NOT MINE MESSAGES
	// FIND MY PRIVATE CHATS

	@Test
	void shouldFindPrivateChatsByUserId() {

		List<Chat> chats = this.chatService.findMyPrivateChats(5);

		Assertions.assertThat(chats.size()).isEqualTo(1);
	}

	// FIND LAST MESSAGE BY CHAT ID

//	@Test
	void shouldFindLastMessageByChatId() {

		Usuario u1 = this.usuarioService.findUserById(5);

		Chat chat = this.chatService.findChatById(32);
		ChatMessage cm = new ChatMessage();
		cm.setMessage("HOLA");
		cm.setId(60);
		cm.setUsuario(u1);
		cm.setChat(chat);
		cm.setDate(LocalDateTime.now());
		this.chatService.saveMessage(cm);

		ChatMessage cm2 = this.chatService.findLastMessageByChatId(32);

		Assertions.assertThat(cm2.getMessage()).isEqualTo("HOLA");
	}

	// FIND ALL

	@Test
	void shouldFindalllChats() {

		List<Chat> chats = this.chatService.findAll();

		Assertions.assertThat(chats.size()).isEqualTo(32);
	}

}