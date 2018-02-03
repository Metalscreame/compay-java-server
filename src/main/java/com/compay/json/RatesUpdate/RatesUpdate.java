package com.compay.json.RateList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatesUpdate {

    @JsonProperty("objectID")
    private int objectID;

    @JsonProperty("serviceID")
    private int serviceID;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("method")
    private int method;

    @JsonProperty("rate")
    private Rate rate;

}
