package com.compay.service;

import com.compay.entity.Scales;
import com.compay.entity.Services;
import com.compay.repository.ScalesRepository;
import com.compay.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ScalesServiceImpl implements  ScalesService{

    @Autowired
    private ScalesRepository scalesRepository;

    @Override
    @Transactional
    public Scales findScaleById(Integer id) {
        return scalesRepository.findOne(id);
    }

    @Override
    @Transactional
    public Scales create(Scales scales) {
        return scalesRepository.save(scales);
    }
}
