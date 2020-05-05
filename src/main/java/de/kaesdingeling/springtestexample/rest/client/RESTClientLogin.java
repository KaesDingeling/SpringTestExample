package de.kaesdingeling.springtestexample.rest.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import de.kaesdingeling.springtestexample.data.models.LoginRequestData;
import de.kaesdingeling.springtestexample.data.models.ProfileData;
import de.kaesdingeling.springtestexample.rest.client.abstracts.AbstractRESTClient;
import reactor.core.publisher.Mono;

public class RESTClientLogin extends AbstractRESTClient {
	public ProfileData login(String username, String password) {
		WebClient webClient = WebClient.create("http://localhost:8070");
		
		RequestHeadersSpec<?> request = webClient.post()
				.uri("login")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(LoginRequestData.builder()
						.username(username)
						.password(password)
						.build()), LoginRequestData.class);
		
		Mono<ProfileData> mono = request.retrieve().bodyToMono(ProfileData.class);
		
		System.out.println("test: " + mono.getClass().getName());
		System.out.println("test2: " + mono.toString());
		
		return mono.block();
	}
}