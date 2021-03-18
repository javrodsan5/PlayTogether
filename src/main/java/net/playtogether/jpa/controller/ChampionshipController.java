package net.playtogether.jpa.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.activity.InvalidActivityException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.UserRepository;
import net.playtogether.jpa.service.ChampionshipService;

@Controller
public class ChampionshipController {
	
	 	@Autowired
	    ChampionshipService championshipService;
	 
	 	
	 	@GetMapping("/championships/add")
		public String initCreationChampionship(ModelMap model) {
			Championship championship = new Championship();
			model.addAttribute("championship",championship);
			return "championships/createOrUpdateChampionshipForm";
		}

		@PostMapping("/championships/add")
		public String postCreationMeeting(@ModelAttribute("championship") Championship championship, BindingResult result, ModelMap model) {
			if(!result.hasErrors()) {
				Sport sport = new Sport();
				sport.setId((long) 1);
				sport.setName("futbol");
				championship.setSport(sport);
				championshipService.save(championship);
				
				return "redirect:/";
			}else {
				return "championships/createOrUpdateChampionshipForm";
			}
		}
		
		
		@GetMapping("/championships")
		public String listChampionships(ModelMap model) {
			Collection<Championship>championships= this.championshipService.listChampionship();
			model.addAttribute("championships",championships);
			return "championships/listChampionship";
		}
		
		@GetMapping("/championships/{championshipId}")
		public String meetingDetails(ModelMap model, @PathVariable ("championshipId") Integer championshipId) {
			Championship championship= this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championship",championship);
			return "championships/championshipDetails";
		}
	 

}
