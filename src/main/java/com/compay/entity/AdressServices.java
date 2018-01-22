package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ADRESSSERVICES")
public class AdressServices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*@Column(name = "ADRESSID",nullable = false)
    private int adressID;*/
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ADRESSID", nullable=false, referencedColumnName="Id")
    private Adress adress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getAdressID() {
        return adressID;
    }

    public void setAdressID(int adressID) {
        this.adressID = adressID;
    }
*/
    public Adress getAdress() {
    return adress;
}

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
/*
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
*/
    /*@Column(name = "SERVICEID",nullable = false)
    private int serviceID;*/

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "SERVICEID", nullable=false, referencedColumnName="Id")
    private Services service;

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="adressServices")
    private Set<Rates> rate;

    @Override
    public String toString() {
        return "AdressServices{" +
                "id=" + id +
                ", adress=" + adress +
                ", service=" + service +
                '}';
    }
}
