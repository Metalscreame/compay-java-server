package com.compay.json.requisites.get;

import com.compay.json.JsonBuilder;
import com.compay.json.requisites.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReqGetBuilder  implements JsonBuilder{

    RequisitesList requisitesList = new RequisitesList();

    @Override
    public void addInfo(Object o) {
        ReqService req = (ReqService) o;
        requisitesList.getRequisites().add(req);
    }

    @Override
    public String createJson() throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(requisitesList);
        return serialized;
    }
}
