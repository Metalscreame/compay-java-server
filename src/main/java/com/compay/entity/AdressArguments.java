package com.compay.entity;


import javax.persistence.*;

@Entity
@Table(name = "ADRESSARGUMENTS")
public class AdressArguments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ADRESSID", nullable=false, referencedColumnName="Id")
    private Adress adress;

    @ManyToOne
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

    public Adress getAdress() {
    return adress;
}

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

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
