package com.compay.controller.AdminControllers;

import com.compay.entity.User;
import com.compay.json.adminResponses.rateList.RateListBuilder;
import com.compay.json.adminResponses.rateList.RateListEntity;
import com.compay.json.adminResponses.userList.Entity;
import com.compay.json.adminResponses.userList.UserListJsonBuilder;
import com.compay.json.calculation.CalculationBuilder;
import com.compay.json.calculation.CalculationEntity;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RateList {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/admin/rateList", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  returnUserList(@RequestHeader(value = "Content-Type") String contentType,
                                  @RequestHeader(value = "Authorization")  String authToken,
                                  HttpServletResponse response) throws JsonProcessingException {

        if (tokenService.authChek(authToken)){
            String result ="";

            ArrayList services= new ArrayList();
            RateListEntity entity = new RateListEntity(services);
            RateListBuilder builder = new RateListBuilder();

            //простите, ну не удержался)) тут проще руками забить)
            result = "[{\"serviceID\": 1,\"serviceName\": \"Электроснабжение\",\"method\": {\"id\": 4,\"name\": \"counterScale\",\"view\": \"По счетчику с применением шкалы\",\"scale\": [{\"minValue\": 0,\"maxValue\": 100,\"mainRate\": 0.95},{\"minValue\": 101, \"maxValue\": 600, \"mainRate\": 1.50},{\"minValue\": 601, \"maxValue\": 0, \t\"mainRate\": 1.83}]}},{\"serviceID\": 2, \"serviceName\": \"Водоснабжение\",\"method\": {\"id\": 3, \"name\": \"counter\",\"view\": \"По счетчику\",\"mainRate\": 12.3}},{\"serviceID\": 3, \"serviceName\": \"Газоснабжение\",\"method\": {\"id\": 3, \"name\": \"counter\",\"view\": \"По счетчику\",\"mainRate\": 6.9}},{\"serviceID\": 4, \"serviceName\": \"Теплоснабжение\",\"method\": {\"id\": 3, \"name\": \"fixFormula\",\"view\": \"Фиксированная формула\",\"formula\": {\"value\": \"livingArea * mainRate\",\"view\": \"[Жилая площадь] x [Тариф]\", \"livingArea\": 49.5, \"mainRate\": 632.2}}},{\"serviceID\": 5, \"serviceName\": \"Квартплата\",\"method\": {\"id\": 2, \"name\": \"fixSum\",\"view\": \"Фиксированная сумма\"}}]";


            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;
        }else {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }



    }




}



