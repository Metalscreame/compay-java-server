package com.compay.controller.ResponseControllers;

import com.compay.exception.AuthException;
import com.compay.global.Constants;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class CalculationMethods {

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/calculationMethods", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response) throws JsonProcessingException, ParseException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            result = "[{\"id\":\"fixSum\", \"name\":\"Фиксированная сумма\"},{\"id\":\"fixFormula\",\"name\":\"Фиксированная формула\"},{\"id\":\"counter\",\"name\":\"По счетчику\"},{\"id\":\"counterScale\",\"name\":\"По счетчику со шкалой\"},{\"id\":\"manual\",\"name\":\"Ручной ввод суммы\"}]";
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }

    @RequestMapping(value = "/fixFormulas", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody2(@RequestHeader(value = CONTENT_TYPE) String type,
                                @RequestHeader(value = AUTHORIZATION) String authToken,
                                HttpServletResponse response) throws JsonProcessingException, ParseException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            result = "[{\"formulaId\":\"rate_livingSpace\",\"formula\":\" [Тариф] х [Жилая площадь]\",\"attrs\":{\"rate\":{\"name\":\"Тариф\",\"val\":\"\",\"units\":\"(грн/м2)/мес\"},\"livingSpace\": {     \"name\":\"Жилая площадь\",\"val\":\"\",\"units\":\"м2\"}}},{\"formulaId\":\"rate_coeff\",\"formula\":\" [Тариф] х [Коэффициент]\",\"attrs\":{\"rate\":{\"name\":\"Тариф\",\"val\":\"\",\"units\":\"(грн/м2)/мес\"},\"coeff\": {\"name\":\"Коэффициент\",\"val\":\"\",\"units\":\"\"}}}]";
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }


}
