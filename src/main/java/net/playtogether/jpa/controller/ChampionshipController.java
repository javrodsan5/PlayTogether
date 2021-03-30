package net.playtogether.jpa.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.MatchService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UserService;

@Controller
public class ChampionshipController {


	@Autowired
	ChampionshipService championshipService;

	@Autowired
	SportService sportService;

	@Autowired
	MatchService matchService;

	@Autowired
	UserService userService;

	@InitBinder("championship")
	public void initChampionshipBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ChampionshipValidator());
	}

	@InitBinder("match")
	public void initMatchBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new MatchValidator());
	}
  
  	@InitBinder("team")
		public void initTeamBinder(final WebDataBinder dataBinder) {
			dataBinder.setValidator(new TeamValidator());
			dataBinder.setDisallowedFields("teamSize");
		}

	@GetMapping("/sports/{sportId}/championships/add")
	public String initCreationChampionship(ModelMap model, @PathVariable("sportId") Integer sportId) {
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
	public String postCreationChampionship(@Valid Championship championship, BindingResult result, ModelMap model,
			@PathVariable("sportId") Integer sportId) {
		if (!result.hasErrors()) {
			championshipService.save(championship);

			return "redirect:/sports/" + sportId + "/championships";
		} else {
			model.put("deporte", sportId);
			return "championships/createOrUpdateChampionshipForm";
		}
	}

	@GetMapping("/sports/{sportId}/championships")
	public String listChampionships(ModelMap model, @PathVariable("sportId") Integer sportId) {
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
	public String championshipDetails(ModelMap model, @PathVariable("sportId") Integer sportId,
			@PathVariable("championshipId") Integer championshipId) {
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("championship", championship);
		User user = this.userService.findUserById(1);
		Boolean b1 = true;
		Boolean b2 = true;

		if (user.getTeams().stream().anyMatch(x -> x.getChampionship().getId().equals(championshipId))) {
			b1 = false;
			b2 = false;
			model.addAttribute("miEquipo", user.getTeams().stream().filter(x-> x.getChampionship().getId().equals(championshipId)).findFirst().get());
		}

		if (championship.getTeams().size() >= championship.getMaxTeams()) {
			b1 = false;
		}
		model.addAttribute("crearEquipo", b1);
		model.addAttribute("participarEquipo", b2);

		return "championships/championshipDetails";
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/add")
	public String initCreationMatch(ModelMap model, @PathVariable("sportId") Integer sportId,
			@PathVariable("championshipId") Integer championshipId) {
		Match match = new Match();
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("match", match);
		model.addAttribute("championship", championshipId);
		model.addAttribute("championshipObj", championship);
		List<Team> equipos = (List<Team>) this.matchService.findTeams();
		model.addAttribute("equipos",equipos);
		return "matches/createOrUpdateMatchForm";
	}

	@PostMapping("/sports/{sportId}/championships/{championshipId}/match/add")
	public String postCreationMatch(@Valid Match match, BindingResult result, ModelMap model,
			@PathVariable("sportId") Integer sportId, @PathVariable("championshipId") Integer championshipId,
			Errors errors) {

		Championship championship = this.championshipService.findChampionshipId(championshipId);

		if (LocalDate
				.of(match.getDateTime().getYear(), match.getDateTime().getMonth(), match.getDateTime().getDayOfMonth())
				.isBefore(championship.getStartDate())) {
			errors.rejectValue("dateTime", "La fecha debe ser posterior a la de inicio del torneo.",
					"La fecha debe ser posterior a la de inicio del torneo.");
		} else if (LocalDate
				.of(match.getDateTime().getYear(), match.getDateTime().getMonth(), match.getDateTime().getDayOfMonth())
				.isAfter(championship.getFinishDate())) {
			errors.rejectValue("dateTime", "La fecha debe ser anterior a la de fin del torneo.",
					"La fecha debe ser anterior a la de fin del torneo.");
		} else if (match.getDateTime().isBefore(LocalDateTime.now())) {
			errors.rejectValue("startDate", "La fecha debe ser posterior a la actual.",
					"La fecha debe ser posterior a la actual.");
		}else if (match.getTeam1().getId()== match.getTeam2().getId()) {
			errors.rejectValue("team1", "No puedes seleccionar el mismo equipo dos veces.", "No puedes seleccionar el mismo equipo dos veces.");
			errors.rejectValue("team2", "No puedes seleccionar el mismo equipo dos veces.", "No puedes seleccionar el mismo equipo dos veces.");
		}

		if (!result.hasErrors()) {
			matchService.save(match);
			return "redirect:/sports/" + sportId + "/championships/" + championshipId;
		} else {
			model.addAttribute("championshipObj", championship);
			model.put("championship", championshipId);
			List<Team> equipos = (List<Team>) this.matchService.findTeams();
			model.addAttribute("equipos",equipos);
			return "matches/createOrUpdateMatchForm";
		}
	}
	
	@GetMapping("/sports/{sportId}/championships/{championshipId}/matches")
	public String listMatches(ModelMap model,@PathVariable("sportId") Integer sportId,@PathVariable ("championshipId") Integer championshipId) {
		Collection<Match>matches= this.matchService.listMatchesByChampionship(sportId);
	
		model.addAttribute("matches",matches);
		model.addAttribute("deporte",sportId);
		model.addAttribute("championship",championshipId);

		return "matches/listMatch";
	}
		
	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/result")
	public String matchDetails(ModelMap model, @PathVariable("sportId") Integer sportId,
			@PathVariable("championshipId") Integer championshipId, @PathVariable("matchId") Integer matchId) {
		Match match = this.matchService.findMatchById(matchId);
		model.addAttribute("match", match);
		return "matches/createOrUpdateMatchForm";
	}

	@PostMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/result")
	public String initMatchAddResult(@Valid Match match, BindingResult result, ModelMap model,
			@PathVariable("sportId") Integer sportId, @PathVariable("championshipId") Integer championshipId,
			@PathVariable("matchId") Integer matchId, Errors errors) {

		if (result.hasErrors()) {
			model.put("match", match);
			return "matches/createOrUpdateMatchForm";
		} else {
			Match matchToUpdate = this.matchService.findMatchById(matchId);
			BeanUtils.copyProperties(match, matchToUpdate, "id", "dateTime", "team1", "team2", "championship");
			this.matchService.save(matchToUpdate);
			return "redirect:/sports/" + sportId + "/championships/" + championshipId + "/matches";
		}

	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/join/{teamId}")
	public String initJoinChampionship(ModelMap model, @PathVariable("sportId") Integer sportId,
			@PathVariable("championshipId") Integer championshipId, @PathVariable("teamId") Integer teamId) {
		Team team = this.championshipService.findTeamId(teamId);
		User user = this.userService.findUserById(1);
		List<User> participants = team.getParticipants();
		if (participants.contains(user)) {
			return "redirect:/sports/" + sportId + "/championships/" + championshipId;

		} else {
			model.put("esParticipante", false);
			participants.add(user);
			team.setParticipants(participants);
			this.championshipService.save(team);

			return "redirect:/sports/" + sportId + "/championships/" + championshipId;
		}
	}

	 	@GetMapping("/championships/{championshipId}/team/create")
		public String initCreationTeam(ModelMap model, @PathVariable ("championshipId") Integer championshipId) {
			Team team = new Team();
			model.addAttribute("team", team);
		
			return "teams/createOrUpdateTeamForm";
		}
	 	
		@PostMapping("/championships/{championshipId}/team/create")
		public String postCreationTeam(@Valid Team team, BindingResult result, @PathVariable("championshipId") int championshipId, ModelMap model, Errors errors) { 		
			List<Team> joinedTeams = this.championshipService.findTeamsByChampionshipId(championshipId);			
			for (Team t: joinedTeams) {
				if (t.getName().equals(team.getName())) {
					errors.rejectValue("name", "Ya existe un equipo con ese nombre en este torneo.",	"Ya existe un equipo con ese nombre en este torneo.");
					break;
				}
			}			
			
			Championship championship =  this.championshipService.findChampionshipId(championshipId);
			if (team.getParticipants().size() >= championship.getSport().getNumberOfPlayersInTeam()) {
				errors.rejectValue("name", "El equipo ya está lleno.",	"El equipo ya está lleno.");
			}
			
			if(!result.hasErrors()) {
				team.setChampionship(championship);
				team.setTeamSize(championship.getSport().getNumberOfPlayersInTeam());
				championshipService.save(team);		
				return "redirect:/championships/team/" + team.getId();
				
			} else {
				model.put("team", team);
				return "teams/createOrUpdateTeamForm";
			}
		}
		
		
	 	@GetMapping("/championships/team/{teamId}")
		public String searchParticipants(ModelMap model, @PathVariable("teamId") Integer teamId) { 	
			Team team = this.championshipService.findTeamId(teamId);
	 		model.put("team_participants", team.getParticipants());
	 		model.put("teamSize", team.getTeamSize());
	 		model.put("search", "");
			return "teams/addParticipantsForm";
		}

	 	@GetMapping("/championships/team/{teamId}/add_partner")
		public String initAddParticipants(@RequestParam(value = "search", required = false) String search, ModelMap model, @PathVariable ("teamId") Integer teamId) {
	 		List<User> searched_users = new ArrayList<>();
	 		Team team = this.championshipService.findTeamId(teamId);
	 		model.put("team_participants", team.getParticipants());
	 		
			if (team.getParticipants().size() >= team.getTeamSize()) {
				model.put("limitedTeamSize", true);
				return "teams/addParticipantsForm";
			}
	 		
	 		try {
	 			search.toString();
	 			searched_users = this.championshipService.findUserByNameOrUsername(search);	
			} catch (Exception e) {			
				model.put("noUser", true);
				return "teams/addParticipantsForm";
			}
	 			 		
	 		deleteRepeatedUsers(team, searched_users);	 		
	 		if (searched_users.isEmpty()) {
				model.put("notMoreUsers", true);
				return "teams/addParticipantsForm";
	 		}
	 		
	 		model.put("searched_users", searched_users);
			return "teams/listSearchedUsers";
		}
	 	
	 	@PostMapping("/championships/team/{teamId}/add_partner")
		public String postAddParticipants(@ModelAttribute("selected_participant") String selected_participant, @PathVariable("teamId") int teamId, BindingResult result, ModelMap model) {		
 			User participant = this.championshipService.findUsersById(Integer.parseInt(selected_participant));
			Team team = this.championshipService.findTeamId(teamId);
			
			if (!team.getParticipants().contains(participant)) {
				team.getParticipants().add(participant);
				championshipService.save(team);
			}

	 		model.put("team_participants", team.getParticipants());
	 		model.put("teamSize", team.getTeamSize());
			return "teams/addParticipantsForm";	
			
		}
		
	 	private void deleteRepeatedUsers(Team team, List<User> searchedUsers) {
	 		
	 		for (int i = 0; i < searchedUsers.size(); i++) {
	 			if (team.getParticipants().contains(searchedUsers.get(i))) {
	 				searchedUsers.remove(i);
	 				i--;
	 			}
	 		}
	 	}
		
		
}
