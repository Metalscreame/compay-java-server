package com.compay.controller.securityContollers;


import com.compay.entity.User;
import com.compay.json.jsonReceive.register.PersonToRegisterEntity;
import com.compay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sqlite.SQLiteException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller

public class RegistrationController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/register",method = RequestMethod.GET)//TODO change to post
    @ResponseBody
    public String registrationPage(@RequestParam(value = "body",required = false) PersonToRegisterEntity newUser1, HttpServletResponse response){
        User user = new User();
        //--for test only
        PersonToRegisterEntity newUser = new PersonToRegisterEntity("root@root.root","adasd","asdaa","adag");
        //--for test only

       try {

           //--for test only
           user.setId(214748367);//cant be null and cant be a number that is already registered
           user.setEmail(newUser.getEmail());
           user.setPassword(newUser.getPassword());
           user.setName(newUser.getName());
           user.setRole("user");
           user.setLastName("a");
           service.create(user);
           //--for test only


           response.setHeader("status","200");
           response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");

           return "{\"info\":\"New User Registered!\"}";
       }catch (Exception e){
           response.setHeader("status","400");
           response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
           return "{\"info\":\"Email is already in use!\"}";
       }
    }

}
