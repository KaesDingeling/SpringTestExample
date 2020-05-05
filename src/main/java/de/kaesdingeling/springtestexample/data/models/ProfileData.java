package de.kaesdingeling.springtestexample.data.models;

import java.io.Serializable;

import de.kaesdingeling.springtestexample.data.interfaces.ICrudData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileData implements ICrudData, Serializable {
	private static final long serialVersionUID = 5809817992717967790L;
	
	private String id;
	private String name;
}