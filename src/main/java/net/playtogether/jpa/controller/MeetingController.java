
package net.playtogether.jpa.controller;

import java.security.Principal; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@Autowired
	UsuarioService userService;

	@Autowired
	SportService sportService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	InvitationService invitationService;

	@Autowired
	ChatService chatService;

	@InitBinder("meeting")
	public void initMeetingBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new MeetingValidator());
	}

	@GetMapping("/sports/{sportId}/meetings/add")
	public String initCreationMeeting(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			final Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Integer listSports = this.sportService.findAll().size();
		Usuario usuario = this.userService.findByUsername(principal.getName());
		if (sportId > 0 && sportId <= listSports) {
			Collection<Meeting> meetingMonth = this.meetingService.findMeetingThisMonthToUser(usuario.getId());
			if (usuario.getType().getId() == 1 && meetingMonth.size() > 0) {
				Collection<Meeting> meetings = this.meetingService.listMeetingsBySport(sportId);
				Sport sport = this.sportService.findSportById(sportId);
				model.addAttribute("meetings", meetings);
				model.addAttribute("deporte", sportId);
				model.addAttribute("nombreDeporte", sport.getName());
				model.addAttribute("limiteMes", true);
				return "meetings/listMeeting";
			} else {
				model.put("meeting", new Meeting());
				model.put("sportId", sportId);
				model.put("sport", this.sportService.findSportById(sportId));
				model.put("numbers", IntStream.range(2, 51).boxed().collect(Collectors.toList()));
				return "meetings/createMeetingForm";
			}
		} else {
			return "error-500";
		}
	}

	@PostMapping("/sports/{sportId}/meetings/add")
	public String postCreationMeeting(@Valid final Meeting meeting, BindingResult result, final ModelMap model,
			@PathVariable("sportId") final Integer sportId, final Principal principal) {
		Sport sport = this.sportService.findSportById(sportId);
		if (result.hasErrors()) {
			model.put("sport", sport);
			model.put("sportId", sportId);
			model.put("numbers", IntStream.range(2, 51).boxed().collect(Collectors.toList()));
			return "meetings/createMeetingForm";
		} else {
			Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
			meeting.setMeetingCreator(usuario);

			List<Usuario> participants = new ArrayList<>();
			participants.add(usuario);
			meeting.setParticipants(participants);
			// meeting.setNumberOfPlayers(sport.getNumberOfPlayersInTeam() * 2);
			meeting.setCreationDate(LocalDate.now());
			this.meetingService.save(meeting);
			usuario.setPuntos(usuario.getPuntos() + 7);
			this.usuarioService.saveUsuarioAlreadyRegistered(usuario);

			Chat chat = new Chat();
			chat.setChatType(this.chatService.findChatTypeById(1)); // MEETING
			chat.setMeeting(meeting);
			this.chatService.saveChat(chat);
			return "redirect:/sports/" + sportId + "/meetings";
		}

	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}/edit")
	public String initUpdateMeeting(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("meetingId") final Integer meetingId, final Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		if (meeting.getMeetingCreator().equals(usuario)) {
			model.put("sport", this.sportService.findSportById(sportId));
			model.put("meeting", meeting);
			model.put("numberPlayers", meeting.getNumberOfPlayers());
			model.put("numbers", IntStream.range(2, 51).boxed().collect(Collectors.toList()));
			return "meetings/updateMeetingForm";
		} else {
			return "error-403";
		}

	}

	@PostMapping("/sports/{sportId}/meetings/{meetingId}/edit")
	public String postUpdateMeeting(@Valid final Meeting meeting, final BindingResult result, final ModelMap model,
			@PathVariable("sportId") final Integer sportId, @PathVariable("meetingId") final Integer meetingId) {
		Meeting meetingToUpdate = this.meetingService.findMeetingById(meetingId);
		if (meeting.getNumberOfPlayers() < meetingToUpdate.getParticipants().size()) {
			result.rejectValue("numberOfPlayers", "Actualmente se encuentra un número mayor de participantes.",
					"Actualmente se encuentra un número mayor de participantes.");
			model.put("errorPlayers", "Actualmente se encuentra un número mayor de participantes.");
		}
		if (result.hasErrors()) {
			model.put("sport", this.sportService.findSportById(sportId));
			meeting.setId(meetingId);
			model.put("meeting", meeting);
			model.put("numbers", IntStream.range(2, 51).boxed().collect(Collectors.toList()));
			model.put("numberPlayers", meeting.getNumberOfPlayers());
			return "meetings/updateMeetingForm";
		} else {

			BeanUtils.copyProperties(meeting, meetingToUpdate, "id", "sport", "meetingCreator", "participants",
					"creationDate");
			this.meetingService.save(meetingToUpdate);
			model.addAttribute("message", "¡Quedada actualizada correctamente!");
			return "redirect:/sports/" + sportId + "/meetings";
		}

	}

	@GetMapping("/sports/{sportId}/meetings")
	public String listMeetings(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			Principal principal) {
		Usuario u = this.usuarioService.usuarioLogueado(principal.getName());
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Collection<Meeting> meetings = this.meetingService.listMeetingsBySport(sportId);
		Sport sport = this.sportService.findSportById(sportId);
		model.addAttribute("meetings", meetings);
		model.addAttribute("deporte", sportId);
		model.addAttribute("nombreDeporte", sport.getName());
		model.addAttribute("usuario_logueado", u);
		return "meetings/listMeeting";
	}

	@CachePut(value = "meeting")
	@GetMapping("/sports/{sportId}/meetings/{meetingId}")
	public String meetingDetails(final ModelMap model, @PathVariable("meetingId") final Integer meetingId,
			final Principal principal,@PathVariable("sportId") final Integer sportId) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		model.addAttribute("meeting", meeting);
		Boolean b = true;
		Boolean estaLlena = false;
		Usuario u = this.usuarioService.usuarioLogueado(principal.getName());
		List<Usuario> usuarios = meeting.getParticipants();
		if(sportId>20) {
			return "error-500";
		}
		if (meeting.getMeetingCreator().equals(u) && meeting.getParticipants().contains(u)) {
			model.put("esCreador", true);
			if (u.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
				model.put("puedeEliminar", true);
			}
		}
		if (!usuarios.contains(u)) {
			b = false;
			
		} else {
			model.put("leave", true);

		}
		model.addAttribute("sport", meeting.getSport());
		if (meeting.getNumberOfPlayers() <= meeting.getParticipants().size()) {
			estaLlena = true;
		}
		if (meeting.getNumberOfPlayers() <= meeting.getParticipants().size() && meeting.getParticipants().contains(u)) {
			estaLlena = true;
			b = true;
		}

		model.addAttribute("existe", b);
		model.addAttribute("estaLlena", estaLlena);
		model.addAttribute("logged_user", u);
		model.addAttribute("hayParticipantes", meeting.getParticipants().size() > 0);
		model.addAttribute("chatId", this.chatService.findChatIdByMeetingId(meetingId));
		model.addAttribute("userId", u.getId());

		return "meetings/meetingDetails";
	}

	@GetMapping("/meetings/{meetingId}/join")
	public String meetingJoin(final ModelMap model, @PathVariable("meetingId") final Integer meetingId,
			final Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		Usuario u = this.usuarioService.usuarioLogueado(principal.getName());

		if (!meeting.getParticipants().contains(u) && meeting.getNumberOfPlayers() > meeting.getParticipants().size()) {

			List<Usuario> list = meeting.getParticipants();
			list.add(u);
			meeting.setParticipants(list);

			if (!meeting.getParticipants().contains(meeting.getMeetingCreator())) {
				meeting.setMeetingCreator(u);
			}

			this.meetingService.save(meeting);
			u.setPuntos(u.getPuntos() + 5);
			this.usuarioService.saveUsuarioAlreadyRegistered(u);

		}

		return this.meetingDetails(model, meetingId, principal, invitacionesTorneos);
	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}/leave")
	public String leaveMeeting(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("meetingId") final Integer meetingId, final Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Meeting meeting = this.meetingService.findMeetingById(meetingId);

		model.addAttribute("meeting", meeting);

		List<Usuario> usuarios = meeting.getParticipants();
		Usuario usuario = this.userService.usuarioLogueado(principal.getName());

		usuarios.remove(usuario);
		this.meetingService.save(meeting);
		if(sportId>20) {
			return "error-500";
		}
		if (meeting.getMeetingCreator().equals(usuario)) {
			Integer puntos = usuario.getPuntos() - 7;
			usuario.setPuntos(puntos);
			this.userService.saveUsuario(usuario);

			if (usuarios.size() == 0) {
				invitationService.deleteInvitationsByMeetingId(meetingId);
				return "redirect:/sports/" + meeting.getSport().getId() + "/meetings/" + meetingId;
			} else {
				meeting.setMeetingCreator(usuarios.get(0));
				this.meetingService.save(meeting);
				Integer puntos2 = usuarios.get(0).getPuntos() + 2;
				usuarios.get(0).setPuntos(puntos2);
				userService.saveUsuario(usuarios.get(0));
			}

		} else {
			Integer puntos = usuario.getPuntos() - 5;
			usuario.setPuntos(puntos);
			this.userService.saveUsuario(usuario);
		}
		return "redirect:/sports/" + sportId + "/meetings/" + meetingId;

	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}/{userId}/delete")
	public String deleteMeetingPlayer(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("meetingId") final Integer meetingId, @PathVariable("userId") final Integer userId,
			final Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Meeting meeting = this.meetingService.findMeetingById(meetingId);

		List<Usuario> usuarios = meeting.getParticipants();
		Usuario usuario = this.userService.usuarioLogueado(principal.getName());
		Usuario deletedUser = this.userService.findUserById(userId);

		model.addAttribute("meeting", meeting);
    
		if(sportId>20) {
			return "error-500";
		}
    
		if (usuarios.contains(usuario)) {
			model.put("leave", true);
		}

		if (!meeting.getMeetingCreator().getUser().getUsername().equals(principal.getName())) {
			model.put("loggedUserIsNotTheMeetingCreator", true);
			return "error-403";

		} else {
			if (!deletedUser.equals(meeting.getMeetingCreator())) {
				if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
					return "error-403";
				}

				if (meeting.getMeetingCreator().equals(usuario)) {
					model.put("esCreador", true);
					if (usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
						model.put("puedeEliminar", true);
					}
				}
				Boolean b = true;
				Boolean estaLlena = false;
				if (!meeting.getParticipants().contains(usuario)) {
					b = false;
				}
				model.addAttribute("sport", meeting.getSport());
				if (meeting.getNumberOfPlayers() <= meeting.getParticipants().size()) {
					estaLlena = true;
				}
				if (meeting.getNumberOfPlayers() <= meeting.getParticipants().size()
						&& meeting.getParticipants().contains(usuario)) {
					estaLlena = true;
					b = true;
				}
				model.addAttribute("existe", b);
				model.addAttribute("estaLlena", estaLlena);
				model.addAttribute("logged_user", usuario);

				usuarios.removeIf(u -> deletedUser.equals(u));
				this.meetingService.save(meeting);
				Integer puntos = deletedUser.getPuntos() - 5;
				deletedUser.setPuntos(puntos);
				this.userService.saveUsuario(deletedUser);
				model.put("sport", sportService.findSportById(sportId));
				model.put("eliminado", true);
				return "meetings/meetingDetails";
			} else {

				model.put("userToDeleteIsMeetingCreator", true);
				return "error-403";
			}

		}

	}

}
