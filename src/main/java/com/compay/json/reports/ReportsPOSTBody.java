package com.compay.json.reports;

public class ReportsPOSTBody {

    private int objectID;
    private int serviceID;
    private String periodFrom;
    private String periodTo;

    public ReportsPOSTBody() {
    }

    public int getObjectID() {
        return objectID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getPeriodFrom() {
        return periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }
}
