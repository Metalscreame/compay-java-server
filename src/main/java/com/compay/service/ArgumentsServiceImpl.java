package com.compay.service;

import com.compay.entity.Arguments;
import com.compay.entity.Methods;
import com.compay.repository.ArgumentsRepository;
import com.compay.repository.MethodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ArgumentsServiceImpl implements  ArgumentsService{

    @Autowired
    private ArgumentsRepository argumentsRepository;

    @Override
    @Transactional
    public Arguments findArgumentById(Integer id) {
        return argumentsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Arguments create(Arguments argument) {
        return argumentsRepository.save(argument);
    }

    @Override
    @Transactional
    public Arguments findArgumentByName(String name) {
        return argumentsRepository.findByName(name);
    }
}
