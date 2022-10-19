package de.thro.inf.prg3.a06.model;

import lombok.Data;

@Data
public class JokesResponse {
	private String type;
	private Joke[] value;
}
