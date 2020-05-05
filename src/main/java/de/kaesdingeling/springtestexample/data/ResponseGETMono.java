package de.kaesdingeling.springtestexample.data;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;

import de.kaesdingeling.springtestexample.data.abstracts.ARESTMono;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResponseGETMono<Response> extends ARESTMono<Response, ResponseGETMono<Response>> {
	
	// Response
	private Class<Response> responseType;
	private Response responseData;
	
	private boolean https;
	private String url;
	private String uri;
	private int port;
	private HttpStatus httpStatus;
	
	private Supplier<ResponseGETMono<Response>> retry;
}