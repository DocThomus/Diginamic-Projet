package fr.durandal.durandalback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import fr.durandal.durandalback.user.AuthenticationService;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER-1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.and().addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
		.authorizeRequests()
		.antMatchers("/", "/test", "/user").permitAll()
		.anyRequest().authenticated();
	}
	
	@Bean
    public WebSessionIdResolver webSessionIdResolver() {
        HeaderWebSessionIdResolver resolver = new HeaderWebSessionIdResolver();
        resolver.setHeaderName("X-AUTH-TOKEN");
        return resolver;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new NoEncodingPasswordEncoder();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationService authService) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }
}