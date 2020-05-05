package de.kaesdingeling.springtestexample.data.models;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class ConfigData {
	private boolean https;
	private String url;
	private int port;
	private long timeout; // in ms
}