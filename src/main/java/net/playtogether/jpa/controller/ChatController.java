package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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

        if(chat.getChatType().getId() == 1) { //MEETING
            isAuthorized = chat.getMeeting().getParticipants().contains(usuario);
        } else if(chat.getChatType().getId() == 2) { //TEAM
            isAuthorized = chat.getTeam().getParticipants().contains(usuario);
        } else { //INDIVIDUAL
            isAuthorized = chat.getUser1().equals(usuario) || chat.getUser2().equals(usuario);
        }

        if(!isAuthorized) {
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

        if(chatId == null) {
            Chat chat = new Chat();
            chat.setUser1(usuario1);
            chat.setUser2(usuario2);
            chat.setChatType(this.chatService.findChatTypeById(3)); //INDIVIDUAL
            this.chatService.saveChat(chat);
            chatId = chat.getId();
        }

        return "redirect:/chat/"+chatId+"/messages";

    }

    @GetMapping(value = "/messages/new")
    public String formNewMessage(@PathVariable("id") Integer id, ModelMap model, Principal principal) {

        Usuario usuario = this.usuarioService.findByUsername(principal.getName());
        Chat chat = this.chatService.findChatById(id);

        Boolean isAuthorized = false;

        if(chat.getChatType().getId() == 1) { //MEETING
            isAuthorized = chat.getMeeting().getParticipants().contains(usuario);
        } else if(chat.getChatType().getId() == 2) { //TEAM
            isAuthorized = chat.getTeam().getParticipants().contains(usuario);
        } else { //INDIVIDUAL
            isAuthorized = chat.getUser1().equals(usuario) || chat.getUser2().equals(usuario);
        }

        if(!isAuthorized) {
            return "error-403";
        } else {
            ChatMessage message = new ChatMessage();
            message.setChat(chat);
            model.addAttribute("message", message);
            return "chat/FormNewMessage";
        }
    }

    @PostMapping(value = "/messages/new")
    public String formNewMessage(@Valid final ChatMessage chatMessage, final BindingResult result, 
    @PathVariable("id") Integer id, final ModelMap model, Principal principal) {

        if(result.hasErrors()) {
            model.put("message", chatMessage);
            return "chat/FormNewMessage";
        } else {
            Usuario usuario = this.usuarioService.findByUsername(principal.getName());
            Chat chat = this.chatService.findChatById(id);
            chatMessage.setDate(LocalDateTime.now());
            chatMessage.setChat(chat);
            chatMessage.setUsuario(usuario);
            Integer lastMessageId = this.chatService.findLastMessageId();
            Integer messageId = lastMessageId != null ? lastMessageId+1 : 1;
            chatMessage.setId(messageId+1);
            this.chatService.saveMessage(chatMessage);

            return "redirect:/chat/"+id+"/messages";
        }
    }


}
