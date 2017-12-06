package com.websecurity.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    public User(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }
    public User() {

    }
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

    public int getId() {return id; }
    public void setId(int id) {this.id = id;}
}