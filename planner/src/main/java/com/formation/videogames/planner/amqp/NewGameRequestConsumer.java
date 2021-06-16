package com.formation.videogames.planner.amqp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewGameRequestConsumer {

	@KafkaListener(topics = "messages")
	public void received(ConsumerRecord<?, ?> message) {

		System.out.println("Message: " + message.value());
	}

}
