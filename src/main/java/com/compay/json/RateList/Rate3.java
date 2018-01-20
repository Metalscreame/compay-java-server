package com.compay.json.RateList;

public class Rate3
{
    private String val;

    public String getVal() { return this.val; }

    public void setVal(String val) { this.val = val; }

    private String units;

    public String getUnits() { return this.units; }

    public void setUnits(String units) { this.units = units; }

    public Rate3(String val, String units) {
        this.val = val;
        this.units = units;
    }
}
