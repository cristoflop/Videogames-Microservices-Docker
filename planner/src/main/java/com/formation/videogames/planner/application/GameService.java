package com.formation.videogames.planner.application;

import com.formation.videogames.planner.application.dto.GameDto;
import com.formation.videogames.planner.application.dto.NewGameDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    List<GameDto> findAll();

    String save(NewGameDto game);

    void delete(String id);

}
