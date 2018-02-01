package com.compay.json.requisites.update;

import com.compay.json.requisites.Req;

public class RequaritiesUpdate {

    public int objectID;

    public int serviceID;
    public Req req;

    public RequaritiesUpdate(int objectID, int serviceID, Req req) {
        this.objectID = objectID;
        this.serviceID = serviceID;
        this.req = req;
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
