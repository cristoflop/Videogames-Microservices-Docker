package com.formation.videogames.server.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formation.videogames.server.domain.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

	List<Message> findByGameId(String gameId);

}
