package com.compay.controller.AdminControllers;

import com.compay.entity.User;
import com.compay.json.adminResponses.rateList.RateListBuilder;
import com.compay.json.adminResponses.rateList.RateListEntity;
import com.compay.json.adminResponses.userList.Entity;
import com.compay.json.adminResponses.userList.UserListJsonBuilder;
import com.compay.json.calculation.CalculationBuilder;
import com.compay.json.calculation.CalculationEntity;
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
import java.util.List;

@Controller
public class RateList {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/admin/rateList", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  returnUserList(@RequestHeader(value = "Content-Type") String contentType,
                                  @RequestHeader(value = "Authorization")  String authToken,
                                  HttpServletResponse response) throws JsonProcessingException {

        if (tokenService.authChek(authToken) && tokenService.findByToken(authToken).getUser().getRole().equals("admin")){
            String result ="";

            ArrayList services= new ArrayList();
            RateListEntity entity = new RateListEntity(services);
            RateListBuilder builder = new RateListBuilder();




            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;
        }else {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }



    }




}



