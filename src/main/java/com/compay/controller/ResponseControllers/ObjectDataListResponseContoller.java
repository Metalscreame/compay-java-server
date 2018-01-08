package com.compay.controller.ResponseControllers;


import com.compay.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ObjectDataListResponseContoller {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/objectDataList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    //TODO скорее всего сюда еще придется загонять токен или его првоерку
    public String responseBody(HttpServletResponse response) {
        String result = null;

        return result;
    }


}
