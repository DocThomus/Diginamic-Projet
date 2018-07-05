package fr.durandal.durandalback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.durandal.durandalback.user.AuthenticationDAO;

@SpringBootApplication
public class DurandalBackApplication implements CommandLineRunner {
	
	@Autowired 
	AuthenticationDAO authenticationDAO;

	public static void main(String[] args) {
		SpringApplication.run(DurandalBackApplication.class, args);		
	}	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started !");
		mockups();	
	}
	
	public void mockups() {
		System.out.println("Running mockups");
		authenticationDAO.mockupUsers();
	}

	
}