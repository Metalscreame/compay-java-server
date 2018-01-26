package com.compay.json.requisites;

public class RequaritiesUpdate {

    public int objectID;

    public int serviceID;
    public Req req;

    public RequaritiesUpdate(int objectID, int serviceID, Req req) {
        this.objectID = objectID;
        this.serviceID = serviceID;
        this.req = req;
    }
}
