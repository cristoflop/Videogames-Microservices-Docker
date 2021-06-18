package com.formation.videogames.server.services;

import java.util.Date;

import com.formation.videogames.server.data.MessageRepository;
import com.formation.videogames.server.domain.Event;
import com.formation.videogames.server.domain.Message;

public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepository;

	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public String saveCreation(String gameId) {
		Message message = new Message(gameId, Event.CREATION, new Date("<YYYY-mm-ddTHH:MM:ss>"));
		return this.messageRepository.save(message).getId();
	}

}
