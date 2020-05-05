package de.kaesdingeling.springtestexample.test.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import de.kaesdingeling.springtestexample.data.interfaces.ICrudData;
import de.kaesdingeling.springtestexample.test.Controller;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public abstract class AbstractCrudTest<CrudType extends ICrudData> {
	
	public void setupDispatcher() {
		Controller.getServer().setDispatcher(new Dispatcher() {
			
			@Override
			public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
				if (MockServerUtils.isPOST(request)) {
					String body = MockServerUtils.readBody(request);
					
					if (body != null) {
						if (MockServerUtils.isPath(request, getPath() + "/findbyid")) {
							if (StringUtils.equals(body, findByIdMatch())) {
								return MockServerUtils.createResponse(200, new Gson().toJson(findByIdTest()));
							}
						} else if (MockServerUtils.isPath(request, getPath() + "/update")) {
							if (StringUtils.equals(body, new Gson().toJson(updateMatch()))) {
								return MockServerUtils.createResponse(200, new Gson().toJson(updateTest()));
							}
						} else if (MockServerUtils.isPath(request, getPath() + "/deletebyid")) {
							if (StringUtils.equals(body, deleteByIdMatch())) {
								return MockServerUtils.createResponse(200, "true");
							}
						}
					} else {
						return MockServerUtils.createResponse(403, "");
					}
				} else if (MockServerUtils.isGET(request)) {
					if (MockServerUtils.isPath(request, getPath() + "/list")) {
						return MockServerUtils.createResponse(200, new Gson().toJson(listTest()));
					}
				}
				
				return MockServerUtils.createResponse(404, "");
			}
		});
	}
	
	public String getPath() {
		return "/api/v1/internal/" + getCrudPath();
	}
	
	public abstract String findByIdMatch();
	public abstract CrudType findByIdTest();
	
	public abstract String deleteByIdMatch();
	
	public abstract CrudType updateMatch();
	public abstract CrudType updateTest();
	
	public abstract List<CrudType> listTest();
	
	public abstract Class<CrudType> getType();
	
	public abstract String getCrudPath();
	
	public abstract void test();
}