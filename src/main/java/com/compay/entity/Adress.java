package com.compay.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ADRESS")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable=false, referencedColumnName="Id")//foreignKey = @ForeignKey())
    private User user;

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy="adress")
    private Set<AdressArguments> adress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="adress")
    private Set<AdressServices> adressService;
}
