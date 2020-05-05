package de.kaesdingeling.springtestexample.data.interfaces;

public interface IRESTRequest<Request> {
	
	public Class<Request> getRequestType();
	public void setRequestType(Class<Request> requestType);
	
	public Request getRequestData();
	public void setRequestData(Request request);
}