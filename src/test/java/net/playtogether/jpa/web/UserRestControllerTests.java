package net.playtogether.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.service.InvitationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTests {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private InvitationService invitationService;

    @BeforeEach
    void setUp() {

        List<Invitation> list = new ArrayList<>();
        list.add(new Invitation());
        list.add(new Invitation());

        BDDMockito.given(this.invitationService.findMeetingInvitationsByUsername("user")).willReturn(list);

        list.add(new Invitation());
        BDDMockito.given(this.invitationService.findChampionshipInvitationsByUsername("user")).willReturn(list);
    }

    @Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
    void homeTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("welcome"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("invitaciones"))
        .andExpect(MockMvcResultMatchers.model().attribute("invitaciones", 6));
    }

    @Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
    void aboutUsTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/about-us"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("about-us"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("invitaciones"))
        .andExpect(MockMvcResultMatchers.model().attribute("invitaciones", 6));
    }

    @Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
    void termsAndConditionTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/terms-and-conditions"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("terms-and-conditions"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("invitaciones"))
        .andExpect(MockMvcResultMatchers.model().attribute("invitaciones", 6));
    }

    @Test
	@WithMockUser(username = "user", authorities = { "premium" }, password = "password")
    void CookiesPolicyTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cookies-policy"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("cookies-policy"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("invitaciones"))
        .andExpect(MockMvcResultMatchers.model().attribute("invitaciones", 6));
    }
    
}
