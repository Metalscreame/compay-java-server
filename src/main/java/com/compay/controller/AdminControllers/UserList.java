package com.compay.controller.AdminControllers;

import com.compay.entity.User;
import com.compay.json.adminResponses.userList.Entity;
import com.compay.json.adminResponses.userList.UserListJsonBuilder;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserList {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/admin/userList", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  returnUserList(@RequestHeader(value = "Content-Type") String contentType,
                                  @RequestHeader(value = "Authorization")  String authToken,
                                  HttpServletResponse response) throws JsonProcessingException {
        //Token check
        if (tokenService.authChek(authToken)){
            UserListJsonBuilder builder = new UserListJsonBuilder();
            List<User> userList;
            userList=userService.findAll();
            for (User o: userList) {
                builder.addInfo(new Entity(o.getId(),o.getEmail(),o.getName(),o.getRole()));
            }

            String result = builder.createJson();
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
