package com.compay.json.RateList;

public class Attrs
{
    private Rate4ForAttrs rate;

    public Rate4ForAttrs getRate() { return this.rate; }

    public Attrs(Rate4ForAttrs rate, LivingSpace livingSpace) {
        this.rate = rate;
        this.livingSpace = livingSpace;
    }

    public void setRate(Rate4ForAttrs rate) { this.rate = rate; }

    private LivingSpace livingSpace;

    public LivingSpace getLivingSpace() { return this.livingSpace; }

    public void setLivingSpace(LivingSpace livingSpace) { this.livingSpace = livingSpace; }
}
