package com.compay.json.RateList;

public class CounterScaleRateHistory {
    private String rangeId;

    public String getRangeId() { return this.rangeId; }

    public void setRangeId(String rangeId) { this.rangeId = rangeId; }

    private String threshold;

    public String getThreshold() { return this.threshold; }

    public void setThreshold(String threshold) { this.threshold = threshold; }

    private Rate3 rate;

    public Rate3 getRate() { return this.rate; }

    public void setRate(Rate3 rate) { this.rate = rate; }

    public CounterScaleRateHistory(String rangeId, String threshold, Rate3 rate) {
        this.rangeId = rangeId;
        this.threshold = threshold;
        this.rate = rate;
    }
}
