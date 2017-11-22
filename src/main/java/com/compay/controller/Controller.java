package com.compay.controller;


import com.compay.entity.User;
import com.compay.repository.UserRepository;
import com.compay.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/testsave",method = RequestMethod.GET)
    public String testSave(User user){
        userService.create(user);

        return "redirect:/index";
    }




}
