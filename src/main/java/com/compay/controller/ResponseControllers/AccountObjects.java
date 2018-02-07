package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.AccountObjectsDelete.AccObjDelEntity;
import com.compay.json.accountObjects.AccountObjectsJSON;
import com.compay.json.accountObjects.AccountObjectsJSONUpdate;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class AccountObjects {

    @Autowired
    TokenService tokenService;

    @Autowired
    AdressService adressService;

    @Autowired
    AdressServicesService adressServicesService;

    @Autowired
    ServicesService servicesService;

    @RequestMapping(value = "/accountObjects/add", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyAdd(@RequestHeader(value = CONTENT_TYPE) String type,
                                  @RequestHeader(value = AUTHORIZATION) String authToken,
                                  @RequestBody AccountObjectsJSON accountObjectsJSON,
                                  HttpServletResponse response) {


        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            if (accountObjectsJSON == null) throw new WrongDataExc();
            User currentUser = tokenService.findByToken(authToken).getUser();

            try {
                Adress adress = new Adress();
                //adress.setId(999999999);
                adress.setType(accountObjectsJSON.getName());
                //костыль для фронта
                adress.setObjectDefault(true);
                adress.setUser(currentUser);
                adress.setAppartmentNumber("");
                adress.setCity("");
                adress.setHouseNumber((short) 0);
                adress.setRegion("");
                adress.setStreet("");

                Set<AdressServices> adressServSet = new HashSet<>();
                for (Integer serviceId : accountObjectsJSON.getServices()) {

                    Services service = servicesService.findServicesById(serviceId);

                    if (service == null) throw new WrongDataExc();

                    AdressServices adressService = new AdressServices();
                    adressService.setAdress(adress);
                    adressService.setService(service);
                    adressServSet.add(adressService);
                }
                adress.setAdressService(adressServSet);
                adressService.create(adress);

                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Объект учета успешно добавлен\"}";
            } catch (WrongDataExc e) {
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Wrong data\"}";
            }

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Wrong data\"}";
        }
    }

    @RequestMapping(value = "/accountObjects/update", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyUpdate(@RequestHeader(value = CONTENT_TYPE) String type,
                                     @RequestHeader(value = AUTHORIZATION) String authToken,
                                     @RequestBody AccountObjectsJSONUpdate AccountObjectsJSONUpdate,
                                     HttpServletResponse response) {
        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            if (AccountObjectsJSONUpdate == null) throw new WrongDataExc();
            User currentUser = tokenService.findByToken(authToken).getUser();

            try {
                Adress adress = adressService.findAdressById(AccountObjectsJSONUpdate.getId());

                List<AdressServices> servicesList = adressServicesService.findAllByAdress(adress);

                //set mark NotActive = true all services
                for (AdressServices adressServicesExist : servicesList) {
                    adressServicesExist.setNotActive(true);
                }
                //find and update mark NotActive
                for (Integer serviceId : AccountObjectsJSONUpdate.getServices()) {
                    boolean newServiceId = true;
                    for (AdressServices adressServicesExist : servicesList) {

                        if (adressServicesExist.getService().getId() == serviceId) {
                            adressServicesExist.setNotActive(false);
                            newServiceId = false;
                        }
                    }
                    if (newServiceId) {
                        Services service = servicesService.findServicesById(serviceId);

                        if (service == null) throw new WrongDataExc();

                        AdressServices adressService = new AdressServices();
                        adressService.setAdress(adress);
                        adressService.setService(service);
                        adressServicesService.create(adressService);
                    }
                }

                //update adressServicesExist
                for (AdressServices adressServicesExist : servicesList) {

                    adressServicesService.update(adressServicesExist);
                }

                return "{\"info\": \"Объект учета успешно обновлён\"}";
            } catch (WrongDataExc e) {
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Wrong data\"}";
            }

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Wrong data\"}";
        }
    }


    @RequestMapping(value = "/accountObjects/delete", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyDelete(@RequestHeader(value = CONTENT_TYPE) String type,
                                     @RequestHeader(value = AUTHORIZATION) String authToken,
                                     @RequestBody String body,
                                     HttpServletResponse response) throws JsonProcessingException, ParseException {

        try {

            try {
                if (tokenService.authChek(authToken)) {
                } else throw new AuthException();

                AccObjDelEntity entity;
                try {
                    entity = new ObjectMapper().readValue(body, AccObjDelEntity.class);
                } catch (IOException e) {
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
            } catch (AuthException e) {
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Unauthorized\"}";
            }

        } catch (Exception e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"" + e + "\"}";
        }

    }
}
