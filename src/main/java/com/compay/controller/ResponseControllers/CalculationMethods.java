package com.compay.controller.ResponseControllers;

import com.compay.exception.AuthException;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@Controller
public class CalculationMethods {

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/calculationMethods", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response) throws JsonProcessingException, ParseException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            result ="[{\"id\":\"fixSum\", \"name\":\"Фиксированная сумма\"},{\"id\":\"fixFormula\",\"name\":\"Фиксированная формула\"},{\"id\":\"counter\",\"name\":\"По счетчику\"},{\"id\":\"counterScale\",\"name\":\"По счетчику со шкалой\"},{\"id\":\"manual\",\"name\":\"Ручной ввод суммы\"}]";
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return  result;

        }catch (AuthException e){
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }

    @RequestMapping(value = "/fixFormulas", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody2(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response) throws JsonProcessingException, ParseException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            result ="[{\"formulaId\":\"rate_livingSpace\",\"formula\":\" [Тариф] х [Жилая площадь]\",\"attrs\":{\"rate\":{\"name\":\"Тариф\",\"val\":\"\",\"units\":\"(грн/м2)/мес\"},\"livingSpace\": {     \"name\":\"Жилая площадь\",\"val\":\"\",\"units\":\"м2\"}}},{\"formulaId\":\"rate_coeff\",\"formula\":\" [Тариф] х [Коэффициент]\",\"attrs\":{\"rate\":{\"name\":\"Тариф\",\"val\":\"\",\"units\":\"(грн/м2)/мес\"},\"coeff\": {\"name\":\"Коэффициент\",\"val\":\"\",\"units\":\"\"}}}]";
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return  result;

        }catch (AuthException e){
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }


}
