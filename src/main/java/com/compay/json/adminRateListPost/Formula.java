package com.compay.json.adminRateListPost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Formula
{
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }
}
