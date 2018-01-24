package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.repository.AdressServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressServicesServiceImpl implements  AdressServicesService{

    @Autowired
    private AdressServicesRepository adressServicesRepository;

    @Override
    public List<AdressServices> findAll() {
        return null;
    }

    @Override
    public AdressServices findAdressServiceById(Integer id) {
        return adressServicesRepository.findOne(id);
    }

    @Override
    public AdressServices create(AdressServices adressService) {
        return adressServicesRepository.save(adressService);
    }

    @Override
    public List<AdressServices> findAllByAdress(Adress adress) {
        return adressServicesRepository.findAllByAdress(adress);
    }

    @Override
    public List<AdressServices> findAllByService(Services service) {
        return  adressServicesRepository.findAllByService(service);
    }

    @Override
    public AdressServices update(AdressServices adressService) {
        return adressServicesRepository.save(adressService);
    }

}
