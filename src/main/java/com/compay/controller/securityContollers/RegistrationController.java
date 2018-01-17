package com.compay.controller.securityContollers;


import com.compay.entity.User;

import com.compay.exception.AuthException;
import com.compay.json.jsonReceive.register.PersonToRegisterEntity;
import com.compay.service.MailService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller

public class RegistrationController {
    @Autowired
    private UserService service;

    @Autowired
    private MailService mailSender;


    @RequestMapping(value = "/auth/register",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")//TODO change to post
    @ResponseBody
    public String registrationPage(@RequestHeader(value = "Content-Type") String contentType,
                                   @RequestBody String body,
                                   HttpServletResponse response) {
       try {
           User user = new User();
           PersonToRegisterEntity newUser;
           try{
               newUser = new ObjectMapper().readValue(body, PersonToRegisterEntity.class);
           }catch (IOException e){
               response.setStatus(402);
               response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
               return "{\"info\":\"Wrong data format!\"}";
           }

           //check for unique email
           User checkerUser = service.findByEmail(newUser.getEmail());
           if(checkerUser!=null){
               if(checkerUser.getEmail().equals(newUser.getEmail()))
                   throw new AuthException();
           }



           user.setId(Integer.MAX_VALUE);//cant be null and cant be a number that is already registered
           user.setEmail(newUser.getEmail());
           user.setPassword(newUser.getPassword());
           user.setName(newUser.getName());
           user.setLastName(newUser.getSurname());
           user.setRole("user");
           service.create(user);

            //because email sends for too long
           Thread thread1 = new Thread () {
               public void run () {
                   mailSender.sendEmail(user);
               }
           };
           thread1.start();

           response.setStatus(200);
           response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");

           return "{\"info\":\"New User Registered!\"}";
       }catch (AuthException e){
           response.setStatus(401);
           response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
           return "{\"info\":\"Email is already in use!\"}";
       }
    }


}


