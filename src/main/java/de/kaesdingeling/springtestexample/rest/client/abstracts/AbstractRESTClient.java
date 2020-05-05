package de.kaesdingeling.springtestexample.rest.client.abstracts;

import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import de.kaesdingeling.springtestexample.data.ResponseGETFlux;
import de.kaesdingeling.springtestexample.data.ResponseGETMono;
import de.kaesdingeling.springtestexample.data.ResponsePOSTFlux;
import de.kaesdingeling.springtestexample.data.ResponsePOSTMono;
import de.kaesdingeling.springtestexample.data.interfaces.IRESTClientBasic;
import de.kaesdingeling.springtestexample.data.models.ConfigData;
import lombok.Getter;
import reactor.core.publisher.Mono;

public abstract class AbstractRESTClient {
	
	private WebClient webClient;
	
	@Getter
	private ConfigData config;
	
	public String getPath() {
		return null;
	}
	
	public WebClient getWebClient() {
		if (webClient == null) {
			if (config != null) {
				StringBuilder urlBuilder = new StringBuilder();
				
				urlBuilder.append("http");
				
				if (config.isHttps()) {
					urlBuilder.append("s");
				}
				
				urlBuilder.append("://").append(config.getUrl());
				
				if (config.isHttps()) {
					if (config.getPort() != 443) {
						urlBuilder.append(":");
						urlBuilder.append(config.getPort());
					}
				} else {
					if (config.getPort() != 80) {
						urlBuilder.append(":");
						urlBuilder.append(config.getPort());
					}
				}
				
				String path = getPath();
				
				if (StringUtils.isNoneBlank(path)) {
					urlBuilder.append("/");
					urlBuilder.append(path);
				}
				
				webClient = WebClient.create(urlBuilder.toString());
			} else {
				throw new IllegalStateException("config canot be null");
			}
		}
		
		return webClient;
	}
	
	public void changeConfig(ConfigData config) {
		this.config = config;
		
		webClient = null;
	}
	
	// GET
	
	public <Response> ResponseGETMono<Response> getMono(String uri, Class<Response> responseType) {
		return getMono(uri, responseType, MediaType.APPLICATION_JSON);
	}
	
	public <Response> ResponseGETFlux<Response> getFlux(String uri, Class<Response> responseType) {
		return getFlux(uri, responseType, MediaType.APPLICATION_JSON);
	}
	
	public <Response> ResponseGETMono<Response> getMono(String uri, Class<Response> responseType, MediaType... acceptableMediaTypes) {
		ResponseGETMono<Response> response = ResponseGETMono.<Response>builder()
				.https(config.isHttps())
				.url(config.getUrl())
				.port(config.getPort())
				.uri(uri)
				.responseType(responseType)
				.retry(() -> getMono(uri, responseType, acceptableMediaTypes))
				.build();
		
		return writeResponse(response, makeGETRequest(uri, acceptableMediaTypes), clientResponse -> response.setResponseData(clientResponse.bodyToMono(responseType).blockOptional().orElse(null)));
	}
	
	public <Response> ResponseGETFlux<Response> getFlux(String uri, Class<Response> responseType, MediaType... acceptableMediaTypes) {
		ResponseGETFlux<Response> response = ResponseGETFlux.<Response>builder()
				.https(config.isHttps())
				.url(config.getUrl())
				.port(config.getPort())
				.uri(uri)
				.responseType(responseType)
				.retry(() -> getFlux(uri, responseType, acceptableMediaTypes))
				.build();
		
		return writeResponse(response, makeGETRequest(uri, acceptableMediaTypes), clientResponse -> response.setResponseData(clientResponse.bodyToFlux(responseType).collectList().blockOptional().orElse(null)));
	}
	
	// POST
	
	public <Response, Request> ResponsePOSTMono<Request, Response> postMono(String uri, Class<Response> responseType, Class<Request> requestType, Request requestData) {
		return postMono(uri, responseType, requestType, requestData, MediaType.APPLICATION_JSON);
	}
	
	public <Response, Request> ResponsePOSTFlux<Request, Response> postFlux(String uri, Class<Response> responseType, Class<Request> requestType, Request requestData) {
		return postFlux(uri, responseType, requestType, requestData, MediaType.APPLICATION_JSON);
	}
	
	public <Response, Request> ResponsePOSTMono<Request, Response> postMono(String uri, Class<Response> responseType, Class<Request> requestType, Request requestData, MediaType... acceptableMediaTypes) {
		ResponsePOSTMono<Request, Response> response = ResponsePOSTMono.<Request, Response>builder()
				.https(config.isHttps())
				.url(config.getUrl())
				.port(config.getPort())
				.uri(uri)
				.responseType(responseType)
				.retry(() -> postMono(uri, responseType, requestType, requestData, acceptableMediaTypes))
				.build();
		
		return writeResponse(response, makePOSTRequest(uri, requestType, requestData, acceptableMediaTypes), clientResponse -> response.setResponseData(clientResponse.bodyToMono(responseType).blockOptional().orElse(null)));
	}
	
	public <Response, Request> ResponsePOSTFlux<Request, Response> postFlux(String uri, Class<Response> responseType, Class<Request> requestType, Request requestData, MediaType... acceptableMediaTypes) {
		ResponsePOSTFlux<Request, Response> response = ResponsePOSTFlux.<Request, Response>builder()
				.https(config.isHttps())
				.url(config.getUrl())
				.port(config.getPort())
				.uri(uri)
				.responseType(responseType)
				.retry(() -> postFlux(uri, responseType, requestType, requestData, acceptableMediaTypes))
				.build();
		
		return writeResponse(response, makePOSTRequest(uri, requestType, requestData, acceptableMediaTypes), clientResponse -> response.setResponseData(clientResponse.bodyToFlux(responseType).collectList().blockOptional().orElse(null)));
	}
	
	// General
	
	public Optional<ClientResponse> makeGETRequest(String uri, MediaType... acceptableMediaTypes) {
		Mono<ClientResponse> responseMono = getWebClient()
			.get()
			.uri(uri)
			.accept(acceptableMediaTypes)
			.exchange();
		
		return responseMono.blockOptional();
	}
	
	public <Request> Optional<ClientResponse> makePOSTRequest(String uri, Class<Request> requestType, Request requestData, MediaType... acceptableMediaTypes) {
		Mono<ClientResponse> responseMono = getWebClient()
			.post()
			.uri(uri)
			.accept(acceptableMediaTypes)
			.body(Mono.just(requestData), requestType)
			.exchange();
		
		return responseMono.blockOptional();
	}
	
	private <Response, R extends IRESTClientBasic<Response>> R writeResponse(R response, Optional<ClientResponse> clientResponseOptional, Consumer<ClientResponse> writeToBodyConsumer) {
		if (clientResponseOptional.isPresent()) {
			ClientResponse clientResponse = clientResponseOptional.get();
			
			response.setHttpStatus(clientResponse.statusCode());
			writeToBodyConsumer.accept(clientResponse);
		}
		
		return response;
	}
}