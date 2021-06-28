package com.formation.videogames.planner.amqp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.videogames.planner.exception.ResourceAlreadyExistsException;
import com.formation.videogames.planner.services.GameService;
import com.formation.videogames.planner.services.dto.NewGameDto;

@Component
public class NewGameRequestConsumer {

	private final Logger logger = LoggerFactory.getLogger(NewGameRequestConsumer.class);

	private ObjectMapper om;

	private GameService gameService;

	public NewGameRequestConsumer(ObjectMapper om, GameService gameService) {
		this.om = om;
		this.gameService = gameService;
	}

	@KafkaListener(topics = "new-game-request-topic")
	public void consume(ConsumerRecord<?, ?> message) throws JsonProcessingException {
		NewGameDto game = this.om.readValue(message.value().toString(), NewGameDto.class);
		try {
			String gameId = this.gameService.save(game);
			this.logger.info("Saved id: {}", gameId);
		} catch (ResourceAlreadyExistsException exc) {
			this.logger.info("Game already exists in database");
		}
	}

}
