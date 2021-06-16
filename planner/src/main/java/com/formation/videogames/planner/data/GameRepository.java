package com.formation.videogames.planner.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formation.videogames.planner.domain.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
	
	Optional<Game> findByName(String name);

	List<Game> findByDescriptionContaining(String description);

	List<Game> findByTagsContaining(String tag);

}
