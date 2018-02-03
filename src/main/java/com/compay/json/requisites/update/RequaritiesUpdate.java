package com.compay.json.requisites.update;

import com.compay.json.requisites.Req;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequaritiesUpdate {

    @JsonProperty("objectID")
    public int objectID;
    @JsonProperty("serviceID")
    public int serviceID;
    @JsonProperty("req")
    public Req req;


    @JsonCreator
    public RequaritiesUpdate(@JsonProperty("objectID") int objectID,
                             @JsonProperty("serviceID") int serviceID,
                             @JsonProperty("req") Req req) {
        this.objectID = objectID;
        this.serviceID = serviceID;
        this.req = req;
    }

    RequaritiesUpdate(){

    }


    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }
}
