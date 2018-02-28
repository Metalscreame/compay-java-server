package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.AdressArguments;
import com.compay.entity.Arguments;

import java.util.List;

public interface AdressArgumentsService {
    AdressArguments findAdressArgumentById(Integer id);
    AdressArguments create(AdressArguments adressArgument);
    List<AdressArguments> findAllByAdress(Adress adress);
    List<AdressArguments> findAllByArgument(Arguments argument);
    AdressArguments findByAdrIdAndArgId(int id,int argId);
}
