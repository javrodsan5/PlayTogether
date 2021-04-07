package net.playtogether.jpa.controller;

 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import net.playtogether.jpa.repository.UsuarioRepository;

 
 
@Controller
@RequestMapping("/")
public class UserRestController {

    
    @RequestMapping("/")
    public String home(ModelMap model) {
    	
        return "welcome";
    }
    
 
}