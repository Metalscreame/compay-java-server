package com.compay.service;

import com.compay.entity.Rates;
import com.compay.entity.Scales;
import com.compay.entity.Services;
import com.compay.repository.ScalesRepository;
import com.compay.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<Scales> findAllByRate(Rates rates) {
        return scalesRepository.findAllByRate(rates);
    }

    @Override
    public void update(Scales scales) {
        scalesRepository.save(scales);
    }
}
