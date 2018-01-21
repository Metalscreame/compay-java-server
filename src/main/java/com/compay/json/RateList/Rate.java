package com.compay.json.RateList;

import java.util.ArrayList;

public class Rate
{
    private String servId;

    public String getServId() { return this.servId; }

    public void setServId(String servId) { this.servId = servId; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String link;

    public String getLink() { return this.link; }

    public void setLink(String link) { this.link = link; }

    private String startDate;

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    private String calcMethod;

    public String getCalcMethod() { return this.calcMethod; }

    public void setCalcMethod(String calcMethod) { this.calcMethod = calcMethod; }

    private ArrayList<CounterScaleRate> counterScaleRate;

    public ArrayList<CounterScaleRate> getCounterScaleRate() { return this.counterScaleRate; }

    public void setCounterScaleRate(ArrayList<CounterScaleRate> counterScaleRate) { this.counterScaleRate = counterScaleRate; }

    private ArrayList<History> history;

    public ArrayList<History> getHistory() { return this.history; }

    public void setHistory(ArrayList<History> history) { this.history = history; }

    private CounterRate counterRate;

    public CounterRate getCounterRate() { return this.counterRate; }

    public void setCounterRate(CounterRate counterRate) { this.counterRate = counterRate; }

    private FixFormulaRate fixFormulaRate;

    public FixFormulaRate getFixFormulaRate() { return this.fixFormulaRate; }

    public void setFixFormulaRate(FixFormulaRate fixFormulaRate) { this.fixFormulaRate = fixFormulaRate; }

    private FixSumRate fixSumRate;

    public FixSumRate getFixSumRate() { return this.fixSumRate; }

    public void setFixSumRate(FixSumRate fixSumRate) { this.fixSumRate = fixSumRate; }

    public Rate(String servId, String name, String link, String startDate, String calcMethod, ArrayList<CounterScaleRate> counterScaleRate, ArrayList<History> history, CounterRate counterRate, FixFormulaRate fixFormulaRate, FixSumRate fixSumRate) {
        this.servId = servId;
        this.name = name;
        this.link = link;
        this.startDate = startDate;
        this.calcMethod = calcMethod;
        this.counterScaleRate = counterScaleRate;
        this.history = history;
        this.counterRate = counterRate;
        this.fixFormulaRate = fixFormulaRate;
        this.fixSumRate = fixSumRate;
    }
}