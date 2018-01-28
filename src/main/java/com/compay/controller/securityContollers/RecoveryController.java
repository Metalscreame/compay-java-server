package com.compay.controller.securityContollers;


import com.compay.entity.User;
import com.compay.json.jsonReceive.recovery.RecoveryEntity;
import com.compay.service.MailService;
import com.compay.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Controller
public class RecoveryController {

    @Autowired
    private UserService service;

    @Autowired
    private MailService mailSender;


    @RequestMapping(value = "/auth/recovery", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String recovery(@RequestHeader(value = "Content-Type") String contentType,
                           @RequestBody String body,
                           HttpServletResponse response) {
        RecoveryEntity recoveryEntity;
        try {
            recoveryEntity = new ObjectMapper().readValue(body, RecoveryEntity.class);

        } catch (IOException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            e.printStackTrace();
            return "{\"info\":\"Wrong Data Format\"}";
        }
        User user;
        try {
            user = service.findByEmail(recoveryEntity.getEmail());
            if (user==null) throw new Exception();
        } catch (Exception e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\":\"Такой эмейл не найден!\"}";
        }

        //generating new pass
        Random random = new Random();
        String newPass = "";
        for (int i = 0; i < 10; i++) newPass += "" + random.nextInt(9);
        //updating password
        try{
            service.updateUser(newPass,recoveryEntity.getEmail());

        }catch (Exception e){
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Что-то пошло не так...\"}";
       }
        user = service.findByEmail(user.getEmail());

        //temp for lambda
        User finalUser = user;
        new Thread(() -> mailSender.sendRecoveryMail(finalUser)).start();

        response.setStatus(200);
        response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
        return "{\"info\": \"На указанный адрес E-mail отправлено письмо с новым паролем\"}";
    }




}
