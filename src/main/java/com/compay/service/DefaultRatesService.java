package com.compay.service;


import com.compay.entity.DefaultRates;
import com.compay.entity.Rates;

import java.util.List;

public interface DefaultRatesService {
    DefaultRates findDefaultRatesById(Integer id);
    DefaultRates create(DefaultRates defaultRates);
    List<DefaultRates> findAll();
    void deleteAll();
    void save(DefaultRates defaultRates);
    DefaultRates findByService_Id(int serviceId);
}
