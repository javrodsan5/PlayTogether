package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UserTypeService userTypeService;

	@Autowired
	InvitationService invitationService;

	@Autowired
	ChatService chatService;

	@Autowired
	PayService payService;

	@InitBinder("usuario")
	public void initUsuariotBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new UsuarioValidator());
	}

	@GetMapping(value = "/registro")
	public String initCreationForm(ModelMap model) {
		model.put("usuario", new Usuario());
		model.put("accept", false);
		return "users/register";
	}

	@PostMapping(value = "/registro")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result, ModelMap model) {

		if (usuarioService.checkCorreoExists(usuario.getCorreo())) {
			result.addError(new FieldError("usuario", "correo", "El correo ya está registrado"));
		}

		if (usuario.getAccept() == false) {
			model.addAttribute("errorAccept", "Debe aceptar las condiciones.");
			result.addError(new FieldError("usuario", ".", "."));
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
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		boolean premium = false;
		if (usuario.getId().equals(user.getId())) {

			return "redirect:/myprofile";
		} else {
			model.addAttribute("user", usuario);
			if (user.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
				Integer quedadas = usuario.getMeetings().size();
				Integer torneos = usuario.getTeams().size();
				int[] datos = { quedadas, torneos };
				String datos1 = Arrays.toString(datos);
				model.addAttribute("quedadasTorneos", datos1.replace(" ", ""));
				Calendar cal = Calendar.getInstance();
				Integer year = cal.get(Calendar.YEAR);
				List<Integer> quedadasPorMesList = this.usuarioService.findMeetingByMonth(usuario.getId(), year);
				List<Integer> torneosPorMesList = this.usuarioService.findChampionshipByMonth(usuario.getId(), year);
				int[] quedadasPorMes = getEventoPorMes(quedadasPorMesList);
				int[] torneosPorMes = getEventoPorMes(torneosPorMesList);
				String datos2 = Arrays.toString(quedadasPorMes);
				String datos3 = Arrays.toString(torneosPorMes);
				premium = true;
				model.addAttribute("quedadasPorMes", datos2.replace(" ", ""));
				model.addAttribute("torneosPorMes", datos3.replace(" ", ""));
				model.addAttribute("tipoUsuario", premium);
			} else {
				model.addAttribute("tipoUsuario", premium);
			}
			model.addAttribute("edad", Period.between(usuario.getBirthdate(), LocalDate.now()).getYears());
			return "users/userDetails";
		}
	}

	@GetMapping("/myprofile")
	public String userProfile(final ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		model.addAttribute("invitacionesQuedadas", invitacionesQuedadas);
		model.addAttribute("invitacionesTorneos", invitacionesTorneos);
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		Integer quedadas = user.getMeetings().size();
		Integer torneos = user.getTeams().size();
		int[] datos = { quedadas, torneos };
		String datos1 = Arrays.toString(datos);
		model.addAttribute("quedadasTorneos", datos1.replace(" ", ""));
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		List<Integer> quedadasPorMesList = this.usuarioService.findMeetingByMonth(user.getId(), year);
		List<Integer> torneosPorMesList = this.usuarioService.findChampionshipByMonth(user.getId(), year);
		int[] quedadasPorMes = getEventoPorMes(quedadasPorMesList);
		int[] torneosPorMes = getEventoPorMes(torneosPorMesList);
		String datos2 = Arrays.toString(quedadasPorMes);
		String datos3 = Arrays.toString(torneosPorMes);
		model.addAttribute("quedadasPorMes", datos2.replace(" ", ""));
		model.addAttribute("torneosPorMes", datos3.replace(" ", ""));
		
		model.addAttribute("edad", Period.between(user.getBirthdate(), LocalDate.now()).getYears());
		return "users/userProfile";
	}

	@GetMapping("/myprofile/edit")
	public String initUpdateUsuario(ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.put("usuario", user);

		return "users/updateUser";

	}

	@PostMapping("/myprofile/edit")
	public String postUpdateMeeting(@Valid Usuario usuario, BindingResult result, ModelMap model, Principal principal) {
		Usuario usuarioToUpdate = this.usuarioService.findByUsername(principal.getName());

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
					"statistics", "payment", "puntos", "description");
			this.usuarioService.saveUsuario(usuarioToUpdate);
			model.addAttribute("message", "¡Cuenta actualizada correctamente!");
			return "redirect:/myprofile";
		}

	}

	@GetMapping("/myprofile/championshipsRecord")
	public String championshipsRecord(final ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
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
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		List<Meeting> meetings = usuario.getMeetings().stream().limit(10).collect(Collectors.toList());
		if (meetings.size() <= 0) {
			model.put("noRecords", true);
		}
		model.addAttribute("meetings", meetings);

		return "users/meetingsRecord";
	}

	@GetMapping("/myprofile/paysRecord")
	public String paysRecord(final ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		List<Pay> pays = usuario.getPayment().stream().filter(p -> p.getDate() != null).limit(10)
				.collect(Collectors.toList());
		pays.sort(Comparator.comparing(Pay::getDate));
		Collections.reverse(pays);
		if (pays.size() <= 0) {
			model.put("noRecords", true);
		}
		model.addAttribute("pays", pays);

		return "users/paysRecord";
	}

	public int[] getEventoPorMes(List<Integer> eventoList) {
		int contadorEnero = 0;
		int contadorFebrero = 0;
		int contadorMarzo = 0;
		int contadorAbril = 0;
		int contadorMayo = 0;
		int contadorJunio = 0;
		int contadorJulio = 0;
		int contadorAgosto = 0;
		int contadorSeptiembre = 0;
		int contadorOctubre = 0;
		int contadorNoviembre = 0;
		int contadorDiciembre = 0;

		for (Integer i : eventoList) {
			if (i == 1) {
				contadorEnero++;
			} else if (i == 2) {
				contadorFebrero++;
			} else if (i == 3) {
				contadorMarzo++;
			} else if (i == 4) {
				contadorAbril++;
			} else if (i == 5) {
				contadorMayo++;
			} else if (i == 6) {
				contadorJunio++;
			} else if (i == 7) {
				contadorJulio++;
			} else if (i == 8) {
				contadorAgosto++;
			} else if (i == 9) {
				contadorSeptiembre++;
			} else if (i == 10) {
				contadorOctubre++;
			} else if (i == 11) {
				contadorNoviembre++;
			} else if (i == 12) {
				contadorDiciembre++;
			}
		}

		int[] arr = { contadorEnero, contadorFebrero, contadorMarzo, contadorAbril, contadorMayo, contadorJunio,
				contadorJulio, contadorAgosto, contadorSeptiembre, contadorOctubre, contadorNoviembre,
				contadorDiciembre };

		return arr;
	}

	@GetMapping("/clasification")
	public String usersClasification(ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		List<Usuario> usuariosOrdenPuntos = usuarioService.findTopUsuarios().stream().sorted(Comparator.comparing(Usuario::getPuntos)).collect(Collectors.toList());
		Usuario usuario = usuarioService.usuarioLogueado(principal.getName());
		Integer posicion = 0;
		Integer tam = usuariosOrdenPuntos.size();
		for (int i = 0; i < tam; i++) {
			if (usuariosOrdenPuntos.get(i).equals(usuario)) {
				posicion = i + 1;
				break;
			}
		}
		if(posicion == usuariosOrdenPuntos.size()) {
			model.addAttribute("puesto", "Estás en última posición");
		} else if(posicion == usuariosOrdenPuntos.size()-1) {
			model.addAttribute("puesto", "Estás en penúltima posición");
		} else {
			model.addAttribute("puesto", "¡Estás en la posición "+posicion+"!");
		}
		model.addAttribute("puntos", usuario.getPuntos());
		model.addAttribute("posicion", posicion);
		if (usuariosOrdenPuntos.size() > 10) {
			List<Usuario> usuariosTop = usuariosOrdenPuntos.subList(0, 3);
			Integer posPrincipal = usuariosOrdenPuntos.indexOf(usuario);
			Boolean isTop10 = false;
			if(!usuariosOrdenPuntos.subList(0, 10).contains(usuario)) {
				Integer posMenos3Principal = usuariosOrdenPuntos.subList(10, 13).contains(usuario) ? usuariosOrdenPuntos.indexOf(usuario)-3 : usuariosOrdenPuntos.indexOf(usuario)-3;
				Integer posMas3Principal = posPrincipal+3 >= usuariosOrdenPuntos.size() ? usuariosOrdenPuntos.size()-1 : posPrincipal+2;
				usuariosTop.addAll(usuariosOrdenPuntos.subList(posMenos3Principal, posMas3Principal+1));
				model.addAttribute("topUsuarios", usuariosTop);
			} else {
				isTop10 = true;
				if(usuariosOrdenPuntos.indexOf(usuario) == 9 || usuariosOrdenPuntos.indexOf(usuario) == 8) {
					model.addAttribute("topUsuarios", usuariosOrdenPuntos.subList(0, usuariosOrdenPuntos.indexOf(usuario)+3));
				} else {
					model.addAttribute("topUsuarios", usuariosOrdenPuntos.subList(0, 10));
				}
			}
			model.addAttribute("isTop10", isTop10);
		} else {
			model.addAttribute("topUsuarios", usuariosOrdenPuntos);
		}
		model.addAttribute("userId", usuario.getId());
		return "users/clasification";
	}

	@GetMapping("/myprofile/description")
	public String initUpdateUsuarioDescription(ModelMap model, Principal principal) {
		Usuario user = this.usuarioService.findByUsername(principal.getName());

		model.put("usuario", user);
		return "users/updateUserDescription";
	}

	@PostMapping("/myprofile/description")
	public String postUpdateUsuarioDescription(Usuario usuario, BindingResult result, ModelMap model,
			Principal principal) {
		Usuario usuarioToUpdate = this.usuarioService.findByUsername(principal.getName());

		usuarioToUpdate.setDescription(usuario.getDescription());
		this.usuarioService.saveUsuario(usuarioToUpdate);
		model.addAttribute("message", "¡Descripción actualizada correctamente!");
		return "redirect:/myprofile";

	}

	@GetMapping("/requestDeleteMyProfile")
	public String requestDeleteMyProfile(ModelMap model, Principal principal) {

		model.addAttribute("confirmationDelete", true);
		return userProfile(model, principal);
	}

	@GetMapping("/confirmationRequestDeleteMyProfile")
	public String confirmationDeleteMyProfile(ModelMap model, Principal principal) {

		model.addAttribute("confirmatedDelete", true);
		return userProfile(model, principal);
	}

	@GetMapping("/requestDataMyProfile")
	public String requestDataMyProfile(ModelMap model, Principal principal) {

		model.addAttribute("confirmationData", true);
		return userProfile(model, principal);
	}

	@GetMapping("/confirmationRequestDataMyProfile")
	public String confirmationRequestDataMyProfile(ModelMap model, Principal principal) {

		model.addAttribute("confirmatedData", true);
		return userProfile(model, principal);
	}

	@GetMapping("/myprofile/incidence")
	public String initIncidence(ModelMap model, Principal principal) {
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.put("usuario", user);

		return "users/incidenceUser";

	}

	@GetMapping("/myprofile/incidence/accepted")
	public String initIncidenceAccepted(ModelMap model, Principal principal) {
		Usuario user = this.usuarioService.findByUsername(principal.getName());
		model.put("usuario", user);

		model.addAttribute("incidenceCorrect", true);
		return userProfile(model, principal);

	}
	
	@GetMapping("/myprofile/teamsRecord")
	public String teamsRecord(final ModelMap model, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName())
				.size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName())
				.size();
		model.addAttribute("invitaciones", invitacionesQuedadas + invitacionesTorneos);
		Usuario usuario = this.usuarioService.usuarioLogueado(principal.getName());
		List<Team> teams = usuario.getTeams().stream().filter(t -> t.getChampionship().getFinishDate().isAfter(LocalDate.now())).distinct()
				.collect(Collectors.toList());
		if (teams.size() <= 0) {
			model.put("noRecords", true);
		}
		model.addAttribute("teams", teams);
		return "users/teamRecord";
	}

}
