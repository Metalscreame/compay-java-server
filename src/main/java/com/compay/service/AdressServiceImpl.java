package com.compay.service;

import com.compay.entity.Adress;
import com.compay.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdressServiceImpl implements  AdressService{

    @Autowired
    private AdressRepository adressRepository;

    @Override
    @Transactional
    public Adress findAdressById(Integer id) {
        return adressRepository.findOne(id);
    }

    @Override
    @Transactional
    public Adress create(Adress adress) {
        return adressRepository.save(adress);
    }
}
