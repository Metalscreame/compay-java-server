package com.compay.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "RATES")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*@Column(name = "SERVICE_ID")
    private int serviceId;*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADRESSSERVICE_ID", nullable = false, referencedColumnName = "Id")
    private AdressServices adressServices;

    @Column(name = "PERIOD_FROM")//,columnDefinition="DATETIME")
    private Timestamp periodFrom;

    @Column(name = "PERIOD_TILL")//,columnDefinition="DATETIME")
    private Timestamp periodTill;

    public Timestamp getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Timestamp periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Timestamp getPeriodTill() {
        return periodTill;
    }

    public void setPeriodTill(Timestamp periodTill) {
        this.periodTill = periodTill;
    }

    @Column(name = "MAINRATE", columnDefinition = "Decimal(10,2) default '0.00'")
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "METHODID", nullable = false, referencedColumnName = "Id")
    private Methods method;

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rate")
    private Set<Scales> scale;

    @Override
    public String toString() {
        return "Rates{" +
                "id=" + id +
                ", adressServicesId=" + adressServices.getId() +
                ", periodFrom=" + periodFrom +
                ", periodTill=" + periodTill +
                ", mainRate=" + mainRate +
                ", userScale=" + userScale +
                ", formula='" + formula + '\'' +
                ", methodId=" + method.getId() +
                '}';
    }
}