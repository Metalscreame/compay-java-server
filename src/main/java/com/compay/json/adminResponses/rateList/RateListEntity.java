package com.compay.json.adminResponses.rateList;

import java.util.ArrayList;

public class RateListEntity {
    public ArrayList l;

    public RateListEntity(ArrayList l) {
        this.l = l;
    }

    public ArrayList getServices() {
        return l;
    }

    public void setServices(ArrayList l) {
        this.l = l;
    }
}
