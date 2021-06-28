package com.formation.videogames.server.services.dto;

import java.util.Date;

public class MessageDto {

	private String id;

	private String gameId;

	private String event;

	private Date date;

	public MessageDto(String id, String gameId, String event, Date date) {
		this.id = id;
		this.gameId = gameId;
		this.event = event;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public String getGameId() {
		return gameId;
	}

	public String getEvent() {
		return event;
	}

	public Date getDate() {
		return date;
	}

}
