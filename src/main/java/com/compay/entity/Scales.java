package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "SCALES")
public class Scales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "RATEID",nullable = false)
    private int rateId;

    @Column(name = "MINVALUE")
    private int minValue;

    @Column(name = "MAXVALUE")
    private int maxValue;

    @Column(name = "MAINRATE")
    private int mainRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMainRate() {
        return mainRate;
    }

    public void setMainRate(int mainRate) {
        this.mainRate = mainRate;
    }
}
