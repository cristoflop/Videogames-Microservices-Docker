package com.formation.videogames.server.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "messages")
public class Message {

	@Id
	private String id;

	@Field(name = "game_id")
	private String gameId;
	
	@Field(name = "event")
	private Event event;
	
	@Field(name = "date")
	private Date date;

	public Message(String gameId, Event event, Date date) {
		super();
		this.gameId = gameId;
		this.event = event;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
