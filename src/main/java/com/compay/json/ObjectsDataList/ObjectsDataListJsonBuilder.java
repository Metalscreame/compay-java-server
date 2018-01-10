package com.compay.json.ObjectsDataList;

import com.compay.json.JsonBuilder;
import com.compay.json.ObjectList.ObjectListEntityList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectsDataListJsonBuilder implements JsonBuilder{

    private ObjectDataListList list = new ObjectDataListList();

    @Override
    public void addInfo(Object o) {
        list.getAccountObjects().add(o);
    }

    public void addFullServiceList(Map serviceList){
        list.getServiceList().add(serviceList);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(list);
        return serialized;
    }


}
