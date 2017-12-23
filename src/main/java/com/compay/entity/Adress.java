package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "ADRESS")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERID",nullable = false)
    private int userId;

    @Column(name = "HOUSE_NUMBER",nullable = false)
    private short houseNumber;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "APPARTMENT_NUMBER")
    private String appartmentNumber;

    @Column(name = "CITY",nullable = false)
    private String city;

    @Column(name = "REGION",nullable = false)
    private String region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public short getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppartmentNumber() {
        return appartmentNumber;
    }

    public void setAppartmentNumber(String appartmentNumber) {
        this.appartmentNumber = appartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
