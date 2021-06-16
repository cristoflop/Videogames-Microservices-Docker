package com.formation.videogames.planner.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.videogames.planner.amqp.NewGameResponsePublisher;
import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
import com.formation.videogames.planner.exception.ResourceAlreadyExistsException;
import com.formation.videogames.planner.exception.ResourceNotFoundException;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	private GameRepository gameRepository;

	// TODO private NewGameResponsePublisher newGameResponsePublisher;

	public GameServiceImpl(GameRepository gameRepository, NewGameResponsePublisher newGameResponsePublisher) {
		this.gameRepository = gameRepository;
		// TODO this.newGameResponsePublisher = newGameResponsePublisher;
	}

	public GameRepository getGameRepository() {
		return gameRepository;
	}

	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public List<GameDto> findAll() {
		return this.gameRepository.findAll().stream().map(this::mapToGameDto).collect(Collectors.toList());
	}

	@Override
	public GameDto findOne(String id) {
		Game game = this.gameRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		return this.mapToGameDto(game);
	}

	@Override
	public String save(NewGameDto newGame) {
		Game game = this.gameRepository.findByName(newGame.getName()).orElse(null);
		if (game != null) {
			throw new ResourceAlreadyExistsException();
		}
		return this.gameRepository.save(this.mapToGame(newGame)).getId();
	}

	@Override
	public void delete(String id) {
		Game game = this.gameRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		this.gameRepository.delete(game);
	}

	@Override
	public GameDto findByName(String name) {
		Game game = this.gameRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
		return this.mapToGameDto(game);
	}

	private GameDto mapToGameDto(Game game) {
		return new GameDto(game.getId(), game.getName(), game.getDescription(), game.getTags());
	}

	private Game mapToGame(NewGameDto game) {
		return new Game(game.getName(), game.getDescription(), game.getTags());
	}

}
