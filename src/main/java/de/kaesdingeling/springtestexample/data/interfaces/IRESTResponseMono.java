package de.kaesdingeling.springtestexample.data.interfaces;

public interface IRESTResponseMono<Response> {
	public Response getResponseData();
	public void setResponseData(Response response);
}