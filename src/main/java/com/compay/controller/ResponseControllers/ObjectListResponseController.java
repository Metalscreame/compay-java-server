package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.json.ObjectList.ObjectListEntity;
import com.compay.json.ObjectList.ObjectListJsonBuilder;
import com.compay.json.ServiceListResponse.ServiceListEntity;
import com.compay.service.AdressService;
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
import java.util.List;

@Controller
public class ObjectListResponseController {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    AdressService adressService;

    @RequestMapping(value = "/objectList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String contentType,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response) {
        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            User currentUser = tokenService.findByToken(authToken).getUser();//пока заглушка
            List<Adress> adressesList = adressService.findAllByUser(currentUser);
            if (!adressesList.isEmpty()) {
                ObjectListJsonBuilder builder = new ObjectListJsonBuilder();
                for (Adress adress : adressesList) {
                    //if its a house or a garage or smth like that
                    if (adress.getAppartmentNumber().isEmpty()) {
                        String objectName = adress.getType() + ", " + adress.getStreet() + " " + adress.getHouseNumber();
                        builder.addInfo(new ObjectListEntity(adress.getId(), objectName, adress.getObjectDefault()));
                    } else {
                        String objectName = adress.getType() + ", " + adress.getStreet() + " " + adress.getHouseNumber() +
                                "/" + adress.getAppartmentNumber();
                        builder.addInfo(new ObjectListEntity(adress.getId(), objectName, adress.getObjectDefault()));
                    }
                }
                try {
                    result = builder.createJson();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return result;
            }
            //Если у пользователя нет зарегистрированных объектов, то в ответ сервер должен отдать JSON с пустым массивом:
            else {
                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{accountObjects: []}";
            }
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }
}
