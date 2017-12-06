package com.websecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration_form")
public class RegistrationController {
    @RequestMapping(method = RequestMethod.GET)
    public String registrationPage(Model model){
        return "registration_form";
    }

}
