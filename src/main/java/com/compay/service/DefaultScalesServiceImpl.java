package com.compay.service;

import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;
import com.compay.repository.DefaultScalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultScalesServiceImpl implements DefaultScalesService {

    @Autowired
    private DefaultScalesRepository defaultScalesRepository;

    @Override
    @Transactional
    public DefaultScales findDefaultScalesById(Integer id) {
        return defaultScalesRepository.findOne(id);
    }

    @Override
    @Transactional
    public DefaultScales create(DefaultScales defaultScales) {
        return defaultScalesRepository.save(defaultScales);
    }

    @Override
    public List<DefaultScales> findByDefaultRates(DefaultRates defaultRates) {
        return defaultScalesRepository.findAllByDefaultRates(defaultRates);
    }
}
