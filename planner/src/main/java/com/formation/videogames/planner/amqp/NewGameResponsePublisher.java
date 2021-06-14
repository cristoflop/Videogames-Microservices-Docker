package com.formation.videogames.planner.amqp;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.formation.videogames.planner.application.dto.NewGameDto;

@Component
public class NewGameResponsePublisher {

	private KafkaTemplate<String, String> kafkaTemplate;

	private int numData;

	public NewGameResponsePublisher(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Scheduled(fixedRate = 1000)
	public void sendMessage(NewGameDto game) {

		String data = "Data " + numData++;

		System.out.println("publishToQueue: '" + data + "'");

		kafkaTemplate.send("messages", data);
	}

}