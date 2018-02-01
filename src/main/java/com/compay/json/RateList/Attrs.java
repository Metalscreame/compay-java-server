package com.compay.json.RateList;

public class Attrs
{
    private MainRate mainRate;

    public MainRate getMainRate() { return this.mainRate; }

    public void setMainRate(MainRate mainRate) { this.mainRate = mainRate; }

    private LivingArea livingArea;

    public LivingArea getLivingArea() { return this.livingArea; }

    public void setLivingArea(LivingArea livingArea) { this.livingArea = livingArea; }

    public Attrs(MainRate mainRate, LivingArea livingArea) {
        this.mainRate = mainRate;
        this.livingArea = livingArea;
    }
}
