package com.compay.controller.ResponseControllers;


import com.compay.entity.Calculations;
import com.compay.repository.AdressRepository;
import com.compay.repository.CalculationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@Controller
public class CalculationsResponseControllers {

/*
    @Autowired
    CalculationsRepository calculationsRepository;

    @Autowired
    AdressRepository adressRepository;


    // /calculations/22/2017-12-01
    @RequestMapping(value = "/calculations/{objectId}/{period}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(HttpServletResponse response, @PathVariable("objectId") String id, @PathVariable("period") String period) {

        String message = null;

        List<Object[]> results = calculationsRepository.findAllByUserAdressPeriod(Integer.parseInt(id), period);

        /*result[0] ADRESSID
        result[1] COUNTCURRENT
        result[2] COUNTLAST
        result[3] PERIOD
        result[4] SUM
        result[5] SERVICEID
        result[6] USER_ID
        result[7] FORMULA
        result[8] MAINRATE
        result[9] METHODID
        result[10] NAME
        result[11] VIEW
        result[12] USERSCALE
        */
 /*       for (Object[] result : results) {
            message += " " + result[0] + " " + result[1] + " - " + result[2] + " " + result[3] + " - " + result[4] + " " + result[5] + " - " + result[6] + " " + result[7] + " - " + result[8]
                    + result[9] + " " + result[10] + " - " + result[11] + " " + result[12]
                    + "------------------------------";
        }

        return message;
    }
*/
}

