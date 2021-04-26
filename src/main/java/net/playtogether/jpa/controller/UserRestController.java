package net.playtogether.jpa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.playtogether.jpa.service.InvitationService;

 
 
@Controller
@RequestMapping("/")
public class UserRestController {

	@Autowired
	InvitationService invitationService;
    
    @RequestMapping("/")
    public String home(ModelMap model,Principal principal) {
    	if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}
        return "welcome";
    }
    
    @RequestMapping("/about-us")
    public String aboutUs(ModelMap model,Principal principal) {
    	if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}
        return "about-us";
    }

	@GetMapping(value = "/terms-and-conditions")
	public String termsAndConditions(ModelMap model, Principal principal) {
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}
		return "terms-and-conditions";
	}

	@GetMapping(value = "/cookies-policy")
	public String cookiesPolicy(ModelMap model, Principal principal) {
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
    	}
		return "cookies-policy";
	}
    
 
}