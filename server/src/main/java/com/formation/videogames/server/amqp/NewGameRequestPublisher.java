package com.formation.videogames.server.amqp;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.videogames.server.services.dto.NewGameDto;

@Component
public class NewGameRequestPublisher {

	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String NEW_GAME_REQUEST_TOPIC = "new-game-request-topic";

	private ObjectMapper om;

	public NewGameRequestPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper om) {
		this.kafkaTemplate = kafkaTemplate;
		this.om = om;
	}

	public void sendMessage(NewGameDto game) throws JsonProcessingException {
		String gameJson = this.om.writeValueAsString(game);
		this.kafkaTemplate.send(NEW_GAME_REQUEST_TOPIC, gameJson);
	}

}
