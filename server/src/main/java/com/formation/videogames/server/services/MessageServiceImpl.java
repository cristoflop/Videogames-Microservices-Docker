package com.formation.videogames.server.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.videogames.server.data.MessageRepository;
import com.formation.videogames.server.domain.Event;
import com.formation.videogames.server.domain.Message;
import com.formation.videogames.server.services.dto.MessageDto;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepository;

	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public String saveCreation(String gameId) {
		Date now = new Date(System.currentTimeMillis());
		Message message = new Message(gameId, Event.CREATION, now);
		return this.messageRepository.save(message).getId();
	}

	public List<MessageDto> findAll() {
		return this.messageRepository.findAll().stream().map(this::mapToMessageDto).collect(Collectors.toList());
	}

	private MessageDto mapToMessageDto(Message message) {
		return new MessageDto(message.getId(), message.getGameId(), message.getEvent().toString(), message.getDate());
	}

}
