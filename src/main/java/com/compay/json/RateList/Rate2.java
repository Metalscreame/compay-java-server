package com.compay.json.RateList;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Rate2
{
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

    public Rate2() {    }

    public Rate2(ArrayList<Scale> scale) {
        this.scale = scale;
    }

    //теплоснабжение
    public Rate2(String value, String view, Attrs attrs) {
        this.value = value;
        this.view = view;
        this.attrs = attrs;
    }

    public Rate2(Float mainRate) {
        this.mainRate = mainRate;
    }

    public void setAttrs(Attrs attrs) { this.attrs = attrs; }
}

