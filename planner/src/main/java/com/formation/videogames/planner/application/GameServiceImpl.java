package com.formation.videogames.planner.application;

import com.formation.videogames.planner.application.dto.GameDto;
import com.formation.videogames.planner.application.dto.NewGameDto;
import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
import com.formation.videogames.planner.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameDto> findAll() {
        return this.gameRepository
                .findAll()
                .stream()
                .map(item -> this.mapToGameDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public GameDto findOne(String id) {
        Game game = this.gameRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return this.mapToGameDto(game);
    }

    @Override
    public String save(NewGameDto game) {
        Game gameToSave = new Game(game.get());
        return this.gameRepository
                .save(gameToSave)
                .getId();
    }

    @Override
    public void delete(String id) {
        Game game = this.gameRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        this.gameRepository.delete(game);
    }

    private GameDto mapToGameDto(Game game) {
        return new GameDto(game.getId(), game.get());
    }

}
