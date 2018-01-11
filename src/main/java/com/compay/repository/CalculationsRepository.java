package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.Calculations;
import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, Integer> {
    List<Calculations> findAllByAdress(Adress adressCalculations);
    List<Calculations> findAllByUser(User user);
}
