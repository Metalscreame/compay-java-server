package com.compay.controller.ResponseControllers;

import com.compay.entity.Faq;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.faq.FaqEntity;
import com.compay.json.faq.FaqEntityListJSON;
import com.compay.json.faq.FaqJsonBuilder;
import com.compay.service.FaqService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class FaqController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FaqService faqService;

    @RequestMapping(value = "/faq", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response) {

        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            User currentUser = tokenService.findByToken(authToken).getUser();

            if (currentUser == null) throw new WrongDataExc();

            List<Faq> faqList = faqService.findAll();

            FaqJsonBuilder builder = new FaqJsonBuilder();
            for (Faq faq : faqList) {
                builder.addInfo(new FaqEntity(faq.getId(), faq.getRequest(), faq.getResponse()));
            }

            try {
                result = builder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return result;

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Wrong data\"}";
        }
    }

    @RequestMapping(value = "/faqUpdate", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyUpdate(@RequestHeader(value = CONTENT_TYPE) String type,
                                     @RequestHeader(value = AUTHORIZATION) String authToken,
                                     @RequestBody FaqEntityListJSON faqEntityListJSON,
                                     HttpServletResponse response) {

        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            User currentUser = tokenService.findByToken(authToken).getUser();

            if (currentUser == null) throw new WrongDataExc();

            //List<Faq> faqList = faqService.findAll();
            faqService.deleteAll();

            for (FaqEntity faqEn : faqEntityListJSON.getFaqObjects()) {
                Faq faq = new Faq();

                faq.setId(faqEn.getId());
                faq.setRequest(faqEn.getRequest());
                faq.setResponse(faqEn.getResponse());
                faqService.create(faq);
            }
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Faq обновлён\"}";

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Wrong data\"}";
        }
    }
}
