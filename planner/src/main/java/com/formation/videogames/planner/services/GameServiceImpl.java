package com.formation.videogames.planner.services;

import com.formation.videogames.planner.amqp.NewGameResponsePublisher;
import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
import com.formation.videogames.planner.exception.ResourceNotFoundException;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	private GameRepository gameRepository;

	private MongoTemplate mongoTemplate;

	private NewGameResponsePublisher newGameResponsePublisher;

	public GameServiceImpl(GameRepository gameRepository, MongoTemplate mongoTemplate,
			NewGameResponsePublisher newGameResponsePublisher) {
		this.gameRepository = gameRepository;
		this.mongoTemplate = mongoTemplate;
		this.newGameResponsePublisher = newGameResponsePublisher;
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
	public String save(NewGameDto game) {
		Game gameToSave = new Game(game.get());
		
		Game savedGame = this.gameRepository.save(gameToSave);
		// this.newGameResponsePublisher.sendMessage(this.mapToGameDto(savedGame));
		return savedGame.getId();
	}

	@Override
	public void delete(String id) {
		Game game = this.gameRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		this.gameRepository.delete(game);
	}

	@Override
	public GameDto findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("data.name").is(name));
		Game game = this.mongoTemplate.findOne(query, Game.class);
		if (game == null)
			throw new ResourceNotFoundException();

		return this.mapToGameDto(game);
	}

	@Override
	public List<GameDto> findByAttributeValueInData(String key, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("data." + key).is(value));
		return this.mongoTemplate.find(query, Game.class).stream().map(this::mapToGameDto).collect(Collectors.toList());
	}

	@Override
	public List<GameDto> findByAttributeExistingInData(String key) {
		Query query = new Query();
		query.addCriteria(Criteria.where("data." + key));
		return this.mongoTemplate.find(query, Game.class).stream().map(this::mapToGameDto).collect(Collectors.toList());
	}

	private GameDto mapToGameDto(Game game) {
		return new GameDto(game.getId(), game.get());
	}

}
