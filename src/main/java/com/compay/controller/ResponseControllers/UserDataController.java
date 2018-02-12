package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.service.AdressService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserDataController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdressService adressService;
    @RequestMapping(value = "/userData", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response) throws AuthException, JsonProcessingException {
        String res = "";

//        String authToken="2f12931cb1c6032a1472d50ccdfc1b4185e6af78";

        if (!tokenService.authChek(authToken)) {
            response.setStatus(401);

            ObjectNode error = new ObjectMapper().createObjectNode();
            error.put("message","Unauthorized");
            res = error.toString();
        }
        else {
            User usrByToken = tokenService.findByToken(authToken).getUser();
            Adress findedAdress = adressService.findDefaultAdressByUsrId(usrByToken.getId());

            ObjectNode rootNode = new ObjectMapper().createObjectNode();
            rootNode.put("isAdmin",usrByToken.getRole().equals("admin"));

            ObjectNode adressNode = new ObjectMapper().createObjectNode();

            if(findedAdress != null) {
                adressNode.put("id", findedAdress.getId());
                adressNode.put("name", findedAdress.getType() + ", " + findedAdress.getStreet() + " " +
                        findedAdress.getHouseNumber() + "/" + findedAdress.getAppartmentNumber());
                adressNode.put("objectDefault", findedAdress.getObjectDefault());

            }
            rootNode.put("currentObject",adressNode);

            res  = rootNode.toString();

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");

        }
        return res;
    }
}
