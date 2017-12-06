package com.websecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/account")
public class AccountController {
        @RequestMapping(method= RequestMethod.GET)
        public String accountPage(Model model) {
            return "account";
        }
    }

