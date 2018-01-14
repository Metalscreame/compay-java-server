package com.compay.json.calculation;

import java.util.ArrayList;

public class CalculationEntity {

    public String period;
    public ArrayList services;

    public CalculationEntity(String period, ArrayList services) {
        this.period = period;
        this.services = services;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList getServices() {
        return services;
    }

    public void setServices(ArrayList services) {
        this.services = services;
    }
}
