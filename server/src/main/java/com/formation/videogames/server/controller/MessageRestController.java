package com.formation.videogames.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.videogames.server.amqp.NewGameRequestPublisher;
import com.formation.videogames.server.services.MessageService;
import com.formation.videogames.server.services.dto.MessageDto;
import com.formation.videogames.server.services.dto.NewGameDto;

@RestController
@RequestMapping("/api")
public class MessageRestController {

	private MessageService messageService;

	private NewGameRequestPublisher newGameRequestPublisher;

	public MessageRestController(MessageService messageService, NewGameRequestPublisher newGameRequestPublisher) {
		this.messageService = messageService;
		this.newGameRequestPublisher = newGameRequestPublisher;
	}

	@PostMapping("/games")
	public ResponseEntity<Void> save(@RequestBody NewGameDto game) throws JsonProcessingException {
		this.newGameRequestPublisher.sendMessage(game);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/messages")
	public ResponseEntity<List<MessageDto>> findAll() {
		List<MessageDto> messages = this.messageService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(messages);
	}

}
