package com.websecurity.entity;




public class Admin {


    int id;
    private String login;
    private String password;

    public Admin(String login, String password, int id){
        this.login = login;
        this.password = password;
        this.id = id;
    }
    public Admin(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
