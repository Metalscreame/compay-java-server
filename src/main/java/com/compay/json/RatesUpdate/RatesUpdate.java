package com.compay.json.RatesUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatesUpdate {

    private int objectID;

    private int serviceID;

    private String startDate;

    private int method;

    private Rate rate;

    public int getObjectID() {
        return objectID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getMethod() {
        return method;
    }

    public Rate getRate() {
        return rate;
    }
}
