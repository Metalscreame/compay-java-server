package com.compay.json.accountObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountObjectsJSONUpdate {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("objectDefault")
    private boolean objectDefault;

    @JsonProperty("services")
    private List<Integer> services;

    //@JsonProperty("id")private int id;
    @JsonCreator
    public AccountObjectsJSONUpdate(@JsonProperty("id")int id,@JsonProperty("name")String name, @JsonProperty("objectDefault")boolean objectDefault, @JsonProperty("services")List<Integer> services) {
        this.id = id;
        this.name = name;
        this.objectDefault = objectDefault;
        this.services = services;
    }

    public int getId() {
        return id;
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
