package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ADRESS")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
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

    @Column(name = "DEFAULT_OBJ",nullable = false)
    private boolean objectDefault;

    @Column(name = "TYPE",nullable = false)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getObjectDefault() {
        return objectDefault;
    }

    public void setObjectDefault(boolean objectDefault) {
        this.objectDefault = objectDefault;
    }


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
    private Set<Calculations> adressCalulations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="adress")
    private Set<AdressServices> adressService;

    public void setAdressService(Set<AdressServices> adressService) {
        this.adressService = adressService;
    }

    public Set<AdressServices> getAdressService() {
        return adressService;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", houseNumber=" + houseNumber +
                ", street='" + street + '\'' +
                ", appartmentNumber='" + appartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", objectDefault=" + objectDefault +
                ", type='" + type + '\'' +
                '}';
    }
}
