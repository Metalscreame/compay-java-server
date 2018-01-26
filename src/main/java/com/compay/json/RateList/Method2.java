package com.compay.json.RateList;

public class Method2
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String view;

    public String getView() { return this.view; }

    public Method2(int id, String name, String view) {
        this.id = id;
        this.name = name;
        this.view = view;
    }

    public void setView(String view) { this.view = view; }
}
