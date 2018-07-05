package fr.durandal.durandalback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import fr.durandal.durandalback.product.ProductDAO;
import fr.durandal.durandalback.storage.StorageProperties;
import fr.durandal.durandalback.storage.StorageService;

@SpringBootApplication@EnableConfigurationProperties(StorageProperties.class)
public class DurandalBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DurandalBackApplication.class, args);
	
		//ProductDAO.initProducts();
		
		
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
	    return (args) -> {
	         storageService.deleteAll();
	         storageService.init();
	    };
	}
}