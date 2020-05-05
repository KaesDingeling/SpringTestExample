package de.kaesdingeling.springtestexample.data.interfaces;

import java.util.List;

public interface IRESTResponseFlux<Response> {
	public List<Response> getResponseData();
	public void setResponseData(List<Response> response);
}