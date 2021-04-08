
package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.MatchService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class ChampionshipController {

	@Autowired
	ChampionshipService championshipService;

	@Autowired
	SportService sportService;

	@Autowired
	MatchService matchService;

	@Autowired
	UsuarioService userService;

	@Autowired
	PayService payService;

	private List<Usuario> users;

	@InitBinder("championship")
	public void initChampionshipBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new ChampionshipValidator());
	}

	@InitBinder("match")
	public void initMatchBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new MatchValidator());
	}

	@InitBinder("team")
	public void initTeamBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new TeamValidator());
		dataBinder.setDisallowedFields("teamSize");
	}

	@GetMapping("/sports/{sportId}/championships/add")
	public String initCreationChampionship(final ModelMap model, @PathVariable("sportId") final Integer sportId) {
		Championship championship = new Championship();
		model.addAttribute("championship", championship);
		model.put("deporte", sportId);
		List<Integer> maximoEquipos = new ArrayList<>();
		maximoEquipos.add(4);
		maximoEquipos.add(8);
		maximoEquipos.add(16);
		model.put("maximoEquipos", maximoEquipos);
		return "championships/createOrUpdateChampionshipForm";
	}

	@PostMapping("/sports/{sportId}/championships/add")
	public String postCreationChampionship(@Valid final Championship championship, final BindingResult result,
			final ModelMap model, @PathVariable("sportId") final Integer sportId) {
		if (!result.hasErrors()) {
			this.championshipService.save(championship);

			return "redirect:/sports/" + sportId + "/championships";
		} else {
			model.put("deporte", sportId);
			List<Integer> maximoEquipos = new ArrayList<>();
			maximoEquipos.add(4);
			maximoEquipos.add(8);
			maximoEquipos.add(16);
			model.put("maximoEquipos", maximoEquipos);
			return "championships/createOrUpdateChampionshipForm";
		}
	}

	@GetMapping("/sports/{sportId}/championships")
	public String listChampionships(final ModelMap model, @PathVariable("sportId") final Integer sportId) {
		Collection<Championship> championships = this.championshipService.listChampionshipsBySport(sportId);
		Sport sport = this.sportService.findSportById(sportId);
		if (sport.getSportType().getName().equals("Equipo")) {
			model.addAttribute("championships", championships);
			model.addAttribute("deporte", sportId);
			model.addAttribute("nombreDeporte", sport.getName());
			return "championships/listChampionship";
		} else {
			return "redirect:/sports";
		}
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}")
	public String championshipDetails(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId) {
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("championship", championship);
		Usuario user = this.userService.findUserById(1);
		Boolean b1 = true;
		Boolean b2 = true;

		if (user.getTeams().stream().anyMatch(x -> x.getChampionship().getId().equals(championshipId))) {
			b1 = false;
			b2 = false;
			model.addAttribute("miEquipo", user.getTeams().stream()
					.filter(x -> x.getChampionship().getId().equals(championshipId)).findFirst().get());
		}

		if (championship.getTeams().size() >= championship.getMaxTeams()) {
			b1 = false;
		}
		model.addAttribute("crearEquipo", b1);
		model.addAttribute("participarEquipo", b2);

		return "championships/championshipDetails";
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/add")
	public String initCreationMatch(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId) {
		Integer listChampionships = this.championshipService.listChampionship().size();
		if (championshipId > 0 && championshipId <= listChampionships) {
			Match match = new Match();
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("match", match);
			model.addAttribute("championship", championshipId);
			model.addAttribute("championshipObj", championship);
			List<Team> equipos = (List<Team>) this.matchService.findTeams(championshipId);
			model.addAttribute("equipos", equipos);
			return "matches/createOrUpdateMatchForm";
		} else {
			return "error-500";
		}
	}

	@PostMapping("/sports/{sportId}/championships/{championshipId}/match/add")
	public String postCreationMatch(@Valid final Match match, final BindingResult result, final ModelMap model,
			@PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, final Errors errors) {

		Championship championship = this.championshipService.findChampionshipId(championshipId);

		if (match.getDateTime() != null) {
			if (LocalDate.of(match.getDateTime().getYear(), match.getDateTime().getMonth(),
					match.getDateTime().getDayOfMonth()).isBefore(championship.getStartDate())) {
				errors.rejectValue("dateTime", "La fecha debe ser posterior a la de inicio del torneo.",
						"La fecha debe ser posterior a la de inicio del torneo.");
			} else if (LocalDate.of(match.getDateTime().getYear(), match.getDateTime().getMonth(),
					match.getDateTime().getDayOfMonth()).isAfter(championship.getFinishDate())) {
				errors.rejectValue("dateTime", "La fecha debe ser anterior a la de fin del torneo.",
						"La fecha debe ser anterior a la de fin del torneo.");
			} else if (match.getDateTime().isBefore(LocalDateTime.now())) {
				errors.rejectValue("startDate", "La fecha debe ser posterior a la actual.",
						"La fecha debe ser posterior a la actual.");
			} else if (match.getTeam1() == null || match.getTeam2() == null) {
				errors.rejectValue("team1", "No hay equipos disponibles para este torneo.",
						"No hay equipos disponibles para este torneo.");
				model.addAttribute("errorSelector", "No hay equipos disponibles para este torneo.");

			} else if (match.getTeam1().getId() == match.getTeam2().getId()) {
				errors.rejectValue("team1", "La fecha debe ser posterior a la actual.",
						"La fecha debe ser posterior a la actual.");
				model.addAttribute("errorSelector", "No puedes seleccionar el mismo equipo dos veces.");
			}
		}

		if (!result.hasErrors()) {
			this.matchService.save(match);
			return "redirect:/sports/" + sportId + "/championships/" + championshipId + "/matches";
		} else {
			model.addAttribute("championshipObj", championship);
			model.put("championship", championshipId);
			List<Team> equipos = (List<Team>) this.matchService.findTeams(championshipId);
			model.addAttribute("equipos", equipos);
			return "matches/createOrUpdateMatchForm";
		}
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/matches")
	public String listMatches(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId) {
		Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);

		model.addAttribute("matches", matches);
		model.addAttribute("deporte", sportId);
		model.addAttribute("championship", championshipId);

		return "matches/listMatch";
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/result/{team}")
	public String matchDetails(@RequestParam(value = "search", required = false) final String search,
			final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("matchId") final Integer matchId, @PathVariable("team") final String team) {
		Boolean isPuntos1 = null;

		try {
			this.users = this.championshipService.findUserByNameOrUsername(search);
		} catch (Exception e) {
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.put("noUser", true); // No se encontró al usuario
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			return "matches/listMatch";
		}

		Match match = this.matchService.findMatchById(matchId);

		if (team.equals("team1") && this.isParticipant(match.getTeam1().getParticipants(), search.toString())) {
			isPuntos1 = true;
		} else if (team.equals("team2") && this.isParticipant(match.getTeam2().getParticipants(), search.toString())) {
			isPuntos1 = false;
		} else {
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			model.put("noTeam", true);
			return "matches/listMatch";
		}

		List<Integer> listaPuntos = new ArrayList<>();
		for (int i = 0; i <= 200; i++) {
			listaPuntos.add(i);
		}

		model.addAttribute("match", match);
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("championshipObj", championship);
		model.put("isPuntos1", isPuntos1);
		model.put("listaPuntos", listaPuntos);

		return "matches/createOrUpdateMatchForm";
	}

	private Boolean isParticipant(final List<Usuario> users, final String name) {
		Boolean res = Boolean.FALSE;
		for (Usuario user : users) {
			res = user.getName().equals(name);
			if (res) {
				break;
			}
		}
		return res;
	}

	@PostMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/result/{team}")
	public String initMatchAddResult(@Valid final Match match, final BindingResult result, final ModelMap model,
			@PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("matchId") final Integer matchId, final Errors errors,
			@PathVariable("team") final String team) {

		if (result.hasErrors()) {
			model.put("match", match);
			return "matches/createOrUpdateMatchForm";
		} else {

			if (match.getPuntos3() != null && match.getPuntos4() != null) {
				Match matchToUpdate1 = this.matchService.findMatchById(matchId);
				BeanUtils.copyProperties(match, matchToUpdate1, "id", "dateTime", "puntos1", "puntos2", "team1",
						"team2", "championship");
				try {
					this.matchService.save(matchToUpdate1);
				} catch (ConstraintViolationException e) {
					errors.rejectValue("puntos3", "El número debe estar entre 0 y 999",
							"El número debe estar entre 0 y 999");
					errors.rejectValue("puntos4", "El número debe estar entre 0 y 999",
							"El número debe estar entre 0 y 999");
					model.put("match", match);
					return "matches/createOrUpdateMatchForm";
				}
			} else if (match.getPuntos1() != null && match.getPuntos2() != null) {
				Match matchToUpdate2 = this.matchService.findMatchById(matchId);
				BeanUtils.copyProperties(match, matchToUpdate2, "id", "dateTime", "puntos3", "puntos4", "team1",
						"team2", "championship");
				this.matchService.save(matchToUpdate2);
			} else {
				Boolean isPuntos1 = null;
				if (team.equals("team1")) {
					isPuntos1 = true;
				}
				if (team.equals("team2")) {
					isPuntos1 = false;
				}

				model.put("isPuntos1", isPuntos1);
				model.put("noPuntos", true);
				model.put("match", match);
				return "matches/createOrUpdateMatchForm";

			}
			return "redirect:/sports/" + sportId + "/championships/" + championshipId + "/matches";
		}
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/join/{teamId}")
	public String initJoinChampionship(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("teamId") final Integer teamId, Principal principal) {
		Team team = this.championshipService.findTeamId(teamId);
		Usuario user = this.userService.findByUsername(principal.getName());
		List<Usuario> participants = team.getParticipants();

		Pay pay = this.payService.findLastFinishedPayForChampionshipByUsername(principal.getName(), championshipId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium")) && pay == null) {
    		return "redirect:/pay/championship/"+championshipId+"/team/"+teamId;
		}
		if (participants.contains(user)) {
			return "redirect:/sports/" + sportId + "/championships/" + championshipId;
		} else {
			pay.setTeam(team);
			this.payService.save(pay);
			model.put("esParticipante", false);
			participants.add(user);
			team.setParticipants(participants);
			this.championshipService.save(team);

			return "redirect:/sports/" + sportId + "/championships/" + championshipId;
		}
	}

	@GetMapping("/championships/{championshipId}/team/create")
	public String initCreationTeam(final ModelMap model, @PathVariable("championshipId") final Integer championshipId) {
		Team team = new Team();
		model.addAttribute("team", team);

		return "teams/createOrUpdateTeamForm";
	}

	@PostMapping("/championships/{championshipId}/team/create")
	public String postCreationTeam(@Valid final Team team, final BindingResult result,
			@PathVariable("championshipId") final int championshipId, final ModelMap model, final Errors errors, Principal principal) {
		List<Team> joinedTeams = this.championshipService.findTeamsByChampionshipId(championshipId);
		for (Team t : joinedTeams) {
			if (t.getName().equals(team.getName())) {
				errors.rejectValue("name", "Ya existe un equipo con ese nombre en este torneo.",
						"Ya existe un equipo con ese nombre en este torneo.");
				break;
			}
		}

		Championship championship = this.championshipService.findChampionshipId(championshipId);
		if (team.getParticipants().size() >= championship.getSport().getNumberOfPlayersInTeam()) {
			errors.rejectValue("name", "El equipo ya está lleno.", "El equipo ya está lleno.");
		}

		if (!result.hasErrors()) {

			/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium"))){
				return "redirect:/pay/championship/"+championshipId;
			} else {*/
			team.setChampionship(championship);
			team.setTeamSize(championship.getSport().getNumberOfPlayersInTeam());
			this.championshipService.save(team);
			return "redirect:/sports/" + championship.getSport().getId() + "/championships/" + championshipId; // CAMBIAR
			//}																									// PARA
																												// EL
																												// SEGUNDO
																												// SPRINT
																												// (MARIO:
																												// "redirect:/championships/team/"
																												// +
																												// team.getId();)

		} else {
			model.put("team", team);
			return "teams/createOrUpdateTeamForm";
		}
	}

//	@GetMapping("/championships/team/{teamId}")
//	public String searchParticipants(final ModelMap model, @PathVariable("teamId") final Integer teamId) {
//		Team team = this.championshipService.findTeamId(teamId);
//		model.put("team_participants", team.getParticipants());
//		model.put("teamSize", team.getTeamSize());
//		model.put("search", "");
//		return "teams/addParticipantsForm";
//	}
//
//	@GetMapping("/championships/team/{teamId}/add_partner")
//	public String initAddParticipants(@RequestParam(value = "search", required = false) final String search,
//			final ModelMap model, @PathVariable("teamId") final Integer teamId) {
//		List<User> searched_users = new ArrayList<>();
//		Team team = this.championshipService.findTeamId(teamId);
//		model.put("team_participants", team.getParticipants());
//
//		if (team.getParticipants().size() >= team.getTeamSize()) {
//			model.put("limitedTeamSize", true);
//			return "teams/addParticipantsForm";
//		}
//
//		try {
//			searched_users = this.championshipService.findUserByNameOrUsername(search);
//		} catch (Exception e) {
//			model.put("noUser", true);
//			return "teams/addParticipantsForm";
//		}
//
//		this.deleteRepeatedUsers(team, searched_users);
//		if (searched_users.isEmpty()) {
//			model.put("notMoreUsers", true);
//			return "teams/addParticipantsForm";
//		}
//
//		model.put("searched_users", searched_users);
//		return "teams/listSearchedUsers";
//	}
//
//	@PostMapping("/championships/team/{teamId}/add_partner")
//	public String postAddParticipants(@ModelAttribute("selected_participant") final String selected_participant,
//			@PathVariable("teamId") final int teamId, final BindingResult result, final ModelMap model) {
//		User participant = this.championshipService.findUsersById(Integer.parseInt(selected_participant));
//		Team team = this.championshipService.findTeamId(teamId);
//
//		if (!team.getParticipants().contains(participant)) {
//			team.getParticipants().add(participant);
//			this.championshipService.save(team);
//		}
//
//		model.put("team_participants", team.getParticipants());
//		model.put("teamSize", team.getTeamSize());
//		return "teams/addParticipantsForm";
//
//	}

//	private void deleteRepeatedUsers(final Team team, final List<User> searchedUsers) {
//
//		for (int i = 0; i < searchedUsers.size(); i++) {
//			if (team.getParticipants().contains(searchedUsers.get(i))) {
//				searchedUsers.remove(i);
//				i--;
//			}
//		}
//	}

}
