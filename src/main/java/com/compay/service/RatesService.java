package com.compay.service;


import com.compay.entity.Rates;

public interface RatesService {
    Rates findRateById(Integer id);
    Rates create(Rates rate);
}
