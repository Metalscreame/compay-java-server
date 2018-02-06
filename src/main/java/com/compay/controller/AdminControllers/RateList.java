package com.compay.controller.AdminControllers;

import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;
import com.compay.entity.Methods;
import com.compay.entity.Services;
import com.compay.entity.User;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.adminRateListPost.RateListPost;
import com.compay.json.adminRateListPost.ScalePost;
import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.Rate;
import com.compay.json.adminResponses.rateList.Scale;
import com.compay.json.adminResponses.rateList.Service;
import com.compay.json.adminResponses.rateList.Formula;
import com.compay.json.adminResponses.rateList.Arguments;
import com.compay.json.adminResponses.rateList.RateListBuilder;
import com.compay.repository.DefaultScalesRepository;
import com.compay.service.DefaultRatesService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class RateList {

    static String errMsg;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DefaultRatesService defaultRatesService;

    @Autowired
    private DefaultScalesRepository defaultScalesRepository;


    @RequestMapping(value = "/admin/rateList", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String returnUserList(
            @RequestHeader(value = CONTENT_TYPE) String contentType,
            @RequestHeader(value = AUTHORIZATION) String authToken,
            HttpServletResponse response) throws JsonProcessingException {

        if (tokenService.authChek(authToken)) {
            String result;

            User currentUser = tokenService.findByToken(authToken).getUser();

            if (currentUser == null) {
                try {
                    throw new WrongDataExc();
                } catch (WrongDataExc wrongDataExc) {
                    wrongDataExc.printStackTrace();
                }
            }

            List<DefaultRates> defaultRatesList = defaultRatesService.findAll();

            ArrayList<Object> arrayList = new ArrayList<>();

            for (DefaultRates defaultRates : defaultRatesList) {
                final Services service = defaultRates.getService();
                final Methods method = defaultRates.getMethod();


                Service serviceRate = new Service(service.getId(), service.getServiceName(), service.getLink(), service.getUnits());
                Method methodRate = new Method(method.getId(), method.getName(), method.getView());

                Rate rate = new Rate(serviceRate, methodRate);

                final ArrayList<Scale> defaultScaleArrayList = new ArrayList<>();
                final List<DefaultScales> defaultScalesList = defaultScalesRepository.findAllByDefaultRates(defaultRates);

                for (DefaultScales defaultScales : defaultScalesList) {
                    defaultScaleArrayList.add(new Scale(defaultScales.getMinValue(), defaultScales.getMaxValue(), defaultScales.getMainRate()));
                }
                rate.setScale(defaultScaleArrayList);

                rate.setMainRate(defaultRates.getMainRate());

                if (defaultRates.getFormula() != null && !defaultRates.getFormula().isEmpty()) {
                    final Formula formula = new Formula(defaultRates.getFormula());
                    rate.setFormula(formula);

                    final Arguments arguments = new Arguments(defaultRates.getMainRate(), 50d);
                    rate.setArguments(arguments);
                }

                arrayList.add(rate);
            }

            RateListBuilder builder = new RateListBuilder();

            result = builder.createJson(arrayList);

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return result;
        } else {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
    }


    @RequestMapping(value = "/admin/rateList", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String returnUserList(
            @RequestHeader(value = CONTENT_TYPE) String contentType,
            @RequestHeader(value = AUTHORIZATION) String authToken,
            @RequestBody String body,
            HttpServletResponse response) throws JsonProcessingException {

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }

        try {
            //deserealize
            ArrayList<RateListPost> rateLists = fromJSON(new TypeReference<ArrayList<RateListPost>>() {
            }, body);
            if (rateLists==null) throw new  WrongDataExc();

            for (RateListPost r : rateLists) {
                DefaultRates defaultRate = defaultRatesService.findDefaultRatesById(r.getService().getId());

                switch (defaultRate.getId()) {
                    case 1:
                        //updscale
                        List<DefaultScales> defScales = defaultScalesRepository.findAllByDefaultRates(defaultRate);
                        ArrayList<ScalePost> scaleToUpdList = r.getScale();
                        int i = 0;
                        for (DefaultScales d : defScales) {
                            ScalePost scaleToUpd = scaleToUpdList.get(i);
                            d.setRate(defaultRate);
                            d.setMainRate(scaleToUpd.getMainRate());
                            d.setMinValue(scaleToUpd.getMinValue());
                            d.setMaxValue(scaleToUpd.getMaxValue());
                            defaultScalesRepository.save(d);
                            ++i;
                        }
                        break;

                    case 4:
                        break;
                    case 5:
                        defaultRate.setFormula(r.getFormula().getId());
                        defaultRatesService.save(defaultRate);
                        break;
                    default:
                        defaultRate.setMainRate(r.getMainRate());
                        defaultRatesService.save(defaultRate);
                        break;
                }
            }
            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Тарифы успешно обновлены\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \""+errMsg+"\"}";
        } catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"" + e + "\"}";
        }
    }
    
    public static <T> T fromJSON(final TypeReference<T> type,
                                 final String jsonPacket) {
        T data = null;
        try {
//            ObjectMapper objectMapper=new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            errMsg=e.getMessage();
            return null;
        }
        return data;
    }

}



