package com.compay.json.adminRateListPost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Method {
    @JsonIgnoreProperties(ignoreUnknown = true)

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String view;

    public String getView() {
        return this.view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
