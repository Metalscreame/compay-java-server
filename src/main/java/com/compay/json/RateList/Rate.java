package com.compay.json.RateList;

import java.util.ArrayList;

public class Rate
{
    private int serviceID;

    public int getServiceID() { return this.serviceID; }

    public void setServiceID(int serviceID) { this.serviceID = serviceID; }

    private String serviceName;

    public String getServiceName() { return this.serviceName; }

    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    private String startDate;

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    private Method2 method;

    public Method2 getMethod() { return this.method; }

    public void setMethod(Method2 method) { this.method = method; }

    private Object rate;

    public Object getRate() { return this.rate; }

    public void setRate(Object rate) { this.rate = rate; }

    private ArrayList<History> history;

    public Rate(int serviceID, String serviceName, String startDate, Method2 method, Object rate, ArrayList<History> history) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.method = method;
        this.rate = rate;
        this.history = history;
    }


    public Rate(int serviceID, String serviceName, String startDate, Method2 method, Object rate) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.method = method;
        this.rate = rate;
    }

    public ArrayList<History> getHistory() { return this.history; }

    public void setHistory(ArrayList<History> history) { this.history = history; }
}
