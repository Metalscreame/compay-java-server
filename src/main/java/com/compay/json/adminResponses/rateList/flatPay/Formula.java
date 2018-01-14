package com.compay.json.adminResponses.rateList.flatPay;

public class Formula {
    public String value;
    public String view;

    public double livingArea;
    public double mainRate;

    public Formula(String value, String view, double livingArea, double mainRate) {
        this.value = value;
        this.view = view;
        this.livingArea = livingArea;
        this.mainRate = mainRate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public double getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(double livingArea) {
        this.livingArea = livingArea;
    }

    public double getMainRate() {
        return mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }
}
