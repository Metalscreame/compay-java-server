package com.compay.json.jsonReceive.register;

public class PersonToRegisterEntity {
    private String email;
    private String name;
    private String surname;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public PersonToRegisterEntity(String email, String name, String surname, String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
