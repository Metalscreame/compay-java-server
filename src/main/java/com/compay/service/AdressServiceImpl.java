package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.User;
import com.compay.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdressServiceImpl implements  AdressService{

    @Autowired
    private AdressRepository adressRepository;

    @Override
    public List<Adress> findAllByUser(User user) {
        return adressRepository.findAllByUser(user);
    }

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

    @Override
    public void deleteById(int id) {
        adressRepository.delete(id);
        //adressRepository.deleteById(id);
    }

    @Override
    public Adress findDefaultAdressByUsrId(int userId) {
        return adressRepository.findDefaultAdressByUsrId(userId).get(0);
    }
}
