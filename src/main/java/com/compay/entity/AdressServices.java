package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ADRESSSERVICES")
public class AdressServices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne//(cascade= CascadeType.ALL)
    @JoinColumn(name = "ADRESSID", nullable=false, referencedColumnName="Id")
    private Adress adress;

    @Column(name="NOTACTIVE")
    private boolean notActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adress getAdress() {
    return adress;
}

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @ManyToOne//(cascade= CascadeType.ALL)
    @JoinColumn(name = "SERVICEID", nullable=false, referencedColumnName="Id")
    private Services service;

    public boolean isNotActive() {
        return notActive;
    }

    public void setNotActive(boolean notActive) {
        this.notActive = notActive;
    }

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
