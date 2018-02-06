package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.Calculations;
import com.compay.entity.Services;
import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, Integer> {
    List<Calculations> findAllByAdress(Adress adressCalculations);
    List<Calculations> findAllByUser(User user);

    @Query("select c from Calculations c where adress =:adress AND service =:service AND period >=:periodFrom AND period <=:periodTo order by period, service")
    List<Calculations> findAllByAdressServicePeriodFromPeriodTo(@Param("adress")Adress adress, @Param("service")Services service, @Param("periodFrom")Timestamp periodFrom, @Param("periodTo")Timestamp periodTo);

    @Query("select c from Calculations c where c.adress =:adress AND c.period >=:periodFrom AND c.period <=:periodTo order by period ASC, service ASC")
    List<Calculations> findAllByAdressPeriodFromPeriodTo(@Param("adress")Adress adress, @Param("periodFrom")Timestamp periodFrom, @Param("periodTo")Timestamp periodTo);


    @Query("select c from Calculations c where c.adress =:adress AND c.period = :period")
    List<Calculations> findAllByAdressPeriod(@Param("adress")Adress adress, @Param("period")Timestamp period);

    @Query(value = "SELECT ADS.ADRESSID, " +
            "CASE WHEN CALC.COUNTCURRENT IS NULL THEN 0 ELSE CALC.COUNTCURRENT END AS COUNTCURRENT, " +
            "CASE WHEN CALC.COUNTLAST IS NULL THEN 0 ELSE CALC.COUNTLAST END AS COUNTLAST, " +
            "CASE WHEN CALC.PERIOD IS NULL THEN 0 ELSE CALC.PERIOD END AS PERIOD, " +
            "CASE WHEN SUM IS NULL THEN 0 ELSE SUM END AS SUM, " +
            "ADS.SERVICEID, " +
            "CASE WHEN CALC.USER_ID IS NULL THEN :user_id ELSE CALC.USER_ID END AS USER_ID, " +
            "CASE WHEN R.FORMULA IS NULL THEN '' ELSE R.FORMULA END AS FORMULA, " +
            "CAST(R.MAINRATE AS FLOAT) AS MAINRATE, R.METHODID, M.NAME, M.VIEW, R.USERSCALE, R.Id AS RATES_ID " +
            "            FROM ADRESSSERVICES AS ADS " +
            "            LEFT JOIN (SELECT id, FORMULA, CAST(MAINRATE AS FLOAT) AS MAINRATE, MAX(PERIOD_FROM) AS PERIOD_FROM, PERIOD_TILL, USERSCALE, ADRESSSERVICE_ID, METHODID " +
            "                       FROM RATES " +
            "                       WHERE PERIOD_FROM <=:period AND CASE WHEN PERIOD_TILL ISNULL THEN 999504213200000 ELSE PERIOD_TILL END >=:period " +
            "                       GROUP BY ADRESSSERVICE_ID) AS R ON ADS.id = R.ADRESSSERVICE_ID " +
            "            LEFT JOIN METHODS AS M ON R.METHODID = M.id " +
            "            LEFT JOIN ( " +
            "                SELECT id AS Id, " +
            "                CASE " +
            "                        WHEN PERIOD =:period THEN COUNTCURRENT " +
            "                        ELSE 0 " +
            "                        END as COUNTCURRENT, " +
            "                COUNTLAST AS COUNTLAST, " +
            "                Max(PERIOD) AS PERIOD, " +
            "            CASE " +
            "            WHEN PERIOD =:period THEN SUM " +
            "            ELSE 0.0 " +
            "            END as SUM, " +
            "            ADRESSID AS ADRESSID, " +
            "            SERVICEID AS SERVICEID, " +
            "            USER_ID AS USER_ID " +
            "            FROM CALCULATIONS " +
            "            WHERE ADRESSID = :id " +
            "            AND PERIOD <=:period " +
            "            GROUP BY ADRESSID, SERVICEID, USER_ID " +
            "            ) AS CALC " +
            "            ON ADS.ADRESSID = CALC.ADRESSID AND ADS.SERVICEID = CALC.SERVICEID " +
            "            WHERE ADS.ADRESSID = :id ", nativeQuery = true)
    List<Object[]> findAllByUserAdressPeriod(@Param("id") Integer id, @Param("period") Timestamp period, @Param("user_id") Integer userId);

}
