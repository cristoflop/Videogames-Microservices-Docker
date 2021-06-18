package com.formation.videogames.server.amqp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.videogames.server.services.MessageService;
import com.formation.videogames.server.services.dto.GameDto;

@Component
public class NewGameResponseConsumer {

	private final Logger logger = LoggerFactory.getLogger(NewGameResponseConsumer.class);

	private ObjectMapper om;

	private MessageService messageService;

	public NewGameResponseConsumer(ObjectMapper om) {
		this.om = om;
	}

	@KafkaListener(topics = "new-game-response-topic")
	public void consume(ConsumerRecord<?, ?> message) throws JsonProcessingException {
		GameDto game = this.om.readValue(message.value().toString(), GameDto.class);
		String gameId = this.messageService.saveCreation(game.getId());
		this.logger.info("Saved game with id: {}", gameId);
	}

}
