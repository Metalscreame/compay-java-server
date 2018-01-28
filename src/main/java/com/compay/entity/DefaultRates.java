package com.compay.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "DEFAULTRATES")
public class DefaultRates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne//(cascade= CascadeType.ALL)
    @JoinColumn(name = "SERVICEID", nullable=false, referencedColumnName="Id")
    private Services defaultServices;

    @ManyToOne//(cascade= CascadeType.ALL)
    @JoinColumn(name = "METHODID", nullable=false, referencedColumnName="Id")
    private Methods defaultMethod;

    @Column(name = "MAINRATE", columnDefinition="Decimal(10,2) default '0.00'")
    private double mainRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="defaultRates")
    private Set<DefaultScales> defaultScales;

    @Column(name = "USERSCALE")
    private boolean userScale;

    @Column(name = "FORMULA")
    private String formula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMainRate() {
        return mainRate;
    }

    public void setMainRate(double mainRate) {
        this.mainRate = mainRate;
    }

    public boolean getUserScale() {
        return userScale;
    }

    public void setUserScale(boolean userScale) {
        this.userScale = userScale;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Methods getMethod() {
        return defaultMethod;
    }

    public void setMethod(Methods method) {
        this.defaultMethod = method;
    }

    public Services getService() {
        return defaultServices;
    }

    public void setService(Services service) {
        this.defaultServices = service;
    }

    public boolean isUserScale() {
        return userScale;
    }

    @Override
    public String toString() {
        return "DefaultRates{" +
                "id=" + id +
                ", service=" + defaultServices +
                ", method=" + defaultMethod +
                ", mainRate=" + mainRate +
                ", defaultScales=" + defaultScales +
                ", userScale=" + userScale +
                ", formula='" + formula + '\'' +
                '}';
    }
}