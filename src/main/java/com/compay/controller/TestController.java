package com.compay.controller;

import com.compay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.compay.service.UserService;

@Controller
@RequestMapping(value = "/")
public class TestController {
    @Autowired
    UserService svc;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String test() {
//        User user = new User();
//
//        user.setName("Roman2");
//        user.setPassword("040593");
//        user.setEmail("test53@test.test");
//        svc.create(user);
        return "First email in database is = "+svc.findUserById(1).getEmail();
        //return "redirect:index2.jsp/";
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public String test(Model model){
//        model.addAttribute("name");
//
//        return "index";
//    }

}