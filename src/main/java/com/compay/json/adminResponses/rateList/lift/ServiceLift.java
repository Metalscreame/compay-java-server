package com.compay.json.adminResponses.rateList.lift;

import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.Service;

public class ServiceLift {
    private Service service;
    private Method method;
    private Double mainRate;

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

    public Double getMainRate() {
        return mainRate;
    }

    public void setMainRate(Double mainRate) {
        this.mainRate = mainRate;
    }

    public ServiceLift(Service service, Method method, Double mainRate) {
        this.service = service;
        this.method = method;
        this.mainRate = mainRate;
    }
}
