package com.formation.videogames.planner.amqp;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.videogames.planner.services.dto.GameDto;

@Component
public class NewGameResponsePublisher {

	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String NEW_GAME_RESPONSE_TOPIC = "new-game-response-topic";

	private ObjectMapper om;

	public NewGameResponsePublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper om) {
		this.kafkaTemplate = kafkaTemplate;
		this.om = om;
	}

	public void sendMessage(GameDto game) throws JsonProcessingException {
		String gameJson = this.om.writeValueAsString(game);
		this.kafkaTemplate.send(NEW_GAME_RESPONSE_TOPIC, gameJson);
	}

}
