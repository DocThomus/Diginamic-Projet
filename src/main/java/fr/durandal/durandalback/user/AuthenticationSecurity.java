package fr.durandal.durandalback.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSecurity implements UserDetailsService {

    @Autowired
    private AuthenticationDAO authenticationDAO;
    
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        
        Set<GrantedAuthority> authorities = new HashSet<>();        
        
        Visitor user = authenticationDAO.getUserByEmail(email);
        if (user.getIsAdmin()) {
        	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } 
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        UserDetails myUserDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getHashedPassword(), authorities);
                
        return myUserDetails;
    }

}