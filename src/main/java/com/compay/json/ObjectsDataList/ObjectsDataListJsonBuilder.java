package com.compay.json.ObjectsDataList;

import com.compay.json.JsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectsDataListJsonBuilder implements JsonBuilder{

   // private ObjectsDataListEntityList list = new ObjectsDataListJsonBuilder();

    @Override
    public void addInfo(Object o) {

    }

    @Override
    public String createJson() throws JsonProcessingException {
       // String serialized = new ObjectMapper().writeValueAsString();
       // return serialized;
        return null;
    }
}
