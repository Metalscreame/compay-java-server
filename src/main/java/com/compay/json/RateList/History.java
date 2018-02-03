package com.compay.json.RateList;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class History
{
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String startDate;

    public String getStartDate() { return this.startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Method2 method;

    public Method2 getMethod() { return this.method; }

    public void setMethod(Method2 method) { this.method = method; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Rate2 rate;

    public Rate2 getRate() { return this.rate; }

    public void setRate(Rate2 rate) { this.rate = rate; }

    public History(String startDate, Method2 method, Rate2 rate) {
        this.startDate = startDate;
        this.method = method;
        this.rate = rate;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<Scale> scale;

    public ArrayList<Scale> getScale() { return this.scale; }

    public void setScale(ArrayList<Scale> scale) { this.scale = scale; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Float mainRate;

    public Float getMainRate() { return this.mainRate; }

    public void setMainRate(Float mainRate) { this.mainRate = mainRate; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String value;

    public String getValue() { return this.value; }

    public void setValue(String value) { this.value = value; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String view;

    public String getView() { return this.view; }

    public void setView(String view) { this.view = view; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Attrs attrs;

    public Attrs getAttrs() { return this.attrs; }

    public void setAttrs(Attrs attrs) { this.attrs = attrs; }
}
