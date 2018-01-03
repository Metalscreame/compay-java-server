package com.compay.service;

import com.compay.entity.Services;
import com.compay.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServicesServiceImpl implements  ServicesService{

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    @Transactional
    public Services findServicesById(Integer id) {
        return servicesRepository.findOne(id);
    }

    @Override
    @Transactional
    public Services create(Services serviceObject) {
        return servicesRepository.save(serviceObject);
    }

//    @Override
//    @Transactional
//    public List<String> findByName(String name) {
//        return servicesRepository.findByName(name);
//    }
}
