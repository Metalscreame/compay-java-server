package com.compay.json.adminResponses.rateList;

public class Service {
    public int id;
    public String serviceName;
    public String link;
    public String units;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Service(int id, String serviceName, String link, String units) {

        this.id = id;
        this.serviceName = serviceName;
        this.link = link;
        this.units = units;
    }
}
