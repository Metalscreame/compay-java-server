package com.compay.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RATES")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "SERVICE_ID")
    private int serviceId;


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

    @Column(name = "MAINRATE")
    private int mainRate;

    @Column(name = "USERSCALE")
    private int userScale;

    @Column(name = "FORMULA")
    private int formula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }


    public int getMainRate() {
        return mainRate;
    }

    public void setMainRate(int mainRate) {
        this.mainRate = mainRate;
    }

    public int getUserScale() {
        return userScale;
    }

    public void setUserScale(int userScale) {
        this.userScale = userScale;
    }

    public int getFormula() {
        return formula;
    }

    public void setFormula(int formula) {
        this.formula = formula;
    }
}