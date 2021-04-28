package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.MatchService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.TeamService;
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

	@Autowired
	TeamService teamService;
	
	@Autowired
	InvitationService invitationService;

	@Autowired
	ChatService chatService;

	private List<Usuario> users;

	private final Integer minutesToFinishPay = 5;

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
	public String initCreationChampionship(final ModelMap model, @PathVariable("sportId") final Integer sportId,Principal principal) {
		if(sportId>20) {
			return "error-500";
		}
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		model.addAttribute("championship", new Championship());
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
			final ModelMap model, @PathVariable("sportId") final Integer sportId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		if (!result.hasErrors()) {
			Usuario user = this.userService.findByUsername(principal.getName());
			championship.setUser(user);
			this.championshipService.save(championship);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium"))) {
				return "redirect:/pay/championship/add?championshipId=" + championship.getId();
			}

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
	public String listChampionships(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		List<Championship> championships = new ArrayList<>(this.championshipService.listChampionshipsBySport(sportId));
		Sport sport = this.sportService.findSportById(sportId);
		List<Championship> championshipsToRemove = new ArrayList<>();
		List<Championship> championshipsToDelete = new ArrayList<>();

		for (Championship c : championships) {
			Pay pay = this.payService.findLastFinishedPayForChampionshipByUsername(c.getUser().getUser().getUsername(),
					c.getId());
			if (pay == null && !c.getUser().getUser().getAuthorities().stream()
					.anyMatch(x -> x.getAuthority().equals("premium"))) {
				Pay incompleto = this.payService.findLastNotFinishedPayForChampionshipByUsername(
					c.getUser().getUser().getUsername(), c.getId());

				if(incompleto.getInitDate() == null) {
					incompleto.setInitDate(LocalDateTime.now().minusHours(1L));
				}

				if (incompleto != null) {
					if(Duration.between(incompleto.getInitDate(), LocalDateTime.now()).getSeconds() / 60 >= minutesToFinishPay) {
						championshipsToDelete.add(c);
						incompleto.setChampionship(null);
						incompleto.setTeam(null);
						this.payService.save(incompleto);
					}
				}
				championshipsToRemove.add(c);
			}
		}
		this.championshipService.deleteAll(championshipsToDelete);
		championships.removeAll(championshipsToRemove);

		if (sport.getSportType().getName().equals("Equipo")) {
			model.addAttribute("championships", championships);
			model.addAttribute("deporte", sportId);
			model.addAttribute("nombreDeporte", sport.getName());
			model.addAttribute("usuario_logueado", this.userService.findByUsername(principal.getName()));
			return "championships/listChampionship";
		} else {
			return "redirect:/sports";
		}
	}

	@CachePut(value = "torneo")
	@GetMapping("/sports/{sportId}/championships/{championshipId}")
	public String championshipDetails(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		if(sportId>20) {
			return "error-500";
		}
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		List<Team> teams = this.championshipService.findTeamsByChampionshipId(championshipId);
		List<Team> teamsToRemove = new ArrayList<>();
		List<Team> teamsToDelete = new ArrayList<>();
		Usuario user = this.userService.findByUsername(principal.getName());
		for (Team t : teams) {
			if (!t.getUser().equals(championship.getUser())) {

				Pay pay = this.payService.findLastFinishedPayForTeamByUsername(t.getUser().getUser().getUsername(),
						t.getId());
				if (pay == null && !t.getUser().getUser().getAuthorities().stream()
						.anyMatch(x -> x.getAuthority().equals("premium"))) {
					Pay incompleto = this.payService.findLastNotFinishedPayForTeamByUsername(
							t.getUser().getUser().getUsername(), t.getId());

					if (incompleto != null) {
						if(Duration.between(incompleto.getInitDate(), LocalDateTime.now()).getSeconds() / 60 >= minutesToFinishPay) {
							teamsToDelete.add(t);
							incompleto.setChampionship(null);
							incompleto.setTeam(null);
							this.payService.save(incompleto);
						}
					}

					teamsToRemove.add(t);
				}
			}
		}

		this.teamService.deleteAll(teamsToDelete);
		teams.removeAll(teamsToRemove);
		model.addAttribute("championship", championship);
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
		for(Team t:teams) {
			if(t.getUser().equals(user)) {
				b1 = false;
			}
		}
		model.addAttribute("crearEquipo", b1);
		model.addAttribute("participarEquipo", b2);
		model.addAttribute("hayEquipos", teams.size()>0);
		model.addAttribute("logged_user", user);
		model.addAttribute("numTeams", teams.size());
		
		if (user.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			model.put("premium", true);
		}

		return "championships/championshipDetails";
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/add")
	public String initCreationMatch(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Integer listChampionships = this.championshipService.listChampionship().size();
		Collection<Usuario> participantes = this.championshipService.findParticipantsChampionship(championshipId);
		Usuario user = this.userService.findByUsername(principal.getName());
		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));

		List<Team> teams = this.championshipService.findTeamsByChampionshipId(championshipId);

		if (championshipId > 0 && championshipId <= listChampionships) {
			if (teams.size() < 2) {
				return "error-403";
			}
			if (participa) {
				Match match = new Match();
				Championship championship = this.championshipService.findChampionshipId(championshipId);
				model.addAttribute("match", match);
				model.addAttribute("championship", championshipId);
				model.addAttribute("championshipObj", championship);
				List<Team> equipos = (List<Team>) this.matchService.findTeams(championshipId);
				model.addAttribute("equipos", equipos);
				return "matches/createOrUpdateMatchForm";
			} else {
				Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);

				model.addAttribute("matches", matches);
				model.addAttribute("deporte", sportId);
				model.addAttribute("championship", championshipId);
				model.addAttribute("noParticipa", true);
				return "matches/listMatch";
			}
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
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		if(sportId>20) {
			return "error-500";
		}
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);

		Championship championship = this.championshipService.findChampionshipId(championshipId);
		
		

		List<Team> equipos = this.championshipService.findTeamsByChampionshipId(championshipId);
		Boolean crearPartido = false;
		if (equipos.size() >= 2) {
			crearPartido = true;
		}
		model.addAttribute("crearPartido", crearPartido);
		
		Usuario user = this.userService.findByUsername(principal.getName());
		model.addAttribute("usuarioLog", user);

		model.addAttribute("matches", matches);
		model.addAttribute("deporte", sportId);
		model.addAttribute("championship", championshipId);
		model.addAttribute("championshipObj", championship);
		model.addAttribute("rondaActual", championship.getMatches().stream().max(Comparator.comparing(Match::getRonda))
				.map(x -> x.getRonda()).orElse(null));
		// GANADOR 4 EQUIPOS
		if (championship.getMatches().size() == 3 && championship.getMaxTeams() == 4) {
			Match ultPartido = this.championshipService.getUltimoPartido(championship);
			if (ultPartido.getPuntos1() != null && ultPartido.getPuntos2() != null && ultPartido.getPuntos3() != null
					&& ultPartido.getPuntos4() != null) {
				Team ganador = this.championshipService.getGanadorPartido(ultPartido);
				model.addAttribute("nombreGanador", ganador.getName());
			}
		}
		// GANADOR 8 EQUIPOS
		if (championship.getMatches().size() == 7 && championship.getMaxTeams() == 8) {
			Match ultPartido = this.championshipService.getUltimoPartido(championship);
			if (ultPartido.getPuntos1() != null && ultPartido.getPuntos2() != null && ultPartido.getPuntos3() != null
					&& ultPartido.getPuntos4() != null) {
				Team ganador = this.championshipService.getGanadorPartido(ultPartido);
				model.addAttribute("nombreGanador", ganador.getName());
					 
				}
			}
		

		// GANADOR 16 EQUIPOS
		if (championship.getMatches().size() == 15 && championship.getMaxTeams() == 16) {
			Match ultPartido = this.championshipService.getUltimoPartido(championship);
			if (ultPartido.getPuntos1() != null && ultPartido.getPuntos2() != null && ultPartido.getPuntos3() != null
					&& ultPartido.getPuntos4() != null) {
				Team ganador = this.championshipService.getGanadorPartido(ultPartido);
				model.addAttribute("nombreGanador", ganador.getName());
			}
		}

		return "matches/listMatch";

	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/result/{team}")
	public String matchDetails(@RequestParam(value = "search", required = false) final String search,
			final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("matchId") final Integer matchId, @PathVariable("team") final String team,
			Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		try {
			this.championshipService.findUserByNameOrUsername(principal.getName());
		} catch (Exception e) {
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.put("noUser", true); // No se encontró al usuario
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			return "matches/listMatch";
		}

		Match match = this.matchService.findMatchById(matchId);
		List<Usuario> participantes1 = new ArrayList<>();
		List<Usuario> participantes2 = new ArrayList<>();
		participantes1.addAll(match.getTeam1().getParticipants());
		participantes2.addAll(match.getTeam2().getParticipants());

		Usuario user = this.userService.findByUsername(principal.getName());

		boolean participa1 = participantes1.stream().anyMatch(p -> p.equals(user));
		boolean participa2 = participantes2.stream().anyMatch(p -> p.equals(user));

		List<Integer> listaPuntos = new ArrayList<>();
		for (int i = 0; i <= 200; i++) {
			listaPuntos.add(i);
		}


		if (participa1 && team.equals("team1")) {

			model.addAttribute("match", match);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			model.put("isPuntos1", true);
			model.put("listaPuntos", listaPuntos);

			return "matches/createOrUpdateMatchForm";

		} else if (participa2 && team.equals("team2")) {

			model.addAttribute("match", match);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			model.put("isPuntos1", false);
			model.put("listaPuntos", listaPuntos);

			return "matches/createOrUpdateMatchForm";

		} else {
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			Championship championship = this.championshipService.findChampionshipId(championshipId);
			model.addAttribute("championshipObj", championship);
			model.put("noTeam", true);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			return "matches/listMatch";
		}

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
						"team2", "championship", "ronda");
//			try {
					this.matchService.save(matchToUpdate1);
//				} catch (ConstraintViolationException e) {
//					errors.rejectValue("puntos3", "El número debe estar entre 0 y 999",
//							"El número debe estar entre 0 y 999");
//					errors.rejectValue("puntos4", "El número debe estar entre 0 y 999",
//							"El número debe estar entre 0 y 999");
//					model.put("match", match);
//					return "matches/createOrUpdateMatchForm";
//				}
			} else if (match.getPuntos1() != null && match.getPuntos2() != null) {
				Match matchToUpdate2 = this.matchService.findMatchById(matchId);
				BeanUtils.copyProperties(match, matchToUpdate2, "id", "dateTime", "puntos3", "puntos4", "team1",
						"team2", "championship", "ronda");
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
			@PathVariable("championshipId") final Integer championshipId, @PathVariable("teamId") final Integer teamId,
			Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		Team team = this.championshipService.findTeamId(teamId);
		Usuario user = this.userService.findByUsername(principal.getName());
		List<Usuario> participants = team.getParticipants();

		if (!championship.getTeams().contains(team)) {
			return "error-404";
		} else if (championship.getTeams().stream().anyMatch(x -> x.getParticipants().contains(user))) {
			return "error-403";
		} else {
			if(participants.size() >= team.getTeamSize()) {
				return "error-403";
			}
		
			Pay pay = this.payService.findLastFinishedPayForChampionshipByUsername(principal.getName(), championshipId);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Boolean isPremium = authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium"));
			if (!isPremium && pay == null) {
				return "redirect:/pay/championship/" + championshipId + "/team/" + teamId + "?invitationId=";
			} else {
				if (participants.contains(user)) {
					return "redirect:/sports/" + sportId + "/championships/" + championshipId;
				} else {
					model.put("esParticipante", false);
					participants.add(user);
					team.setParticipants(participants);
					this.championshipService.save(team);
					user.setPuntos(user.getPuntos() + 5);
					userService.saveUsuarioAlreadyRegistered(user);

					return "redirect:/sports/" + sportId + "/championships/" + championshipId;
				}
			}
		}
	}

	@GetMapping("/championships/{championshipId}/team/create")
	public String initCreationTeam(final ModelMap model, @PathVariable("championshipId") final Integer championshipId,
			Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Team team = new Team();
		Championship championship = championshipService.findChampionshipId(championshipId);
		Usuario userPrincipal = this.userService.findByUsername(principal.getName());
		List<Team> joinedTeams = this.championshipService.findTeamsByChampionshipId(championshipId);
		for (Team t : joinedTeams) {
			if (t.getParticipants().contains(userPrincipal) || t.getUser().equals(userPrincipal)) {
				return "error-403";
			}
		}
		model.addAttribute("team", team);
		model.put("principal", userPrincipal);
		model.put("championship", championship);

		return "teams/createOrUpdateTeamForm";
	}

	@PostMapping("/championships/{championshipId}/team/create")
	public String postCreationTeam(@Valid final Team team, final BindingResult result,
			@PathVariable("championshipId") final int championshipId, final ModelMap model, final Errors errors,
			Principal principal) {
		Championship championship = championshipService.findChampionshipId(championshipId);
		model.put("championship", championship);
		
		List<Team> joinedTeams = this.championshipService.findTeamsByChampionshipId(championshipId);
		for (Team t : joinedTeams) {
			if (t.getParticipants().contains(this.userService.findByUsername(principal.getName()))) {
				return "error-403";
			}
			if (t.getName().equals(team.getName())) {
				errors.rejectValue("name", "Ya existe un equipo con ese nombre en este torneo.",
						"Ya existe un equipo con ese nombre en este torneo.");
				break;
			}
		}

		if (team.getParticipants().size() >= championship.getSport().getNumberOfPlayersInTeam()) {
			errors.rejectValue("name", "El equipo ya está lleno.", "El equipo ya está lleno.");
		}

		if (!result.hasErrors()) {

			Pay pay = this.payService.findLastFinishedPayForChampionshipByUsername(principal.getName(), championshipId);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			team.setUser(this.userService.findByUsername(principal.getName()));
			if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium")) && pay == null) {
				return "redirect:/pay/championship/" + championshipId + "?teamName=" + team.getName();
			} else {
				Usuario usuario = userService.usuarioLogueado(principal.getName());
				team.setUser(usuario);
				team.setChampionship(championship);
				team.setTeamSize(championship.getSport().getNumberOfPlayersInTeam());
				this.championshipService.save(team);

				Chat chat = new Chat();
				chat.setChatType(this.chatService.findChatTypeById(2)); //TEAM
				chat.setTeam(team);
				this.chatService.saveChat(chat);

				usuario.setPuntos(usuario.getPuntos() + 2);
				initJoinChampionship(model, championship.getSport().getId(), championshipId, team.getId(), principal);
				return "redirect:/sports/" + championship.getSport().getId() + "/championships/" + championshipId;
			}

		} else {
			model.put("team", team);
			return "teams/createOrUpdateTeamForm";
		}
	}

	@GetMapping("/championships/{championshipId}/teams/{teamId}")
	public String teamDetails(ModelMap model, @PathVariable("championshipId") Integer championshipId,
			@PathVariable("teamId") Integer teamId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Team team = this.teamService.findTeamById(teamId);

		model.addAttribute("team", team);
		Collection<Match> matchesTeam = this.matchService.findMatchesByTeamId(teamId);

		model.addAttribute("matches", matchesTeam);
		model.addAttribute("championship", team.getChampionship());
		model.addAttribute("chatId", this.chatService.findChatIdByTeam1Id(teamId));

		Usuario usuario = userService.usuarioLogueado(principal.getName());
		model.addAttribute("userId", usuario.getId());
		List<Usuario> usuarios = team.getParticipants();

		if (team.getUser().equals(usuario)
				&& usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
			model.put("puedeEliminar", true);
		}

		if (usuarios.stream().anyMatch(u -> usuario.equals(u))) {
			model.put("leave", true);
		}

		return "teams/teamDetails";
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/generate1")
	public String initGenerateFirst(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);

		Collection<Usuario> participantes = this.championshipService.findParticipantsChampionship(championshipId);
		Usuario user = this.userService.findByUsername(principal.getName());
		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));
		Championship championship = this.championshipService.findChampionshipId(championshipId);

	
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			model.addAttribute("championshipObj", championship);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			if (participa) {
				
				if (championship.getMatches().size() > 0) {

					model.addAttribute("yagenerado", true);
					return "matches/listMatch";
				} else if ((Integer) championship.getTeams().size() == championship.getMaxTeams()) {

					if (championship.getTeams().stream().allMatch(t -> t.getParticipants().size() == t.getTeamSize())) {

						for (int i = 0; i < championship.getMaxTeams(); i = i + 2) {
							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(championship.getTeams().get(i));
							m.setTeam2(championship.getTeams().get(i + 1));
							m.setRonda(1);
							this.matchService.save(m);
						}

						return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
								+ championshipId + "/matches";
					} else {

						
						model.addAttribute("faltaParticipantes", true);
						return "matches/listMatch";
					}
				}

				else {
					
					model.addAttribute("faltaEquipos", true);
					return "matches/listMatch";
				}
			} else {
				
				model.addAttribute("noParticipa", true);
				return "matches/listMatch";
			}
	
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/generate2")
	public String initGenerateSecond(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);

		Collection<Usuario> participantes = this.championshipService.findParticipantsChampionship(championshipId);
		Usuario user = this.userService.findByUsername(principal.getName());
		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));
		Championship championship = this.championshipService.findChampionshipId(championshipId);

		
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			model.addAttribute("championshipObj", championship);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			if (participa) {
				if (championship.getMatches().size() == 0) {
					
					model.addAttribute("noprimera", true);
					return "matches/listMatch";
					
				} else if ((Integer) championship.getTeams().size() != championship.getMaxTeams()) {
					
					model.addAttribute("faltaEquipos", true);
					return "matches/listMatch";

				} else if (championship.getMatches().stream()
						.allMatch(m -> m.getPuntos1() != null && m.getPuntos2() != null && m.getPuntos3() != null
								&& m.getPuntos4() != null && m.getDateTime() != null)) {

					// PARA EQUIPOS DE 4

					if (championship.getMaxTeams() == 4 && championship.getMatches().size() == 2) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(0);
						Match partido2 = matches1ronda.get(1);
						
						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2) ) {

							
								Team ganadorPartido1  = this.championshipService.getGanadorPartido(partido1);
								Team ganadorPartido2 = this.championshipService.getGanadorPartido(partido2);

								Match m = new Match();
								m.setChampionship(championship);
								m.setTeam1(ganadorPartido1);
								m.setTeam2(ganadorPartido2);
								m.setRonda(2);
								this.matchService.save(m);

								return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
										+ championshipId + "/matches";

							

						} else {
							
							model.addAttribute("nocoinc", true);
							return "matches/listMatch";
						}

					}

					// PARA EQUIPOS DE 8

					else if (championship.getMaxTeams() == 8 && championship.getMatches().size() == 4) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(0);
						Match partido2 = matches1ronda.get(1);
						Team ganadorPartido2;
						Team ganadorPartido1;
						Match partido3 = matches1ronda.get(2);
						Match partido4 = matches1ronda.get(3);
						Team ganadorPartido3;
						Team ganadorPartido4;

						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2) 
								&& this.championshipService.coincideResultados(partido3) && this.championshipService.coincideResultados(partido4)) {

						
							
							ganadorPartido1 = this.championshipService.getGanadorPartido(partido1); 
							ganadorPartido2 = this.championshipService.getGanadorPartido(partido2); 
							ganadorPartido3 = this.championshipService.getGanadorPartido(partido3); 
							ganadorPartido4 = this.championshipService.getGanadorPartido(partido4); 


							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(ganadorPartido1);
							m.setTeam2(ganadorPartido2);
							m.setRonda(2);
							this.matchService.save(m);
							Match m2 = new Match();
							m2.setChampionship(championship);
							m2.setTeam1(ganadorPartido3);
							m2.setTeam2(ganadorPartido4);
							m2.setRonda(2);
							this.matchService.save(m2);

							return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
									+ championshipId + "/matches";

						} else {
							
							model.addAttribute("nocoinc", true);
							return "matches/listMatch";
						}

							

					}

					// PARA EQUIPOS DE 16

					else if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 8) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(0);
						Match partido2 = matches1ronda.get(1);
						Team ganadorPartido2;
						Team ganadorPartido1;
						Match partido3 = matches1ronda.get(2);
						Match partido4 = matches1ronda.get(3);
						Team ganadorPartido3;
						Team ganadorPartido4;
						Match partido5 = matches1ronda.get(4);
						Match partido6 = matches1ronda.get(5);
						Team ganadorPartido5;
						Team ganadorPartido6;
						Match partido7 = matches1ronda.get(6);
						Match partido8 = matches1ronda.get(7);
						Team ganadorPartido7;
						Team ganadorPartido8;

						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2) 
								&& this.championshipService.coincideResultados(partido3) && this.championshipService.coincideResultados(partido4)
								&& this.championshipService.coincideResultados(partido5) && this.championshipService.coincideResultados(partido6) 
								&& this.championshipService.coincideResultados(partido7) && this.championshipService.coincideResultados(partido8)) {
							

							ganadorPartido1 = this.championshipService.getGanadorPartido(partido1); 
							ganadorPartido2 = this.championshipService.getGanadorPartido(partido2); 
							ganadorPartido3 = this.championshipService.getGanadorPartido(partido3); 
							ganadorPartido4 = this.championshipService.getGanadorPartido(partido4); 

							ganadorPartido5 = this.championshipService.getGanadorPartido(partido5); 
							ganadorPartido6 = this.championshipService.getGanadorPartido(partido6); 
							ganadorPartido7 = this.championshipService.getGanadorPartido(partido7); 
							ganadorPartido8 = this.championshipService.getGanadorPartido(partido8); 

						
							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(ganadorPartido1);
							m.setTeam2(ganadorPartido2);
							m.setRonda(2);
							this.matchService.save(m);
							Match m2 = new Match();
							m2.setChampionship(championship);
							m2.setTeam1(ganadorPartido3);
							m2.setTeam2(ganadorPartido4);
							m2.setRonda(2);
							this.matchService.save(m2);
							Match m3 = new Match();
							m3.setChampionship(championship);
							m3.setTeam1(ganadorPartido5);
							m3.setTeam2(ganadorPartido6);
							m3.setRonda(2);
							this.matchService.save(m3);
							Match m4 = new Match();
							m4.setChampionship(championship);
							m4.setTeam1(ganadorPartido7);
							m4.setTeam2(ganadorPartido8);
							m4.setRonda(2);
							this.matchService.save(m4);
							
							return "redirect:/sports/" + championship.getSport().getId()
									+ "/championships/" + championshipId + "/matches";

						} else {
							
							model.addAttribute("nocoinc", true);
							return "matches/listMatch";
						}


					} else {
						
						model.addAttribute("yagenerada2", true);
						return "matches/listMatch";
					}

				} else {
					
					model.addAttribute("faltaresultados", true);
					return "matches/listMatch";

				}

			} else {
				
				model.addAttribute("noParticipa", true);
				return "matches/listMatch";
			}
	
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/generate3")
	public String initGenerateThird(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);

		Collection<Usuario> participantes = this.championshipService.findParticipantsChampionship(championshipId);
		Usuario user = this.userService.findByUsername(principal.getName());
		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("rondaActual", championship.getMatches().stream().max(Comparator.comparing(Match::getRonda))
				.map(x -> x.getRonda()).orElse(null));

		
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			model.addAttribute("championshipObj", championship);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			if (participa) {
				if (championship.getMatches().size() == 0) {
					
					model.addAttribute("noprimera", true);
					return "matches/listMatch";
					
				} else if (championship.getMaxTeams() == 8 && championship.getMatches().size() == 4) {
					
					model.addAttribute("nosegunda", true);
					return "matches/listMatch";
					
				} else if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 8) {
					
					model.addAttribute("nosegunda", true);
					return "matches/listMatch";
					
				} else if ((Integer) championship.getTeams().size() != championship.getMaxTeams()) {
					
					model.addAttribute("faltaEquipos", true);
					return "matches/listMatch";

				} else if (championship.getMatches().stream()
						.allMatch(m -> m.getPuntos1() != null && m.getPuntos2() != null && m.getPuntos3() != null
								&& m.getPuntos4() != null && m.getDateTime() != null)) {

					// PARA EQUIPOS DE 8

					if (championship.getMaxTeams() == 8 && championship.getMatches().size() == 6) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(4);
						Match partido2 = matches1ronda.get(5);
						Team ganadorPartido2;
						Team ganadorPartido1;
						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2) ) {

							
							ganadorPartido1 = this.championshipService.getGanadorPartido(partido1); 
							ganadorPartido2 = this.championshipService.getGanadorPartido(partido2); 
							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(ganadorPartido1);
							m.setTeam2(ganadorPartido2);
							m.setRonda(3);
							this.matchService.save(m);
							
							return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
									+ championshipId + "/matches";


						} else {
							
							model.addAttribute("nocoinc", true);
							return "matches/listMatch";
						}

					}

					// PARA EQUIPOS DE 16

					else if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 12) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(8);
						Match partido2 = matches1ronda.get(9);
						Team ganadorPartido2;
						Team ganadorPartido1;
						Match partido3 = matches1ronda.get(10);
						Match partido4 = matches1ronda.get(11);
						Team ganadorPartido3;
						Team ganadorPartido4;

						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2) 
								&& this.championshipService.coincideResultados(partido3) && this.championshipService.coincideResultados(partido4)) {
							
							
							ganadorPartido1 = this.championshipService.getGanadorPartido(partido1); 
							ganadorPartido2 = this.championshipService.getGanadorPartido(partido2); 
							ganadorPartido3 = this.championshipService.getGanadorPartido(partido3); 
							ganadorPartido4 = this.championshipService.getGanadorPartido(partido4); 

							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(ganadorPartido1);
							m.setTeam2(ganadorPartido2);
							m.setRonda(3);
							this.matchService.save(m);
							Match m2 = new Match();
							m2.setChampionship(championship);
							m2.setTeam1(ganadorPartido3);
							m2.setTeam2(ganadorPartido4);
							m2.setRonda(3);
							this.matchService.save(m2);
							return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
									+ championshipId + "/matches";

							} else {
								
								model.addAttribute("nocoinc", true);
								return "matches/listMatch";
							}

					} else {
						
						model.addAttribute("yagenerada3", true);
						return "matches/listMatch";
					}
				} else {
					
					model.addAttribute("faltaresultados", true);
					return "matches/listMatch";

				}

			} else {
				
				model.addAttribute("noParticipa", true);
				return "matches/listMatch";
			}
	
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/generate4")
	public String initGenerateFourth(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId, Principal principal) {

		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Collection<Usuario> participantes = this.championshipService.findParticipantsChampionship(championshipId);
		Usuario user = this.userService.findByUsername(principal.getName());
		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));
		Championship championship = this.championshipService.findChampionshipId(championshipId);


	
			Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);
			model.addAttribute("matches", matches);
			model.addAttribute("deporte", sportId);
			model.addAttribute("championship", championshipId);
			model.addAttribute("championshipObj", championship);
			model.addAttribute("rondaActual", championship.getMatches().stream()
					.max(Comparator.comparing(Match::getRonda)).map(x -> x.getRonda()).orElse(null));
			if (participa) {
				if (championship.getMatches().size() == 0) {
					
					model.addAttribute("noprimera", true);
					return "matches/listMatch";
					
				} else if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 8) {
					
					model.addAttribute("nosegunda", true);
					return "matches/listMatch";
					
				} else if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 12) {
					
					model.addAttribute("notercera", true);
					return "matches/listMatch";
					
				} else if ((Integer) championship.getTeams().size() != championship.getMaxTeams()) {
					
					model.addAttribute("faltaEquipos", true);
					return "matches/listMatch";

				} else if (championship.getMatches().stream()
						.allMatch(m -> m.getPuntos1() != null && m.getPuntos2() != null && m.getPuntos3() != null
								&& m.getPuntos4() != null && m.getDateTime() != null)) {

					// PARA EQUIPOS DE 16

					if (championship.getMaxTeams() == 16 && championship.getMatches().size() == 14) {

						Collection<Match> matches1rondaRaw = this.matchService
								.listMatchesByChampionship(championshipId);
						List<Match> matches1ronda = matches1rondaRaw.stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());

						Match partido1 = matches1ronda.get(12);
						Match partido2 = matches1ronda.get(13);
						Team ganadorPartido2;
						Team ganadorPartido1;
						if (this.championshipService.coincideResultados(partido1) && this.championshipService.coincideResultados(partido2)) {
							
							ganadorPartido1 = this.championshipService.getGanadorPartido(partido1); 
							ganadorPartido2 = this.championshipService.getGanadorPartido(partido2); 

							Match m = new Match();
							m.setChampionship(championship);
							m.setTeam1(ganadorPartido1);
							m.setTeam2(ganadorPartido2);
							m.setRonda(4);
							this.matchService.save(m);
							return "redirect:/sports/" + championship.getSport().getId() + "/championships/"
									+ championshipId + "/matches";

							} else {
								
								model.addAttribute("nocoinc", true);
								return "matches/listMatch";
							}

					}

					else {

						model.addAttribute("yagenerada4", true);
						return "matches/listMatch";
					}

				} else {
					
					model.addAttribute("faltaresultados", true);
					return "matches/listMatch";

				}

			} else {
				
				model.addAttribute("noParticipa", true);
				return "matches/listMatch";
			}
		
	}

	@GetMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/date")
	public String initAddDateMatch(final ModelMap model, @PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("matchId") final Integer matchId, Principal principal) {
		if(sportId>20) {
			return "error-500";
		}
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);

		Boolean existeChampionship = this.championshipService.existeChampionship(championshipId);
		Match match = this.matchService.findMatchById(matchId);
		List<Usuario> participantes = new ArrayList<>();
		participantes.addAll(match.getTeam1().getParticipants());
		participantes.addAll(match.getTeam2().getParticipants());

		Usuario user = this.userService.findByUsername(principal.getName());
		model.addAttribute("usuarioLog", user);

		boolean participa = participantes.stream().anyMatch(p -> p.equals(user));
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		model.addAttribute("rondaActual", championship.getMatches().stream().max(Comparator.comparing(Match::getRonda))
				.map(x -> x.getRonda()).orElse(null));
		if (existeChampionship) {
			if (participa) {

				model.addAttribute("match", match);
				model.addAttribute("championshipObj", championship);
				return "matches/addDateForm";
			} else {
				Collection<Match> matches = this.matchService.listMatchesByChampionship(championshipId);

				model.addAttribute("matches", matches);
				model.addAttribute("deporte", sportId);
				model.addAttribute("championship", championshipId);
				model.addAttribute("noParticipaDate", true);
				model.addAttribute("championshipObj", championship);
				return "matches/listMatch";
			}
		} else {
			return "error-500";
		}
	}

	@PostMapping("/sports/{sportId}/championships/{championshipId}/match/{matchId}/date")
	public String postAddDateMatch(@Valid final Match match, final BindingResult result, final ModelMap model,
			@PathVariable("sportId") final Integer sportId,
			@PathVariable("championshipId") final Integer championshipId,
			@PathVariable("matchId") final Integer matchId, final Errors errors) {

		Championship championship = this.championshipService.findChampionshipId(championshipId);
		List<Match> partidos = championship.getMatches().stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());
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
				errors.rejectValue("dateTime", "La fecha debe ser posterior a la actual.",
						"La fecha debe ser posterior a la actual.");
			}
			// PARA 4 EQUIPOS
			else if (championship.getTeams().size() == 4 && partidos.size() == 3) {
				if (partidos.get(0).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(1).getDateTime().isAfter(match.getDateTime()))
					errors.rejectValue("dateTime",
							"La fecha debe ser posterior a las de los partidos de la ronda previa",
							"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

			// PARA 8 EQUIPOS

			else if (championship.getMaxTeams() == 8 && partidos.size() == 6) {

				if (partidos.get(0).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(1).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(2).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(3).getDateTime().isAfter(match.getDateTime()))
					
				errors.rejectValue("dateTime", "La fecha debe ser posterior a las de los partidos de la ronda previa",
						"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

			else if (championship.getMaxTeams() == 8 && partidos.size() == 7) {

				if (partidos.get(0).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(1).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(2).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(3).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(4).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(5).getDateTime().isAfter(match.getDateTime()))
					
				errors.rejectValue("dateTime", "La fecha debe ser posterior a las de los partidos de la ronda previa",
						"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

			// PARA 16 EQUIPOS

			else if (championship.getMaxTeams() == 16 && partidos.size() == 12) {

				if (championship.getMatches().get(0).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(1).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(2).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(3).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(4).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(5).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(6).getDateTime().isAfter(match.getDateTime())
						|| championship.getMatches().get(7).getDateTime().isAfter(match.getDateTime()))
					
				errors.rejectValue("dateTime", "La fecha debe ser posterior a las de los partidos de la ronda previa",
						"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

			else if (championship.getMaxTeams() == 16 && partidos.size() == 14) {

				if (partidos.get(8).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(9).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(10).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(11).getDateTime().isAfter(match.getDateTime()))
					
				errors.rejectValue("dateTime", "La fecha debe ser posterior a las de los partidos de la ronda previa",
						"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

			else if (championship.getMaxTeams() == 16 && partidos.size() == 15) {

				if (partidos.get(12).getDateTime().isAfter(match.getDateTime())
						|| partidos.get(13).getDateTime().isAfter(match.getDateTime()))
					
				errors.rejectValue("dateTime", "La fecha debe ser posterior a las de los partidos de la ronda previa",
						"La fecha debe ser posterior a las de los partidos de la ronda previa");
			}

		}

		if (!result.hasErrors()) {
			Match matchToUpdate = this.matchService.findMatchById(matchId);
			BeanUtils.copyProperties(match, matchToUpdate, "id", "team1", "team2", "championship", "puntos1", "puntos2",
					"puntos3", "puntos4", "ronda");
			this.matchService.save(matchToUpdate);
			return "redirect:/sports/" + sportId + "/championships/" + championshipId + "/matches";
		} else {
			model.addAttribute("championshipObj", championship);
			model.put("championship", championshipId);
			List<Team> equipos = (List<Team>) this.matchService.findTeams(championshipId);
			model.addAttribute("equipos", equipos);
			return "matches/addDateForm";
		}
	}

	@GetMapping("/championships/{championshipId}/teams/{teamId}/leave")
	public String leaveTeam(ModelMap model, @PathVariable("championshipId") Integer championshipId,
			@PathVariable("teamId") Integer teamId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Championship championship = championshipService.findChampionshipId(championshipId);
		Team team = this.teamService.findTeamById(teamId);
		Collection<Match> matchesTeam = this.matchService.findMatchesByTeamId(teamId);

		model.addAttribute("championship", championship);
		model.addAttribute("team", team);
		model.addAttribute("matches", matchesTeam);

		List<Usuario> usuarios = team.getParticipants();
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		payService.deleteTeamUser(usuario.getId(), teamId);
		usuarios.remove(usuario);		

		this.championshipService.save(team);
		if (team.getUser().equals(usuario)) {
			Integer puntos = usuario.getPuntos() - 7;
			usuario.setPuntos(puntos);
			this.userService.saveUsuario(usuario);

			if (usuarios.size() == 0) {		
				Integer chatId = this.chatService.findChatIdByTeam1Id(teamId);	
				this.chatService.deleteById(chatId);
				teamService.delete(team);
				invitationService.deleteInvitationsByTeamId(teamId);
				return "redirect:/sports/" + championship.getSport().getId() + "/championships/" + championshipId;
				
			} else {
				team.setUser(usuarios.get(0));
				this.championshipService.save(team);
				Integer puntos2 = usuarios.get(0).getPuntos() + 2;
				usuarios.get(0).setPuntos(puntos2);
				userService.saveUsuario(usuarios.get(0));
			}

		} else {
			Integer puntos = usuario.getPuntos() - 5;
			usuario.setPuntos(puntos);
			this.userService.saveUsuario(usuario);
		}
		return "redirect:/championships/" + championshipId + "/teams/" + teamId;

	}

	@GetMapping("/championships/{championshipId}/teams/{teamId}/{userId}/delete")
	public String deleteTeamPlayer(ModelMap model, @PathVariable("championshipId") Integer championshipId,
			@PathVariable("teamId") Integer teamId, @PathVariable("userId") Integer userId, Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Championship championship = championshipService.findChampionshipId(championshipId);
		Team team = this.teamService.findTeamById(teamId);

		Collection<Match> matchesTeam = this.matchService.findMatchesByTeamId(teamId);

		List<Usuario> usuarios = team.getParticipants();
		Usuario usuario = userService.usuarioLogueado(principal.getName());
		Usuario deletedUser = this.userService.findUserById(userId);

		model.addAttribute("championship", championship);
		model.addAttribute("team", team);
		model.addAttribute("matches", matchesTeam);

		if (usuarios.stream().anyMatch(u -> usuario.equals(u))) {
			model.put("leave", true);
		}

		if (!team.getUser().getUser().getUsername().equals(principal.getName())) {
			model.put("loggedUserIsNotTheTeamOwner", true);
			return "error-403";
		} else {
			if (!deletedUser.equals(team.getUser())) {
				if (!usuario.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
					return "error-403";
				}
				usuarios.removeIf(u -> deletedUser.equals(u));
				this.championshipService.save(team);
				Integer puntos = deletedUser.getPuntos() - 5;
				deletedUser.setPuntos(puntos);
				this.userService.saveUsuario(deletedUser);
				model.put("eliminado", "Se ha eliminado el jugador correctamente.");
				return "teams/teamDetails";
			} else {
				model.put("userToDeleteIsTeamOwner", true);
				return "teams/teamDetails";
			}

		}

	}

}
