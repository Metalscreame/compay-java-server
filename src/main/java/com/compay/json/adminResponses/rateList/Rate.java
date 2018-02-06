package com.compay.json.adminResponses.rateList;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Rate {
    private Service service;
    private Method method;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<Scale> scale;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double mainRate;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Formula formula;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Arguments arguments;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public void setArguments(Arguments arguments) {
        this.arguments = arguments;
    }

    public Double getMainRate() {
        return mainRate;
    }

    public void setMainRate(Double mainRate) {
        this.mainRate = mainRate;
    }

    public ArrayList<Scale> getScale() {
        return scale;
    }

    public void setScale(ArrayList<Scale> scale) {
        this.scale = scale;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Rate(Service service, Method method) {
        this.service = service;
        this.method = method;
    }
}
