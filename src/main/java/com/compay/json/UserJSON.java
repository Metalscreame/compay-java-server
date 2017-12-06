package com.compay.json;

import com.fasterxml.jackson.annotation.JsonView;

public class UserJSON {

    @JsonView(Views.Public.class)
    private String password;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Public.class)
    private String name;

    public UserJSON(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
