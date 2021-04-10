package net.playtogether.jpa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.UserService;
import net.playtogether.jpa.service.UserTypeService;
import net.playtogether.jpa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UsuarioService usuarioService;
	
	@MockBean
	private UserTypeService userTypeService;
	

	private User u;
	
	private Usuario usuario;
	
	private UserType userType;
	
	@BeforeEach
	void setup() {
		usuario= new Usuario();
		usuario.setName("usuarioPr");
		usuario.setCorreo("correo@cor.com");
		usuario.setBirthdate(LocalDate.of(1999, 2, 14));
		usuario.setPhone("123456789");	
		
		u= new User();
		u.setUsername("user1");
		u.setPassword("password");
		
		usuario.setUser(u);
		
		userType = new UserType();
		userType.setId(1);
		userType.setName("Basico");
		
		given(this.userService.findUserByUsername("user1")).willReturn(u);
		given(this.userTypeService.findUserTypeById(1)).willReturn(userType);
		given(this.usuarioService.findUserById(1)).willReturn(usuario);
	}
	
	
	// Test de consultar un usuario externo
		@Test
		void getUser() throws Exception {
			this.mockMvc.perform(get("/usuarios/1")).andExpect(status().is2xxSuccessful());

			Usuario userEntity = usuarioService.findUserById(1);
			assertThat(userEntity.getName()).isEqualTo("usuarioPr");

	}

		// Test de consultar un usuario negative
		@Test
		void getUserNegative() throws Exception {
			this.mockMvc.perform(get("/usuarios/1")).andExpect(status().is2xxSuccessful());
			Usuario userEntity = usuarioService.findUserById(1);
			assertThat(userEntity.getName()).isNotEqualTo("usuarioPq");

		}
		
		
	// Test de consultar un usuario externo
				@Test
				void getMyProfile() throws Exception {
					this.mockMvc.perform(get("/myprofile/1")).andExpect(status().is2xxSuccessful());

					Usuario userEntity = usuarioService.findUserById(1);
					assertThat(userEntity.getName()).isEqualTo("usuarioPr");

			}

	// Test de consultar un usuario negative
				@Test
				void getMyProfileNegative() throws Exception {
					this.mockMvc.perform(get("/myprofile/1")).andExpect(status().is2xxSuccessful());
					Usuario userEntity = usuarioService.findUserById(1);
					assertThat(userEntity.getName()).isNotEqualTo("usuarioPq");

				}
	
		// Test de GetMapping crear usuario
			@Test
			void getCreateUser() throws Exception {
				this.mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/new")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.view().name("users/register")).andExpect(MockMvcResultMatchers.model().attributeExists("usuario"));
		
		}
			
		// Test de PostMapping de crear usuario
			@Test
			void createUser() throws Exception {

				mockMvc.perform(
						post("/usuarios/new")
						.with(csrf()).param("name", "Usuario")
						.param("correo", "usu@usuario.com")
						.param("birthdate", "1999/06/12")
						.param("phone", "666555888")
						.param("user.username", "usuarioPruebas")
						.param("user.password", "Usuar1o"))
						.andExpect(status().is3xxRedirection())
						.andExpect(view().name("redirect:/"));

			}
}
