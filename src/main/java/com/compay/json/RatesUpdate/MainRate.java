package com.compay.json.RatesUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MainRate {


   // view:'Тариф',
    //value:6


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String view;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int value;

    public String getView() {
        return view;
    }

    public int getValue() {
        return value;
    }
}
