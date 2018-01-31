package com.compay.controller.securityContollers;

import com.compay.entity.Token;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;


@Controller
public class DeleteUserController {

    @Autowired
    TokenService tokenService;


    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response) throws JsonProcessingException, ParseException {

        try{
            if(tokenService.authChek(authToken)) {
            } else throw new AuthException();

            Token currentToken=tokenService.findByToken(authToken);

            userService.deleteUser(currentToken.getUser().getId());

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Пользователь был успешно удален\"}";
        }catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Что-то пошло не так\"}"+e;
        }


    }
}
