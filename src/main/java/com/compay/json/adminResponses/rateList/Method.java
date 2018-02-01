package com.compay.json.adminResponses.rateList;

public class Method {

    public int id;
    public String name;
    public String view;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Method(int id, String name, String view) {
        this.id = id;
        this.name = name;
        this.view = view;

    }
}
