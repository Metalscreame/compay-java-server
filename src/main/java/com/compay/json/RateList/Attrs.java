package com.compay.json.RateList;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Attrs
{
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MainRate mainRate;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Argument livingArea;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Argument registeredPersons;

    public MainRate getMainRate() { return this.mainRate; }

    public void setMainRate(MainRate mainRate) { this.mainRate = mainRate; }

    public Argument getLivingArea() { return this.livingArea; }

    public void setLivingArea(Argument livingArea) { this.livingArea = livingArea; }

    public Argument getRegisteredPersons() { return this.registeredPersons; }

    public void setRegisteredPersons(Argument registeredPersons) { this.registeredPersons = registeredPersons; }

    public Attrs(){}

    public Attrs(MainRate mainRate, Argument livingArea, Argument registeredPersons) {
        this.mainRate = mainRate;
        this.livingArea = livingArea;
        this.registeredPersons = registeredPersons;
    }
}
