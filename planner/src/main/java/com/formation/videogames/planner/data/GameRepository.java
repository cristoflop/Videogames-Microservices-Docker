package com.formation.videogames.planner.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formation.videogames.planner.domain.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
