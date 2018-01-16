package com.compay.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "CALCULATIONS")
public class Calculations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable=false, referencedColumnName="Id")
    private User user;

    //TODO
    @Column(name = "PERIOD")//"DATETIME")
    private Timestamp period;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ADRESSID", nullable=false, referencedColumnName="Id")
    private Adress adress;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "SERVICEID", nullable=false, referencedColumnName="Id")
    private Services service;

    @Column(name = "COUNTLAST")
    private int countLast;


    @Column(name = "COUNTCURRENT")
    private int countCurrent;

    @Column(name = "SUM", columnDefinition="Decimal(10,2) default '0.00'")
    private double sum;

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

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public int getCountLast() {
        return countLast;
    }

    public void setCountLast(int countLast) {
        this.countLast = countLast;
    }

    public int getCountCurrent() {
        return countCurrent;
    }

    public void setCountCurrent(int countCurrent) {
        this.countCurrent = countCurrent;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
