package de.kaesdingeling.springtestexample.data.models;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class LoginRequestData implements Serializable {
	private static final long serialVersionUID = -5402850636529381880L;
	
	private String username;
	private String password;
}