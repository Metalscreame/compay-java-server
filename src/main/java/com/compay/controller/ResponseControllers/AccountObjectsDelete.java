package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.exception.AuthException;
import com.compay.json.AccountObjectsDelete.AccObjDelEntity;
import com.compay.json.jsonReceive.register.PersonToRegisterEntity;
import com.compay.service.AdressService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
@Controller
public class AccountObjectsDelete {

    @Autowired
    AdressService adressService;

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/accountObjects/delete", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               @RequestBody String body,
                               HttpServletResponse response ) throws JsonProcessingException, ParseException {

            try{

                try {
                    if (tokenService.authChek(authToken)) {
                    } else throw new AuthException();

                AccObjDelEntity entity;
                try{
                    entity = new ObjectMapper().readValue(body, AccObjDelEntity.class);
                }catch (IOException e){
                    response.setStatus(402);
                    response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                    return "{\"info\":\"Wrong data format!\"}";
                }

                Adress adress = adressService.findAdressById(Integer.parseInt(entity.getId()));
                adress.getId();
                adressService.deleteById(Integer.parseInt(entity.getId()));


                    response.setStatus(200);
                    response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
                    return "\"info\": \"Объект учета успешно удален\"";
                }catch (AuthException e){
                    response.setStatus(401);
                    response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                    return "{\"message\": \"Unauthorized\"}";
                }

            }catch (Exception e ){
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Что-то пошло не так\"}"+e;
            }

    }

}
