package com.formation.videogames.planner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.videogames.planner.amqp.NewGameResponsePublisher;
import com.formation.videogames.planner.data.GameRepository;
import com.formation.videogames.planner.domain.Game;
import com.formation.videogames.planner.exception.ResourceNotFoundException;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;
import com.formation.videogames.planner.services.dto.NewGameDtoBuilder;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class GameServiceImplTest {

	private GameServiceImpl gameService;

	private GameRepository gameRepository;

	private NewGameResponsePublisher publisher;

	@BeforeAll
	void setUp() {
		this.gameRepository = mock(GameRepository.class);
		this.publisher = mock(NewGameResponsePublisher.class);
		this.gameService = new GameServiceImpl(gameRepository, publisher);
	}

	@Test
	void testGivenGameNameWhenSearchForAnSpecificGameThenReturnTheGame() throws JsonProcessingException {
		NewGameDto newGame = new NewGameDtoBuilder().setValues("Minecraft", "Juego de cubos").build();
		Game game = new Game("Minecraft", "", null);

		when(gameRepository.save(ArgumentMatchers.any()))
				.thenReturn(game);
		when(gameRepository.findByName(ArgumentMatchers.any()))
				.thenReturn(Optional.empty())
				.thenReturn(Optional.of(game));

		this.gameService.save(newGame);
		GameDto gameDto = this.gameService.findByName("Minecraft");
		assertEquals("Minecraft", gameDto.getName());
	}

	@Test
	void testGivenGameNameWhenSearchForAnSpecificGameThenEspectExceptionIfNotExist() {
		when(gameRepository.findByName(ArgumentMatchers.any())).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> this.gameService.findByName("Minecraft"));
	}

}
