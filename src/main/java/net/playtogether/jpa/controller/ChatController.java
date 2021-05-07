package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.ChatMessage;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.UsuarioService;
import net.playtogether.jpa.util.MetodosAux;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private InvitationService invitationService;

	@GetMapping(value = "/chats")
	public String listPrivateChats(ModelMap model, Principal principal) {
		Usuario usuario = this.usuarioService.findByUsername(principal.getName());

		List<Chat> chats = this.chatService.findMyPrivateChats(usuario.getId()).stream().sorted(Comparator.comparing(Chat::getDateTimeLastMessage)).collect(Collectors.toList());

		Collections.reverse(chats);

		model.addAttribute("chats", chats);
		model.addAttribute("principalUsername", principal.getName());

		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}

		return "chat/ListChats";
	}

	@GetMapping(value = "/chat/{id}/messages")
	public String listMeetingMessages(@PathVariable("id") Integer id, ModelMap model, Principal principal) {
		Usuario usuario = this.usuarioService.findByUsername(principal.getName());
		Chat chat = this.chatService.findChatById(id);
		model.addAttribute("usuarioId", usuario.getId());
		model.addAttribute("chat", chat);
		model.addAttribute("typeId", chat.getChatType().getId());
		Boolean isAuthorized = false;
		String urlBack = "";
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}

		if (chat.getChatType().getId() == 1) { // MEETING
			isAuthorized = chat.getMeeting().getParticipants().contains(usuario);
			urlBack = "/sports/"+chat.getMeeting().getSport().getId()+"/meetings/"+chat.getMeeting().getId();
		} else if (chat.getChatType().getId() == 2) { // TEAM
			isAuthorized = chat.getTeam().getParticipants().contains(usuario);
			urlBack = "/championships/"+chat.getTeam().getChampionship().getId()+"/teams/"+chat.getTeam().getId();
		} else { // INDIVIDUAL
			isAuthorized = chat.getUser1().equals(usuario) || chat.getUser2().equals(usuario);
			Integer idUser = chat.getUser1().getUser().getUsername().equals(principal.getName()) ? chat.getUser2().getId() : chat.getUser1().getId();
			urlBack = "/usuarios/"+ idUser;
		}

		if (!isAuthorized) {
			return "error-403";
		} else {
			model.addAttribute("urlBack", urlBack);
			ChatMessage message = new ChatMessage();
			message.setChat(chat);
			model.addAttribute("message", message);
			return "chat/ListMessages";
		}
	}

	@GetMapping(value = "/chat/{id}/{username}")
	public String checkIfExistsIndividualChat(ModelMap model,@PathVariable("username") String username, Principal principal) {
		Usuario usuario1 = this.usuarioService.findByUsername(principal.getName());
		Usuario usuario2 = this.usuarioService.findByUsername(username);
		Integer chatId = this.chatService.findIndividualChatIdBetweenTwoUsers(usuario1.getId(), usuario2.getId());
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}
		if (chatId == null) {
			Chat chat = new Chat();
			chat.setUser1(usuario1);
			chat.setUser2(usuario2);
			chat.setChatType(this.chatService.findChatTypeById(3)); // INDIVIDUAL
			this.chatService.saveChat(chat);
			chatId = chat.getId();
		}

		return "redirect:/chat/" + chatId + "/messages";

	}

	@PostMapping(value = "/chat/{id}/messages/new")
	public String formNewMessage(@Valid final ChatMessage chatMessage, final BindingResult result,
			@PathVariable("id") Integer id, final ModelMap model, Principal principal) {
		String m = MetodosAux.htmlEntities(chatMessage.getMessage());
		Usuario usuario = this.usuarioService.findByUsername(principal.getName());
		model.addAttribute("usuarioId", usuario.getId());
		Chat chat = this.chatService.findChatById(id);
		
		model.addAttribute("chat", chat);
		List<String> l = MetodosAux.leerFichero("insultos.txt");
		List<String> x = new ArrayList<String>();

		boolean p = false;
		for (int i = 0; i < l.size(); i++) {
			if (m.indexOf(l.get(i)) != -1 || m.indexOf(l.get(i).toLowerCase()) != -1
					|| m.indexOf(l.get(i).toUpperCase()) != -1) {
				p = true;
				x.add(l.get(i));
			}
		}
		
		chatMessage.setChat(chat);
		String urlBack = "";
		if (chat.getChatType().getId() == 1) { // MEETING
			if(!chat.getMeeting().getParticipants().contains(usuario)) {
				return "redirect:/chats";
			}
			urlBack = "/sports/"+chat.getMeeting().getSport().getId()+"/meetings/"+chat.getMeeting().getId();
		} else if (chat.getChatType().getId() == 2) { // TEAM
			if(!chat.getTeam().getParticipants().contains(usuario)) {
				return "redirect:/chats";
			}
			urlBack = "/championships/"+chat.getTeam().getChampionship().getId()+"/teams/"+chat.getTeam().getId();
		} else { // INDIVIDUAL
			Integer idUser = chat.getUser1().getUser().getUsername().equals(principal.getName()) ? chat.getUser2().getId() : chat.getUser1().getId();
			urlBack = "/usuarios/"+ idUser;
		}
		if (p) {
			model.addAttribute("messages", chat.getMessages());
			model.put("message", chatMessage);
			model.put("urlBack", urlBack);
			model.put("spam", true);
			return "chat/ListMessages";
		}
		if (chatMessage.getMessage().isEmpty() || chatMessage.getMessage().trim().isEmpty() ) {
			model.addAttribute("messages", chat.getMessages());
			model.put("message", chatMessage);
			model.put("urlBack", urlBack);
			model.put("vacio", true);
			return "chat/ListMessages";
		}

		if (result.hasErrors()) {
			model.addAttribute("messages", chat.getMessages());
			model.put("message", chatMessage);
			model.put("urlBack", urlBack);

			return "chat/ListMessages";

		} else {
			
			chatMessage.setDate(LocalDateTime.now());
			chatMessage.setUsuario(usuario);
			Integer lastMessageId = this.chatService.findLastMessageId();
			Integer messageId = lastMessageId != null ? lastMessageId + 1 : 1;
			chatMessage.setId(messageId + 1);
			this.chatService.saveMessage(chatMessage);

			return "redirect:/chat/" + id + "/messages";
		}
	}

}
