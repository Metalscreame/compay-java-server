package com.compay.json.faq;

public class FaqEntity {

    private int id;
    private String request;
    private String response;

    public FaqEntity() {    }

    public FaqEntity(int id, String request, String response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "FaqEntity{" +
                "id=" + id +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
