package com.compay.json.adminResponses.rateList.heat;

import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.Service;

public class ServiceHeat {

    private Service service;
    private Method method;

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

    public ServiceHeat(Service service, Method method) {
        this.service = service;
        this.method = method;
    }
}
