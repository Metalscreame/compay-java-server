package com.compay.controller.ResponseControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class PostTestController {

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    @ResponseBody
    public String poster(@RequestHeader(value = CONTENT_TYPE) String contentType, HttpServletResponse response) {
        response.setStatus(200);
        response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
        return "{\"message\": \"Test success!\"}";
    }


}
