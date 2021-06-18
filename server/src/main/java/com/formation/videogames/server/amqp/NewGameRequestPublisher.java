package com.formation.videogames.server.amqp;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.videogames.server.services.dto.NewGameDto;

public class NewGameRequestPublisher {

	private KafkaTemplate<String, String> kafkaTemplate;

	private final String NEW_GAME_REQUEST_TOPIC = "new-game-request-topic";

	private ObjectMapper om;

	public NewGameRequestPublisher(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(NewGameDto game) throws JsonProcessingException {
		String gameJson = this.om.writeValueAsString(game);
		this.kafkaTemplate.send(NEW_GAME_REQUEST_TOPIC, gameJson);
	}

}
