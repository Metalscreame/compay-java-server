package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
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
    //TODO скорее всего сюда еще придется загонять токен или его
    //when they will give us a token
    // public String responseBody(@RequestHeader("Authorization") String authToken, HttpServletResponse response) {
    public String responseBody(HttpServletResponse response) {
        String result = null;

        /*
        Token part begin
         */
       // Token token = tokenService.findByToken(authToken);
        //TODO проверка на истекание токена

        //пока считаем, что токен прошел
        //User currentUser = token.getUser();

        User currentUser = userService.findUserById(1);//пока заглушка
        List<Adress> adressesList = adressService.findAllByUser(currentUser);


        //TODO сделать большое условие проверки токена на експайр и если фолсе, то ретурн  400 и возврат на страницу авторизации
        /*
        Token part end
         */
        //TODO тут сделать вычитку из юзера
        //TODO тут будет проверка на то есть ли зарегестрированные обжекты (если нет, то после елса)

        if (!adressesList.isEmpty()) {
            ObjectListJsonBuilder builder = new ObjectListJsonBuilder();

            //TODO здесь будет метод, который будет вытягивать данные объектов учета, зарегистрированных у пользователя:
            // т.е. нужно подвязываться к пользователю. по токену.
            for(Adress adress: adressesList){
                //if its a house or a garage or smth like that
                if(adress.getAppartmentNumber().isEmpty()){
                    String objectName = adress.getType() + ", " + adress.getStreet() + " " + adress.getHouseNumber();
                    builder.addInfo(new ObjectListEntity(adress.getId(), objectName, adress.getObjectDefault()));
                }else {
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
        //TODO проверка токена и все вытекающие
    }
}
