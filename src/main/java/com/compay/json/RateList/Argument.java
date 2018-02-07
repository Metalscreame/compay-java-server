package com.compay.json.RateList;

public class Argument
{
    private String view;

    public String getView() { return this.view; }

    public void setView(String view) { this.view = view; }

    private double value;

    public double getValue() { return this.value; }

    public void setValue(double value) { this.value = value; }

    public Argument(String view, double value) {
        this.view = view;
        this.value = value;
    }
}
