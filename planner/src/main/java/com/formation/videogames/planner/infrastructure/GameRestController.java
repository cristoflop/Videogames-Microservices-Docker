package com.formation.videogames.planner.infrastructure;

import com.formation.videogames.planner.application.GameService;
import com.formation.videogames.planner.application.dto.GameDto;
import com.formation.videogames.planner.application.dto.NewGameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    private GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GameDto>> findAll() {
        List<GameDto> games = this.gameService.findAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody NewGameDto game) {
        String id = this.gameService.save(game);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        this.gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
