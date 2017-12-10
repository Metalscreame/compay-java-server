package com.compay.controller;

import com.compay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.compay.service.UserService;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Stream;

@Controller
public class TestController{
    @Autowired
    UserService svc;


    @RequestMapping(value = "/save",method = RequestMethod.GET)
    @ResponseBody
    public String saveTest(){
        User user = new User();

        Random random = new Random();
        int rand=random.nextInt();
        user.setName("romka");
        user.setPassword("040593");
        user.setEmail("test"+rand+"@test.test");
        user.setLastName("Kosiy");
        user.setSurrName("Stanislavovich");
        svc.create(user);
        return "User " + user.getName() + "with " + user.getEmail() + " email has been saved";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {

//        ArrayList<String> buffer = new ArrayList<>();
//
//        try (Stream<User> stream = svc.findAll()) {
//            stream.forEach((k)->buffer.add("User : " + k));
//        }




        return "The first user in the database is : "+svc.findUserById(1).getEmail();
        //return "redirect:index2.jsp/";
//        return buffer.get(1);
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public String test(Model model){
//        model.addAttribute("name");
//
//        return "index";
//    }



}