package de.kaesdingeling.springtestexample.data;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;

import de.kaesdingeling.springtestexample.data.abstracts.ARESTFlux;
import de.kaesdingeling.springtestexample.data.interfaces.IRESTRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResponsePOSTFlux<Request, Response> extends ARESTFlux<Response, ResponsePOSTFlux<Request, Response>> implements IRESTRequest<Request> {
	
	// Request
	private Class<Request> requestType;
	private Request requestData;
	
	// Response
	private Class<Response> responseType;
	private List<Response> responseData;
	
	private boolean https;
	private String url;
	private String uri;
	private int port;
	private HttpStatus httpStatus;
	
	private Supplier<ResponsePOSTFlux<Request, Response>> retry;
}