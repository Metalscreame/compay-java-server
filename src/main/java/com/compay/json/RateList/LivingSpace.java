package com.compay.json.RateList;

public class LivingSpace
{
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private int val;

    public int getVal() { return this.val; }

    public void setVal(int val) { this.val = val; }

    private String units;

    public String getUnits() { return this.units; }

    public void setUnits(String units) { this.units = units; }

    public LivingSpace(String name, int val, String units) {
        this.name = name;
        this.val = val;
        this.units = units;
    }
}
