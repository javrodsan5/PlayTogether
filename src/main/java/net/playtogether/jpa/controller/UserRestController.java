package net.playtogether.jpa.controller;

import java.util.List;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.repository.UsuarioRepository;
 
 
@Controller
@RequestMapping("/")
public class UserRestController {
 
    @Autowired
    UsuarioRepository userRepository;
    
    @RequestMapping("/")
    public String home(ModelMap model) {
    	
    	List<Usuario> usr=userRepository.findAll();
    	model.addAttribute("usr",usr);
        return "welcome";
    }
    
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") Usuario user, BindingResult result, ModelMap model) {
        userRepository.save(user);
        
        String exit="Su correo ha sido añadido correctamente";
        model.addAttribute("exit",exit);
    	List<Usuario> usr=userRepository.findAll();
    	model.addAttribute("usr",usr);
        return "welcome";
    }
 
}