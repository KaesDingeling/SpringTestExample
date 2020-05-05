package de.kaesdingeling.springtestexample.data;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;

import de.kaesdingeling.springtestexample.data.abstracts.ARESTMono;
import de.kaesdingeling.springtestexample.data.interfaces.IRESTRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResponsePOSTMono<Request, Response> extends ARESTMono<Response, ResponsePOSTMono<Request, Response>> implements IRESTRequest<Request> {
	
	// Request
	private Class<Request> requestType;
	private Request requestData;
	
	// Response
	private Class<Response> responseType;
	private Response responseData;
	
	private boolean https;
	private String url;
	private String uri;
	private int port;
	private HttpStatus httpStatus;
	
	private Supplier<ResponsePOSTMono<Request, Response>> retry;
}