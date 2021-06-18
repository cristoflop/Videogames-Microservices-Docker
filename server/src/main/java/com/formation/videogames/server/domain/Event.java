package com.formation.videogames.server.domain;

public enum Event {

	CREATION("creation"), DELETION("deletion");

	private String name;

	Event(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
