package net.playtogether.jpa.controller;

import java.util.Collection;

import javax.validation.Valid;

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


import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.SportService;

@Controller
public class ChampionshipController {
	
	 	@Autowired
	    ChampionshipService championshipService;
	 	
		@Autowired
		SportService sportService;
	 
		@InitBinder("championship")
		public void initChampionshipBinder(WebDataBinder dataBinder) {
			dataBinder.setValidator(new ChampionshipValidator());
		}
	 	
	 	@GetMapping("/sports/{sportId}/championships/add")
		public String initCreationChampionship(ModelMap model, @PathVariable("sportId") Integer sportId) {
			Championship championship = new Championship();
			model.addAttribute("championship",championship);
			model.put("deporte", sportId);
			return "championships/createOrUpdateChampionshipForm";
		}

		@PostMapping("/sports/{sportId}/championships/add")
		public String postCreationMeeting(@Valid Championship championship, BindingResult result, ModelMap model,@PathVariable("sportId") Integer sportId) {
			if(!result.hasErrors()) {
				championshipService.save(championship);
				
				return "redirect:/sports/"+sportId+"/championships";
			}else {
			
				model.put("deporte", sportId);
				return "championships/createOrUpdateChampionshipForm";
			}
		}
		
		
		@GetMapping("/sports/{sportId}/championships")
		public String listChampionships(ModelMap model,@PathVariable("sportId") Integer sportId) {
			Collection<Championship>championships= this.championshipService.listChampionshipsBySport(sportId);
			Sport sport = this.sportService.findSportById(sportId);
			model.addAttribute("championships",championships);
			model.addAttribute("deporte",sportId);
			model.addAttribute("nombreDeporte", sport.getName());
			return "championships/listChampionship";
		}
		
		@GetMapping("/sports/{sportId}/championships/{championshipId}")
		public String meetingDetails(ModelMap model, @PathVariable ("championshipId") Integer championshipId) {
			Championship championship= this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championship",championship);
			return "championships/championshipDetails";
		}
	 

}
