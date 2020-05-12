package de.kaesdingeling.spring.test.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.kaesdingeling.spring.test.example.constants.CONSTANTS;

/**
 * @created 11.05.2020 - 16:18:29
 * @author KaesDingeling
 * @version 0.1
 */
@SpringBootApplication
public class Main {
	
	/**
	 * 
	 * @param args
	 * @created 11.05.2020 - 16:23:11
	 * @author KaesDingeling
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Main.class);
		
		application.setAdditionalProfiles(CONSTANTS.PROFILE_DEV);
		
		application.run(args);
	}
}