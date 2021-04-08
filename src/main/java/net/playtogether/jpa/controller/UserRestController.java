package net.playtogether.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

 
 
@Controller
@RequestMapping("/")
public class UserRestController {

    
    @RequestMapping("/")
    public String home(ModelMap model) {
    	
        return "welcome";
    }
    
 
}