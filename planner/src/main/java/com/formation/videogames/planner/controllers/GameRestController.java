package com.formation.videogames.planner.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.videogames.planner.services.GameService;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Game rest controller")
@RestController
@RequestMapping("/api")
public class GameRestController {

	private GameService gameService;

	public GameRestController(GameService gameService) {
		this.gameService = gameService;
	}

	@Operation(summary = "Devuelve una lista con todos los juegos de la BD o busca por nombre")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Libros encontrados", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDto.class))) }) })
	@GetMapping("/games")
	public ResponseEntity<List<GameDto>> findAll(@RequestParam(required = false) String name) {
		List<GameDto> games;
		if (name != null) {
			games = new ArrayList<>();
			games.add(this.gameService.findByName(name));
		} else {
			games = this.gameService.findAll();
		}
		return ResponseEntity.status(HttpStatus.OK).body(games);
	}

	@Operation(summary = "Guarda un juego en BD")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Juego guardado en BD") })
	@PostMapping("/games")
	public ResponseEntity<String> save(@RequestBody NewGameDto game) throws JsonProcessingException {
		String id = this.gameService.save(game);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@Operation(summary = "Devuelve un juego si existe")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Juego encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }) })
	@GetMapping("/games/{id}")
	public ResponseEntity<GameDto> findOne(@PathVariable("id") String id) {
		GameDto game = this.gameService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(game);
	}

	@Operation(summary = "Devuelve un juego si existe")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Juego borrado") })
	@DeleteMapping("/games/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		this.gameService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
