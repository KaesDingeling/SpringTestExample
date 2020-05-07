package de.kaesdingeling.springtestexample.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;

import de.kaesdingeling.springtestexample.data.ResponsePOSTMono;
import de.kaesdingeling.springtestexample.data.models.ConfigData;
import de.kaesdingeling.springtestexample.data.models.LoginRequestData;
import de.kaesdingeling.springtestexample.data.models.ProfileData;
import de.kaesdingeling.springtestexample.rest.client.RESTClient;
import de.kaesdingeling.springtestexample.test.utils.MockServerUtils;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

@ExtendWith(MockitoExtension.class)
public class TestLoginApi {
	
	@Test
	public void test() {
		
		
		
		Controller.getServer().setDispatcher(new Dispatcher() {
			
			@Override
			public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
				if (MockServerUtils.isPOST(request)) {
					if (MockServerUtils.isPath(request, "/login")) {
						String body = MockServerUtils.readBody(request);
						
						if (body != null) {
							LoginRequestData loginData = new Gson().fromJson(body, LoginRequestData.class);
							
							if (loginData != null && StringUtils.equals(loginData.getUsername(), "admin") && StringUtils.equals(loginData.getPassword(), "bambus123")) {
								return MockServerUtils.createResponse(200, new Gson().toJson(ProfileData.builder()
										.id("login-test-id")
										.name("login-test-name")
										.build()));
							}
						} else {
							return MockServerUtils.createResponse(403, "");
						}
					}
				}
				
				return MockServerUtils.createResponse(404, "");
			}
		});
		
		RESTClient restClient = RESTClient.init(ConfigData.builder()
    			.https(false)
    			.url("localhost")
    			.port(8070)
    			.build());
		
		ResponsePOSTMono<LoginRequestData, ProfileData> loginResponse = restClient.getLogin().login("admin", "bambus123");
		
		assertEquals(loginResponse.getResponseData().getId(), "login-test-id");
		assertEquals(loginResponse.getResponseData().getName(), "login-test-name");
	}
}