package com.compay.controller.ResponseControllers;


import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.entity.User;
import com.compay.json.ObjectList.ObjectListEntity;
import com.compay.json.ObjectList.ObjectListJsonBuilder;
import com.compay.json.ObjectsDataList.ObjectsDataListEntity;
import com.compay.json.ObjectsDataList.ObjectsDataListJsonBuilder;
import com.compay.json.ServiceListResponse.ServiceListEntity;
import com.compay.json.ServiceListResponse.ServiceListJsonBuilder;
import com.compay.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ObjectDataListResponseContoller {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;
    @Autowired
    private AdressServicesService adressServicesService;

    @Autowired
    private ServicesService servicesService;


    @RequestMapping(value = "/objectDataList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    //TODO скорее всего сюда еще придется загонять токен или его првоерку
    public String responseBody(HttpServletResponse response) {
        ObjectsDataListJsonBuilder builder = new ObjectsDataListJsonBuilder();


        //token here
        User currentUser = userService.findUserById(1);//пока заглушка
        List<Adress> adressesList = adressService.findAllByUser(currentUser);
        ServiceListJsonBuilder serviceListFullBuilder = new ServiceListJsonBuilder();


        //fullServiceList
        List<Services> servicesList = servicesService.findAll();
        for(Services service: servicesList){
            LinkedHashMap fullServiceListMap = new LinkedHashMap();
            fullServiceListMap.put("serviceID",service.getId());
            fullServiceListMap.put("name",service.getServiceName());
            builder.addFullServiceList(fullServiceListMap);
        }


        //if adress list in not empty
        if (!adressesList.isEmpty()) {
            String result = "";
            for(Adress adres: adressesList){
                //if its a house or a garage or smth like that
                if(adres.getAppartmentNumber().isEmpty()){
                    //building accountObjects
                    String objectName = adres.getType() + ", " + adres.getStreet() + " " + adres.getHouseNumber();
                    //building serviceList
                    String resultServiceList="";
                    Adress adress = adressService.findAdressById(adres.getId());
                    List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);
                    List<Map> serviceList = new ArrayList<>();
                    for(AdressServices adressServices: adressServicesList){
                        Services service = adressServices.getService();
                        LinkedHashMap map= new LinkedHashMap();
                        map.put("serviceID",service.getId());
                        map.put("name",service.getServiceName());
                        serviceList.add(map);
                    }
                    builder.addInfo(new ObjectsDataListEntity(adres.getId(), objectName, adres.getObjectDefault(),serviceList));
                }else {//if its a flat
                    //accountObjects
                    String objectName = adres.getType() + ", " + adres.getStreet() + " " + adres.getHouseNumber() +
                            "/" + adres.getAppartmentNumber();
                    //serviceList
                    Adress adress = adressService.findAdressById(adres.getId());
                    List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);
                    List<Map> serviceList = new ArrayList<>();
                    for(AdressServices adressServices: adressServicesList){
                        Services service = adressServices.getService();
                        LinkedHashMap map= new LinkedHashMap();
                        map.put("serviceID",service.getId());
                        map.put("name",service.getServiceName());
                        serviceList.add(map);
                    }
                    builder.addInfo(new ObjectsDataListEntity(adres.getId(), objectName, adres.getObjectDefault(),serviceList));
                }
            }
            try {
                result = builder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
           // String fixedResult = result.replace("\\","");
            return result;
        }
        //Если у пользователя нет зарегистрированных объектов, то в ответ сервер должен отдать JSON с пустым массивом:
        else {
            String result ="";
            try {
                result = builder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return result;
        }
    }
}
