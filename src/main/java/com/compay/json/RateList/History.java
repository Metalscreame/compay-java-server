package com.compay.json.RateList;

import java.util.ArrayList;

public class History
{
    private String startDate;

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    private String calcMethod;

    public String getCalcMethod() { return this.calcMethod; }

    public void setCalcMethod(String calcMethod) { this.calcMethod = calcMethod; }

    private ArrayList<CounterScaleRateHistory> counterScaleRate;

    public ArrayList<CounterScaleRateHistory> getCounterScaleRate() { return this.counterScaleRate; }

    public void setCounterScaleRate(ArrayList<CounterScaleRateHistory> counterScaleRate) { this.counterScaleRate = counterScaleRate; }

    public History(String startDate, String calcMethod, ArrayList<CounterScaleRateHistory> counterScaleRate) {
        this.startDate = startDate;
        this.calcMethod = calcMethod;
        this.counterScaleRate = counterScaleRate;
    }
}
