package com.compay.json.adminResponses.rateList.flatPay;

public class MethodFlat {

    public int id;
    public String name;
    public String view;
    public Formula formula;


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

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public MethodFlat(int id, String name, String view, Formula formula) {
        this.id = id;
        this.name = name;
        this.view = view;
        this.formula = formula;
    }
}
