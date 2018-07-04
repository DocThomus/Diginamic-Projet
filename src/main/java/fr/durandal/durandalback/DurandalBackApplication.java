package fr.durandal.durandalback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.durandal.durandalback.product.ProductDAO;

@SpringBootApplication
public class DurandalBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DurandalBackApplication.class, args);
	
		//ProductDAO.initProducts();
	}

	
}