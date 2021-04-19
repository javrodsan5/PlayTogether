
package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class InvitationController {

	@Autowired
	InvitationService invitationService;
	
	@Autowired
	ChampionshipService championshipService;
	
	@Autowired
	MeetingService meetingService;

	@Autowired
	UsuarioService userService;


	@InitBinder("invitation")
	public void initInvitationBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new InvitationValidator());
	}
	

	@GetMapping("/invitations/team/{teamId}")
	public String searchParticipants(final ModelMap model, @PathVariable("teamId") final Integer teamId, Principal principal) {
		Team team = this.championshipService.findTeamId(teamId);
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		
		if (!team.getUser().getUser().getUsername().equals(principal.getName()) || (team.getParticipants().size() >= team.getTeamSize())) {
			return "redirect:/sports/" + team.getChampionship().getSport().getId() + "/championships/" + team.getChampionship().getId();
		}
		
		if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			return "error-403";
		}
		
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);
		model.put("search", "");
		model.put("teamView", true);
		return "invitations/addParticipantsForm";
	}

	@GetMapping("/invitations/team/{teamId}/send_invitation")
	public String initSendInvitation(@RequestParam(value = "search", required = false) final String search,
			final ModelMap model, @PathVariable("teamId") final Integer teamId, Principal principal) {
		List<Usuario> searched_users = new ArrayList<>();
		Team team = this.championshipService.findTeamId(teamId);
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		
		if (!team.getUser().getUser().getUsername().equals(principal.getName()) || (team.getParticipants().size() >= team.getTeamSize())) {
			return "redirect:/sports/" + team.getChampionship().getSport().getId() + "/championships/" + team.getChampionship().getId();
		}
		
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);
		model.put("teamView", true);
		
		if (!team.getUser().getUser().getUsername().equals(principal.getName())) {
			model.put("loggedUserIsNotTheTeamOwner", true);
			return "invitations/addParticipantsForm";
		}
		
		if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			return "error-403";
		}
		
		if (search == null || search.equals("")) {
			return "invitations/addParticipantsForm";
		}

		if (team.getParticipants().size() >= team.getTeamSize()) {
			model.put("limitedTeamSize", true);
			return "invitations/addParticipantsForm";
		}
		searched_users = this.championshipService.findUserByNameOrUsername(search);
		
		if (searched_users.isEmpty()) {
			model.put("noUser", true);
			return "invitations/addParticipantsForm";
		}

		this.deleteRepeatedUsers(team, searched_users);
		if (searched_users.isEmpty()) {
			model.put("notMoreUsers", true);
			return "invitations/addParticipantsForm";
		}
		
		Usuario user = this.userService.findByUsername(principal.getName());
		if (searched_users.contains(user)) {
			searched_users.remove(user);
		}
		
		model.put("searched_users", searched_users);
		model.put("teamView", true);
		return "invitations/listSearchedUsers";
	}

	@PostMapping("/invitations/team/{teamId}/send_invitation")
	public String postSendInvitation(@ModelAttribute("selected_participant") final String selected_participant,
			@PathVariable("teamId") final int teamId, final BindingResult result, final ModelMap model, Principal principal) {
		
		Usuario participant = this.championshipService.findUsersById(Integer.parseInt(selected_participant));
		Team team = this.championshipService.findTeamId(teamId);
		
		if (!team.getUser().getUser().getUsername().equals(principal.getName()) || (team.getParticipants().size() >= team.getTeamSize())) {
			return "redirect:/sports/" + team.getChampionship().getSport().getId() + "/championships/" + team.getChampionship().getId();
		}
		
		if (team.getUser().getUser().getUsername().equals(principal.getName())) {
			if (!principal.getName().equals(participant.getUser().getUsername())) {
				if (!team.getChampionship().getFinishDate().isBefore(LocalDate.now())) {
					if (isNotInChampionshipTeam(team.getChampionship().getId(), participant)) {	
						Boolean isNotInvitedYetToChampionshipTeam = this.invitationService.isNotInvitedYetToChampionshipTeam(teamId, participant.getId());			
						
						if (isNotInvitedYetToChampionshipTeam) {
							Invitation invitation = new Invitation();
							invitation.setTeam(team);
							invitation.setReceiver(participant);
							this.invitationService.save(invitation);
							model.put("invited", true);
						} else {
							model.put("alreadyInvited", true);
						}
					} else {
						model.put("alreadyInChampionshipTeam", true);
					}
				} else {
					model.put("championshipIsFinished", true);
				}
			} else {
				model.put("cantSelfInvite", true);
			}
		} else {
			model.put("loggedUserIsNotTheTeamOwner", true);
		}
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);
		model.put("teamView", true);
		return "invitations/addParticipantsForm";
	}

	
	private Boolean isNotInChampionshipTeam(Integer championshipId, Usuario user) {
		Boolean isNotInChampionshipTeam = this.championshipService.findTeamsByChampionshipId(championshipId)
				.stream().flatMap(t -> t.getParticipants().stream()).noneMatch(u -> u.equals(user));
		return isNotInChampionshipTeam;
	}
	
	private void deleteRepeatedUsers(final Team team, final List<Usuario> searched_users) {
		for (int i = 0; i < searched_users.size(); i++) {
			if (team.getParticipants().contains(searched_users.get(i))) {
				searched_users.remove(i);
				i--;
			}
		}
	}
	
	
	@GetMapping("/invitations/championshipInvitations")
	public String listChampionshipInvitations(final ModelMap model, Principal principal) {	
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUsername(principal.getName());	
		
		List<Invitation> expiredInvitations = invitations.stream().filter(i -> i.getTeam().getChampionship().getFinishDate().isBefore(LocalDate.now())).collect(Collectors.toList());		
		expiredInvitations.stream().forEach(i -> this.invitationService.delete(i.getId()));
		invitations.removeAll(expiredInvitations);
		
		Usuario usuario = this.userService.findByUsername(principal.getName());
		List<Championship> participating = usuario.getTeams().stream().map(t -> t.getChampionship()).distinct().collect(Collectors.toList());
		
		expiredInvitations = invitations.stream().filter(i -> participating.contains(i.getTeam().getChampionship())).collect(Collectors.toList());		
		expiredInvitations.stream().forEach(i -> this.invitationService.delete(i.getId()));
		invitations.removeAll(expiredInvitations);
		
		model.addAttribute("invitations", invitations);
		model.addAttribute("areTeamInvitations", true);
		return "invitations/listInvitations";
	}

	@GetMapping("/invitations/championshipInvitations/{invitationId}/")
	public String initAcceptInvitation(@RequestParam(value = "accepted", required = true) final String accepted,
			final ModelMap model, @PathVariable("invitationId") final Integer invitationId, Principal principal) {
		Boolean hasPermission = false;
		Invitation invitation = this.invitationService.findById(invitationId);
		
		if (invitation != null) {
			hasPermission = principal.getName().equals(invitation.getReceiver().getUser().getUsername());
			
			if (!hasPermission) {
				model.put("noPermission", true);
			} 
		} else {
			model.put("noInvitation", true);
		}
			
		if (hasPermission && invitation != null && accepted.equals("true")) {
			
			if (invitation.getTeam().getParticipants().size() < invitation.getTeam().getTeamSize()) {
				Boolean isNotInChampionshipTeam =isNotInChampionshipTeam(invitation.getTeam().getChampionship().getId(), invitation.getReceiver());
				
				if (isNotInChampionshipTeam) {

					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium"))) {
						return "redirect:/pay/championship/" + invitation.getTeam().getChampionship().getId() + "/team/"+invitation.getTeam().getId()+"?invitationId="+invitationId;
					} else {
						invitation.getTeam().getParticipants().add(invitation.getReceiver());			
						this.championshipService.save(invitation.getTeam());
            
            Usuario usuario = invitation.getReceiver();
		        usuario.setPuntos(usuario.getPuntos() + 5); 
		        this.userService.saveUsuarioAlreadyRegistered(usuario);
		            
						this.invitationService.delete(invitationId);
						model.put("joined", true);
					}		

				} else {
					this.invitationService.delete(invitationId);
					model.put("isInChampionshipTeam", true);
				}
				
			} else {
				model.put("teamIsFull", true);
				this.invitationService.delete(invitationId);
			}
			
		} else if (hasPermission && invitation != null && accepted.equals("false")) {
			this.invitationService.delete(invitationId);
			model.put("notJoined", true);
		} 
		
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUsername(principal.getName());	
		model.addAttribute("invitations", invitations);
		model.addAttribute("areTeamInvitations", true);
		return "invitations/listInvitations";
	}

	
	

	@GetMapping("/invitations/meeting/{meetingId}")
	public String searchParticipantsMeeting(final ModelMap model, @PathVariable("meetingId") final Integer meetingId, Principal principal) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		
		if (!meeting.getMeetingCreator().getUser().getUsername().equals(principal.getName()) || (meeting.getParticipants().size() >= meeting.getNumberOfPlayers())) {
			return "redirect:/sports/" + meeting.getSport().getId() + "/meetings/" + meetingId;
		}
		
		if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			return "error-403";
		}
		
		model.put("meeting_participants", meeting.getParticipants());
		model.put("meetingSize", meeting.getNumberOfPlayers());
		model.put("meeting", meeting);
		model.put("search", "");
		model.put("meetingView", true);
		return "invitations/addParticipantsForm";
	}

	@GetMapping("/invitations/meeting/{meetingId}/send_invitation")
	public String initSendInvitationMeeting(@RequestParam(value = "search", required = false) final String search,
			final ModelMap model, @PathVariable("meetingId") final Integer meetingId, Principal principal) {
		List<Usuario> searched_users = new ArrayList<>();
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		model.put("meeting_participants", meeting.getParticipants());
		model.put("meetingSize", meeting.getNumberOfPlayers());
		model.put("meeting", meeting);
		model.put("meetingView", true);
		
		if (!meeting.getMeetingCreator().getUser().getUsername().equals(principal.getName()) || (meeting.getParticipants().size() >= meeting.getNumberOfPlayers())) {
			return "redirect:/sports/" + meeting.getSport().getId() + "/meetings/" + meetingId;
		}
		
		if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			return "error-403";
		}
		
		if (search == null || search.equals("")) {
			return "invitations/addParticipantsForm";
		}

		if (meeting.getParticipants().size() >= meeting.getNumberOfPlayers()) {
			model.put("limitedMeetingSize", true);
			return "invitations/addParticipantsForm";
		}
		searched_users = this.championshipService.findUserByNameOrUsername(search);

		if (searched_users.isEmpty()) {
			model.put("noUser", true);
			return "invitations/addParticipantsForm";
		}
		
		this.deleteRepeatedUsersMeeting(meeting, searched_users);
		if (searched_users.isEmpty()) {
			model.put("notMoreUsers", true);
			return "invitations/addParticipantsForm";
		}
		
		Usuario user = this.userService.findByUsername(principal.getName());
		if (searched_users.contains(user)) {
			searched_users.remove(user);
		}
		
		model.put("searched_users", searched_users);
		return "invitations/listSearchedUsers";
	}

	@PostMapping("/invitations/meeting/{meetingId}/send_invitation")
	public String postSendInvitationMeeting(@ModelAttribute("selected_participant") final String selected_participant,
			@PathVariable("meetingId") final int meetingId, final BindingResult result, final ModelMap model, Principal principal) {	
		
		Usuario participant = this.championshipService.findUsersById(Integer.parseInt(selected_participant));
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		
		if (!meeting.getMeetingCreator().getUser().getUsername().equals(principal.getName()) || (meeting.getParticipants().size() >= meeting.getNumberOfPlayers())) {
			return "redirect:/sports/" + meeting.getSport().getId() + "/meetings/" + meetingId;
		}
		
		if (meeting.getMeetingCreator().getUser().getUsername().equals(principal.getName())) {
			if (!principal.getName().equals(participant.getUser().getUsername())) {
				if (!meeting.getDate().isBefore(LocalDateTime.now())) {
					if (isNotInMeeting(meeting, participant)) {	
						Boolean isNotInvitedYetToMeeting = this.invitationService.isNotInvitedYetToMeeting(meetingId, participant.getId());			
						
						if (isNotInvitedYetToMeeting) {
							Invitation invitation = new Invitation();
							invitation.setMeeting(meeting);
							invitation.setReceiver(participant);
							this.invitationService.save(invitation);
							model.put("invited", true);
						} else {
							model.put("alreadyInvited", true);
						}
					} else {
						model.put("alreadyInMeetingTeam", true);
					}
				} else {
					model.put("meetingIsFinished", true);
				}
			} else {
				model.put("cantSelfInvite", true);
			}
		} else {
			model.put("loggedUserIsNotTheMeetingOwner", true);
		}
		model.put("meeting_participants", meeting.getParticipants());
		model.put("meetingSize", meeting.getNumberOfPlayers());
		model.put("meeting", meeting);
		model.put("meetingView", true);
		return "invitations/addParticipantsForm";
	}

	
	private void deleteRepeatedUsersMeeting(final Meeting meeting, final List<Usuario> searched_users) {
		for (int i = 0; i < searched_users.size(); i++) {
			if (meeting.getParticipants().contains(searched_users.get(i))) {
				searched_users.remove(i);
				i--;
			}
		}
	}
	
	private Boolean isNotInMeeting(Meeting meeting, Usuario user) {
		Boolean isNotInChampionshipTeam = meeting.getParticipants().stream().noneMatch(u -> u.equals(user));
		return isNotInChampionshipTeam;
	}

	
	@GetMapping("/invitations/meetingInvitations")
	public String listMeetingInvitations(final ModelMap model, Principal principal) {	
		Collection<Invitation> invitations = this.invitationService.findMeetingInvitationsByUsername(principal.getName());	
		
		List<Invitation> expiredInvitations = invitations.stream().filter(i -> i.getMeeting().getDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());		
		expiredInvitations.stream().forEach(i -> this.invitationService.delete(i.getId()));
		invitations.removeAll(expiredInvitations);
		
		Usuario usuario = this.userService.findByUsername(principal.getName());
		List<Meeting> participating = usuario.getMeetings(); 
		
		expiredInvitations = invitations.stream().filter(i -> participating.contains(i.getMeeting())).collect(Collectors.toList());		
		expiredInvitations.stream().forEach(i -> this.invitationService.delete(i.getId()));
		invitations.removeAll(expiredInvitations);
		
		model.addAttribute("invitations", invitations);
		model.addAttribute("areMeetingInvitations", true);
		return "invitations/listInvitations";
	}

	@GetMapping("/invitations/meetingInvitations/{invitationId}/")
	public String initAcceptMeetingInvitation(@RequestParam(value = "accepted", required = true) final String accepted,
			final ModelMap model, @PathVariable("invitationId") final Integer invitationId, Principal principal) {
		Boolean hasPermission = false;
		Invitation invitation = this.invitationService.findById(invitationId);
		
		if (invitation != null) {
			hasPermission = principal.getName().equals(invitation.getReceiver().getUser().getUsername());
			
			if (!hasPermission) {
				model.put("noPermission", true);
			} 
		} else {
			model.put("noInvitation", true);
		}
			
		if (hasPermission && invitation != null && accepted.equals("true")) {

			if (invitation.getMeeting().getParticipants().size() < invitation.getMeeting().getNumberOfPlayers()) {
				Boolean isNotInMeeting =isNotInMeeting(invitation.getMeeting(), invitation.getReceiver());
				
				if (isNotInMeeting) {
					invitation.getMeeting().getParticipants().add(invitation.getReceiver());			
					this.meetingService.save(invitation.getMeeting());
					
					Usuario usuario = invitation.getReceiver();
		            usuario.setPuntos(usuario.getPuntos() + 5); 
		            this.userService.saveUsuarioAlreadyRegistered(usuario);
		            
					
					this.invitationService.delete(invitationId);
					model.put("joined", true);
					
				} else {
					this.invitationService.delete(invitationId);
					model.put("isInMeeting", true);
				}
				
			} else {
				model.put("meetingIsFull", true);
				this.invitationService.delete(invitationId);
			}
			
		} else if (hasPermission && invitation != null && accepted.equals("false")) {
			this.invitationService.delete(invitationId);
			model.put("notJoined", true);
		} 
		
		Collection<Invitation> invitations = this.invitationService.findMeetingInvitationsByUsername(principal.getName());	
		model.addAttribute("invitations", invitations);
		model.addAttribute("areMeetingInvitations", true);
		return "invitations/listInvitations";
	}


	
	
	
}
