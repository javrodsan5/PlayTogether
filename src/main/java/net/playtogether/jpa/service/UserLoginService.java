package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.Usuario;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PayService payService;
    
    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserTypeService userTypeService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username "+ username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		List<Pay> pay = payService.findLastPayByUsernamePremium(user.getUsername());		
		
		if(user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium")) && pay!=null) {
            LocalDate payDateStart = pay.get(pay.size()-1).getDate();
            LocalDate payDateEnd = payDateStart.plusMonths(1);
            LocalDate dateNow = LocalDate.now();
			if(!dateNow.isAfter(payDateEnd) && !dateNow.isBefore(payDateStart)) {
	            grantedAuthorities.add(new SimpleGrantedAuthority("premium"));
			}else {
                Usuario usuario = this.usuarioService.findByUsername(user.getUsername());
                usuario.setType(this.userTypeService.findUserTypeById(1)); //BÁSICO
                this.usuarioService.saveUsuarioAlreadyRegistered(usuario);
			}
        }
		grantedAuthorities.add(new SimpleGrantedAuthority("usuario"));
		return grantedAuthorities;
	}
    
}
