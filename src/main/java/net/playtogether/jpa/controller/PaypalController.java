package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Chat;
import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Order;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.PayType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.AuthoritiesService;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.ChatService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.PayTypeService;
import net.playtogether.jpa.service.PaypalService;
import net.playtogether.jpa.service.TeamService;
import net.playtogether.jpa.service.UserLoginService;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@Controller
public class PaypalController {

	@Autowired
	private PaypalService service;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PayService payService;

	@Autowired
	private ChampionshipService championshipService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	private PayTypeService payTypeService;

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private ChatService chatService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	private final String messageTime = "Una vez que inicie el pago, si no se finaliza en 5 minutos, este se cancelará. ¡OJO! Si sale no podrá acceder de nuevo.";
	private final Integer minutesToFinishPay = 5;

	@GetMapping("/pay/championship/{championshipId}/team/{teamId}")
	public String formPaymentJoinTeam(ModelMap model, Principal principal,
			@PathVariable("championshipId") Integer championshipId, @PathVariable("teamId") Integer teamId,
			@RequestParam("invitationId") Integer invitationId) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Order order = new Order();
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		String teamName = this.championshipService.findTeamId(teamId).getName();
		order.setIntent("sale");
		order.setCurrency("EUR");
		order.setMethod("paypal");
		order.setPrice(2);
		order.setDescription("Pago por participar en el torneo " + championship.getName() + ".");
		model.addAttribute("order", order);
		model.addAttribute("payCham", true);
		model.addAttribute("championshipId", championshipId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("newChampionship", false);
		model.addAttribute("teamName", teamName);
		model.addAttribute("timeToDelete", messageTime);
		if (invitationId != null) {
			Invitation invitation = this.invitationService.findById(invitationId);
			if (invitation == null) {
				return "error-404";
			} else if (!invitation.getReceiver().getUser().getUsername().equals(principal.getName())) {
				return "error-403";
			}
			model.put("invitationId", invitationId);
			model.put("isInvitation", true);
		} else {
			model.put("isInvitation", false);
			model.put("invitationId", -1);
		}

		return "pay/createPaymentForm";
	}

	@GetMapping("/pay/championship/{championshipId}")
	public String formPaymentCreateTeam(ModelMap model, Principal principal,
			@PathVariable("championshipId") Integer championshipId, @RequestParam("teamName") String teamName) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Order order = new Order();
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		order.setIntent("sale");
		order.setCurrency("EUR");
		order.setMethod("paypal");
		order.setPrice(2);
		order.setDescription("Pago por participar en el torneo " + championship.getName() + ".");
		model.addAttribute("order", order);
		model.addAttribute("championshipId", championshipId);
		model.addAttribute("payCham", true);
		model.addAttribute("newTeam", true);
		model.addAttribute("teamName", teamName);
		model.addAttribute("newChampionship", false);
		model.addAttribute("timeToDelete", messageTime);
		model.addAttribute("championshipName", championship.getName());

		return "pay/createPaymentForm";
	}

	@GetMapping("/pay/championship/add")
	public String formPaymentCreateChampionship(ModelMap model, Principal principal,
			@RequestParam("championshipId") Integer championshipId) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Order order = new Order();
		Championship championship = this.championshipService.findChampionshipId(championshipId);
		order.setIntent("sale");
		order.setCurrency("EUR");
		order.setMethod("paypal");
		order.setPrice(2);
		order.setDescription("Pago por la creación del torneo: " + championship.getName() + ".");
		model.addAttribute("order", order);
		model.addAttribute("payCham", true);
		model.addAttribute("championshipId", championship.getId());
		model.addAttribute("newChampionship", true);
		model.addAttribute("timeToDelete", messageTime);
		Pay pay = new Pay();
		pay.setUser(this.usuarioService.findByUsername(principal.getName()));
		pay.setChampionship(championship);
		this.payService.save(pay);

		return "pay/createPaymentForm";
	}

	@GetMapping("/pay/premium")
	public String formPaymentPremium(ModelMap model, Principal principal) {
		if(principal!=null) {
			Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
			Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
			model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		}
		Order order = new Order();
		order.setIntent("sale");
		order.setCurrency("EUR");
		order.setMethod("paypal");
		order.setPrice(5);
		order.setDescription("Pago por premium.");
		if (principal != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Boolean isPremium = authentication.getAuthorities().contains(new SimpleGrantedAuthority("premium"));
			if (isPremium) {
				return "error-403";
			}
		} else {
			return "redirect:/login";
		}

		model.addAttribute("order", order);
		model.addAttribute("premium", true);
		model.addAttribute("newChampionship", false);
		model.addAttribute("timeToDelete", messageTime);

		return "pay/createPaymentForm";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order, Principal principal, ModelMap model,
			@RequestParam("championshipId") Integer championshipId, @RequestParam("teamId") Integer teamId,
			@RequestParam("teamName") String teamName, @RequestParam("newChampionship") Boolean newChampionship,
			@RequestParam("invitationId") Integer invitationId, @RequestParam("isInvitation") Boolean isInvitation) {
		try {
			Payment payment = service.createPayment(Double.valueOf(order.getPrice()), order.getCurrency(),
					order.getMethod(), order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					Pay pay = new Pay();
					if (!newChampionship) {
						this.payService.deleteAll(this.payService.findIdPaysNotFinishedByUsername(principal.getName()));
					} else {
						pay = this.payService.findLastPayByUsername(principal.getName());
					}
					pay.setAmount(order.getPrice());
					Integer payTypeId = 1; // PREMIUM
					if (order.getPrice() == 2.) {
						payTypeId = 2; // CHAMPIONSHIP
						Championship championship = this.championshipService.findChampionshipId(championshipId);
						if (teamId != null) {
							Team team = this.championshipService.findTeamId(teamId);
							pay.setTeam(team);
							if (isInvitation) {
								payTypeId = 3; // INVITATION
								pay.setInvitationId(invitationId);
							}
						} else if (teamName != "") {
							Team team = new Team();
							team.setName(teamName);
							team.setChampionship(championship);
							team.setTeamSize(championship.getSport().getNumberOfPlayersInTeam());
							team.setUser(usuarioService.findByUsername(principal.getName()));
							this.championshipService.save(team);

							Chat chat = new Chat();
							chat.setChatType(this.chatService.findChatTypeById(2)); //TEAM
							chat.setTeam(team);
							this.chatService.saveChat(chat);

							pay.setTeam(team);
						}
						pay.setChampionship(championship);
					}

					PayType payType = this.payTypeService.findById(payTypeId);
					pay.setPayType(payType);
					Usuario usuario = usuarioService.findByUsername(principal.getName());
					pay.setUser(usuario);
					pay.setInitDate(LocalDateTime.now());
					this.payService.save(pay);
					return "redirect:" + link.getHref();
				}
			}
		} catch (PayPalRESTException e) {

			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay(ModelMap model,Principal principal) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		return "pay/cancel";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Principal principal,ModelMap model) {
		Integer invitacionesQuedadas = this.invitationService.findMeetingInvitationsByUsername(principal.getName()).size();
		Integer invitacionesTorneos = this.invitationService.findChampionshipInvitationsByUsername(principal.getName()).size();
		model.addAttribute("invitaciones",invitacionesQuedadas+invitacionesTorneos);
		Pay pay = this.payService.findLastPayByUsername(principal.getName());
		if(Duration.between(pay.getInitDate(), LocalDateTime.now()).getSeconds() / 60 >= minutesToFinishPay) {
			ModelMap m = new ModelMap();
			model.addAttribute("timeOut", "El tiempo se ha agotado.");
			return this.cancelPay(m, principal);
		}
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				pay.setDate(LocalDate.now());
				payService.save(pay);
				if (pay.getPayType().getName().equals("Premium")) {
					Usuario user = this.usuarioService.findByUsername(principal.getName());
					user.setType(this.userTypeService.findUserTypeById(2)); //PREMIUM
					this.usuarioService.saveUsuarioAlreadyRegistered(user);
					if(!user.getUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium"))) {
						Authorities au = new Authorities();
						au.setAuthority("premium");
						au.setUser(user.getUser());
						this.authoritiesService.save(au);
					}
					return "pay/success";
				} else if (pay.getTeam() == null) {
					return "redirect:/sports/" + pay.getChampionship().getSport().getId() + "/championships/"
							+ pay.getChampionship().getId();
				} else if (pay.getPayType().getName().equals("Championship")) {
					return "redirect:/sports/" + pay.getChampionship().getSport().getId() + "/championships/"
							+ pay.getChampionship().getId() + "/join/" + pay.getTeam().getId();
				} else if (pay.getPayType().getName().equals("Invitation")) {
					Invitation invitation = this.invitationService.findById(pay.getInvitationId());
					invitation.getTeam().getParticipants().add(invitation.getReceiver());
					this.championshipService.save(invitation.getTeam());
					this.invitationService.delete(pay.getInvitationId());
					return "redirect:/sports/" + pay.getChampionship().getSport().getId() + "/championships/"
							+ pay.getChampionship().getId();
				}
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
}
