package com.compay.json.RateList;

import com.compay.json.JsonBuilder;
import com.compay.json.ObjectList.ObjectListEntityList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class RatesBuilder implements JsonBuilder {
    private ObjectListEntityList list = new ObjectListEntityList();

    @Override
    public void addInfo(Object o) {
        list.getAccountObjects().add(o);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list.getAccountObjects());
        return serialized;
    }
}
