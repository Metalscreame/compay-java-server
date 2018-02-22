package com.compay.repository;

import com.compay.entity.DefaultRates;
import com.compay.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DefaultRatesRepository extends JpaRepository<DefaultRates, Integer> {

    @Query("SELECT d from DefaultRates d where d.defaultServices.id=:serviceId")
    DefaultRates findDefaultRatesByService_Id(@Param("serviceId")int serviceId);

    @Transactional
    @Modifying
    void deleteAll();
}
