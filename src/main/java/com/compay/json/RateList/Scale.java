package com.compay.json.RateList;

public class Scale
{
    private int maxValue;

    public int getMaxValue() { return this.maxValue; }

    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }

    private double mainRate;

    public double getMainRate() { return this.mainRate; }

    public Scale(int maxValue, double mainRate) {
        this.maxValue = maxValue;
        this.mainRate = mainRate;
    }

    public void setMainRate(double mainRate) { this.mainRate = mainRate; }
}
