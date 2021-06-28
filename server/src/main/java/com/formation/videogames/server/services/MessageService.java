package com.formation.videogames.server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.videogames.server.services.dto.MessageDto;

@Service
public interface MessageService {

	String saveCreation(String id);
	
	List<MessageDto> findAll();

}
