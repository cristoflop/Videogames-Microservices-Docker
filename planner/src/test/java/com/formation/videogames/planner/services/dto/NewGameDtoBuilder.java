package com.formation.videogames.planner.services.dto;

import java.util.HashMap;
import java.util.Map;

public class NewGameDtoBuilder {

	private Map<String, Object> data;

	public NewGameDtoBuilder() {
		this.data = new HashMap<>();
	}

	public NewGameDtoBuilder addAttribute(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public NewGameDto build() {
		NewGameDto result = new NewGameDto();
		this.data.forEach((key, value) -> result.add(key, value));
		return result;
	}

}
