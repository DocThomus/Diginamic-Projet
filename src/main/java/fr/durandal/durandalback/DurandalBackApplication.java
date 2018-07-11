package fr.durandal.durandalback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import fr.durandal.durandalback.user.AuthenticationDAO;
import fr.durandal.durandalback.command.CommandDAO;
import fr.durandal.durandalback.product.ProductDAO;
import fr.durandal.durandalback.storage.StorageProperties;
import fr.durandal.durandalback.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DurandalBackApplication implements CommandLineRunner {
	
	@Autowired 
	AuthenticationDAO authenticationDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CommandDAO commandeDAO;

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
		productDAO.initProducts();
		commandeDAO.initCommands();
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
	    return (args) -> {
	         //storageService.deleteAll();
	         //storageService.init();
	    };
	}
}
