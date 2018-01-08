package com.compay.entity;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Entity
@Table(name = "TOKENS")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable=false, referencedColumnName="Id")
    private User user;

    @Column(name = "TOKEN",nullable = false,unique = true)
    private String token;

    @Column(name = "TOKEN_CREATE_DATE",nullable = false)
    private Timestamp tokenCreateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTokenCreateDate() {
        return tokenCreateDate;
    }

    public void setTokenCreateDate() {
        this.tokenCreateDate = new Timestamp(System.currentTimeMillis());
    }
}
