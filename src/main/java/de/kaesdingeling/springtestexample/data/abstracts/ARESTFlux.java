package de.kaesdingeling.springtestexample.data.abstracts;

import java.util.function.Supplier;

import de.kaesdingeling.springtestexample.data.interfaces.IRESTClientBasic;
import de.kaesdingeling.springtestexample.data.interfaces.IRESTResponseFlux;

public abstract class ARESTFlux<Response, ResponseObject extends ARESTFlux<Response, ResponseObject>> implements IRESTResponseFlux<Response>, IRESTClientBasic<Response> {
	
	public abstract Supplier<ResponseObject> getRetry();
	
	public ResponseObject retry() {
		return getRetry().get();
	}
}