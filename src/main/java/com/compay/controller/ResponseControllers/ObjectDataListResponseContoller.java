package com.compay.controller.ResponseControllers;


import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.global.Constants;
import com.compay.json.ObjectsDataList.ObjectsDataListEntity;
import com.compay.json.ObjectsDataList.ObjectsDataListJsonBuilder;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

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


    @RequestMapping(value = "/objectsDataList", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response) throws AuthException {
        try {
            //Token check
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            ObjectsDataListJsonBuilder builder = new ObjectsDataListJsonBuilder();

            User currentUser = tokenService.findByToken(authToken).getUser();

            List<Adress> adressesList = adressService.findAllByUser(currentUser);

            //fullServiceList
            List<Services> servicesList = servicesService.findAll();
            for (Services service : servicesList) {
                LinkedHashMap fullServiceListMap = new LinkedHashMap();
                fullServiceListMap.put("serviceID", service.getId());
                fullServiceListMap.put("name", service.getServiceName());
                builder.addFullServiceList(fullServiceListMap);
            }

            //if adress list in not empty
            if (!adressesList.isEmpty()) {
                String result = "";
                for (Adress adres : adressesList) {
                    //if its a house or a garage or smth like that
                    if (adres.getAppartmentNumber().isEmpty()) {
                        //building accountObjects
                        String objectName = adres.getType() + ", " + adres.getStreet() + " " + adres.getHouseNumber();
                        //building serviceList
                        String resultServiceList = "";
                        Adress adress = adressService.findAdressById(adres.getId());
                        List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);
                        List<Map> serviceList = new ArrayList<>();
                        for (AdressServices adressServices : adressServicesList) {
                            Services service = adressServices.getService();
                            LinkedHashMap map = new LinkedHashMap();
                            map.put("serviceID", service.getId());
                            map.put("name", service.getServiceName());
                            serviceList.add(map);
                        }
                        builder.addInfo(new ObjectsDataListEntity(adres.getId(), objectName, adres.getObjectDefault(), serviceList));
                    } else {//if its a flat
                        //accountObjects
                        String objectName = adres.getType() + ", " + adres.getStreet() + " " + adres.getHouseNumber() +
                                "/" + adres.getAppartmentNumber();
                        //serviceList
                        Adress adress = adressService.findAdressById(adres.getId());
                        List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);
                        List<Map> serviceList = new ArrayList<>();
                        for (AdressServices adressServices : adressServicesList) {
                            Services service = adressServices.getService();
                            LinkedHashMap map = new LinkedHashMap();
                            map.put("serviceID", service.getId());
                            map.put("name", service.getServiceName());
                            serviceList.add(map);
                        }
                        builder.addInfo(new ObjectsDataListEntity(adres.getId(), objectName, adres.getObjectDefault(), serviceList));
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
                String result = "";
                try {
                    result = builder.createJson();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return result;
            }
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }

    }
}
