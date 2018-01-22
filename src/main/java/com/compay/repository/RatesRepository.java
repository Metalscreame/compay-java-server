package com.compay.repository;

import com.compay.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RatesRepository extends JpaRepository<Rates, Integer> {
    @Query(value = "SELECT ADS.ADRESSID, AD.TYPE, " +
            "            ADS.SERVICEID,  SERVICES.LINK, SERVICES.SERVICE_NAME, SERVICES.UNITS, " +
            "            CASE WHEN R.FORMULA IS NULL THEN '' ELSE R.FORMULA END AS FORMULA, " +
            "            R.PERIOD_FROM AS STARTDATE, M.NAME, R.MAINRATE, R.METHODID, R.USERSCALE, R.Id AS RATES_ID " +
            "            FROM ADRESSSERVICES AS ADS " +
            "            LEFT JOIN ADRESS AS AD ON ADS.ADRESSID = AD.id " +
            "            LEFT JOIN SERVICES AS SERVICES ON ADS.SERVICEID = SERVICES.id\n" +
            "            LEFT JOIN (SELECT id, FORMULA, MAINRATE, MAX(PERIOD_FROM) AS PERIOD_FROM, PERIOD_TILL, USERSCALE, ADRESSSERVICE_ID, METHODID " +
            "                       FROM RATES " +
            "                       WHERE PERIOD_FROM <=:period AND CASE WHEN PERIOD_TILL ISNULL THEN 999504213200000 ELSE PERIOD_TILL END >=:period " +
            "                       GROUP BY ADRESSSERVICE_ID) AS R ON ADS.id = R.ADRESSSERVICE_ID\n" +
            "            LEFT JOIN METHODS AS M ON R.METHODID = M.id " +
            "            WHERE ADS.ADRESSID = :id ", nativeQuery = true)
    List<Object[]> findAllByAdressServices(@Param("id") Integer id, @Param("period") Timestamp period);
}
