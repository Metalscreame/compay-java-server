package com.websecurity.entity;

public class Anonym {
    private String password;
    private String login;

    public Anonym(String password, String login){
        this.login = login;
        this.password = password;
    }
    public Anonym(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
