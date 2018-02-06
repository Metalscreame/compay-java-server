package com.compay.json.adminRateListPost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScalePost
{
    @JsonIgnoreProperties(ignoreUnknown = true)
    private int minValue;

    public int getMinValue() { return this.minValue; }

    public void setMinValue(int minValue) { this.minValue = minValue; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private int maxValue;

    public int getMaxValue() { return this.maxValue; }

    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private double mainRate;

    public double getMainRate() { return this.mainRate; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setMainRate(double mainRate) { this.mainRate = mainRate; }

}
