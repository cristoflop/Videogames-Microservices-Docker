package com.formation.videogames.planner.services.dto;

import java.util.Map;

public class GameDto {

	private String id;

	private Map<String, Object> gameData;

	public GameDto(String id, Map<String, Object> gameData) {
		this.id = id;
		this.gameData = gameData;
	}

	public String getId() {
		return id;
	}

	public Map<String, Object> getGameData() {
		return gameData;
	}

	@Override
	public String toString() {
		return this.gameData.get("name").toString();
	}
}