package com.compay.service;

import com.compay.entity.Rates;
import com.compay.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
