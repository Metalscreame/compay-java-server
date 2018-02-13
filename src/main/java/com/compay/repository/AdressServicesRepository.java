package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.service.AdressService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AdressServicesRepository extends JpaRepository<AdressServices, Integer> {
    List<AdressServices> findAllByAdress(Adress adress);

    List<AdressServices> findAllByService(Services service);

    @Query("select c from AdressServices c where c.adress =:adress AND c.service = :service")
    AdressServices findOneByAdressService(@Param("adress") Adress adress, @Param("service") Services service);

    @Query("SELECT a from  AdressServices a where a.adress.id =:aId and a.service.id = :sId")
    AdressServices findByAdressIdAndServiceId(@Param("aId") int adressId,@Param("sId") int serviceId);

    @Query("select a from AdressServices a, in (a.adress) ad where ad.id =:id")
    ArrayList<AdressServices> findAllByAdress_Id(@Param("id") int id);

    @Query("select c from AdressServices c where c.adress =:adress AND c.notActive = false")
    List<AdressServices> findAllByActiveServiceAdress(@Param("adress") Adress adress);

    @Query("select c.service from AdressServices c where c.adress =:adress AND c.notActive = false")
    List<Services> findAllServiceByActiveServiceAdress(@Param("adress") Adress adress);

}
