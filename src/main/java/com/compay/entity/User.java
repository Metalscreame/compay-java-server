package com.compay.entity;



import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;

    @Column (name = "NAME",nullable = false)
    private String name;

    @Column(name = "LASTNAME",nullable = false)
    private String lastName;

    @Column(name = "ROLE",nullable = false)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
//        byte[] decoded = Base64.getDecoder().decode(password);
//        String str = new String(decoded, StandardCharsets.UTF_8);
//        return str;
        return password;
    }

    public String getPasswordDecoded(){
        byte[] decoded = Base64.getDecoder().decode(password);
        String str = new String(decoded, StandardCharsets.UTF_8);
        return str;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
    private Set<Adress> adress;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /*
    public void setAdress(Set<Adress> adress){
        this.adress = adress;
    }

    public Set<Adress> getAdress(){
        return this.adress;
    }*/
}



