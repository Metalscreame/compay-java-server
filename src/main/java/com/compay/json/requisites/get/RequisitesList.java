package com.compay.json.requisites.get;

import java.util.ArrayList;

public class RequisitesList {

    private ArrayList<ReqService> requisites = new ArrayList<>();

    public ArrayList<ReqService> getRequisites() {
        return requisites;
    }

    public void addReqService(ReqService reqService) {
        requisites.add(reqService);
    }

    public void setRequisites(ArrayList<ReqService> requisites) {
        this.requisites = requisites;
    }
}
