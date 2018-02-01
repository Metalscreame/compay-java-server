package com.compay.json.RateList;

import java.util.ArrayList;

public class History
{
    private String startDate;

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    private Method2 method;

    public Method2 getMethod() { return this.method; }

    public void setMethod(Method2 method) { this.method = method; }

    private Rate2 rate;

    public Rate2 getRate() { return this.rate; }

    public void setRate(Rate2 rate) { this.rate = rate; }

    public History(String startDate, Method2 method, Rate2 rate) {
        this.startDate = startDate;
        this.method = method;
        this.rate = rate;
    }
}
