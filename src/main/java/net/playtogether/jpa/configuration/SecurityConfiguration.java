package net.playtogether.jpa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.playtogether.jpa.service.UserLoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
	private UserLoginService userLoginService;

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**","/webjars/**").permitAll()
		.antMatchers(HttpMethod.GET, "/","/error").permitAll()
		.antMatchers("**/pay/**").hasAnyAuthority("usuario")
		.antMatchers("/usuarios/**").hasAnyAuthority("usuario")
		.antMatchers("/myprofile/**").authenticated()
		.antMatchers("/clasification/**").authenticated()
		.antMatchers("/invitations/**").hasAnyAuthority("usuario")
		.antMatchers("/sports/*/meetings/**").hasAnyAuthority("usuario", "premium")
		.antMatchers("/sports/*/championships/**").hasAnyAuthority("usuario", "premium")
		.antMatchers("/championships/**").hasAnyAuthority("usuario", "premium")
		.antMatchers("/chat/**").authenticated()
		.antMatchers("/chats").authenticated()

		.anyRequest().permitAll()
		.and().csrf().disable()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error=true")
		.and()
			.logout()
				.logoutSuccessUrl("/"); 
    }

    @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userLoginService)      	      
	    .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
    
}
