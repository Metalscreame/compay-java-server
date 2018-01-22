package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "ADRESSARGUMENTS")
public class AdressArguments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*@Column(name = "ADRESSID",nullable = false)
    private int adressID;*/
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ADRESSID", nullable=false, referencedColumnName="Id")
    private Adress adress;

    /*@Column(name = "ARGUMENTID",nullable = false)
    private int argumentID;*/
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ARGUMENTID", nullable=false, referencedColumnName="Id")
    private Arguments argument;

    @Column(name = "VALUE",nullable = false, columnDefinition="default '0.00'")
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getAdressID() {
        return adressID;
    }

    public void setAdressID(int adressID) {
        this.adressID = adressID;
    }
*/
    public Adress getAdress() {
    return adress;
}

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
/*
    public int getArgumentID() {
        return argumentID;
    }

    public void setArgumentID(int argumentID) {
        this.argumentID = argumentID;
    }
*/
    public Arguments getArgument() {
    return argument;
}

    public void setArgument(Arguments argument) {
        this.argument = argument;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AdressArguments{" +
                "id=" + id +
                ", adressId=" + adress.getId() +
                ", argumentId=" + argument.getId() +
                ", value=" + value +
                '}';
    }
}
