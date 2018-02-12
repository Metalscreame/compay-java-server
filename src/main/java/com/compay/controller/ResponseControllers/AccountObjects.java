package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.AccountObjectsDelete.AccObjDelEntity;
import com.compay.json.accountObjects.AccountObjectsJSON;
import com.compay.json.accountObjects.AccountObjectsJSONUpdate;
import com.compay.json.profile.ProfileBuilder;
import com.compay.json.profile.ProfileEntity;
import com.compay.repository.DefaultRatesRepository;
import com.compay.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    DefaultRatesService defaultRatesService;

    @Autowired
    RatesService ratesService;


        @RequestMapping(value = "/accountObjects/add", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
        @ResponseBody
        public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                                   @RequestHeader(value = AUTHORIZATION) String authToken,
//                @RequestBody AccountObjectsJSON accountObjectsJSON,
                                   @RequestBody Adress adress,
                                   HttpServletResponse response, HttpServletRequest request) {


        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            try {


                List<AdressServices> receivedAdressServiceesList =
                        adressServicesService.findAllByAdress(adress);



                for(AdressServices adressServices:receivedAdressServiceesList){
                    Services service = adressServices.getService();

                    DefaultRates defaultRates = defaultRatesService.findByService_Id(service.getId());


                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    calendar.set(Calendar.DAY_OF_MONTH,1);



                    Rates rates = new Rates();
                    rates.setAdressService(adressServices);
                    rates.setFormula(defaultRates.getFormula());
                    rates.setMainRate(defaultRates.getMainRate());
                    rates.setUserScale(defaultRates.getUserScale());
                    rates.setMethod(defaultRates.getMethod());
                    rates.setPeriodFrom( new Timestamp(calendar.getTimeInMillis()));

                    ratesService.create(rates);


                }

                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Объект учета успешно добавлен\"}";
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Wrong data\"}";
            }

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (Exception e) {
            e.printStackTrace();
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
