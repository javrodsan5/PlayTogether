package net.playtogether.jpa.controller;

import java.util.List;
import java.util.Optional;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.UserRepository;
 
 
@Controller
@RequestMapping("/")
public class UserRestController {
 
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping("/")
    public String home(ModelMap model) {
    	
    	List<User> usr=userRepository.findAll();
    	model.addAttribute("usr",usr);
        return "welcome";
    }
    
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        userRepository.save(user);
        
        String exit="Su correo ha sido añadido correctamente";
        model.addAttribute("exit",exit);
    	List<User> usr=userRepository.findAll();
    	model.addAttribute("usr",usr);
        return "welcome";
    }
 
}