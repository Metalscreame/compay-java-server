package com.compay.controller.ResponseControllers;

import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class Faq {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/faq", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                                  @RequestHeader(value = "Authorization") String authToken,
                                  HttpServletResponse response){


        try {
            String result = null;
            if(tokenService.authChek(authToken)) {
            } else throw new AuthException();

            User currentUser = tokenService.findByToken(authToken).getUser();

            if(currentUser == null) throw new WrongDataExc();


            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"faq\"}";

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
