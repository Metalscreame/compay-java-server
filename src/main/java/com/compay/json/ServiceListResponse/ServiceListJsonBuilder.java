package com.compay.json.ServiceListResponse;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceListJsonBuilder implements JsonBuilder{
    private ServiceListEntityList list = new ServiceListEntityList();

    @Override
    public void addInfo(Object o){
        list.getService().add(o);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list);
        return serialized;
    }
}
