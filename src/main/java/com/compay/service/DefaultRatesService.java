package com.compay.service;


import com.compay.entity.DefaultRates;
import com.compay.entity.Rates;

import java.util.List;

public interface DefaultRatesService {
    DefaultRates findDefaultRateById(Integer id);
    DefaultRates create(DefaultRates defaultRates);
    List<DefaultRates> findAll();
}
