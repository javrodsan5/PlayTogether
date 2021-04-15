package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.User;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PayService payService;
    
    @Autowired
    private AuthoritiesService authoritiesService;
    
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
        
		Pay pay = payService.findLastPayByUsernamePremium(user.getUsername());		
		
		if(user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("premium")) && pay!=null) {
			if(!pay.getDate().isAfter(LocalDate.now().minusMonths(1))) {
	            grantedAuthorities.add(new SimpleGrantedAuthority("premium"));

			}else {
				Authorities a = user.getAuthorities().stream().filter(x->x.getAuthority().equals("premium")).findFirst().orElse(null);
				this.authoritiesService.delete(a);
			}
        }
		grantedAuthorities.add(new SimpleGrantedAuthority("usuario"));
		return grantedAuthorities;
	}
    
}
