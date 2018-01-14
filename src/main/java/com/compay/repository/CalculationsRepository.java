package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.Calculations;
import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, Integer> {
    List<Calculations> findAllByAdress(Adress adressCalculations);
    List<Calculations> findAllByUser(User user);


    @Query(value = "SELECT ADS.ADRESSID, CALC.COUNTCURRENT, CALC.COUNTLAST, CALC.PERIOD, CALC.SUM, ADS.SERVICEID, CALC.USER_ID, R.FORMULA, R.MAINRATE, R.METHODID, M.NAME, M.VIEW, R.USERSCALE " +
            "            FROM ADRESSSERVICES AS ADS " +
            "            LEFT JOIN RATES AS R ON ADS.id = R.ADRESSSERVICE_ID " +
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
            "            ELSE 0 " +
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
    List<Object[]> findAllByUserAdressPeriod(@Param("id") Integer id, @Param("period") String period);

}
