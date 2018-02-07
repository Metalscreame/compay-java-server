package com.compay.json.calculation;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Formula {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String value;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String view;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public double livingArea;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public double registeredPersons;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Float mainRate;

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

    public double getRegisteredPersons() {
        return registeredPersons;
    }

    public void setRegisteredPersons(double registeredPersons) {
        this.registeredPersons = registeredPersons;
    }

    public Float getMainRate() {
        return mainRate;
    }

    public void setMainRate(Float mainRate) {
        this.mainRate = mainRate;
    }

    public Formula() {
    }

    public Formula(String value, String view, double livingArea, Float mainRate) {

        this.value = value;
        this.view = view;
        this.livingArea = livingArea;
        this.mainRate = mainRate;
    }
}
