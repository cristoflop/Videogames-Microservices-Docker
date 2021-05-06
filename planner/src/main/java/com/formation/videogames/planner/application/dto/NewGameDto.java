package com.formation.videogames.planner.application.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class NewGameDto {

    private Map<String, Object> data;

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
