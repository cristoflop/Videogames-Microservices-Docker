package com.formation.videogames.planner.services;

import org.springframework.stereotype.Service;

import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

import java.util.List;

@Service
public interface GameService {

	List<GameDto> findAll();

	GameDto findOne(String id);

	String save(NewGameDto game);

	void delete(String id);

	GameDto findByName(String name);

}
