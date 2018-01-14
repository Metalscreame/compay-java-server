package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.json.ServiceListResponse.ServiceListEntity;
import com.compay.json.ServiceListResponse.ServiceListJsonBuilder;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
Example
status: 200
headers: { 'Content-Type': 'application/json' }
data: {
    services: [
        { name: "Все услуги",       link: "all" },
        { name: "Электроснабжение", link: "electric" },
        { name: "Газоснабжение",    link: "gas" },
        { name: "Водоснабжение",    link: "water" },
        { name: "Отопление",        link: "hate" },
        { name: "Квартплата",       link: "house" },
        { name: "Лифт",             link: "lift" },
        { name: "Вывоз мусора",     link: "garbage" }
    ]
}
objectID - это ID текущего объекта учета, который выбран в хедере сайта.
Может быть пустым (если у пользователя еще нет зарегистрированных объектов учета).
В ответ сервер должен отдать JSON, который содержит массив услуг, "подвязанных" к выбранному объекту учета:

Если у пользователя нет зарегистрированных объектов (передан пустой objectID), то в ответ сервер должен отдать JSON с данными:
status: 200
headers: { 'Content-Type': 'application/json' }
data: {
    services: [
        { name: "Все услуги", link: "all" },
    ],
}
 */
//TODO добавить в каждый контроллер проверку токена и если истек, то выкидываем хидер со статусом 401 и переход на главную страницу

@Controller
public class ServiceListResponseController {

    @Autowired
    AdressService adressService;

    @Autowired
    ServicesService servicesService;

    @Autowired
    AdressServicesService adressServicesService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/serviceList/{objectId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String contentType,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response, @PathVariable("objectId") String id) {
        /*
        Это ID из другой таблицы: Address. По нему определяется объект учета (домохозяйство).
        Потом из соединения AddressServices с Services по этому ID получается список услуг
        objectID - это ID текущего объекта учета, который выбран в хедере сайта. Может быть пустым (если у пользователя еще нет зарегистрированных объектов учета). В ответ сервер должен отдать JSON, который содержит массив услуг, "подвязанных" к выбранному объекту учета:
         */
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            String result = null;
            ServiceListJsonBuilder serviceListJsonBuilder = new ServiceListJsonBuilder();

            serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));
            Adress adress = adressService.findAdressById(Integer.valueOf(id));
            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail())|| adress==null) {
                throw new WrongDataExc();
            }

            List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);
            for (AdressServices adressServices : adressServicesList) {
                Services service = adressServices.getService();
                serviceListJsonBuilder.addInfo(new ServiceListEntity(service.getServiceName(), service.getLink()));
            }
            try {
                result = serviceListJsonBuilder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //TODO условие проверке токена авторизации и выдача 401 в случае чего -> редирект
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Wrong objectID\"}";
        }


    }

    // if objectID == null
    @RequestMapping(value = "/serviceList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(
            @RequestHeader(value = "Content-Type") String contentType,
            @RequestHeader(value = "Authorization") String authToken,
            HttpServletResponse response) {
        try {
            if (tokenService.authChek(authToken)) {}
            else throw new AuthException();

            String result = null;
            ServiceListJsonBuilder serviceListJsonBuilder = new ServiceListJsonBuilder();
            serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));

            try {
                result = serviceListJsonBuilder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }

}
