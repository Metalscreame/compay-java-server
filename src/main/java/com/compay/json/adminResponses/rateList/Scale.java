package com.compay.json.adminResponses.rateList;

public class Scale {
    public int minValue;
    public int maxValue;
    public double mainRate;

    public Scale(int minValue, int maxValue, double mainRate) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.mainRate = mainRate;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public double getMainRate() {
        return mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }
}
