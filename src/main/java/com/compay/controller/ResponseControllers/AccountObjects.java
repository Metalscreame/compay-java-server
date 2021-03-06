package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.AccountObjectsDelete.AccObjDelEntity;
import com.compay.json.accountObjects.AccountObjectsJSON;
import com.compay.json.accountObjects.AccountObjectsJSONUpdate;
import com.compay.service.*;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
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

    @Autowired
    DefaultRatesService defaultRatesService;

    @Autowired
    DefaultScalesService defaultScalesService;

    @Autowired
    AdressArgumentsService adressArgumentsService;
    @Autowired
    ArgumentsService argumentsService;

    @RequestMapping(value = "/accountObjects/add", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyAdd(@RequestHeader(value = CONTENT_TYPE) String type,
                                  @RequestHeader(value = AUTHORIZATION) String authToken,
                                  @RequestBody AccountObjectsJSON accountObjectsJSON,
                                  HttpServletResponse response) {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            if (accountObjectsJSON == null) throw new WrongDataExc();
            User currentUser = tokenService.findByToken(authToken).getUser();

            try {

                //creating adress
                Adress adress = new Adress();
                adress.setType(accountObjectsJSON.getName());
                adress.setObjectDefault(true);//костыль для фронта
                adress.setUser(currentUser);
                adress.setAppartmentNumber("");
                adress.setCity("");
                adress.setHouseNumber((short) 0);
                adress.setRegion("");
                adress.setStreet("");


                //creating current first day of a month
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH,1);
                calendar.set(Calendar.YEAR,2017);

                Timestamp timestampObj = new Timestamp(calendar.getTimeInMillis());
                boolean isFormula = false;

                //creating adress services and default rates for selected services
                Set<AdressServices> adressServSet = new HashSet<>();
                for (Integer serviceId : accountObjectsJSON.getServices()) {

                    Services service = servicesService.findServicesById(serviceId);

                    if (service == null) throw new WrongDataExc();

                    AdressServices adressService = new AdressServices();
                    adressService.setAdress(adress);
                    adressService.setService(service);

                    Set<Rates> rateSet = new HashSet<>();
                    DefaultRates defaultRate = defaultRatesService.findByService_Id(serviceId);
                    Rates rateToSet = new Rates();
                    rateToSet.setMainRate(defaultRate.getMainRate());
                    rateToSet.setMethod(defaultRate.getMethod());
                    rateToSet.setPeriodFrom(timestampObj);
                    rateToSet.setUserScale(defaultRate.getUserScale());
                    rateToSet.setFormula(defaultRate.getFormula());
                    if (defaultRate.getFormula() != null && !defaultRate.getFormula().isEmpty()){
                        isFormula = true;
                    }
                    rateToSet.setAdressService(adressService);
                    List<DefaultScales> defaultScalesList = defaultScalesService.findByDefaultRates(defaultRate);
                    Set<Scales> scalesSet = new HashSet<>();
                    for (DefaultScales defaultScales : defaultScalesList) {
                        Scales scale = new Scales();
                        scale.setMinValue(defaultScales.getMinValue());
                        scale.setMaxValue(defaultScales.getMaxValue());
                        scale.setMainRate(defaultScales.getMainRate());
                        scale.setRate(rateToSet);
                        scalesSet.add(scale);
                    }
                    rateToSet.setScale(scalesSet);

                    rateSet.add(rateToSet);

                    adressService.setRate(rateSet);
                    adressServSet.add(adressService);

                }
                adress.setAdressService(adressServSet);
                adressService.create(adress);

                //made for rates update, dummy arguments
                if (isFormula) {
                    for (int i = 1; i <= 3; i++) {
                        Arguments arguments = argumentsService.findArgumentById(i);
                        AdressArguments ad = new AdressArguments();
                        ad.setAdress(adress);
                        ad.setValue(0.0);
                        ad.setArgument(arguments);
                        adressArgumentsService.create(ad);
                    }
                }

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

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);

                        Timestamp timestampObj = new Timestamp(calendar.getTimeInMillis());

                        Set<Rates> rateSet = new HashSet<>();
                        DefaultRates defaultRate = defaultRatesService.findByService_Id(serviceId);
                        Rates rateToSet = new Rates();
                        rateToSet.setMainRate(defaultRate.getMainRate());
                        rateToSet.setMethod(defaultRate.getMethod());
                        rateToSet.setPeriodFrom(timestampObj);
                        rateToSet.setUserScale(defaultRate.getUserScale());
                        rateToSet.setFormula(defaultRate.getFormula());
                        rateToSet.setAdressService(adressService);
                        rateSet.add(rateToSet);
                        adressService.setRate(rateSet);

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
