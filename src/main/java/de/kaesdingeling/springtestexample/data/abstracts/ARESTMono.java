package de.kaesdingeling.springtestexample.data.abstracts;

import java.util.function.Supplier;

import de.kaesdingeling.springtestexample.data.interfaces.IRESTClientBasic;
import de.kaesdingeling.springtestexample.data.interfaces.IRESTResponseMono;

public abstract class ARESTMono<Response, ResponseObject extends ARESTMono<Response, ResponseObject>> implements IRESTResponseMono<Response>, IRESTClientBasic<Response> {
	
	public abstract Supplier<ResponseObject> getRetry();
	
	public ResponseObject retry() {
		return getRetry().get();
	}
}