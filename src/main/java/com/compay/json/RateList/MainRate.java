package com.compay.json.RateList;

public class MainRate
{
    private String view;

    public String getView() { return this.view; }

    public void setView(String view) { this.view = view; }

    private Float value;

    public Float getValue() { return this.value; }

    public void setValue(Float value) { this.value = value; }

    public MainRate(String view, Float value) {
        this.view = view;
        this.value = value;
    }
}
