package com.formation.videogames.planner.application;

import com.formation.videogames.planner.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    List<Game> findAll();

    String save(Game game);

}
