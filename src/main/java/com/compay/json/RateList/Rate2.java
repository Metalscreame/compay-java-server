package com.compay.json.RateList;


import java.util.ArrayList;

public class Rate2
{
    private ArrayList<Scale> scale;

    public ArrayList<Scale> getScale() { return this.scale; }

    public void setScale(ArrayList<Scale> scale) { this.scale = scale; }

    private Double mainRate;

    public Double getMainRate() { return this.mainRate; }

    public void setMainRate(Double mainRate) { this.mainRate = mainRate; }

    private String value;

    public String getValue() { return this.value; }

    public void setValue(String value) { this.value = value; }

    private String view;

    public String getView() { return this.view; }

    public void setView(String view) { this.view = view; }

    private Attrs attrs;

    public Attrs getAttrs() { return this.attrs; }


    public Rate2(ArrayList<Scale> scale) {
        this.scale = scale;
    }

    //теплоснабжение
    public Rate2(String value, String view, Attrs attrs) {
        this.value = value;
        this.view = view;
        this.attrs = attrs;
    }

    public Rate2(Double mainRate) {
        this.mainRate = mainRate;
    }

    public void setAttrs(Attrs attrs) { this.attrs = attrs; }
}

