package com.compay.json.adminResponses.userList;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserListJsonBuilder implements JsonBuilder {
    private List list = new List();

    @Override
    public void addInfo(Object o) {
        list.getBody().add(o);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list);
        return serialized.substring(8, serialized.length() - 1);
    }
}

