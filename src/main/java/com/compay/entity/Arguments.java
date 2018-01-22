package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ARGUMENTS")
public class Arguments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "VIEW",nullable = false)
    private String view;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="argument")
    private Set<AdressArguments> argument;

    @Override
    public String toString() {
        return "Arguments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", view='" + view + '\'' +
                '}';
    }
}
