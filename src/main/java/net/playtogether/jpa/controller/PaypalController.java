package net.playtogether.jpa.controller;

import java.security.Principal;
import java.time.LocalDate;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Order;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.PayType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.PayTypeService;
import net.playtogether.jpa.service.PaypalService;
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
	private PayTypeService payTypeService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@GetMapping("/pay/championship/{championshipId}/team/{teamId}")
	public String formPaymentJoinTeam(ModelMap model, Principal principal,
			@PathVariable("championshipId") Integer championshipId, @PathVariable("teamId") Integer teamId) {
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
		model.addAttribute("teamName", teamName);

		return "pay/createPaymentForm";
	}

	@GetMapping("/pay/championship/{championshipId}")
	public String formPaymentCreateTeam(ModelMap model, Principal principal,
			@PathVariable("championshipId") Integer championshipId, @RequestParam("teamName") String teamName) {
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
		model.addAttribute("teamName", teamName);

		return "pay/createPaymentForm";
	}

	@GetMapping("/pay/premium")
	public String formPaymentPremium(ModelMap model, Principal principal) {
		Order order = new Order();
		order.setIntent("sale");
		order.setCurrency("EUR");
		order.setMethod("paypal");
		order.setPrice(5);
		order.setDescription("Pago por premium.");
		model.addAttribute("order", order);
		model.addAttribute("premium", true);

		return "pay/createPaymentForm";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order, Principal principal, ModelMap model,
			@RequestParam("championshipId") Integer championshipId, @RequestParam("teamId") Integer teamId,
			@RequestParam("teamName") String teamName) {
		try {
			Payment payment = service.createPayment(Double.valueOf(order.getPrice()), order.getCurrency(),
					order.getMethod(), order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					this.payService.deleteAll(this.payService.findIdPaysNotFinishedByUsername(principal.getName()));
					Pay pay = new Pay();
					pay.setAmount(order.getPrice());
					Integer payTypeId = 1; // PREMIUM
					if (order.getPrice() == 2.) {
						payTypeId = 2; // CHAMPIONSHIP
						Championship championship = this.championshipService.findChampionshipId(championshipId);
						if (teamId != null) {
							Team team = this.championshipService.findTeamId(teamId);
							pay.setTeam(team);
						} else if (teamName != "") {
							Team team = new Team();
							Usuario usuario = usuarioService.usuarioLogueado(principal);
							team.setTeamCreator(usuario);
							team.setName(teamName);
							team.setChampionship(championship);
							team.setTeamSize(championship.getSport().getNumberOfPlayersInTeam());
							this.championshipService.save(team);
							pay.setTeam(team);
						}
						pay.setChampionship(championship);
					}
					PayType payType = this.payTypeService.findById(payTypeId);
					pay.setPayType(payType);
					Usuario usuario = usuarioService.findByUsername(principal.getName());
					pay.setUser(usuario);
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
	public String cancelPay(Principal principal) {
		Pay pay = this.payService.findLastPayByUsername(principal.getName());
		this.payService.delete(pay);
		return "pay/cancel";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Principal principal) {
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				Pay pay = this.payService.findLastPayByUsername(principal.getName());
				pay.setDate(LocalDate.now());
				payService.save(pay);
				if (pay.getPayType().getName().equals("Premium")) {
					return "redirect:/sports";
				} else if (pay.getTeam() == null) {
					return "redirect:/sports/" + pay.getChampionship().getSport().getId() + "/championships/"
							+ pay.getChampionship().getId();
				} else if (pay.getPayType().getName().equals("Championship")) {
					return "redirect:/sports/" + pay.getChampionship().getSport().getId() + "/championships/"
							+ pay.getChampionship().getId() + "/join/" + pay.getTeam().getId();
				}
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
}
