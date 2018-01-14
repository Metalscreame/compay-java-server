package com.compay.json.calculation.electricity;

import java.util.ArrayList;

public class MethodElectricity {
        public int id;
        public String name;
        public String view;
        public ArrayList<ScaleElectr> scale;

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

    public ArrayList<ScaleElectr> getScale() {
        return scale;
    }

    public void setScale(ArrayList<ScaleElectr> scale) {
        this.scale = scale;
    }

    public MethodElectricity(int id, String name, String view, ArrayList<ScaleElectr> scale) {

        this.id = id;
        this.name = name;
        this.view = view;
        this.scale = scale;
    }
}
