package com.formation.videogames.planner.amqp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class NewGameRequestConsumer {
	
	private Logger logger = LoggerFactory.getLogger(NewGameRequestConsumer.class);

	@KafkaListener(topics = "messages")
	public void received(ConsumerRecord<?, ?> message) {
	

		System.out.println("Message: " + message.value());
	}

}
