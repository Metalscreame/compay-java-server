package com.compay.controller.ResponseControllers;

import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.profile.ProfileBuilder;
import com.compay.json.profile.ProfileEntity;
import com.compay.json.profile.ProfileEntityJSON;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class ProfileController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response) throws AuthException, JsonProcessingException {

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            User user = tokenService.findByToken(authToken).getUser();
            if (user == null) {
                throw new WrongDataExc();
            }


            ProfileBuilder builder = new ProfileBuilder();
            result = builder.createJson(new ProfileEntity(user));
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");

            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Wrong user\"}";
        }

    }

    @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
    @ResponseBody
    public String responseEntityUpdate(@RequestHeader(value = CONTENT_TYPE) String type,
                                       @RequestHeader(value = AUTHORIZATION) String authToken,
                                       @RequestBody ProfileEntityJSON profileEntityJSON,
                                       HttpServletResponse response) throws AuthException, JsonProcessingException {

    /*"name": "Дмитрий",
    "surname": "Грищенко",
    "email": "daminort@gmail.com",
    "password": "qwerty"*/

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            User user = tokenService.findByToken(authToken).getUser();

            if (user == null) {
                throw new WrongDataExc();
            }

            if (!profileEntityJSON.getName().isEmpty()) {
                user.setName(profileEntityJSON.getName());
            }

            if (!profileEntityJSON.getName().isEmpty()) {
                user.setEmail(profileEntityJSON.getEmail());
            }

            if (!profileEntityJSON.getName().isEmpty()) {
                user.setLastName(profileEntityJSON.getSurname());
            }

            if (!profileEntityJSON.getPassword().isEmpty()) {
                user.setPassword(profileEntityJSON.getPassword());
            }

            userService.create(user);
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");

            return "{\"info\": \"Данные пользователя успешно обновлены\"}";

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Wrong user\"}";
        }
    }
}
