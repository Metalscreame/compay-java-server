package com.compay.json.RateList;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Attrs
{
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MainRate mainRate;

    public MainRate getMainRate() { return this.mainRate; }

    public void setMainRate(MainRate mainRate) { this.mainRate = mainRate; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LivingArea livingArea;

    public LivingArea getLivingArea() { return this.livingArea; }

    public void setLivingArea(LivingArea livingArea) { this.livingArea = livingArea; }

    public Attrs(){}

    public Attrs(MainRate mainRate, LivingArea livingArea) {
        this.mainRate = mainRate;
        this.livingArea = livingArea;
    }
}
