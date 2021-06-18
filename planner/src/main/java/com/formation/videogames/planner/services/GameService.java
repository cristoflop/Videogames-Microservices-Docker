package com.formation.videogames.planner.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.videogames.planner.services.dto.GameDto;
import com.formation.videogames.planner.services.dto.NewGameDto;

import java.util.List;

@Service
public interface GameService {

	List<GameDto> findAll();

	GameDto findOne(String id);

	String save(NewGameDto game) throws JsonProcessingException;

	void delete(String id);

	GameDto findByName(String name);

}
