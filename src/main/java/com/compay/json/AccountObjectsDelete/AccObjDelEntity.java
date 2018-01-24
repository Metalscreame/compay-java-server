package com.compay.json.AccountObjectsDelete;

import java.util.ArrayList;

public class AccObjDelEntity {
    public String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String objectDefault;

    public String getObjectDefault() { return this.objectDefault; }

    public void setObjectDefault(String objectDefault) { this.objectDefault = objectDefault; }

    public ArrayList<Integer> services;

    public ArrayList<Integer> getServices() { return this.services; }

    public void setServices(ArrayList<Integer> services) { this.services = services; }


}
