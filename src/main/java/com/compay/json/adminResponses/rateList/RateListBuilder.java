package com.compay.json.adminResponses.rateList;

import com.compay.json.JsonBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RateListBuilder implements JsonBuilder {
    @Override
    public void addInfo(Object o) {}

    @Override
    public String createJson() throws JsonProcessingException {
        return null;
    }


    public String createJson(RateListEntity entity) throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(entity);
                return "["+serialized.substring(4,serialized.length()-1)+"]";
    }
}
