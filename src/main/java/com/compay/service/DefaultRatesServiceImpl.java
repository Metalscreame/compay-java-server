package com.compay.service;

import com.compay.entity.DefaultRates;
import com.compay.entity.Rates;
import com.compay.repository.DefaultRatesRepository;
import com.compay.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultRatesServiceImpl implements  DefaultRatesService{

    @Autowired
    private DefaultRatesRepository defaultRatesRepository;

    @Override
    @Transactional
    public DefaultRates findDefaultRatesById(Integer id) {
        return defaultRatesRepository.findOne(id);
    }

    @Override
    @Transactional
    public DefaultRates create(DefaultRates rate) {
        return defaultRatesRepository.save(rate);
    }

    @Override
    public List<DefaultRates> findAll() {
        return defaultRatesRepository.findAll();
    }

    @Override
    public void deleteAll() {
        defaultRatesRepository.deleteAll();
    }

    @Override
    public void save(DefaultRates defaultRates) {
        defaultRatesRepository.save(defaultRates);
    }

    @Override
    public DefaultRates findByService_Id(int serviceId) {
        return defaultRatesRepository.findDefaultRatesByService_Id(serviceId);
    }
}
