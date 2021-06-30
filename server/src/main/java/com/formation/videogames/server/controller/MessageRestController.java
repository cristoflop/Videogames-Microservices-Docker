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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Meesages rest controller")
@RestController
@RequestMapping("/api")
public class MessageRestController {

	private MessageService messageService;

	private NewGameRequestPublisher newGameRequestPublisher;

	public MessageRestController(MessageService messageService, NewGameRequestPublisher newGameRequestPublisher) {
		this.messageService = messageService;
		this.newGameRequestPublisher = newGameRequestPublisher;
	}

	@Operation(summary = "Publica un mensaje de creacion de un juego en un topic")
	@ApiResponses(value = { @ApiResponse(responseCode = "202", description = "Envia un mensaje al topic") })
	@PostMapping("/games")
	public ResponseEntity<Void> save(@RequestBody NewGameDto game) throws JsonProcessingException {
		this.newGameRequestPublisher.sendMessage(game);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Devuelve una lista con todos los mensajes en la BD")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mensajes", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageDto.class))) }) })
	@GetMapping("/messages")
	public ResponseEntity<List<MessageDto>> findAll() {
		List<MessageDto> messages = this.messageService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(messages);
	}

}
