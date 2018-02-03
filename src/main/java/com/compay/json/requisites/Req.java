package com.compay.json.requisites;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Req {

    @JsonProperty("persAcc")
    public int persAcc;
    @JsonProperty("checkAcc")
    public String checkAcc;
    @JsonProperty("MFO")
    public int MFO;
    @JsonProperty("EGRPO")
    public int EGRPO;

    public Req(int persAcc, String checkAcc, int MFO, int EGRPO) {
        this.persAcc = persAcc;
        this.checkAcc = checkAcc;
        this.MFO = MFO;
        this.EGRPO = EGRPO;
    }

    Req(){

    }


    public int getPersAcc() {
        return persAcc;
    }

    public String getCheckAcc() {
        return checkAcc;
    }

    public int getMFO() {
        return MFO;
    }

    public int getEGRPO() {
        return EGRPO;
    }
}
