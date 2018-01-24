package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Services;
import com.compay.entity.User;

import java.util.List;

public interface AdressService {
    List<Adress> findAllByUser(User user);
    Adress findAdressById(Integer id);
    Adress create(Adress adress);
    void deleteById(int id);

}
