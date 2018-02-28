package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.RateList.Argument;
import com.compay.json.RateList.Attrs;
import com.compay.json.RateList.History;
import com.compay.json.RateList.MainRate;
import com.compay.json.RateList.Method2;
import com.compay.json.RateList.Rate;
import com.compay.json.RateList.Rate2;
import com.compay.json.RateList.RateListEntity;
import com.compay.json.RateList.RatesBuilder;
import com.compay.json.RateList.Scale;
import com.compay.json.RatesUpdate.RatesUpdate;
import com.compay.json.adminResponses.rateList.Formula;
import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.RateListBuilder;
import com.compay.json.adminResponses.rateList.Service;
import com.compay.repository.DefaultScalesRepository;
import com.compay.repository.RatesRepository;
import com.compay.repository.ScalesRepository;
import com.compay.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class RatesControllers {

    @Autowired
    private RatesService ratesService;

    @Autowired
    private TokenService tokenService;

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

    @Autowired
    private AdressServicesService adressServicesService;

    @Autowired
    private ScalesService scalesService;

    @Autowired
    private DefaultRatesService defaultRatesService;

    @Autowired
    private DefaultScalesRepository defaultScalesRepository;



    @RequestMapping(value = "/rates/{objectID}", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response, @PathVariable("objectID") int objectID) throws JsonProcessingException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result;

            //checking for correct objectID
            Adress adress = adressService.findAdressById(objectID);
            if (adress == null || !adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail())) {
                throw new WrongDataExc();
            }

            Timestamp periodTimestamp = new Timestamp(System.currentTimeMillis());
            List<Object[]> resultQuery;

            try {
                resultQuery = ratesRepository.findAllByAdressServices(objectID, periodTimestamp.getTime());
            } catch (RuntimeException e) {
                //if there is no rates

                List<AdressServices> adressServices = adressServicesService.findAllByAdressId(objectID);
                List<DefaultRates> defaultRatesList = new ArrayList<>();

                for (AdressServices a : adressServices) {
                    defaultRatesList.add(defaultRatesService.findByService_Id(a.getId()));
                }

                //arr list for json
                ArrayList<Object> arrayList = new ArrayList<>();

                for (DefaultRates defaultRates : defaultRatesList) {
                    final Services service = defaultRates.getService();
                    final Methods method = defaultRates.getMethod();


                    Service serviceRate = new Service(service.getId(), service.getServiceName(), service.getLink(), service.getUnits());
                    Method methodRate = new Method(method.getId(), method.getName(), method.getView());

                    com.compay.json.adminResponses.rateList.Rate rate = new com.compay.json.adminResponses.rateList.Rate(serviceRate, methodRate);

                    final ArrayList<com.compay.json.adminResponses.rateList.Scale> defaultScaleArrayList = new ArrayList<>();
                    final List<DefaultScales> defaultScalesList = defaultScalesRepository.findAllByDefaultRates(defaultRates);

                    for (DefaultScales defaultScales : defaultScalesList) {
                        defaultScaleArrayList.add(new com.compay.json.adminResponses.rateList.Scale(defaultScales.getMinValue(), defaultScales.getMaxValue(), defaultScales.getMainRate()));
                    }
                    rate.setScale(defaultScaleArrayList);

                    rate.setMainRate(defaultRates.getMainRate());

                    if (defaultRates.getFormula() != null && !defaultRates.getFormula().isEmpty()) {
                        final Formula formula = new Formula(defaultRates.getFormula());
                        rate.setFormula(formula);

                        final com.compay.json.adminResponses.rateList.Arguments arguments = new com.compay.json.adminResponses.rateList.Arguments(defaultRates.getMainRate(), 50d);
                        rate.setArguments(arguments);
                    }

                    arrayList.add(rate);
                }

                RateListBuilder builder = new RateListBuilder();

                result = builder.createJson(arrayList);


//                RateListEntity rateListEntity = new RateListEntity();
//                RatesBuilder builder = new RatesBuilder();
//                builder.addInfo(rateListEntity);
//                result = builder.createJson();
                response.setStatus(200);
                response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
                return "{\"rates\":" + result + "}";
            }

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

            for (Object[] rQ : resultQuery) {


                //createRate2(int methodId, int rates_id, float mainRate, String formula);
                Rate2 rate2 = createRate2(adress, (int) rQ[12], (Float) rQ[9], ((String) rQ[6]));

                Method2 method2 = new Method2();
                if ((int) rQ[10] != 0 ) {
                    Methods methods = methodsService.findMethodById((int) rQ[10]);
                    method2.setId(methods.getId());
                    method2.setName(methods.getName());
                    method2.setView(methods.getView());
                }else {
                    method2.setId(0);
                    method2.setName("manual");
                    method2.setView("Ручной ввод");
                }

                //History
                ArrayList<History> historyList = new ArrayList<>();
                List<Object[]> resultQueryHistory = new LinkedList<>();

                try {
                    resultQueryHistory = ratesRepository.findAllHistoryByAdressServices((int) rQ[13], Long.parseLong((String) rQ[7]));
                } catch (RuntimeException e) {
                }
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
                    Rate2 rate2History = createRate2(adress, (int) rQH[0], (Float) rQH[2], (String) rQH[1]);

                    Methods methodsHistory = methodsService.findMethodById((int) rQH[6]);
                    Method2 method2History = new Method2(methodsHistory.getId(), methodsHistory.getName(), methodsHistory.getView());

                    timestamp.setTime(Long.parseLong((String) rQH[3]));
                    History history = new History(sdfDate.format(timestamp), method2History, rate2History);

                    historyList.add(history);
                }

                timestamp.setTime(Long.parseLong((String) rQ[7]));
                Rate rate = new Rate((int) rQ[2], (String) rQ[4], sdfDate.format(timestamp), method2, rate2, historyList);

                rateListEntity.addRates(rate);
            }


            RatesBuilder builder = new RatesBuilder();
            builder.addInfo(rateListEntity);
            result = builder.createJson();

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
            return "{\"message\": \"Wrong objectID\"}";
        }

    }

    public Rate2 createRate2(Adress adress, int rates_id, float mainRate, String formula) {

        //Scales
        List<Scales> scalesEntityList = scalesRepository.findAllByRate(ratesRepository.findOne(rates_id));
        ArrayList<Scale> scaleArrayList = new ArrayList<Scale>();

        for (Scales scalesEntity : scalesEntityList) {
            scaleArrayList.add(new Scale(scalesEntity.getMinValue(), scalesEntity.getMaxValue(), scalesEntity.getMainRate()));
        }

        Rate2 rate2 = new Rate2();
        rate2.setMainRate(mainRate);
        rate2.setScale(scaleArrayList);


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
                                attrs.setLivingArea(new Argument(viewArgument, adressArguments.getValue()));
                                attrs.setMainRate(new MainRate(viewArgument, mainRate));
                                break;
                            case "registeredPersons":
                                attrs.setRegisteredPersons(new Argument(viewArgument, adressArguments.getValue()));
                                attrs.setMainRate(new MainRate(viewArgument, mainRate));
                                break;
                        }
                    }
                }
            }
            rate2.setView(formulaView);
            rate2.setValue(formula);
            rate2.setAttrs(attrs);
        }

        return rate2;
    }

    public String convertFormula(String formula) {

        List<com.compay.entity.Arguments> argumentsList = argumentsService.findAll();

        for (com.compay.entity.Arguments arguments : argumentsList) {
            formula = formula.replaceAll(arguments.getName(), "[" + arguments.getView() + "]");
        }
        return formula;
    }

    @RequestMapping(value = "/rates/update", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String ratesUpdate(
            @RequestHeader(value = CONTENT_TYPE) String type,
            @RequestHeader(value = AUTHORIZATION) String authToken,
            @RequestBody String body,
            HttpServletResponse response) throws AuthException, JsonProcessingException, ParseException {

        //AuthCheck
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        }
        //AuthCheck

        //deserialize
        RatesUpdate updateBody;
        try {
            updateBody = new ObjectMapper().readValue(body, RatesUpdate.class);
        } catch (Exception e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Wrong data\"}";
        }
        //deserialize


        //first - find adressServiceId by objid and serv Id
        AdressServices addrSvcToFind = adressServicesService.findByAdressIdandServiceId(updateBody.getObjectID(), updateBody.getServiceID());

        try {
            if (addrSvcToFind == null) throw new WrongDataExc();
        } catch (WrongDataExc e) {
            response.setStatus(400);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return "{\"info\": \"Такого ObjectId или ServiceId не существует\"}";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startDateMS = new java.sql.Timestamp(dateFormat.parse(updateBody.getStartDate()).getTime());

        Rates rateToUpdt = ratesService.findByAddIdAndStartDateAndMethod(addrSvcToFind.getId(), startDateMS, updateBody.getMethod());


        //if there is no current date or method then create new rates
        if (rateToUpdt == null){
            rateToUpdt = new Rates();
            rateToUpdt.setPeriodFrom(startDateMS);
            Methods methods = methodsService.findMethodById(updateBody.getMethod());
            rateToUpdt.setMethod(methods);
            rateToUpdt.setAdressService(addrSvcToFind);
            ratesService.create(rateToUpdt);
        }



        try {
            if (updateBody.getServiceID() == 1) {
                ArrayList<com.compay.json.RatesUpdate.Scale> scalesReceived = updateBody.getRate().getScale();
                ArrayList<Scales> scalesToUpd = scalesService.findAllByRate(rateToUpdt);

                //if there is no scale in the db
                if(scalesToUpd.size()==0){

                    for (int i =0;i<scalesReceived.size();i++) {
                        Scales scales = new Scales();
                        com.compay.json.RatesUpdate.Scale scaleToSet = scalesReceived.get(i);
                        scales.setMainRate(scaleToSet.getMainRate());
                        scales.setMaxValue(scaleToSet.getMaxValue());
                        scales.setMinValue(scaleToSet.getMinValue());
                        scales.setRate(rateToUpdt);
                        scalesRepository.save(scales);
                    }
                }

                int i = 0;
                for (Scales s : scalesToUpd) {
                    com.compay.json.RatesUpdate.Scale scaleToSet = scalesReceived.get(i);
                    s.setMainRate(scaleToSet.getMainRate());
                    s.setMaxValue(scaleToSet.getMaxValue());
                    s.setMinValue(scaleToSet.getMinValue());
                    s.setRate(rateToUpdt);
                    scalesService.update(s);
                    ++i;
                }
            }

            if (updateBody.getServiceID() == 2 || updateBody.getServiceID() == 3 || updateBody.getServiceID() == 6 || updateBody.getServiceID() == 7 || updateBody.getServiceID() == 4) {
                rateToUpdt.setMainRate(updateBody.getRate().getMainRate());
                ratesService.update(rateToUpdt);
            }

            if (updateBody.getServiceID() == 5) {
                rateToUpdt.setMainRate(updateBody.getRate().getMainRate());
                rateToUpdt.setFormula(updateBody.getRate().getValue());
                //find adress arg by arg id and adress id to update
                AdressArguments adrArgTpUpd = adressArgumentsService.findByAdrIdAndArgId(updateBody.getObjectID(), 1);
                if(adrArgTpUpd!= null &&
                        updateBody.getRate().getAttrs().getLivingArea() != null){
                    adrArgTpUpd.setValue(updateBody.getRate().getAttrs().getLivingArea().getValue());
                    adressArgumentsService.create(adrArgTpUpd);
                }
                adrArgTpUpd = adressArgumentsService.findByAdrIdAndArgId(updateBody.getObjectID(), 3);
                if(adrArgTpUpd!= null &&
                        updateBody.getRate().getAttrs().getRegisteredPersons()!= null){
                    adrArgTpUpd.setValue(updateBody.getRate().getAttrs().getRegisteredPersons().getValue());
                    adressArgumentsService.create(adrArgTpUpd);
                }
                ratesService.update(rateToUpdt);
            }

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type':\"application/json\"}");
            return "{\"info\":\"Тариф на ServiceId" + updateBody.getServiceID() + " c " + updateBody.getStartDate() + "  успешно обновлен\"}";
        } catch (Exception e) {
            response.setStatus(400);
            response.setHeader("headers", "{\"Content-Type\": \"application/json\"}");
            return "{\"info\": \"" + e + "\"}";
        }
    }

}
