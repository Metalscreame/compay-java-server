package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdressServicesService {
    List<AdressServices> findAll();
    AdressServices findAdressServiceById(Integer id);
    AdressServices create(AdressServices adressService);
    AdressServices update(AdressServices adressService);
    List<AdressServices> findAllByAdress(Adress adress);
    List<AdressServices> findAllByService(Services service);

    AdressServices findByAdressIdandServiceId(int adressId,int services);
    List<AdressServices> findAllByAdressId(int id);


}
