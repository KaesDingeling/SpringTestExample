package de.kaesdingeling.springtestexample.data.interfaces;

import org.springframework.http.HttpStatus;

public interface IRESTClientBasic<Response> {
	public boolean isHttps();
	public String getUrl();
	public String getUri();
	public int getPort();
	public HttpStatus getHttpStatus();
	public Class<Response> getResponseType();

	public void setHttps(boolean https);
	public void setUrl(String url);
	public void setUri(String uri);
	public void setPort(int port);
	public void setHttpStatus(HttpStatus httpStatus);
	public void setResponseType(Class<Response> responseType);
}