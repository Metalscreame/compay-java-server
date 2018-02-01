package com.compay.json.RateList;

public class MainRate
{
    private String view;

    public String getView() { return this.view; }

    public void setView(String view) { this.view = view; }

    private int value;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public MainRate(String view, int value) {
        this.view = view;
        this.value = value;
    }
}
