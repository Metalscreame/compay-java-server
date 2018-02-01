package com.compay.json.adminResponses.rateList;

import com.compay.json.JsonBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class RateListBuilder implements JsonBuilder {
    @Override
    public void addInfo(Object o) {}

    @Override
    public String createJson() throws JsonProcessingException {
        return null;
    }


    public String createJson(ArrayList entity) throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(entity);
                return serialized;
    }
}
