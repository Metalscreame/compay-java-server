package com.compay.json.calculation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CalculationEntity {

    @JsonProperty("period")
    public String period;
    @JsonProperty("objectID")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer objectID;
    @JsonProperty("services")
    public ArrayList<CalcServicesArrList> services;

    public CalculationEntity(String period, ArrayList services) {
        this.period = period;
        this.services = services;
    }

    @JsonCreator
    public CalculationEntity(@JsonProperty("period") String period, @JsonProperty("services") ArrayList<CalcServicesArrList> services, @JsonProperty("objectID") Integer objectID) {
        this.period = period;
        this.services = services;
        this.objectID = objectID;
    }

    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    @JsonProperty("services")
    public ArrayList<CalcServicesArrList> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(ArrayList<CalcServicesArrList> services) {
        this.services = services;
    }

    @JsonProperty("objectID")
    public Integer getObjectID() {
        return objectID;
    }

    @JsonProperty("objectID")
    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }
}
