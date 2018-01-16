package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Calculations;
import com.compay.entity.User;

import java.sql.Date;
import java.util.List;

public interface CalculationsService {
    Calculations findCalculationsById(Integer id);
    Calculations create(Calculations calculation);
    List<Calculations> findAllByAdress(Adress adress);
    List<Calculations> findAllByUser(User user);
}
