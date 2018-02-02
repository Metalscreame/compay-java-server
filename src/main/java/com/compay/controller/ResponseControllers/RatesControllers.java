package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressArguments;
import com.compay.entity.Methods;
import com.compay.entity.Scales;
import com.compay.entity.Arguments;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;

import com.compay.json.RateList.Method2;
import com.compay.json.RateList.Rate2;
import com.compay.json.RateList.RateListEntity;
import com.compay.json.RateList.RatesBuilder;
import com.compay.json.RateList.History;
import com.compay.json.RateList.Rate;
import com.compay.json.RateList.Attrs;
import com.compay.json.RateList.Scale;
import com.compay.json.RateList.LivingArea;
import com.compay.json.RateList.MainRate;
import com.compay.repository.RatesRepository;
import com.compay.repository.ScalesRepository;

import com.compay.service.AdressService;
import com.compay.service.MethodsService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.compay.service.AdressArgumentsService;
import com.compay.service.ArgumentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RatesControllers {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private RatesRepository ratesRepository;

    @Autowired
    private MethodsService methodsService;

    @Autowired
    private AdressArgumentsService adressArgumentsService;

    @Autowired
    private ArgumentsService argumentsService;

    @Autowired
    private ScalesRepository scalesRepository;

    @RequestMapping(value = "/rates/{objectID}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseBody(@RequestHeader(value = "Content-Type") String type,
                               @RequestHeader(value = "Authorization") String authToken,
                               HttpServletResponse response, @PathVariable("objectID") int objectID) throws JsonProcessingException, ParseException {


        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            //checking for correct objectID
            Adress adress = adressService.findAdressById(objectID);
            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail()) || adress == null) {
                throw new WrongDataExc();
            }

            Timestamp periodTimestamp = new Timestamp(System.currentTimeMillis());

            try {
                List<Object[]> resultQuery = ratesRepository.findAllByAdressServices(objectID, periodTimestamp.getTime());

                /*
                rQ[0] ADRESSSERVICES.ADRESSID
                rQ[1] ADRESS.TYPE
                rQ[2] ADRESSSERVICES.SERVICEID
                rQ[3] SERVICES.LINK
                rQ[4] SERVICES.SERVICE_NAME
                rQ[5] SERVICES.UNITS
                rQ[6] RATES.FORMULA
                rQ[7] RATES.PERIOD_FROM AS STARTDATE
                rQ[8] METHODS.NAME
                rQ[9] RATES.MAINRATE
                rQ[10] RATES.METHODID
                rQ[11] RATES.USERSCALE
                rQ[12] RATES.Id AS RATES_ID
                rQ[13] ADRESSSERVICES.ID

                */
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                RateListEntity rateListEntity = new RateListEntity();
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

                for (Object[] rQ : resultQuery){

                    //createRate2(int methodId, int rates_id, float mainRate, String formula);
                    Rate2 rate2  = createRate2(adress, (int) rQ[12], (Float)rQ[9], ((String)rQ[6]));

                    Methods methods = methodsService.findMethodById((int) rQ[10]);
                    Method2 method2 = new Method2(methods.getId(), methods.getName(), methods.getView());

                    //History
                    ArrayList<History> historyList = new ArrayList<>();
                    List<Object[]> resultQueryHistory;

                    try {
                        resultQueryHistory = ratesRepository.findAllHistoryByAdressServices((int)rQ[13], Long.parseLong((String)rQ[7]));
                        /*
                        rQH[0] id
                        rQH[1] FORMULA
                        rQH[2] MAINRATE
                        rQH[3] PERIOD_FROM
                        rQH[4] USERSCALE
                        rQH[5] ADRESSSERVICE_ID
                        rQH[6] METHODID
                        */
                        for (Object[] rQH : resultQueryHistory) {
                            //createRate2(methodId, rates_id, mainRate, formula);
                            Rate2 rate2History = createRate2(adress, (int)rQH[0], (Float)rQH[2], (String)rQH[1]);

                            Methods methodsHistory = methodsService.findMethodById((int)rQH[6]);
                            Method2 method2History = new Method2(methodsHistory.getId(), methodsHistory.getName(), methodsHistory.getView());

                            timestamp.setTime(Long.parseLong((String)rQH[3]));
                            History history = new History(sdfDate.format(timestamp), method2History, rate2History);

                            historyList.add(history);
                        }
                    }catch (RuntimeException e){}

                    timestamp.setTime(Long.parseLong((String)rQ[7]));
                    Rate rate = new Rate((int)rQ[2], (String)rQ[4], sdfDate.format(timestamp), method2, rate2, historyList);

                    rateListEntity.addRates(rate);
                }


                RatesBuilder builder = new RatesBuilder();
                builder.addInfo(rateListEntity);
                result = builder.createJson();

                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type':\"application/json\"}");

                return result;
            }catch (MappingException e){
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"message\": \"Wrong objectID\"}";
            }
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Wrong objectID\"}";
        }

    }

    public Rate2 createRate2(Adress adress, int rates_id, float mainRate, String formula) {

        //Scales
        List<Scales> scalesEntityList = scalesRepository.findAllByRate(ratesRepository.findOne(rates_id));
        ArrayList<Scale> scaleArrayList = new ArrayList<Scale>();

        for (Scales scalesEntity : scalesEntityList) {
            scaleArrayList.add(new Scale(scalesEntity.getMinValue(), scalesEntity.getMaxValue()));
        }

        //Attrs
        String formulaView = formula;
        Attrs attrs = new Attrs();
        if (formulaView != null && !formulaView.isEmpty()) {

            String[] strings = formulaView.split(" ");

            formulaView = convertFormula(formulaView);

            List<AdressArguments> adressArgumentsList = adressArgumentsService.findAllByAdress(adress);
            for (AdressArguments adressArguments : adressArgumentsList) {

                String nameArgument = adressArguments.getArgument().getName();
                String viewArgument = adressArguments.getArgument().getView();

                for (String t : strings) {
                    if (nameArgument.equals(t)) {
                        switch (t) {
                            case "livingArea":
                                attrs.setLivingArea(new LivingArea(viewArgument, adressArguments.getValue()));
                                attrs.setMainRate(new MainRate(viewArgument, mainRate));
                                break;
                            case "registeredPersons":

                                break;
                        }
                    }
                }
            }
        }

        Rate2 rate2 = new Rate2();
        rate2.setMainRate(mainRate);
        rate2.setScale(scaleArrayList);
        rate2.setView(formulaView);
        rate2.setValue(formula);
        rate2.setAttrs(attrs);

        return rate2;
    }

    public String convertFormula(String formula) {

        List<Arguments> argumentsList = argumentsService.findAll();

        for (Arguments arguments : argumentsList) {
            formula = formula.replaceAll(arguments.getName(), "[" + arguments.getView() + "]");
        }
        return formula;
    }
}