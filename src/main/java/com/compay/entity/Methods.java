package com.compay.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "METHODS")
public class Methods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VIEW", nullable = false)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "method")
    private Set<Methods> method;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "defaultMethod")
    private Set<DefaultRates> defaultMethod;

    @Override
    public String toString() {
        return "Methods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", view='" + view + '\'' +
                '}';
    }
}
