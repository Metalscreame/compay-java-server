package com.compay.controller;

import com.compay.entity.User;
import com.compay.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    UserService svc;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        User user = new User();

        user.setName(StringUtils.EMPTY);
        user.setPassword("040593");
        user.setEmail("test52@test.test");
        svc.create(user);
        return "The first user in the database is : " + svc.findUserById(1).getEmail();
        //return "redirect:index2.jsp/";
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String test(Model model){
//        model.addAttribute("name");
//
//        return "index";
//    }
}
