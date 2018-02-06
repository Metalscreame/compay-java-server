package com.compay.json.reports;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportsBuilder implements JsonBuilder {
    @Override
    public void addInfo(Object o) {

    }

    @Override
    public String createJson() throws JsonProcessingException {
        return null;
    }

    public String createJson(ReportCalculation entity) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(entity);
    }
}
