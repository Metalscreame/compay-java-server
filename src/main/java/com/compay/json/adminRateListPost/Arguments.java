package com.compay.json.adminRateListPost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Arguments {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private double mainRate;

    public double getMainRate() {
        return this.mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private int livingArea;

    public int getLivingArea() {
        return this.livingArea;
    }

    public void setLivingArea(int livingArea) {
        this.livingArea = livingArea;
    }
}
