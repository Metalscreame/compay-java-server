package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Methods;
import com.compay.repository.AdressRepository;
import com.compay.repository.MethodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MethodsServiceImpl implements  MethodsService{

    @Autowired
    private MethodsRepository methodsRepository;

    @Override
    @Transactional
    public Methods findMethodById(Integer id) {
        return methodsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Methods create(Methods method) {
        return methodsRepository.save(method);
    }

    @Override
    @Transactional
    public Methods findMethodByName(String name) {
        return methodsRepository.findByName(name);
    }
}
