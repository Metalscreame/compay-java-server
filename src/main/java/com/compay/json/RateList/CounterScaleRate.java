package com.compay.json.RateList;

public class CounterScaleRate {

    private String rangeId;

    public String getRangeId() { return this.rangeId; }

    public void setRangeId(String rangeId) { this.rangeId = rangeId; }

    private String threshold;

    public String getThreshold() { return this.threshold; }

    public void setThreshold(String threshold) { this.threshold = threshold; }

    private Rate2 rate;

    public Rate2 getRate() { return this.rate; }

    public void setRate(Rate2 rate) { this.rate = rate; }

    public CounterScaleRate(String rangeId, String threshold, Rate2 rate) {
        this.rangeId = rangeId;
        this.threshold = threshold;
        this.rate = rate;
    }
}
