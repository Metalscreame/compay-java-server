package com.compay.service;

import com.compay.entity.DefaultScales;
import com.compay.entity.Scales;
import com.compay.repository.DefaultScalesRepository;
import com.compay.repository.ScalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultScalesServiceImpl implements  DefaultScalesService{

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
}
