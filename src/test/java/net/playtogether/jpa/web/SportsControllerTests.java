package net.playtogether.jpa.web;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.service.SportService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SportsControllerTests {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private SportService sportService;

    @BeforeEach
    void setup() {
        BDDMockito.given(sportService.findAll()).willReturn(new ArrayList<Sport>());
    }

    @Test
    @WithMockUser(value = "user1", authorities="usuario")
    void testFindAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/sports")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("sports/sportListing"));
    }
    
}
