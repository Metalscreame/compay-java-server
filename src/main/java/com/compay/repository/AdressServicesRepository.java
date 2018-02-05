package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressServicesRepository extends JpaRepository<AdressServices, Integer> {
    List<AdressServices> findAllByAdress(Adress adress);

    List<AdressServices> findAllByService(Services service);

    @Query("select c from AdressServices c where c.adress =:adress AND c.service = :service")
    AdressServices findOneByAdressService(@Param("adress") Adress adress, @Param("service") Services service);
}
