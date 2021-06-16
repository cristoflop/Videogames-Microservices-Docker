package com.formation.videogames.planner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
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
	void testGivenAttributeNameAndValueWhenSearchForAnSpecificGameTheReturnTheGame() {
		NewGameDto newGame = new NewGameDtoBuilder().addAttribute("name", "Minecraft")
				.addAttribute("description", "Juego de aventuras").build();

		this.gameService.save(newGame);
		List<GameDto> games = this.gameService.findByAttributeValueInData("name", "Minecraft");
		assertEquals("Minecraft", games.get(0).getGameData().get("name"));

	}

}
