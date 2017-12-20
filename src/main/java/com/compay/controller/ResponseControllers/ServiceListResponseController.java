package com.compay.controller.ResponseControllers;


import com.compay.json.ServiceListResponse.ServiceListEntity;
import com.compay.json.ServiceListResponse.ServiceListJsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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
    //TODO может быть разный айди. поискать как получать разные айдишники при запросе
    //TODO @RequestMapping(value="/car/{carId}", method = RequestMethod.Get)
    @RequestMapping(value = "/serviceList/{objectId}", method = RequestMethod.GET)
    @ResponseBody
    public String  responseBody(HttpServletResponse response, @PathVariable("objectId") String id) {
        /*
        Это ID из другой таблицы: Address. По нему определяется объект учета (домохозяйство).
        Потом из соединения AddressServices с Services по этому ID получается список услуг
         */
        String result = null;
        ServiceListJsonBuilder serviceListJsonBuilder = new ServiceListJsonBuilder();

        if(id!=null){
            serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));

            //TODO здесь будут вытаскиваться данные из базы и добавляться в виде обжектов
            serviceListJsonBuilder.addInfo(new ServiceListEntity("Электроснабжение", "electric"));

            try {
                result = serviceListJsonBuilder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


        else {
            serviceListJsonBuilder.addInfo(new ServiceListEntity("Все услуги", "all"));
            try {
                result = serviceListJsonBuilder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //TODO условие проверке токена авторизации и выдача 401 в случае чего ->редирект
        response.setStatus(200);
        response.setHeader("headers","{ 'Content-Type': 'application/json' }");
        response.setHeader("data",result);
        return result;
    }
}
