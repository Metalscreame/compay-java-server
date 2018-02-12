package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ADRESSSERVICES")
public class AdressServices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ADRESSID", nullable = false, referencedColumnName = "Id")
    private Adress adress;

    @Column(name = "NOTACTIVE")
    private boolean notActive;

    @Column(name = "PERSACC")
    private int persAcc;

    @Column(name = "CHECKACC")
    private long checkAcc;

    @Column(name = "MFO")
    private int MFO;

    @Column(name = "EGRPO")
    private int EGRPO;

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

    @ManyToOne
    @JoinColumn(name = "SERVICEID", nullable = false, referencedColumnName = "Id")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adressServices")
    private Set<Rates> rate;

    public int getPersAcc() {
        return persAcc;
    }

    public void setPersAcc(int persAcc) {
        this.persAcc = persAcc;
    }

    public long getCheckAcc() {
        return checkAcc;
    }

    public void setCheckAcc(long checkAcc) {
        this.checkAcc = checkAcc;
    }

    public int getMFO() {
        return MFO;
    }

    public void setMFO(int MFO) {
        this.MFO = MFO;
    }

    public int getEGRPO() {
        return EGRPO;
    }

    public void setEGRPO(int EGRPO) {
        this.EGRPO = EGRPO;
    }

    @Override
    public String toString() {
        return "AdressServices{" +
                "id=" + id +
                ", adress=" + adress +
                ", service=" + service +
                ", persAcc=" + persAcc +
                ", checkAcc=" + checkAcc +
                ", MFO=" + MFO +
                ", EGRPO=" + EGRPO +
                '}';
    }
}
