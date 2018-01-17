package com.compay.json.Login;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginResponseBuilder implements JsonBuilder {

    private LoginResponseEntityList list= new LoginResponseEntityList();

    @Override
    public void addInfo(Object o) {
        list.getList().add(o);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list);
        return serialized.substring(9,serialized.length()-2);
    }
}
