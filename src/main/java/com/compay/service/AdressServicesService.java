package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;

import java.util.List;

public interface AdressServicesService {
    AdressServices findAdressServiceById(Integer id);
    AdressServices create(AdressServices adressService);
    List<AdressServices> findAllByAdress(Adress adress);
    List<AdressServices> findAllByService(Services service);
}
