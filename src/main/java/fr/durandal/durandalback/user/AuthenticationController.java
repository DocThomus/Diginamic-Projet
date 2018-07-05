package fr.durandal.durandalback.user;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@GetMapping("/user")
	public Principal current(Principal user) {
		return user;
	}
}
