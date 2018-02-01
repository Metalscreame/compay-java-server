package com.compay.json.RateList;

import java.util.ArrayList;

public class RateListEntity {

    private ArrayList<Rate> rates;

    public RateListEntity() {
        this.rates = new ArrayList<>();
    }

    public void addRates(Rate rate){
        rates.add(rate);
    }

    public ArrayList<Rate> getRates() { return this.rates; }

    public void setRates(ArrayList<Rate> rates) { this.rates = rates; }
}
