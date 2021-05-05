package com.formation.videogames.planner.infrastructure;

import com.formation.videogames.planner.application.GameService;
import com.formation.videogames.planner.domain.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    private GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = this.gameService.findAll();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    public ResponseEntity<String> save(@RequestBody Game game) {
        String savedGameId = this.gameService.save(game);
        return new ResponseEntity<>(savedGameId, HttpStatus.OK);
    }

}
