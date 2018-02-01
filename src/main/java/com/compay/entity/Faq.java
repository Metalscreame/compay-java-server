package com.compay.entity;

import javax.persistence.*;

@Entity
@Table(name = "FAQ")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "request",nullable = false)
    private String request;

    @Column (name = "response",nullable = false)
    private String response;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
