package com.formation.videogames.planner.infrastructure;

import com.formation.videogames.planner.application.GameService;
import com.formation.videogames.planner.application.dto.GameDto;
import com.formation.videogames.planner.application.dto.NewGameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@GetMapping("/prueba1")
	public ResponseEntity<Void> prueba1() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
