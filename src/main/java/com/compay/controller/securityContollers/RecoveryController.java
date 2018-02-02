package com.compay.controller.securityContollers;


import com.compay.entity.Token;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.global.Constants;
import com.compay.json.jsonReceive.recovery.RecoveryEntity;
import com.compay.service.MailService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class RecoveryController {

    @Autowired
    private UserService service;

    @Autowired
    private MailService mailSender;

    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/auth/recovery", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String recovery(@RequestHeader(value = CONTENT_TYPE) String contentType,
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
            if (user == null) throw new Exception();
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
        try {
            service.updateUser(newPass, recoveryEntity.getEmail());

        } catch (Exception e) {
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


    @RequestMapping(value = "/admin/resetUserPassword", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String reset(@RequestHeader(value = CONTENT_TYPE) String contentType,
                        @RequestHeader(value = AUTHORIZATION) String authToken,
                        HttpServletResponse response) {
        User user;
        try {
            try {
                if (tokenService.authChek(authToken)) {
                } else throw new AuthException();
            } catch (AuthException e) {
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Unauthorized\"}";
            }

            Token currentToken = tokenService.findByToken(authToken);

            //generating new pass
            Random random = new Random();
            String newPass = "";

            for (int i = 0; i < 10; i++) newPass += "" + random.nextInt(9);

            //updating password
            try {
                service.updateUser(newPass, currentToken.getUser().getEmail());
            } catch (Exception e) {
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Что-то пошло не так...\"}";
            }

            //recieving updated user
            user = service.findByEmail(currentToken.getUser().getEmail());

            //temp for lambda
            User finalUser = user;
            new Thread(() -> mailSender.sendRecoveryMail(finalUser)).start();

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"На указанный адрес E-mail отправлено письмо с новым паролем\"}";
        } catch (Exception e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Что-то пошло не так\"}" + "\n" + e;
        }
    }
}
