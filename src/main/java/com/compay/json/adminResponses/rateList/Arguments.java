package com.compay.json.adminResponses.rateList;

public class Arguments {
    public Double mainRate;
    public Double livingArea;

    public Arguments(Double mainRate, Double livingArea) {
        this.mainRate = mainRate;
        this.livingArea = livingArea;
    }

    public Double getMainRate() {
        return mainRate;
    }

    public void setMainRate(Double mainRate) {
        this.mainRate = mainRate;
    }

    public Double getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(Double livingArea) {
        this.livingArea = livingArea;
    }
}
