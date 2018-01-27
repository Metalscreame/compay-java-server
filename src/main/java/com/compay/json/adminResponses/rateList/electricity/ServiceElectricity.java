package com.compay.json.adminResponses.rateList.electricity;

import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.Service;

import java.util.ArrayList;

public class ServiceElectricity {

    private Service service;
    private Method method;
    private ArrayList<ScaleElectr> scale;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ArrayList<ScaleElectr> getScale() {
        return scale;
    }

    public void setScale(ArrayList<ScaleElectr> scale) {
        this.scale = scale;
    }

    public ServiceElectricity(Service service, Method method, ArrayList<ScaleElectr> scale) {

        this.service = service;
        this.method = method;
        this.scale = scale;
    }
}
