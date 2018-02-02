package com.compay.json.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.compay.global.Constants.Profile.EMAIL;
import static com.compay.global.Constants.Profile.NAME;
import static com.compay.global.Constants.Profile.PASSWORD;
import static com.compay.global.Constants.Profile.SURNAME;

public class ProfileEntityJSON {

    @JsonProperty(NAME)
    private String name;
    @JsonProperty(SURNAME)
    private String surname;
    @JsonProperty(EMAIL)
    private String email;
    @JsonProperty(PASSWORD)
    private String password;

    @JsonCreator
    public ProfileEntityJSON(
            @JsonProperty(NAME) String name, @JsonProperty(SURNAME) String surname,
            @JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password) {
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
