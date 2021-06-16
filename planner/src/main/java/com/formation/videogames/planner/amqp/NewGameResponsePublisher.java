package com.formation.videogames.planner.amqp;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.formation.videogames.planner.services.dto.GameDto;

@Component
public class NewGameResponsePublisher {

	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String newGameResponseTopic = "new-game-response-topic";
	
	public NewGameResponsePublisher(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(GameDto game) {
		this.kafkaTemplate.send(newGameResponseTopic, "New game created - " + game);
	}

}
