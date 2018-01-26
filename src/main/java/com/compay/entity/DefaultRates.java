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
    private Services service;

    @ManyToOne//(cascade= CascadeType.ALL)
    @JoinColumn(name = "METHODID", nullable=false, referencedColumnName="Id")
    private Methods method;

    @Column(name = "MAINRATE", columnDefinition="Decimal(10,2) default '0.00'")
    private double mainRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="rate")
    private Set<Scales> scale;

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
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public boolean isUserScale() {
        return userScale;
    }

    @Override
    public String toString() {
        return "DefaultRates{" +
                "id=" + id +
                ", service=" + service +
                ", method=" + method +
                ", mainRate=" + mainRate +
                //", scale=" + scale +
                ", userScale=" + userScale +
                ", formula='" + formula + '\'' +
                '}';
    }
}