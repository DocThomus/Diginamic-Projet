package fr.durandal.durandalback.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommandController {

	@Autowired
	CommandDAO commandDAO;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/commands", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Command> getAllCommands() {
		return commandDAO.getAllCommands();
	}
	
	@PostMapping(value = "/addCommand", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('USER')")
	public void addProduct(@RequestBody NewCommand nc) {
		System.out.println("ADDCOMMAND " + nc);
		commandDAO.addCommand(nc);
	}
	
}
