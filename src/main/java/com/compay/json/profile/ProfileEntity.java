package com.compay.json.profile;

import com.compay.entity.User;

public class ProfileEntity {

    private String name;
    private String surname;
    private String email;

    public ProfileEntity(String name, String surname, String email) {
        this.name = name;
        this.email = email;
        this.surname = surname;
    }

    public ProfileEntity(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.surname = user.getLastName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
