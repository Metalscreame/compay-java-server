package com.compay.json.faq;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FaqJsonBuilder implements JsonBuilder {
    private FaqEntityList list = new FaqEntityList();

    @Override
    public void addInfo(Object o) { list.getFaqObjects().add(o); }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list);
        return serialized;
    }
}
