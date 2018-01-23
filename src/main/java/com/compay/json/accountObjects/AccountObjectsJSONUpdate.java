package com.compay.json.accountObjects;

import com.compay.entity.AdressServices;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class AccountObjectsJSON {

    @JsonProperty("name")
    private String name;

    @JsonProperty("objectDefault")
    private boolean objectDefault;

    @JsonProperty("services")
    private List<Integer> services;

    //@JsonProperty("id")private int id;
    @JsonCreator
    public AccountObjectsJSON(@JsonProperty("name")String name, @JsonProperty("objectDefault")boolean objectDefault, @JsonProperty("services")List<Integer> services) {
        this.name = name;
        this.objectDefault = objectDefault;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public boolean isObjectDefault() {
        return objectDefault;
    }

    public List<Integer> getServices() {
        return services;
    }
}
