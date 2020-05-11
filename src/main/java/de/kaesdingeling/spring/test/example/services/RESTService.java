package de.kaesdingeling.spring.test.example.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import de.kaesdingeling.spring.test.example.constants.CONSTANTS;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * The `@Profile` Annotation makes this config only usable/visible for {@link CONSTANTS#PROFILE_DEV} or {@link CONSTANTS#PROFILE_INTEGRATION_TEST} or {@link CONSTANTS#PROFILE_CONFIG_REST} Profile
 * 
 * @created 11.05.2020 - 16:22:06
 * @author KaesDingeling
 * @version 0.1
 */
@Log4j2
@Configuration("rest-service")
@Profile({ CONSTANTS.PROFILE_DEV, CONSTANTS.PROFILE_INTEGRATION_TEST, CONSTANTS.PROFILE_CONFIG_REST })
public class RESTService {
	
	/**
	 * 
	 * 
	 * @created 11.05.2020 - 16:24:04
	 * @author KaesDingeling
	 */
	@Bean
	public void init_rest_service() {
		log.info("[LOAD] REST-Service");
	}
}