package com.formation.videogames.planner.services.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewGameDtoBuilder {

	private String name;

	private String description;

	private List<String> tags;

	public NewGameDtoBuilder() {
		this.tags = new ArrayList<>();
	}

	public NewGameDtoBuilder setValues(String name, String description, String... tags) {
		this.name = name;
		this.description = description;
		this.tags = Arrays.asList(tags);
		return this;
	}

	public NewGameDtoBuilder setDescription(String name) {
		this.name = name;
		return this;
	}

	public NewGameDto build() {
		return new NewGameDto(this.name, this.description, this.tags);
	}

}
