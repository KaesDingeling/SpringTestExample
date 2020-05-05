package de.kaesdingeling.springtestexample.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import de.kaesdingeling.springtestexample.data.ResponseGETFlux;
import de.kaesdingeling.springtestexample.data.ResponsePOSTMono;
import de.kaesdingeling.springtestexample.data.models.ConfigData;
import de.kaesdingeling.springtestexample.data.models.ProfileData;
import de.kaesdingeling.springtestexample.rest.client.RESTClient;
import de.kaesdingeling.springtestexample.test.utils.AbstractCrudTest;

public class ProfileCrudTest extends AbstractCrudTest<ProfileData> {
	
	@Test
	public void test() {
		setupDispatcher();
		
		RESTClient restClient = RESTClient.init(ConfigData.builder()
    			.https(false)
    			.url("localhost")
    			.port(8070)
    			.build());
    	
		
		// FindById
		ResponsePOSTMono<String, ProfileData> findByIdReponse = restClient.getProfileCurd().findById(findByIdMatch());
		
		assertEquals(findByIdReponse.getResponseData().getId(), findByIdTest().getId());
		assertEquals(findByIdReponse.getResponseData().getName(), findByIdTest().getName());
		
		
		// DeleteById
		ResponsePOSTMono<String, Boolean> deleteByIdReponse = restClient.getProfileCurd().deleteById(deleteByIdMatch());
		
		assertTrue(deleteByIdReponse.getResponseData());
		
		
		// Update
		ResponsePOSTMono<ProfileData, ProfileData> updateReponse = restClient.getProfileCurd().update(updateMatch());
		
		assertEquals(updateReponse.getResponseData().getId(), updateTest().getId());
		assertEquals(updateReponse.getResponseData().getName(), updateTest().getName());
		
		
		// List
		ResponseGETFlux<ProfileData> listReponse = restClient.getProfileCurd().list();
		
		assertEquals(listReponse.getResponseData().size(), 2);
		
		boolean foundRestOne = false;
		boolean foundRestTwo = false;
		
		for (ProfileData profileData : listReponse.getResponseData()) {
			if (StringUtils.equals(profileData.getId(), "list-test-1-id")) {
				foundRestOne = true;
			} else if (StringUtils.equals(profileData.getId(), "list-test-2-id")) {
				foundRestTwo = true;
			}
		}

		assertTrue(foundRestOne);
		assertTrue(foundRestTwo);
	}

	@Override
	public String findByIdMatch() {
		return "findbyid-match";
	}

	@Override
	public ProfileData findByIdTest() {
		return ProfileData.builder()
				.id("findbyid-test-id")
				.name("findbyid-test-name")
				.build();
	}

	@Override
	public String deleteByIdMatch() {
		return "delete-by-id-match";
	}

	@Override
	public ProfileData updateMatch() {
		return ProfileData.builder()
				.id("update-match-id")
				.name("update-match-name")
				.build();
	}

	@Override
	public ProfileData updateTest() {
		return ProfileData.builder()
				.id("update-test-id")
				.name("update-test-name")
				.build();
	}

	@Override
	public List<ProfileData> listTest() {
		List<ProfileData> items = new ArrayList<ProfileData>();
		
		items.add(ProfileData.builder()
				.id("list-test-1-id")
				.name("list-test-1-name")
				.build());
		items.add(ProfileData.builder()
				.id("list-test-2-id")
				.name("list-test-2-name")
				.build());
		
		return items;
	}

	@Override
	public Class<ProfileData> getType() {
		return ProfileData.class;
	}

	@Override
	public String getCrudPath() {
		return "profile";
	}
}