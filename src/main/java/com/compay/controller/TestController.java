package com.compay.controller;

import com.compay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.compay.service.UserService;

@Controller
@RequestMapping(value = "/test")
public class TestController{
    @Autowired
    UserService svc;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String test() {
//        User user = new User();
//
//        user.setName("Roman2");
//        user.setPassword("040593");
//        user.setEmail("test532@test.test");
//        svc.create(user);
//        svc.findUserById(1).getEmail();
        return svc.findUserById(2).getEmail();
        //return "redirect:index2.jsp/";
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public String test(Model model){
//        model.addAttribute("name");
//
//        return "index";
//    }



}