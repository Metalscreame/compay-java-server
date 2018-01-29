package com.compay.controller.ResponseControllers;


import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.exception.AuthException;
import com.compay.json.requisites.Req;
import com.compay.json.requisites.get.ReqGetBuilder;
import com.compay.json.requisites.get.ReqService;
import com.compay.json.requisites.get.RequisitesList;
import com.compay.json.requisites.update.RequaritiesUpdate;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Requarities {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressServicesService adressServicesService;

    @Autowired
    private AdressService adressService;



    @RequestMapping(value = "/requisites/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String reqUpdate(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               @RequestBody String body,
                               HttpServletResponse response) throws AuthException, JsonProcessingException {
        String result="";

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

                RequaritiesUpdate requaritiesUpdate;
                try {
                    requaritiesUpdate= new ObjectMapper().readValue(body,RequaritiesUpdate.class);
                }catch (Exception e){
                    response.setStatus(402);
                    response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                    return "{\"info\": \"Wrong date\"}";
                }

                ReqGetBuilder reqGetBuilder = new ReqGetBuilder();


                /*
                body: {
                objectID:'22';
                serviceID: '1',
                req:{
                    persAcc:"123456",
                    checkAcc:"260065789876890",
                    MFO:"309802",
                    EGRPO:"12324232"
                    }
                 }
                 */






            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return "{\"info\": \"Реквизиты успешно обновлены\"}";

        }catch (AuthException e){
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }catch (Exception e)
        {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Something is wrong\"}" + e;
        }

    }



    @RequestMapping(value = "/requisites/{objectID}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String reqGet(@RequestHeader(value = "Content-Type") String type,
                            @RequestHeader(value = "Authorization") String authToken,
                            HttpServletResponse response,
                            @PathVariable("objectID") int id) throws AuthException, JsonProcessingException {

        String result ="";

        try{
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();



            Adress adress = adressService.findAdressById(id);

            List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);

            ReqGetBuilder builder = new ReqGetBuilder();

            for(AdressServices adressService: adressServicesList){

                Req req = new Req(adressService.getPersAcc(), adressService.getCheckAcc(), adressService.getMFO(), adressService.getEGRPO());

                ReqService reqService = new ReqService((byte)adressService.getService().getId(), adressService.getService().getServiceName(), req);
                builder.addInfo(reqService);
            }

            result = builder.createJson();

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return result;
        }catch (AuthException e){
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Something is wrong\"}" + e;
        }

    }
}
