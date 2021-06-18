package com.formation.videogames.planner.services.dto;

import java.util.List;

public class GameDto {

	private String id;

	private String name;

	private String description;

	private List<String> tags;

	public GameDto(String id, String name, String description, List<String> tags) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
