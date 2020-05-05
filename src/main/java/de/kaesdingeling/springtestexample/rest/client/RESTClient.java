package de.kaesdingeling.springtestexample.rest.client;

import de.kaesdingeling.springtestexample.data.models.ConfigData;
import lombok.Getter;

public class RESTClient {
	
	@Getter
	private ConfigData config;
	
	@Getter
	private RESTClientLogin login;
	@Getter
	private RESTClientProfileCrud profileCurd;
	
	public static RESTClient init(ConfigData config) {
		return new RESTClient(config);
	}
	
	private RESTClient(ConfigData config) {
		this.config = config;
		
		login = new RESTClientLogin();
		profileCurd = new RESTClientProfileCrud();
		
		login.changeConfig(getConfig());
		profileCurd.changeConfig(getConfig());
	}
}