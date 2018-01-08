package com.compay.entity;


import javax.persistence.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Set;

@Entity
@Table(name = "RATES")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*@Column(name = "SERVICE_ID")
    private int serviceId;*/
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ADRESSSERVICE_ID", nullable=false, referencedColumnName="Id")
    private AdressServices adressServices;

    @Column(name = "PERIOD_FROM",columnDefinition="DATETIME")
    private Date periodFrom;

    @Column(name = "PERIOD_TILL",columnDefinition="DATETIME")
    private Date periodTill;

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTill() {
        return periodTill;
    }

    public void setPeriodTill(Date periodTill) {
        this.periodTill = periodTill;
    }

    @Column(name = "MAINRATE", columnDefinition="Decimal(10,2) default '0.00'")
    private double mainRate;

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
/*
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
*/
    public AdressServices getAdressService() {
    return adressServices;
}

    public void setAdressService(AdressServices adressServices) {
        this.adressServices = adressServices;
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
/*
    @Column(name = "METHODID")
    private int methodID;

    public int getMethodID() {
        return methodID;
    }

    public void setMethodID(int methodID) {
        this.methodID = methodID;
    }
*/
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "METHODID", nullable=false, referencedColumnName="Id")
    private Methods method;

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="rate")
    private Set<Scales> scale;
}