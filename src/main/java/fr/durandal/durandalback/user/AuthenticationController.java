package fr.durandal.durandalback.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.durandal.durandalback.SecurityConfiguration;

@RestController
public class AuthenticationController {
	@Autowired
	SecurityConfiguration securityConfig;
	
	@Autowired
	AuthenticationDAO authDAO;

	@GetMapping("/user")
	public Principal current(Principal user) {
		return user;
	}
	
	@PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.CREATED)
	public Visitor addUser(@RequestBody Visitor v) {
		String passwordNotHashed = v.getHashedPassword();
		String passwordHashed = securityConfig.passwordEncoder().encode(passwordNotHashed);
		v.setHashedPassword(passwordHashed);
		authDAO.addUser(v);
		v.setHashedPassword(null);
		return v;		
	}


	@PutMapping(value = "/updateUser" , consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.ACCEPTED)
	public Visitor updateUser(@RequestBody Visitor v) {
		System.out.println("UPDATE " + v);
		String passwordNotHashed = v.getHashedPassword();
		String passwordHashed = securityConfig.passwordEncoder().encode(passwordNotHashed);
		v.setHashedPassword(passwordHashed);
		authDAO.updateUser(v);
		v.setHashedPassword(null);
		return v;	
	}
	
	@GetMapping(value="/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public Visitor current(@RequestParam String email) {
		return authDAO.getUserByEmail(email);
	}
	
}
