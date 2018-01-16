package com.compay.json.calculation;

import java.util.Map;

//obj to add to arraylist in calcEntity
public class CalcServicesArrList {
    public int serviceID;
    public String serviceName;
    public Object method;
    //different methods

    public int lastCounter;
    public int currentCounter;
    public double currentSum;

    public CalcServicesArrList(int serviceID, String serviceName, Object method, int lastCounter, int currentCounter, double currentSum) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.method = method;
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

    public Object getMethod() {
        return method;
    }

    public void setMethod(Object method) {
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
