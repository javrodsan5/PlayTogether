package net.playtogether.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UserTypeService userTypeService;

	@InitBinder("usuario")
	public void initUsuariotBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new UsuarioValidator());
	}

	@GetMapping(value = "/usuarios/new")
	public String initCreationForm(ModelMap model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "users/register";
	}

	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result) {
		
		if (usuarioService.checkCorreoExists(usuario.getCorreo())) {
			result.addError(new FieldError("usuario", "correo", "El correo ya está registrado"));
		}
		
		if (usuarioService.checkPhoneExists(usuario.getPhone())) {
			result.addError(new FieldError("usuario", "phone", "El teléfono ya está registrado"));
		}
		
		if (usuarioService.checkUsernameExists(usuario.getUser().getUsername())) {
			result.addError(new FieldError("usuario", "user.username", "El nombre de usuario ya está en uso"));
		}

		if (result.hasErrors()) {
			return "users/register";
		} else {
			UserType usrType= this.userTypeService.findUserTypeById(1);
			usuario.setType(usrType);
			usuario.setPuntos(0);
			this.usuarioService.saveUsuario(usuario);
			return "redirect:/";
		}
	}
	
    @GetMapping("/usuarios/{userId}")
	public String userDetails(final ModelMap model, @PathVariable("userId") final Integer userId) {
		Usuario usuario = this.usuarioService.findUserById(userId);
		model.addAttribute("user", usuario);


		return "users/userDetails";
	}
    
    @GetMapping("/myprofile/{userId}")
	public String userProfile(final ModelMap model, @PathVariable("userId") final Integer userId) {
		Usuario usuario = this.usuarioService.findUserById(userId);
		model.addAttribute("user", usuario);


		return "users/userProfile";
	}
}
