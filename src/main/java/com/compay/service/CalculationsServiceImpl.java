package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Calculations;
import com.compay.entity.User;
import com.compay.repository.CalculationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class CalculationsServiceImpl implements  CalculationsService{

    @Autowired
    private CalculationsRepository calculationsRepository;

    @Override
    @Transactional
    public Calculations findCalculationsById(Integer id) {
        return calculationsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Calculations create(Calculations calculation) {
        return calculationsRepository.save(calculation);
    }

    @Override
    @Transactional
    public List<Calculations> findAllByAdress(Adress adress) {
        return calculationsRepository.findAllByAdress(adress);
    }

    @Override
    public List<Calculations> findAllByUser(User user) {
        return calculationsRepository.findAllByUser(user);
    }

    @Override
    public List<Calculations> findAllByUserAdressPeriod(User user, Adress adress, Date period) {
        return calculationsRepository.findAllByUserAdressPeriod(user, adress, period);
    }
}
