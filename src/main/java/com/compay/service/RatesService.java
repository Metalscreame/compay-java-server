package com.compay.service;

import com.compay.entity.Rates;

import java.sql.Timestamp;

public interface RatesService {
    Rates findRateById(Integer id);
    Rates create(Rates rate);
    Rates findByAddIdAndStartDateAndMethod(int adressId, Timestamp startDate,int methodId);
    void update(Rates r);
}
