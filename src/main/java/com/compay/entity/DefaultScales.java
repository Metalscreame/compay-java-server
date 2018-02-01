package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "DEFAULTSCALES")
public class DefaultScales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column(name = "RATEID",nullable = false)
    //private int rateId;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "DEFAULTRATEID", nullable=false, referencedColumnName="Id")
    private DefaultRates defaultRates;

    @Column(name = "MINVALUE")
    private int minValue;

    @Column(name = "MAXVALUE")
    private int maxValue;

    @Column(name = "MAINRATE", columnDefinition="Decimal(10,2) default '0.00'")
    private double mainRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DefaultRates getRate() {
    return defaultRates;
}

    public void setRate(DefaultRates defaultRates) {
        this.defaultRates = defaultRates;
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

    public double getMainRate() {
        return mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }

    @Override
    public String toString() {
        return "DefaultScales{" +
                "id=" + id +
                ", defaultRates=" + defaultRates +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", mainRate=" + mainRate +
                '}';
    }
}
