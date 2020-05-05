package de.kaesdingeling.springtestexample.test;

import org.junit.jupiter.api.AfterAll;

import de.kaesdingeling.springtestexample.test.utils.MockServerUtils;
import okhttp3.mockwebserver.MockWebServer;

public class Controller {
	
	private static MockWebServer server;
	
	public static MockWebServer getServer() {
		if (server == null) {
			try {
				server = MockServerUtils.start(8070);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return server;
	}
	
	@AfterAll
	public static void detach() throws Exception {
		MockServerUtils.stop(server);
	}
}