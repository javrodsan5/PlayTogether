package net.playtogether.jpa.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.ArrayList;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Usuario;
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

	@InitBinder("meeting")
	public void initMeetingBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new MeetingValidator());
	}

	@GetMapping("/sports/{sportId}/meetings/add")
	public String initCreationMeeting(ModelMap model, @PathVariable("sportId") Integer sportId) {
		Integer listSports = this.sportService.findAll().size();
		if(sportId>0 && sportId<=listSports) {
			model.put("meeting", new Meeting());
			model.put("sportId", sportId);
			model.put("sport", sportService.findSportById(sportId));
			return "meetings/createMeetingForm";
		}else {
			return "error-500";
		}
	}

	@PostMapping("/sports/{sportId}/meetings/add")
	public String postCreationMeeting(@Valid Meeting meeting, BindingResult result, ModelMap model,
			@PathVariable("sportId") Integer sportId,Principal principal) {
		Sport sport = sportService.findSportById(sportId);
		if (result.hasErrors()) {
			model.put("sport", sport);
			model.put("sportId", sportId);
			return "meetings/createMeetingForm";
		} else {
			Usuario usuario =usuarioService.usuarioLogueado(principal.getName());
			meeting.setMeetingCreator(usuario);
			
			List<Usuario> participants = new ArrayList<>();
			participants.add(usuario);
			System.out.println(usuario.getName());
			meeting.setParticipants(participants);
			meeting.setNumberOfPlayers(sport.getNumberOfPlayersInTeam()*2);
			meetingService.save(meeting);
			usuario.setPuntos(usuario.getPuntos()+7);
			usuarioService.saveUsuario(usuario);
			return "redirect:/sports/" + sportId + "/meetings";
		}

	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}/edit")
	public String initUpdateMeeting(ModelMap model, @PathVariable("sportId") Integer sportId,
			@PathVariable("meetingId") Integer meetingId) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		model.put("sport", sportService.findSportById(sportId));
		model.put("meeting", meeting);
		return "meetings/updateMeetingForm";
	}

	@PostMapping("/sports/{sportId}/meetings/{meetingId}/edit")
	public String postUpdateMeeting(@Valid Meeting meeting, BindingResult result, ModelMap model,
			@PathVariable("sportId") Integer sportId, @PathVariable("meetingId") Integer meetingId) {
		if (result.hasErrors()) {
			model.put("sport", sportService.findSportById(sportId));
			model.put("meeting", meeting);
			return "meetings/updateMeetingForm";
		} else {
			Meeting meetingToUpdate = this.meetingService.findMeetingById(meetingId);
			BeanUtils.copyProperties(meeting, meetingToUpdate, "id", "sport", "numberOfPlayers","meetingCreator");
			this.meetingService.save(meetingToUpdate);
			model.addAttribute("message", "¡Quedada actualizada correctamente!");
			return "redirect:/sports/" + sportId + "/meetings";
		}

	}

	@GetMapping("/sports/{sportId}/meetings")
	public String listMeetings(ModelMap model, @PathVariable("sportId") Integer sportId) {
		Collection<Meeting> meetings = this.meetingService.listMeetingsBySport(sportId);
		Sport sport = this.sportService.findSportById(sportId);
		model.addAttribute("meetings", meetings);
		model.addAttribute("deporte", sportId);
		model.addAttribute("nombreDeporte", sport.getName());
		return "meetings/listMeeting";
	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}")
	public String meetingDetails(ModelMap model, @PathVariable("meetingId") Integer meetingId,Principal principal) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		model.addAttribute("meeting", meeting);
		Boolean b = true;
		Boolean estaLlena = false;
		Usuario u = usuarioService.usuarioLogueado(principal.getName());

		if (!meeting.getParticipants().contains(u)) {
			b = false;
		}
		model.addAttribute("sport", meeting.getSport());
		if(meeting.getNumberOfPlayers()<=meeting.getParticipants().size()) {
			estaLlena=true;
		}
			model.addAttribute("existe", b);
			model.addAttribute("estaLlena", estaLlena);
			

		return "meetings/meetingDetails";
	}

	@GetMapping("/meetings/{meetingId}/join")
	public String meetingJoin(ModelMap model, @PathVariable("meetingId") Integer meetingId, Principal principal) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		Usuario u = usuarioService.usuarioLogueado(principal.getName());

		if (!meeting.getParticipants().contains(u) &&
				meeting.getNumberOfPlayers()>meeting.getParticipants().size()) {

			List<Usuario> list = meeting.getParticipants();
			list.add(u);
			meeting.setParticipants(list);

			this.meetingService.save(meeting);
			
			u.setPuntos(u.getPuntos()+5);
			usuarioService.saveUsuario(u);

		}

		return meetingDetails(model, meetingId, principal);
	}

}
