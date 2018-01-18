package com.compay.json.profile;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProfileBuilder implements JsonBuilder {

    @Override
    public void addInfo(Object o) {

    }

    @Override
    public String createJson() throws JsonProcessingException {
        return null;
    }

    public String createJson(ProfileEntity entity) throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(entity);
        return serialized;
    }
}
