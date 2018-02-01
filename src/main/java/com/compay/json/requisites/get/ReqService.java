package com.compay.json.requisites.get;

import com.compay.json.requisites.Req;

public class ReqService {


    private byte serviceID;

    private String serviceName;

    private Req req;

    public ReqService(byte serviceID, String serviceName, Req req) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.req = req;
    }


    public byte getServiceID() {
        return serviceID;
    }

    public void setServiceID(byte serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }
}
