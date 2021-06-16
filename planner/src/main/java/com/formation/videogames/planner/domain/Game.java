package com.formation.videogames.planner.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "games")
public class Game {

	@Id
	private String id;

	@Field(name = "name")
	private String name;

	@Field(name = "description")
	private String description;

	@Field(name = "tags")
	private List<String> tags;

	public Game(String name, String description, List<String> tags) {
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
