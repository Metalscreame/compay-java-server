package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.requisites.Req;
import com.compay.json.requisites.get.ReqGetBuilder;
import com.compay.json.requisites.get.ReqService;
import com.compay.json.requisites.update.RequaritiesUpdate;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


@Controller
public class RequaritiesController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdressServicesService adressServicesService;

    @Autowired
    private AdressService adressService;

    @Autowired
    ServicesService servicesService;

    @RequestMapping(value = "/requisites/update", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String reqUpdate(
            @RequestHeader(value = CONTENT_TYPE) String type,
            @RequestHeader(value = AUTHORIZATION) String authToken,
            @RequestBody String body,
            HttpServletResponse response) throws AuthException, JsonProcessingException {
        String result = "";

        try {

            //auth
            try {
                if (tokenService.authChek(authToken)) {
                } else throw new AuthException();
            } catch (AuthException e) {
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Unauthorized\"}";
            }

            RequaritiesUpdate updateBody;
            try {
                updateBody = new ObjectMapper().readValue(body, RequaritiesUpdate.class);
            } catch (Exception e) {
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Wrong data\"}";
            }


            AdressServices reqToUpdate;

            try {
                reqToUpdate = adressServicesService.findByAdressIdandServiceId(updateBody.getObjectID(), updateBody.getServiceID());
                if (reqToUpdate == null) throw new WrongDataExc();
            } catch (WrongDataExc e) {
                response.setStatus(400);
                response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
                return "{\"info\": \"Такого ObjectId или ServiceId не существует\"}";
            }

            reqToUpdate.setPersAcc(updateBody.getReq().getPersAcc());
            reqToUpdate.setCheckAcc(Long.parseLong(updateBody.getReq().getCheckAcc()));
            reqToUpdate.setEGRPO(updateBody.getReq().getEGRPO());
            reqToUpdate.setMFO(updateBody.getReq().getMFO());

            adressServicesService.update(reqToUpdate);

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return "{\"info\": \"Реквизиты успешно обновлены\"}";
        } catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \" " + e + " \"}";
        }


    }

    @RequestMapping(value = "/requisites/{objectID}", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String reqGet(
            @RequestHeader(value = CONTENT_TYPE) String type,
            @RequestHeader(value = AUTHORIZATION) String authToken,
            HttpServletResponse response,
            @PathVariable("objectId") int id) throws AuthException, JsonProcessingException {

        String result = "";


        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }


        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            Adress adress = adressService.findAdressById(id);

            List<AdressServices> adressServicesList = adressServicesService.findAllByAdress(adress);

            ReqGetBuilder builder = new ReqGetBuilder();

            for (AdressServices adressService : adressServicesList) {

                Req req = new Req(adressService.getPersAcc(), String.valueOf(adressService.getCheckAcc()), adressService.getMFO(), adressService.getEGRPO());

                ReqService reqService = new ReqService((byte) adressService.getService().getId(), adressService.getService().getServiceName(), req);
                builder.addInfo(reqService);
            }

            result = builder.createJson();

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return result;
        } catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \" " + e + "\"}";
        }
    }
}
