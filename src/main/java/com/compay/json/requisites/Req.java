package com.compay.json.requisites;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Req {

    @JsonProperty("persAcc")
    public String  persAcc;
    @JsonProperty("checkAcc")
    public String checkAcc;
    @JsonProperty("MFO")
    public String MFO;
    @JsonProperty("EGRPO")
    public String EGRPO;

    public Req(String persAcc, String checkAcc, String MFO, String EGRPO) {
        this.persAcc = persAcc;
        this.checkAcc = checkAcc;
        this.MFO = MFO;
        this.EGRPO = EGRPO;
    }

    Req(){

    }


    public String getPersAcc() {
        return persAcc;
    }

    public String getCheckAcc() {
        return checkAcc;
    }

    public String getMFO() {
        return MFO;
    }

    public String getEGRPO() {
        return EGRPO;
    }
}
