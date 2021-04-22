package net.playtogether.jpa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.ChatMessage;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
@RequestMapping("/chat/{id}")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/messages")
	public String listMeetingMessages(@PathVariable("id") Integer id, ModelMap model, Principal principal) {
		Usuario usuario = this.usuarioService.findByUsername(principal.getName());
		Chat chat = this.chatService.findChatById(id);

		Boolean isAuthorized = false;

		if (chat.getChatType().getId() == 1) { // MEETING
			isAuthorized = chat.getMeeting().getParticipants().contains(usuario);
		} else if (chat.getChatType().getId() == 2) { // TEAM
			isAuthorized = chat.getTeam().getParticipants().contains(usuario);
		} else { // INDIVIDUAL
			isAuthorized = chat.getUser1().equals(usuario) || chat.getUser2().equals(usuario);
		}

		if (!isAuthorized) {
			return "error-403";
		} else {
			model.addAttribute("messages", chat.getMessages());
			model.addAttribute("chatId", id);
			return "chat/ListMessages";
		}
	}

	@GetMapping(value = "/{username}")
	public String checkIfExistsIndividualChat(@PathVariable("username") String username, Principal principal) {
		Usuario usuario1 = this.usuarioService.findByUsername(principal.getName());
		Usuario usuario2 = this.usuarioService.findByUsername(username);
		Integer chatId = this.chatService.findIndividualChatIdBetweenTwoUsers(usuario1.getId(), usuario2.getId());

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

	@GetMapping(value = "/messages/new")
	public String formNewMessage(@PathVariable("id") Integer id, ModelMap model, Principal principal) {

		Usuario usuario = this.usuarioService.findByUsername(principal.getName());
		Chat chat = this.chatService.findChatById(id);

		Boolean isAuthorized = false;

		if (chat.getChatType().getId() == 1) { // MEETING
			isAuthorized = chat.getMeeting().getParticipants().contains(usuario);
		} else if (chat.getChatType().getId() == 2) { // TEAM
			isAuthorized = chat.getTeam().getParticipants().contains(usuario);
		} else { // INDIVIDUAL
			isAuthorized = chat.getUser1().equals(usuario) || chat.getUser2().equals(usuario);
		}

		if (!isAuthorized) {
			return "error-403";
		} else {
			ChatMessage message = new ChatMessage();
			message.setChat(chat);
			model.addAttribute("message", message);
			return "chat/FormNewMessage";
		}
	}

	private String htmlEntities(String s) {
		return s.replace("&ntilde;", "ñ").replace("&Ntilde;", "Ñ").replace("&amp;", "&").replace("&Ntilde;", "Ñ")
				.replace("&ntilde;", "ñ").replace("&Ntilde;", "Ñ").replace("&Agrave;", "À").replace("&Aacute;", "Á")
				.replace("&Acirc;", "Â").replace("&Atilde;", "Ã").replace("&Auml;", "Ä").replace("&Aring;", "Å")
				.replace("&AElig;", "Æ").replace("&Ccedil;", "Ç").replace("&Egrave;", "È").replace("&Eacute;", "É")
				.replace("&Ecirc;", "Ê").replace("&Euml;", "Ë").replace("&Igrave;", "Ì").replace("&Iacute;", "Í")
				.replace("&Icirc;", "Î").replace("&Iuml;", "Ï").replace("&ETH;", "Ð").replace("&Ntilde;", "Ñ")
				.replace("&Ograve;", "Ò").replace("&Oacute;", "Ó").replace("&Ocirc;", "Ô").replace("&Otilde;", "Õ")
				.replace("&Ouml;", "Ö").replace("&Oslash;", "Ø").replace("&Ugrave;", "Ù").replace("&Uacute;", "Ú")
				.replace("&Ucirc;", "Û").replace("&Uuml;", "Ü").replace("&Yacute;", "Ý").replace("&THORN;", "Þ")
				.replace("&szlig;", "ß").replace("&agrave;", "à").replace("&aacute;", "á").replace("&acirc;", "â")
				.replace("&atilde;", "ã").replace("&auml;", "ä").replace("&aring;", "å").replace("&aelig;", "æ")
				.replace("&ccedil;", "ç").replace("&egrave;", "è").replace("&eacute;", "é").replace("&ecirc;", "ê")
				.replace("&euml;", "ë").replace("&igrave;", "ì").replace("&iacute;", "í").replace("&icirc;", "î")
				.replace("&iuml;", "ï").replace("&eth;", "ð").replace("&ntilde;", "ñ").replace("&ograve;", "ò")
				.replace("&oacute;", "ó").replace("&ocirc;", "ô").replace("&otilde;", "õ").replace("&ouml;", "ö")
				.replace("&oslash;", "ø").replace("&ugrave;", "ù").replace("&uacute;", "ú").replace("&ucirc;", "û")
				.replace("&uuml;", "ü").replace("&yacute;", "ý").replace("&thorn;", "þ").replace("&yuml;", "ÿ");
	}

	private List<String> leerFichero(String fichero) {
		List<String> l = new ArrayList<String>();

		try {
			l = Files.lines(Paths.get(fichero)).collect(Collectors.toList());

		} catch (IOException e) {

			System.out.println("No se puede leer el fichero de insultos.");
		}
		return l;
	}

	@PostMapping(value = "/messages/new")
	public String formNewMessage(@Valid final ChatMessage chatMessage, final BindingResult result,
			@PathVariable("id") Integer id, final ModelMap model, Principal principal) {
		String m = htmlEntities(chatMessage.getMessage());

		List<String> l = leerFichero("insultos.txt");
		List<String> x = new ArrayList<String>();

		boolean p = false;
		for (int i = 0; i < l.size(); i++) {
			if (m.indexOf(l.get(i)) != -1 || m.indexOf(l.get(i).toLowerCase()) != -1
					|| m.indexOf(l.get(i).toUpperCase()) != -1) {
				p = true;
				x.add(l.get(i));
			}
		}
		if (p) {
			model.put("message", chatMessage);
			model.put("spam", true);
			return "chat/FormNewMessage";
		}
		if (chatMessage.getMessage().isEmpty() || chatMessage.getMessage().trim().isEmpty() ) {
			model.put("message", chatMessage);
			model.put("vacio", true);
			return "chat/FormNewMessage";
		}

		if (result.hasErrors()) {

			model.put("message", chatMessage);

			return "chat/FormNewMessage";

		} else {
			Usuario usuario = this.usuarioService.findByUsername(principal.getName());
			Chat chat = this.chatService.findChatById(id);
			chatMessage.setDate(LocalDateTime.now());
			chatMessage.setChat(chat);
			chatMessage.setUsuario(usuario);
			Integer lastMessageId = this.chatService.findLastMessageId();
			Integer messageId = lastMessageId != null ? lastMessageId + 1 : 1;
			chatMessage.setId(messageId + 1);
			this.chatService.saveMessage(chatMessage);

			return "redirect:/chat/" + id + "/messages";
		}
	}

}
