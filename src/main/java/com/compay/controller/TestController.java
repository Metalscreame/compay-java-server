package com.compay.controller;

import com.compay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.compay.service.UserService;

@Controller
public class TestController {
    @Autowired
    UserService svc;

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        User user = new User();
        //user.setId(1);
        user.setName("Roman2");
        user.setPassword("040593");
        user.setEmail("test5@test.test");
        svc.create(user);
        return svc.findUserById(3).getEmail();
    }
}