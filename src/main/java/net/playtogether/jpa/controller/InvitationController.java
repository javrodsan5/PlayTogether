
package net.playtogether.jpa.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.InvitationService;

@Controller
public class InvitationController {

	@Autowired
	InvitationService invitationService;
	
	@Autowired
	ChampionshipService championshipService;


	@InitBinder("invitation")
	public void initInvitationBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new InvitationValidator());
	}
	

	@GetMapping("/invitations/team/{teamId}")
	public String searchParticipants(final ModelMap model, @PathVariable("teamId") final Integer teamId) {
		Team team = this.championshipService.findTeamId(teamId);
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);
		model.put("search", "");
		return "invitations/addParticipantsForm";
	}


	@GetMapping("/invitations/team/{teamId}/send_invitation")
	public String initSendInvitation(@RequestParam(value = "search", required = false) final String search,
			final ModelMap model, @PathVariable("teamId") final Integer teamId) {
		List<User> searched_users = new ArrayList<>();
		Team team = this.championshipService.findTeamId(teamId);
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);

		if (team.getParticipants().size() >= team.getTeamSize()) {
			model.put("limitedTeamSize", true);
			return "invitations/addParticipantsForm";
		}

		try {
			searched_users = this.championshipService.findUserByNameOrUsername(search);
		} catch (Exception e) {
			model.put("noUser", true);
			return "invitations/addParticipantsForm";
		}
		this.deleteRepeatedUsers(team, searched_users);
		if (searched_users.isEmpty()) {
			model.put("notMoreUsers", true);
			return "invitations/addParticipantsForm";
		}
		model.put("searched_users", searched_users);
		return "invitations/listSearchedUsers";
	}

	@PostMapping("/invitations/team/{teamId}/send_invitation")
	public String postSendInvitation(@ModelAttribute("selected_participant") final String selected_participant,
			@PathVariable("teamId") final int teamId, final BindingResult result, final ModelMap model) {
		
		User participant = this.championshipService.findUsersById(Integer.parseInt(selected_participant));
		Team team = this.championshipService.findTeamId(teamId);
		
		if (!team.getChampionship().getFinishDate().isBefore(LocalDate.now())) {
			if (isNotInChampionshipTeam(team.getChampionship().getId(), participant)) {	
				Boolean isNotInvitedYetToChampionshipTeam = this.invitationService.isNotInvitedYetToChampionshipTeam(teamId, participant.getId());			
				
				if (isNotInvitedYetToChampionshipTeam) {
					Invitation invitation = new Invitation();
					invitation.setTeam(team);
					invitation.setReceiver(participant);
					this.invitationService.save(invitation);
				} else {
					model.put("alreadyInvited", true);
				}
			} else {
				model.put("alreadyInChampionshipTeam", true);
			}
		} else {
			model.put("championshipIsFinished", true);
		}
		model.put("team_participants", team.getParticipants());
		model.put("teamSize", team.getTeamSize());
		model.put("team", team);
		return "invitations/addParticipantsForm";
	}

	private Boolean isNotInChampionshipTeam(Integer championshipId, User user) {
		Boolean isNotInChampionshipTeam = this.championshipService.findTeamsByChampionshipId(championshipId)
				.stream().flatMap(t -> t.getParticipants().stream()).noneMatch(u -> u.equals(user));
		return isNotInChampionshipTeam;
	}
	
	private void deleteRepeatedUsers(final Team team, final List<User> searchedUsers) {
		for (int i = 0; i < searchedUsers.size(); i++) {
			if (team.getParticipants().contains(searchedUsers.get(i))) {
				searchedUsers.remove(i);
				i--;
			}
		}
	}
	
	
	@GetMapping("/invitations/championshipInvitations")
	public String listChampionshipInvitations(final ModelMap model) {
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUserName("Antonio");	// CAMBIAR AL NAME DEL PRINCIPAL CUANDO SE PUEDA
		
		List<Invitation> expiredInvitations = invitations.stream().filter(i -> i.getTeam().getChampionship().getFinishDate().isBefore(LocalDate.now())).collect(Collectors.toList());
		
		if (expiredInvitations.size() > 0) {
			expiredInvitations.stream().forEach(i -> this.invitationService.delete(i.getId()));
			invitations.removeAll(expiredInvitations);
		}
		
		model.addAttribute("invitations", invitations);
		model.addAttribute("areTeamInvitations", true);
		return "invitations/listInvitations";
	}

	@GetMapping("/invitations/{invitationId}/")
	public String initAcceptInvitation(@RequestParam(value = "accepted", required = true) final String accepted,
			final ModelMap model, @PathVariable("invitationId") final Integer invitationId) {
		
		// COMPROBAR QUE USUARIO NO HA ENTRADO POR URL (AUTENTICIDAD DE USUARIO, EVITAR QUE OTRO USUARIO ACEPTE LA INVITACION)
		Invitation invitation = this.invitationService.findById(invitationId);
		
		if (invitation != null && accepted.equals("true")) {
			
			if (invitation.getTeam().getParticipants().size() < invitation.getTeam().getTeamSize()) {
				Boolean isNotInChampionshipTeam =isNotInChampionshipTeam(invitation.getTeam().getChampionship().getId(), invitation.getReceiver());
				
				if (isNotInChampionshipTeam) {
					invitation.getTeam().getParticipants().add(invitation.getReceiver());			
					this.championshipService.save(invitation.getTeam());
					this.invitationService.delete(invitationId);
					model.put("joined", true);
					
				} else {
					this.invitationService.delete(invitationId);
					model.put("isInChampionshipTeam", true);
				}
				
			} else {
				model.put("teamIsFull", true);
				this.invitationService.delete(invitationId);
			}
			
		} else if (invitation != null && accepted.equals("false")) {
			this.invitationService.delete(invitationId);
			model.put("notJoined", true);
		} 
		
		Collection<Invitation> invitations = this.invitationService.findChampionshipInvitationsByUserName("Antonio");	// CAMBIAR AL NAME DEL PRINCIPAL CUANDO SE PUEDA
		model.addAttribute("invitations", invitations);
		model.addAttribute("areTeamInvitations", true);
		return "invitations/listInvitations";
	}

}
