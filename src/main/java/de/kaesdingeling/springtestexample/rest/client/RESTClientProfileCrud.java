package de.kaesdingeling.springtestexample.rest.client;

import de.kaesdingeling.springtestexample.data.models.ProfileData;
import de.kaesdingeling.springtestexample.rest.client.abstracts.AbstractCrudRESTClient;

public class RESTClientProfileCrud extends AbstractCrudRESTClient<ProfileData> {

	@Override
	public Class<ProfileData> getType() {
		return ProfileData.class;
	}

	@Override
	public String getCrudPath() {
		return "profile";
	}
}