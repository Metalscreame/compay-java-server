package com.compay.controller.ResponseControllers;


import com.compay.exception.AuthException;
import com.compay.json.requisites.get.ReqGetBuilder;
import com.compay.json.requisites.update.RequaritiesUpdate;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class Requarities {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/requisites/update", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String reqUpdate(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               @RequestBody String body,
                               HttpServletResponse response) throws AuthException, JsonProcessingException {
        String result="";

        try {

            //auth
            try {
                if (tokenService.authChek(authToken)) {
                } else throw new AuthException();
            }catch (AuthException e){
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Unauthorized\"}";
            }

            RequaritiesUpdate requaritiesUpdate;
            try {
                 requaritiesUpdate= new ObjectMapper().readValue(body,RequaritiesUpdate.class);
                }catch (Exception e){
                    response.setStatus(402);
                    response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                    return "{\"info\": \"Wrong date\"}";
            }

                ReqGetBuilder reqGetBuilder = new ReqGetBuilder();

                /*
                body: {
                objectID:'22';
                serviceID: '1',
                req:{
                    persAcc:"123456",
                    checkAcc:"260065789876890",
                    MFO:"309802",
                    EGRPO:"12324232"
                    }
                 }
                 */
            // всеостальное






            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return "{\"info\": \"Реквизиты успешно обновлены\"}";

        }catch (Exception e)
        {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \" "+ e +" \"}" ;
        }

    }



    @RequestMapping(value = "/requisites/{objectID}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String reqGet(@RequestHeader(value = "Content-Type") String type,
                            @RequestHeader(value = "Authorization") String authToken,
                            HttpServletResponse response,
                            @PathVariable("objectId") int id) throws AuthException, JsonProcessingException {

        String result ="";

        try{

            try {
                if (tokenService.authChek(authToken)) {
                } else throw new AuthException();
            }
            catch (AuthException e){
                response.setStatus(401);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Unauthorized\"}";
            }









            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return result;
        }catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Something is wrong\"}" + e;
        }

    }
}
