package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "SCALES")
public class Scales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "RATEID", nullable = false, referencedColumnName = "Id")
    private Rates rate;

    @Column(name = "MINVALUE")
    private int minValue;

    @Column(name = "MAXVALUE")
    private int maxValue;

    @Column(name = "MAINRATE", columnDefinition = "Decimal(10,2) default '0.00'")
    private double mainRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rates getRate() {
        return rate;
    }

    public void setRate(Rates rate) {
        this.rate = rate;
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
        return "Scales{" +
                "id=" + id +
                ", rate=" + rate +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", mainRate=" + mainRate +
                '}';
    }
}
