package com.compay.json.adminResponses.rateList.flatPay;

import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.Service;

public class ServiceFlat {

    private Service service;
    private Method method;
    private Formula formula;
    private Arguments arguments;

    public ServiceFlat(Service service, Method method, Formula formula, Arguments arguments) {
        this.service = service;
        this.method = method;
        this.formula = formula;
        this.arguments = arguments;
    }

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

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public void setArguments(Arguments arguments) {
        this.arguments = arguments;
    }
}
