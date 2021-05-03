package net.playtogether.jpa.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.paypal.api.payments.Payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Order;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.PayType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.AuthoritiesService;
import net.playtogether.jpa.service.ChampionshipService;
import net.playtogether.jpa.service.InvitationService;
import net.playtogether.jpa.service.PayService;
import net.playtogether.jpa.service.PayTypeService;
import net.playtogether.jpa.service.PaypalService;
import net.playtogether.jpa.service.UserLoginService;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaypalControllerTests {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private PaypalService service;

	@MockBean
	private UsuarioService usuarioService;

	@MockBean
	private PayService payService;

	@MockBean
	private ChampionshipService championshipService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@MockBean
	private PayTypeService payTypeService;

	@MockBean
	private InvitationService invitationService;

	@MockBean
	private UserTypeService userTypeService;

	@MockBean
	private UserLoginService userLoginService;

    @BeforeEach
	void setup() throws Exception {

        Sport s = new Sport();
		SportType st = new SportType();
		st.setId(1);
		st.setName("Equipo");
		s.setChampionships(new ArrayList<Championship>());
		s.setId(1);
		s.setMeetings(new ArrayList<Meeting>());
		s.setName("Tenis");
		s.setSportType(st);
		s.setNumberOfPlayersInTeam(1);

		Championship testChampionship = new Championship();
		testChampionship.setName("Torneo8");
		testChampionship.setCity("Sevilla");
		testChampionship.setDescription("Descripcion del torneo");
		testChampionship.setStartDate(LocalDate.of(2021, 06, 15));
		testChampionship.setFinishDate(LocalDate.of(2021, 06, 25));
		testChampionship.setId(1);
		testChampionship.setMaxTeams(16);
		testChampionship.setMatches(new ArrayList<Match>());
		testChampionship.setSport(s);

        Usuario u = new Usuario();
		u.setId(1);
		u.setName("Usuario1");
		u.setCorreo("correo@correo.com");
		u.setBirthdate(LocalDate.of(1999, 3, 16));
		u.setPhone("123456789");
		u.setPayment(null);
		u.setStatistics(null);
		u.setType(null);
		u.setTeams(null);
		u.setMeetings(null);
		u.setPuntos(10);

		User user = new User();
		user.setUsername("user1");
		user.setPassword("password");
		user.setEnabled(true);

		u.setUser(user);

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(u);

        Team t = new Team();
		t.setChampionship(testChampionship);
		t.setId(1);
		t.setName("Equipo8");
		t.setParticipants(usuarios);
		t.setUser(u);

        Invitation inv = new Invitation();
        inv.setId(1);
        inv.setMeeting(null);
        inv.setName("Invitation");
        inv.setReceiver(u);
        inv.setTeam(t);

		Pay pay = new Pay();
		pay.setAmount(2.);
		pay.setChampionship(testChampionship);
		pay.setDate(LocalDate.now());
		pay.setId(1);
		pay.setInvitationId(1);
		PayType payType = new PayType();
		payType.setId(1);
		payType.setName("Championship");
		pay.setPayType(payType);
		pay.setTeam(t);
		pay.setUser(u);

		Payment payment = new Payment();
		payment.setState("approved");

        BDDMockito.given(this.championshipService.findChampionshipId(1)).willReturn(testChampionship);
        BDDMockito.given(this.championshipService.findTeamId(1)).willReturn(t);
        BDDMockito.given(this.invitationService.findById(1)).willReturn(inv);
        BDDMockito.given(this.usuarioService.findByUsername("user1")).willReturn(u);
		BDDMockito.given(this.payService.findIdPaysNotFinishedByUsername("user1")).willReturn(new ArrayList<Pay>());
		BDDMockito.given(this.payService.findLastPayByUsername("user1")).willReturn(pay);
		BDDMockito.given(this.payTypeService.findById(1)).willReturn(payType);
		BDDMockito.given(service.executePayment("1", "1")).willReturn(payment);
	}

    @Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentJoinTeamInvitationNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/championship/1/team/1?invitationId="))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/createPaymentForm"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("order", "payCham", "championshipId"));
    }

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentJoinTeamInvitationNotNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/championship/1/team/1?invitationId=1"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/createPaymentForm"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("order", "payCham", "championshipId", "invitationId", "isInvitation"));
    }

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentCreateTeam() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/championship/1?teamName=teamTest"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/createPaymentForm"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("order", "payCham", "championshipId"));
    }

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentCreateChampionship() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/championship/add?championshipId=1"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/createPaymentForm"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("order", "payCham", "championshipId"));
    }

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentPremiumOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/premium"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/createPaymentForm"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("order", "premium", "newChampionship"));
    }

	@Test
	//@WithMockUser(value = "user1", authorities="usuario")
    void testFormPaymentPremiumPrincipalNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/premium"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }

	@Test
	@WithMockUser(value = "user1", authorities={"usuario", "premium"})
    void testFormPaymentPremiumUserPremium() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/premium"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("error-403"));
    }

	@Test
	@WithMockUser(value = "user1", authorities="usuario")
    void testCancelPay() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pay/cancel"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("pay/cancel"));
    }
}
