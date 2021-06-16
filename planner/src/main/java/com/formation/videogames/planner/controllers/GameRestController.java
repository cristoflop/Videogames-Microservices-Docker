package com.formation.videogames.planner.controllers;

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

import com.formation.videogames.planner.services.GameService;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

@RestController
@RequestMapping("/api")
public class GameRestController {

	private GameService gameService;

	public GameRestController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/games")
	public ResponseEntity<List<GameDto>> findAll(@RequestParam(required = false) String name) {
		List<GameDto> games;
		if (name != null) {
			games = this.gameService.findByName(name);
		} else {
			games = this.gameService.findAll();
		}
		return ResponseEntity.status(HttpStatus.OK).body(games);
	}

	@PostMapping("/games")
	public ResponseEntity<String> save(@RequestBody NewGameDto game) {
		String id = this.gameService.save(game);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@GetMapping("/games/{id}")
	public ResponseEntity<GameDto> findOne(@PathVariable("id") String id) {
		GameDto game = this.gameService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(game);
	}

	@DeleteMapping("/games/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		this.gameService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
