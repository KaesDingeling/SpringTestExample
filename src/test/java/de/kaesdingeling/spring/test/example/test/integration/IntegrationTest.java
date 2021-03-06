package de.kaesdingeling.spring.test.example.test.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import de.kaesdingeling.spring.test.example.Main;
import de.kaesdingeling.spring.test.example.configs.DBConfig;
import de.kaesdingeling.spring.test.example.configs.RESTConfig;
import de.kaesdingeling.spring.test.example.constants.CONSTANTS;
import de.kaesdingeling.spring.test.example.test.utils.annotations.JunitTestWithWebServer;
import lombok.extern.log4j.Log4j2;

/**
 * @created 11.05.2020 - 16:40:33
 * @author KaesDingeling
 * @version 0.1
 */
@Log4j2
@JunitTestWithWebServer(classes = Main.class)
@ActiveProfiles({ CONSTANTS.PROFILE_INTEGRATION_TEST })
public class IntegrationTest {
	
	@Autowired(required = false)
	private DBConfig dbConfig;
	
	@Autowired(required = false)
	private RESTConfig restConfig;
	
	@LocalServerPort
	private int serverPort;
	
	/**
	 * 
	 * 
	 * @created 11.05.2020 - 16:41:05
	 * @author KaesDingeling
	 */
	@Test
	public void test() {
		log.info("[INTEGRATION-TEST] init");
		
		assertNotNull(dbConfig);
		assertNotNull(restConfig);
		assertTrue(serverPort > 0);
		
		log.info("[TOMCAT] starts on port: " + serverPort);
		
		log.info("[INTEGRATION-TEST] finished");
	}
}