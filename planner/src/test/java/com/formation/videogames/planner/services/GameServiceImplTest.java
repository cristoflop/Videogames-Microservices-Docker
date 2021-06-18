package com.formation.videogames.planner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
import com.formation.videogames.planner.exception.ResourceNotFoundException;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;
import com.formation.videogames.planner.services.dto.NewGameDtoBuilder;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class GameServiceImplTest {

	@Autowired
	private GameServiceImpl gameService;

	@Autowired
	private GameRepository gameRepository;

	private List<Game> backup;

	@BeforeAll
	void setUp() {
		this.backup = this.gameRepository.findAll();
	}

	@AfterAll
	void end() {
		this.gameRepository.deleteAll();
		this.gameRepository.saveAll(this.backup);
	}

	@BeforeEach
	void eraseContent() {
		this.gameRepository.deleteAll();
	}

	@Test
	void testGivenGameNameWhenSearchForAnSpecificGameThenReturnTheGame() throws JsonProcessingException {
		NewGameDto newGame = new NewGameDtoBuilder().setValues("Minecraft", "Juego de cubos").build();
		this.gameService.save(newGame);
		GameDto game = this.gameService.findByName("Minecraft");
		assertEquals("Minecraft", game.getName());
	}

	@Test
	void testGivenGameNameWhenSearchForAnSpecificGameThenEspectExceptionIfNotExist() {
		assertThrows(ResourceNotFoundException.class, () -> this.gameService.findByName("Minecraft"));
	}

}
