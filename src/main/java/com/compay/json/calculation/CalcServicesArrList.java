package com.compay.json.calculation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalcServicesArrList {

    @JsonProperty("serviceID")
    public int serviceID;
    public String serviceName;
    public Method method;

    @JsonProperty("lastCounter")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int lastCounter;
    @JsonProperty("currentCounter")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int currentCounter;
    @JsonProperty("currentSum")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public double currentSum;

    public CalcServicesArrList(int serviceID, String serviceName, Method method, int lastCounter, int currentCounter, double currentSum) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.method = method;
        this.lastCounter = lastCounter;
        this.currentCounter = currentCounter;
        this.currentSum = currentSum;
    }

    @JsonCreator
    public CalcServicesArrList(@JsonProperty("serviceID") int serviceID, @JsonProperty("lastCounter") int lastCounter, @JsonProperty("currentCounter") int currentCounter, @JsonProperty("currentSum") double currentSum) {
        this.serviceID = serviceID;
        this.lastCounter = lastCounter;
        this.currentCounter = currentCounter;
        this.currentSum = currentSum;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getLastCounter() {
        return lastCounter;
    }

    public void setLastCounter(int lastCounter) {
        this.lastCounter = lastCounter;
    }

    public int getCurrentCounter() {
        return currentCounter;
    }

    public void setCurrentCounter(int currentCounter) {
        this.currentCounter = currentCounter;
    }

    public double getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(double currentSum) {
        this.currentSum = currentSum;
    }
}
