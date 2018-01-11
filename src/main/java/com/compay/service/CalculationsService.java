package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Services;

public interface AdressService {
    Adress findAdressById(Integer id);
    Adress create(Adress adress);
}
