package com.formation.videogames.planner.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "games")
public class Game {

    @Id
    private String id;

    private Map<String, Object> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> get() {
        return data;
    }

    @JsonAnySetter
    public void add(String key, Object value) {
        if (this.data == null) data = new HashMap<>();
        this.data.put(key, value);
    }
}
