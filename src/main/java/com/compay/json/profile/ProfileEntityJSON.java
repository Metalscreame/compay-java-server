package com.compay.json.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileEntityJSON {

    @JsonProperty("name")private String name;
    @JsonProperty("surname")private String surname;
    @JsonProperty("email")private String email;
    @JsonProperty("password")private String password;

    @JsonCreator
    public ProfileEntityJSON(@JsonProperty("name") String name, @JsonProperty("surname") String surname,
                             @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
