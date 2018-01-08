package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.json.ServiceListResponse.ServiceListEntity;
import com.compay.json.ServiceListResponse.ServiceListJsonBuilder;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping(value = "/serviceList/{objectId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(HttpServletResponse response, @PathVariable("objectId") String id) {
        /*
        Это ID из другой таблицы: Address. По нему определяется объект учета (домохозяйство).
        Потом из соединения AddressServices с Services по этому ID получается список услуг
        objectID - это ID текущего объекта учета, который выбран в хедере сайта. Может быть пустым (если у пользователя еще нет зарегистрированных объектов учета). В ответ сервер должен отдать JSON, который содержит массив услуг, "подвязанных" к выбранному объекту учета:

         */
        String result = null;
        ServiceListJsonBuilder serviceListJsonBuilder = new ServiceListJsonBuilder();

        serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));
        Adress adress = adressService.findAdressById(Integer.valueOf(id));
        List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);

        //TODO здесь будут вытаскиваться данные из базы и добавляться в виде обжектов
        //serviceListJsonBuilder.addInfo(new ServiceListEntity("Электроснабжение", "electric"));
        //serviceListJsonBuilder.addInfo(new ServiceListEntity("Газоснабжение","gas"));
        //serviceListJsonBuilder.addInfo(new ServiceListEntity("Водоснабжение","water"));
        for(AdressServices adressServices: adressServicesList){
            Services service = adressServices.getService();
            serviceListJsonBuilder.addInfo(new ServiceListEntity(service.getServiceName(),service.getLink()));
        }
        try {
            result = serviceListJsonBuilder.createJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //TODO условие проверке токена авторизации и выдача 401 в случае чего -> редирект
        response.setStatus(200);
        response.setHeader("headers", "{ 'Content-Type': 'application/json' }");

        return result;
    }

    // if objectID == null
    @RequestMapping(value = "/serviceList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(HttpServletResponse response) {

        String result = null;
        ServiceListJsonBuilder serviceListJsonBuilder = new ServiceListJsonBuilder();
        serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));
        List<Services> servicesList = servicesService.findAll();
        for(Services service: servicesList){
            serviceListJsonBuilder.addInfo(new ServiceListEntity(service.getServiceName(),service.getLink()));
        }

        try {
            result = serviceListJsonBuilder.createJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //TODO условие проверке токена авторизации и выдача 401 в случае чего ->редирект
        response.setStatus(200);
        response.setHeader("headers", "{ 'Content-Type': 'application/json' }");
        return result;
    }


}
