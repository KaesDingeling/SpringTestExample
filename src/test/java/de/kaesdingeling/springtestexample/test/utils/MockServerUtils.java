package de.kaesdingeling.springtestexample.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class MockServerUtils {
	public static boolean isGET(RecordedRequest request) {
		return (request != null && StringUtils.equalsAnyIgnoreCase(request.getMethod(), "GET"));
	}
	
	public static boolean isPOST(RecordedRequest request) {
		return (request != null && StringUtils.equalsAnyIgnoreCase(request.getMethod(), "POST"));
	}
	
	public static boolean isPath(RecordedRequest request, String path) {
		return (request != null && StringUtils.equals(request.getPath(), path));
	}
	
	public static MockResponse createResponse(int htmlCode, String body) {
		return new MockResponse()
				.setResponseCode(htmlCode)
				.setBody(body)
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}
	
	public static String readBody(RecordedRequest request) {
		try {
			return request.getBody().readUtf8();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static MockWebServer start(int port) throws Exception {
		MockWebServer server = new MockWebServer();
		
		server.start(port);
		
		return server;
	}
	
	public static void stop(MockWebServer server) throws Exception {
		server.shutdown();
	}
}