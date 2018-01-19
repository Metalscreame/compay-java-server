package com.compay.json.RateList;


public class FixSumRate
{
    private String val;

    public String getVal() { return this.val; }

    public void setVal(String val) { this.val = val; }

    private String units;

    public String getUnits() { return this.units; }

    public void setUnits(String units) { this.units = units; }

    public FixSumRate(String val, String units) {
        this.val = val;
        this.units = units;
    }
}
