package com.compay.json.adminRateListPost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

public class RateListPost {

    private Service service;

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    private Method method;

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private ArrayList<ScalePost> scale;

    public ArrayList<ScalePost> getScale() {
        return this.scale;
    }

    public void setScale(ArrayList<ScalePost> scale) {
        this.scale = scale;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private double mainRate;

    public double getMainRate() {
        return this.mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Formula formula;

    public Formula getFormula() {
        return this.formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    private Arguments arguments;

    public Arguments getArguments() {
        return this.arguments;
    }

    public void setArguments(Arguments arguments) {
        this.arguments = arguments;
    }


}
