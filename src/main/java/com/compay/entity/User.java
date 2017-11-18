package com.compay.entity;
import javax.persistence.*;

@Entity
@Table(name = "TEST")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// если ставить авто, то  HIBERNATE_SEQUENCE exceptrion
    private int id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

//    @Column(name = "LAST_NAME")
//    private String lastName;

    @Column (name = "NAME")
    private String name;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
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

//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

