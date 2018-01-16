package com.compay.json.adminResponses.rateList.gas;

public class MethodGas {
    public int id;
    public String name;
    public String view;
    public double mainRate;

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

    public double getMainRate() {
        return mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }

    public MethodGas(int id, String name, String view, double mainRate) {

        this.id = id;
        this.name = name;
        this.view = view;
        this.mainRate = mainRate;
    }
}
