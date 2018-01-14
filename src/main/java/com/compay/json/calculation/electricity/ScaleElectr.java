package com.compay.json.calculation.electricity;

public class ScaleElectr {
    public int minValue;
    public int maxValue;
    public int mainRate;

    public ScaleElectr(int minValue, int maxValue, int mainRate) {
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

    public int getMainRate() {
        return mainRate;
    }

    public void setMainRate(int mainRate) {
        this.mainRate = mainRate;
    }
}
