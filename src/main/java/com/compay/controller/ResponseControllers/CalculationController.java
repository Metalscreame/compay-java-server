package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.json.calculation.CalculationBuilder;
import com.compay.json.calculation.CalculationEntity;
import com.compay.service.AdressService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class CalculationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;


    @RequestMapping(value = "/calculations/{objectID}/{period}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response, @PathVariable("objectID") int objectID, @PathVariable("period") String period) throws JsonProcessingException {

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            //checking for correct objectID
//            Adress adress = adressService.findAdressById(objectID);
//            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail())|| adress==null) {
//                throw new WrongDataExc();
//            }



            //---------json building-------------
            ArrayList services= new ArrayList();
            CalculationEntity entity = new CalculationEntity(period,services);
            CalculationBuilder builder = new CalculationBuilder();
                //services.add  electricity and etc


            result=builder.createJson(entity);
            //---------json building-------------
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
//        } catch (WrongDataExc e) {
//            response.setStatus(402);
//            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
//            return "{\"message\": \"Wrong objectID\"}";
//        }
    }}
}
