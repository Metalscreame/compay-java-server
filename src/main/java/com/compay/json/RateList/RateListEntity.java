package com.compay.json.RateList;

import java.util.ArrayList;

public class RateListEntity {
    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private ArrayList<Rate> rates;

    public ArrayList<Rate> getRates() { return this.rates; }

    public void setRates(ArrayList<Rate> rates) { this.rates = rates; }

    public RateListEntity(String id, String name, ArrayList<Rate> rates) {
        this.id = id;
        this.name = name;
        this.rates = rates;
    }
}
