package com.compay.json.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ReportCalculation {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<Object>> sumDetailData = null;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<Object>> counterDetailData = null;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<Object>> sumTotalData = null;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<List<Object>> counterTotalData = null;


    public List<List<Object>> getSumDetailData() {
        return sumDetailData;
    }

    public void setSumDetailData(List<List<Object>> sumDetailData) {
        this.sumDetailData = sumDetailData;
    }

    public List<List<Object>> getCounterDetailData() {
        return counterDetailData;
    }

    public void setCounterDetailData(List<List<Object>> counterDetailData) {
        this.counterDetailData = counterDetailData;
    }

    public List<List<Object>> getSumTotalData() {
        return sumTotalData;
    }

    public void setSumTotalData(List<List<Object>> sumTotalData) {
        this.sumTotalData = sumTotalData;
    }

    public List<List<Object>> getCounterTotalData() {
        return counterTotalData;
    }

    public void setcCounterTotalData(List<List<Object>> counterTotalData) {
        this.counterTotalData = counterTotalData;
    }
}
