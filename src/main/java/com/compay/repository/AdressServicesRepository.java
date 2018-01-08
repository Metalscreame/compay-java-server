package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressServicesRepository extends JpaRepository<AdressServices, Integer> {
    List<AdressServices> findAllByAdress(Adress adress);
    List<AdressServices> findAllByService(Services service);
}
