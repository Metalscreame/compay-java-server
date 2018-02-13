package com.compay.service;

import com.compay.entity.Rates;
import com.compay.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class RatesServiceImpl implements  RatesService{

    @Autowired
    private RatesRepository ratesRepository;

    @Override
    @Transactional
    public Rates findRateById(Integer id) {
        return ratesRepository.findOne(id);
    }

    @Override
    @Transactional
    public Rates create(Rates rate) {
        return ratesRepository.save(rate);
    }

    @Override
    public Rates findByAddIdAndStartDateAndMethod(int adressId, Timestamp startDate, int methodId) {
        return ratesRepository.findByAddIdAndStartDateAndMethod(adressId,startDate,methodId);
    }

    @Override
    public void update(Rates r){
        ratesRepository.save(r);
    }
}
