package net.playtogether.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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



	@GetMapping(value = "/usuarios/new")
	public String initCreationForm(ModelMap model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "users/register";
	}

	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result) {
		
//		if (usuarioService.checkCorreoExists(usuario.getCorreo())) {
//			result.addError(new FieldError("usuario", "correo", "El correo ya existe"));
//		}
//		if (usuarioService.checkUsuarioExists(usuario.getUser().getUsername())) {
//			result.addError(new FieldError("usuario", "user.username", "El nombre de usuario ya está en uso"));
//		}

		if (result.hasErrors()) {
			return "users/register";
		} else {
			UserType usrType= this.userTypeService.findUserTypeById(1).orElse(null);
			usuario.setType(usrType);

			this.usuarioService.saveUsuario(usuario);
			return "redirect:/";
		}
	}
}
