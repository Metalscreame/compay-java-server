package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Services;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.json.accountObjects.AccountObjectsJSON;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.compay.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

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

    @RequestMapping(value = "/accountObjects/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               @RequestBody AccountObjectsJSON accountObjectsJSON,
                               HttpServletResponse response){


        try {
            String result = null;
            if(tokenService.authChek(authToken)) {
            } else throw new AuthException();
            if(accountObjectsJSON == null) throw new WrongDataExc();
                User currentUser = tokenService.findByToken(authToken).getUser();

                try {
                    Adress adress = new Adress();
                    //adress.setId(999999999);
                    adress.setType(accountObjectsJSON.getName());
                    adress.setObjectDefault(accountObjectsJSON.isObjectDefault());
                    adress.setUser(currentUser);
                    adress.setAppartmentNumber("");
                    adress.setCity("");
                    adress.setHouseNumber((short)0);
                    adress.setRegion("");
                    adress.setStreet("");

                    Set<AdressServices> adressServSet = new HashSet<>();
                    for (Integer serviceId : accountObjectsJSON.getServices()) {

                        Services service = servicesService.findServicesById(serviceId);

                        if(service == null) throw new WrongDataExc();

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
                }catch (WrongDataExc e) {
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

}
