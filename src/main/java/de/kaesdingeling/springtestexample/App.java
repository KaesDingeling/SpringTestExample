package de.kaesdingeling.springtestexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.kaesdingeling.springtestexample.data.models.ConfigData;
import de.kaesdingeling.springtestexample.rest.client.RESTClient;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    public void test() {
    	RESTClient restClient = RESTClient.init(ConfigData.builder()
    			.https(false)
    			.url("localhost")
    			.port(8070)
    			.build());
    	
    	restClient.getProfileCurd().findById("test");
    }
}