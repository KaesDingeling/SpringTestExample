package de.kaesdingeling.springtestexample.rest.client.abstracts;

import org.springframework.http.MediaType;

import de.kaesdingeling.springtestexample.data.ResponseGETFlux;
import de.kaesdingeling.springtestexample.data.ResponsePOSTMono;
import de.kaesdingeling.springtestexample.data.interfaces.ICrudData;

public abstract class AbstractCrudRESTClient<CrudType extends ICrudData> extends AbstractRESTClient {
	
	@Override
	public String getPath() {
		return "api/v1/internal/" + getCrudPath();
	}
	
	public ResponseGETFlux<CrudType> list() {
		return getFlux("/list", getType());
	}
	
	public ResponsePOSTMono<CrudType, CrudType> update(CrudType requestData) {
		return postMono("/update", getType(), getType(), requestData);
	}
	
	public ResponsePOSTMono<String, CrudType> findById(String id) {
		return postMono("/findbyid", getType(), String.class, id);
	}
	
	public ResponsePOSTMono<String, Boolean> deleteById(String id) {
		return postMono("/deletebyid", Boolean.class, String.class, id, MediaType.TEXT_PLAIN);
	}
	
	public abstract Class<CrudType> getType();
	
	public abstract String getCrudPath();
}