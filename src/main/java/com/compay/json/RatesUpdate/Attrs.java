package com.compay.json.RatesUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Attrs {


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MainRate mainRate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MainRate livingArea;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MainRate registeredPersons;

    public MainRate getRegisteredPersons() {
        return registeredPersons;
    }

    public MainRate getMainRate() {
        return mainRate;
    }

    public MainRate getLivingArea() {
        return livingArea;
    }
}
