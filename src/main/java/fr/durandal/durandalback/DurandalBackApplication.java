package fr.durandal.durandalback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("fr.durandal.durandalback")
@SpringBootApplication
public class DurandalBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DurandalBackApplication.class, args);
	}
}