package com.compay.controller.securityContollers;

import com.compay.entity.Adress;
import com.compay.entity.Token;
import com.compay.entity.User;
import com.compay.json.Login.LoginResponseBuilder;
import com.compay.json.Login.LoginResponseEntity;
import com.compay.json.ObjectList.ObjectListEntity;
import com.compay.json.jsonReceive.login.PersonToLoginEntity;
import com.compay.service.AdressService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class LoginController {
    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdressService adressService;

    @ResponseBody
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String loginPage(@RequestHeader(value = "Content-Type") String contentType,
                            @RequestBody String body,
                            HttpServletResponse response) throws IOException {
        boolean isUser, isAdmin;
        LoginResponseBuilder builder = new LoginResponseBuilder();


        //json deserealize
        PersonToLoginEntity userToLogin = new ObjectMapper().readValue(body, PersonToLoginEntity.class);

        Token newToken = new Token();
        try {
            String result;
            //will throw exception if not find
            User user = service.findByEmail(userToLogin.getEmail());
            //check for password

            if (!user.getPasswordDecoded().equals(userToLogin.getPassword())) {
                throw new Exception();
            }

           //check if admin
            if (user.getRole().equals("admin")) {
                isAdmin = true;
                isUser = false;
            } else {
                isAdmin = false;
                isUser = true;
            }

            String usrPassHash = DigestUtils.sha1Hex(user.getEmail() + user.getPassword());
            try{
                //will throw an exception if not found
                Token currentUsrToken=tokenService.findByUsrPssHash(usrPassHash);//eqls to findbyid
                if (currentUsrToken.getUser().getRole().equals("admin")){
                    newToken.setId(Integer.MAX_VALUE);
                    newToken.setUser(user);
                    newToken.setTokenCreateDate();
                    newToken.setUserPlusPassHash(user.getEmail()+user.getPassword());
                    newToken.setToken();
                    tokenService.create(newToken);
                }else {
                    //if users token was in da base -> delets old and creates new
                    tokenService.delete(currentUsrToken.getToken());
                    newToken.setId(Integer.MAX_VALUE);
                    newToken.setUser(user);
                    newToken.setTokenCreateDate();
                    newToken.setUserPlusPassHash(usrPassHash);
                    newToken.setToken();
                    tokenService.create(newToken);
                }

            }catch (Exception e){
                //first time Login
                newToken.setId(Integer.MAX_VALUE);
                newToken.setUser(user);
                newToken.setTokenCreateDate();
                newToken.setUserPlusPassHash(usrPassHash);
                newToken.setToken();
                tokenService.create(newToken);
            }


            ObjectListEntity objectDefault = new ObjectListEntity();
            List<Adress> adresses = adressService.findAllByUser(user);
            List<Adress> adressesObjDefault = adresses;
            adressesObjDefault = adresses.stream().filter(d->d.getObjectDefault()==true).collect(Collectors.toList());

            
            if(adressesObjDefault.size() > 0) {
                objectDefault = new ObjectListEntity(adressesObjDefault.get(0).getId(), adressesObjDefault.get(0).getType(), adressesObjDefault.get(0).getObjectDefault());
            }else if(adresses.size() > 0) {
                objectDefault = new ObjectListEntity(adresses.get(0).getId(), adresses.get(0).getType(), adresses.get(0).getObjectDefault());
            }

            builder.addInfo(new LoginResponseEntity(newToken.getToken(),  isAdmin,isUser, objectDefault));
            result = builder.createJson();
            response.setStatus(200);
            response.setHeader("Headers", "{\"Content-Type\":\"application/json\"}");
            return result;
        } catch (Exception e) {
            response.setStatus(401);
            response.setHeader("Headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\":\"Неверный пароль или логин\"}";
        }


    }
}
