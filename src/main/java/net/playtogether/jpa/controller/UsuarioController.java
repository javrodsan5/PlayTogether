package net.playtogether.jpa.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
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

	@GetMapping(value = "/registro")
	public String initCreationForm(ModelMap model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "users/register";
	}

	@PostMapping(value = "/registro")
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
			UserType usrType = this.userTypeService.findUserTypeById(1);
			usuario.setType(usrType);
			usuario.setPuntos(0);
			this.usuarioService.saveUsuario(usuario);
			return "redirect:/";
		}
	}

	@GetMapping("/usuarios/{userId}")
	public String userDetails(final ModelMap model, @PathVariable("userId") final Integer userId, Principal principal) {
		Usuario usuario = this.usuarioService.findUserById(userId);
		model.addAttribute("user", usuario);
		Usuario user = this.usuarioService.findByUsername(principal.getName());

		if (usuario.getId().equals(user.getId())) {

			return "redirect:/myprofile";
		} else {
			return "users/userDetails";
		}
	}

	@GetMapping("/myprofile")
	public String userProfile(final ModelMap model, Principal principal) {
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("puntos",user.getPuntos());
		Integer quedadas = user.getMeetings().size();
		Integer torneos = user.getTeams().size();
		int[] datos = {quedadas,torneos};
		String datos1 = Arrays.toString(datos);
		model.addAttribute("quedadasTorneos",datos1.replace(" ",""));
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		List<Integer> quedadasPorMesList = this.usuarioService.findMeetingByMonth(user.getId(),year);
		List<Integer> torneosPorMesList = this.usuarioService.findChampionshipByMonth(user.getId(),year);
		int[] quedadasPorMes = getEventoPorMes(quedadasPorMesList);
		int[] torneosPorMes = getEventoPorMes(torneosPorMesList);
		String datos2 = Arrays.toString(quedadasPorMes);
		String datos3 = Arrays.toString(torneosPorMes);
		model.addAttribute("quedadasPorMes",datos2.replace(" ",""));
		model.addAttribute("torneosPorMes",datos3.replace(" ",""));
		return "users/userProfile";
	}

	@GetMapping("/myprofile/{userId}/edit")
	public String initUpdateUsuario(ModelMap model, @PathVariable("userId") Integer userId, Principal principal) {
		Usuario usuario = this.usuarioService.findUserById(userId);
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.put("usuario", usuario);
		if (usuario.getId().equals(user.getId())) {

			return "users/updateUser";
		} else {
			return "error-403";
		}
	}

	@PostMapping("/myprofile/{userId}/edit")
	public String postUpdateMeeting(@Valid Usuario usuario, BindingResult result, ModelMap model,
			@PathVariable("userId") Integer userId) {
		Usuario usuarioToUpdate = this.usuarioService.findUserById(userId);

		if (!usuario.getCorreo().equals(usuarioToUpdate.getCorreo())
				&& usuarioService.checkCorreoExists(usuario.getCorreo())) {
			result.addError(new FieldError("usuario", "correo", "El correo ya está registrado"));
		}

		if (!usuario.getPhone().equals(usuarioToUpdate.getPhone())
				&& usuarioService.checkCorreoExists(usuario.getPhone())) {
			result.addError(new FieldError("usuario", "correo", "El teléfono ya está registrado"));
		}

		if (result.hasErrors()) {

			model.put("usuario", usuario);
			return "users/updateUser";
		} else {

			BeanUtils.copyProperties(usuario, usuarioToUpdate, "id", "user.username", "meetings", "teams", "type",
					"statistics", "payment", "puntos");
			this.usuarioService.saveUsuario(usuarioToUpdate);
			model.addAttribute("message", "¡Cuenta actualizada correctamente!");
			return "redirect:/myprofile/" + userId;
		}

	}
	
	@GetMapping("/myprofile/championshipsRecord")
	public String championshipsRecord(final ModelMap model, Principal principal) {
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		List<Championship> championships = usuario.getTeams().stream().map(t -> t.getChampionship()).distinct()
				.collect(Collectors.toList());
		if (championships.size() <= 0) {
			model.put("noRecords", true);
		}
		model.addAttribute("championships", championships);
		return "users/championshipRecord";
	}
	
	@GetMapping("/myprofile/meetingsRecord")
	public String meetingsRecord(final ModelMap model, Principal principal) {
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		List<Meeting> meetings = usuario.getMeetings().stream().limit(10).collect(Collectors.toList());
		if (meetings.size() <= 0) {
			model.put("noRecords", true);
		}
		model.addAttribute("meetings", meetings);

		return "users/meetingsRecord";
	}
	
	@GetMapping(value = "/myprofile/stats")
	public String getStats(ModelMap model,Principal principal) {
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		model.addAttribute("puntos",usuario.getPuntos());
		Integer quedadas = usuario.getMeetings().size();
		Integer torneos = usuario.getTeams().size();
		int[] datos = {quedadas,torneos};
		String datos1 = Arrays.toString(datos);
		model.addAttribute("quedadasTorneos",datos1.replace(" ",""));
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		List<Integer> quedadasPorMesList = this.usuarioService.findMeetingByMonth(usuario.getId(),year);
		List<Integer> torneosPorMesList = this.usuarioService.findChampionshipByMonth(usuario.getId(),year);
		int[] quedadasPorMes = getEventoPorMes(quedadasPorMesList);
		int[] torneosPorMes = getEventoPorMes(torneosPorMesList);
		String datos2 = Arrays.toString(quedadasPorMes);
		String datos3 = Arrays.toString(torneosPorMes);
		model.addAttribute("quedadasPorMes",datos2.replace(" ",""));
		model.addAttribute("torneosPorMes",datos3.replace(" ",""));
		return "users/charts";
	}
	
	public int[] getEventoPorMes(List<Integer> eventoList) {
		int contadorEnero=0;
		int contadorFebrero=0;
		int contadorMarzo=0;
		int contadorAbril=0;
		int contadorMayo=0;
		int contadorJunio=0;
		int contadorJulio=0;
		int contadorAgosto=0;
		int contadorSeptiembre=0;
		int contadorOctubre=0;
		int contadorNoviembre=0;
		int contadorDiciembre=0;
		
		for (Integer i:eventoList) {
			System.out.println(i);
		    if(i==1) {
		    	contadorEnero++;
		    }else if(i==2) {
		    	contadorFebrero++;
		    }else if(i==3) {
		    	contadorMarzo++;
		    }else if(i==4) {
		    	contadorAbril++;
		    }else if(i==5) {
		    	contadorMayo++;
		    }else if(i==6) {
		    	contadorJunio++;
		    }else if(i==7) {
		    	contadorJulio++;
		    }else if(i==8) {
		    	contadorAgosto++;
		    }else if(i==9) {
		    	contadorSeptiembre++;
		    }else if(i==10) {
		    	contadorOctubre++;
		    }else if(i==11) {
		    	contadorNoviembre++;
		    }else if(i==12) {
		    	contadorDiciembre++;
		    }
		}
		
		int[] arr =  {contadorEnero,contadorFebrero,contadorMarzo,contadorAbril,contadorMayo,contadorJunio,contadorJulio,contadorAgosto,contadorSeptiembre,contadorOctubre,contadorNoviembre,contadorDiciembre};
		
		return arr;
	}

	@GetMapping("/clasification")
	public String usersClasification(ModelMap model, Principal principal) {
		List<Usuario> topUsuarios = usuarioService.findTopUsuarios().stream().limit(10).collect(Collectors.toList());
		Usuario usuario = usuarioService.usuarioLogueado(principal.getName());
		Integer posicion = 0;
		for (int i = 0; i < topUsuarios.size(); i++) {
			if (topUsuarios.get(i).equals(usuario)) {
				posicion = i + 1;
				break;
			}
		}
		model.addAttribute("puntos", usuario.getPuntos());
		model.addAttribute("posicion", posicion);
		model.addAttribute("topUsuarios", topUsuarios);
		return "users/clasification";
	}
}
