package net.playtogether.jpa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class SportController {
	
	public static final String SPORTS_LISTING = "sports/sportListing";

	
	@Autowired
	SportService sportService;

	@Autowired
	UsuarioService userService;
	
	@Autowired
	InvitationService invitationService;
	
	@CachePut(value="sports")
	@GetMapping("/sports")
	public String listSports(ModelMap model,Principal principal) {
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		}
		model.addAttribute("sports", sportService.findAll());
		return SPORTS_LISTING;
	}

}
