package com.compay.entity;

import com.compay.json.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@Table(name = "USERS")
public class User {


    public User() {
    }

    public User(String email, String name) {//нужен для авторизации
        this.email = email;
        this.name = name;//TODO заменить email на password
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    @Column(name = "id")
    private int id;

    @Column(name = "PASSWORD",nullable = false)
    @JsonView(Views.Public.class)
    private String password;

    @Column(name = "EMAIL",nullable = false,unique = true)
    @JsonView(Views.Public.class)
    private String email;

    @Column(name = "SURRNAME", nullable = false)
    @JsonView(Views.Public.class)
    private String surrName;


    @Column (name = "NAME",nullable = false)
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "LASTNAME",nullable = false)
    @JsonView(Views.Public.class)
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSurrName() {
        return surrName;
    }

    public void setSurrName(String surrName) {
        this.surrName = surrName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


