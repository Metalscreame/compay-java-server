package com.compay.json.calculation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Method {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String view;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<ScaleElectr> scale;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Float mainRate;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Formula formula;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Float getMainRate() {
        return mainRate;
    }

    public void setMainRate(Float mainRate) {
        this.mainRate = mainRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public ArrayList<ScaleElectr> getScale() {
        return scale;
    }

    public void setScale(ArrayList<ScaleElectr> scale) {
        this.scale = scale;
    }

    public Method(int id, String name, String view, ArrayList<ScaleElectr> scale, Float mainRate) {

        this.id = id;
        this.name = name;
        this.view = view;
        this.scale = scale;
        this.mainRate = mainRate;
    }
}
