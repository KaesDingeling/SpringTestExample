package de.kaesdingeling.springtestexample.rest.client;

import de.kaesdingeling.springtestexample.data.ResponsePOSTMono;
import de.kaesdingeling.springtestexample.data.models.LoginRequestData;
import de.kaesdingeling.springtestexample.data.models.ProfileData;
import de.kaesdingeling.springtestexample.rest.client.abstracts.AbstractRESTClient;

public class RESTClientLogin extends AbstractRESTClient {
	public ResponsePOSTMono<LoginRequestData, ProfileData> login(String username, String password) {
		return postMono("/login", ProfileData.class, LoginRequestData.class, LoginRequestData.builder()
				.username(username)
				.password(password)
				.build());
	}
}