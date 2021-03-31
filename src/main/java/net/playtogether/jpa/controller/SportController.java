package net.playtogether.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UserService;

@Controller
public class SportController {
	
	public static final String SPORTS_LISTING = "sports/sportListing";

	
	@Autowired
	SportService sportService;

	@Autowired
	UserService userService;
	
	
	@GetMapping("/sports")
	public String listSports(ModelMap model) {
		model.addAttribute("sports", sportService.findAll());
		return SPORTS_LISTING;
	}

}
