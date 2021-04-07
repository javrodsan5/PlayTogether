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
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.PaypalService;
import net.playtogether.jpa.service.UserService;

@Controller
public class PaypalController {
    
    @Autowired
    private PaypalService service;

    @Autowired 
    private UserService userService;

    @Autowired
    private PayService payService;

    @Autowired
    private ChampionshipService championshipService;

    private Pay pay;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/pay/{id}/championship")
    public String formPaymentTournament(ModelMap model, Principal principal, @PathVariable("id") Integer championshipId) {
        Order order =  new Order();
        order.setIntent("sale");
        order.setCurrency("EUR");
        order.setMethod("paypal");
        order.setPrice(2);
        order.setDescription("Pago por torneo.");
        model.addAttribute("order", order);

        pay = new Pay();
        pay.setAmount(2);
        pay.setPayType(PayType.CHAMPIONSHIP);
        //User user = userService.findUserByUsername(principal.getName());
        User user = userService.findUserById(1);
        pay.setUser(user);
        Championship championship = this.championshipService.findChampionshipId(championshipId);
        pay.setChampionship(championship);
        return "pay/createPaymentForm";
    }

    @GetMapping("/pay/premium")
    public String formPaymentPremium(ModelMap model, Principal principal) {
        Order order =  new Order();
        order.setIntent("sale");
        order.setCurrency("EUR");
        order.setMethod("paypal");
        order.setPrice(5);
        order.setDescription("Pago por premium.");
        model.addAttribute("order", order);

        pay = new Pay();
        pay.setAmount(5);
        pay.setPayType(PayType.PREMIUM);
        //User user = userService.findUserByUsername(principal.getName());
        User user = userService.findUserById(1);
        pay.setUser(user);
        return "pay/createPaymentForm";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = service.createPayment(Double.valueOf(order.getPrice()), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "pay/cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                pay.setDate(LocalDate.now());
                payService.save(pay);
                return "pay/success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
