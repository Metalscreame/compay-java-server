package com.compay.json.calculation;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculationBuilder implements JsonBuilder {
    @Override
    public void addInfo(Object o) {

    }

    @Override
    public String createJson() throws JsonProcessingException {
        return null;
    }


    public String createJson(CalculationEntity entity) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(entity);
    }
}
