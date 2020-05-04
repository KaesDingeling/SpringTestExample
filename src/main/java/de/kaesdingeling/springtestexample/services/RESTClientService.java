package de.kaesdingeling.springtestexample.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import de.kaesdingeling.springtestexample.data.TestData;

@Service
public class RESTClientService {
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			TestData data = restTemplate.getForObject("localhost:4040/test", TestData.class);
			
			System.out.println("test: " + new Gson().toJson(data));
		};
	}
}