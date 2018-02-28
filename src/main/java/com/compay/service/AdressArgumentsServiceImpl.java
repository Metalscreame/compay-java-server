package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.AdressArguments;
import com.compay.entity.Arguments;
import com.compay.repository.AdressArgumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdressArgumentsServiceImpl implements  AdressArgumentsService{

    @Autowired
    private AdressArgumentsRepository adressArgumentsRepository;

    @Override
    public AdressArguments findAdressArgumentById(Integer id) {
        return adressArgumentsRepository.findOne(id);
    }

    @Override
    public AdressArguments create(AdressArguments adressArgument) {
        return adressArgumentsRepository.save(adressArgument);
    }

    @Override
    public List<AdressArguments> findAllByAdress(Adress adress) {
        return  adressArgumentsRepository.findAllByAdress(adress);
    }

    @Override
    public List<AdressArguments> findAllByArgument(Arguments argument) {
        return adressArgumentsRepository.findAllByArgument(argument);
    }

    @Override
    public AdressArguments findByAdrIdAndArgId(int id,int argId) {
        return adressArgumentsRepository.findByAdress_IdAndArgument_Id(id,argId);
    }
}
