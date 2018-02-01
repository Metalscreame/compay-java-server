package com.compay.controller.AdminControllers;

import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;
import com.compay.entity.User;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.adminResponses.rateList.Method;
import com.compay.json.adminResponses.rateList.RateListBuilder;
import com.compay.json.adminResponses.rateList.Service;
import com.compay.json.adminResponses.rateList.electricity.ScaleElectr;
import com.compay.json.adminResponses.rateList.electricity.ServiceElectricity;
import com.compay.json.adminResponses.rateList.flatPay.Arguments;
import com.compay.json.adminResponses.rateList.flatPay.Formula;
import com.compay.json.adminResponses.rateList.flatPay.ServiceFlat;
import com.compay.json.adminResponses.rateList.garbage.ServiceGarbage;
import com.compay.json.adminResponses.rateList.gas.ServiceGas;
import com.compay.json.adminResponses.rateList.heat.ServiceHeat;
import com.compay.json.adminResponses.rateList.lift.ServiceLift;
import com.compay.json.adminResponses.rateList.water.ServiceWater;
import com.compay.repository.DefaultRatesRepository;
import com.compay.repository.DefaultScalesRepository;
import com.compay.service.DefaultRatesService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class RateList {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DefaultRatesService defaultRatesService;

    @Autowired
    private DefaultScalesRepository defaultScalesRepository;

    @Autowired
    private DefaultRatesRepository defaultRatesRepository;

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

                switch (defaultRates.getService().getId()) {
                    case 1: //Electricity
                        ArrayList<ScaleElectr> defaultScaleArrayList = new ArrayList<>();
                        List<DefaultScales> defaultScalesList = defaultScalesRepository.findAllByDefaultRates(defaultRates);
                        //Set<DefaultScales> defaultScalesList = defaultRates.getDefaultScales();

                        for (DefaultScales defaultScales : defaultScalesList) {
                            defaultScaleArrayList.add(new ScaleElectr(defaultScales.getMinValue(), defaultScales.getMaxValue(), defaultScales.getMainRate()));
                        }
                        Service serviceE = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodE = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceElectricity serviceElectricity = new ServiceElectricity(serviceE, methodE, defaultScaleArrayList);

                        arrayList.add(serviceElectricity);

                        break;
                    case 2: //Water

                        Service serviceW = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodW = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceWater serviceWater = new ServiceWater(serviceW, methodW, defaultRates.getMainRate());

                        arrayList.add(serviceWater);
                        break;
                    case 3: //Gas
                        Service serviceG = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodG = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceGas serviceGas = new ServiceGas(serviceG, methodG, defaultRates.getMainRate());

                        arrayList.add(serviceGas);
                        break;
                    case 4: //Heat
                        Service serviceH = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodH = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceHeat serviceHeat = new ServiceHeat(serviceH, methodH);

                        arrayList.add(serviceHeat);
                        break;
                    case 5: //Flat
                        Service serviceF = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodF = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        Formula formula = new Formula(defaultRates.getFormula());
                        Arguments arguments = new Arguments(12.5, 50d);
                        ServiceFlat serviceFlat = new ServiceFlat(serviceF, methodF, formula, arguments);

                        arrayList.add(serviceFlat);
                        break;
                    case 6: //Garbage
                        Service serviceGB = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodGB = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceGarbage serviceGarbage = new ServiceGarbage(serviceGB, methodGB, defaultRates.getMainRate());

                        arrayList.add(serviceGarbage);
                        break;
                    case 7: //Lift
                        Service serviceL = new Service(defaultRates.getService().getId(), defaultRates.getService().getServiceName(), defaultRates.getService().getLink(), defaultRates.getService().getUnits());
                        Method methodL = new Method(defaultRates.getMethod().getId(), defaultRates.getMethod().getName(), defaultRates.getMethod().getView());
                        ServiceLift serviceLift = new ServiceLift(serviceL, methodL, defaultRates.getMainRate());

                        arrayList.add(serviceLift);
                        break;
                }
            }

            RateListBuilder builder = new RateListBuilder();

            //простите, ну не удержался)) тут проще руками забить)
            //result = "[{\"serviceID\": 1,\"serviceName\": \"Электроснабжение\",\"method\": {\"id\": 4,\"name\": \"counterScale\",\"view\": \"По счетчику с применением шкалы\",\"scale\": [{\"minValue\": 0,\"maxValue\": 100,\"mainRate\": 0.95},{\"minValue\": 101, \"maxValue\": 600, \"mainRate\": 1.50},{\"minValue\": 601, \"maxValue\": 0, \t\"mainRate\": 1.83}]}},{\"serviceID\": 2, \"serviceName\": \"Водоснабжение\",\"method\": {\"id\": 3, \"name\": \"counter\",\"view\": \"По счетчику\",\"mainRate\": 12.3}},{\"serviceID\": 3, \"serviceName\": \"Газоснабжение\",\"method\": {\"id\": 3, \"name\": \"counter\",\"view\": \"По счетчику\",\"mainRate\": 6.9}},{\"serviceID\": 4, \"serviceName\": \"Теплоснабжение\",\"method\": {\"id\": 3, \"name\": \"fixFormula\",\"view\": \"Фиксированная формула\",\"formula\": {\"value\": \"livingArea * mainRate\",\"view\": \"[Жилая площадь] x [Тариф]\", \"livingArea\": 49.5, \"mainRate\": 632.2}}},{\"serviceID\": 5, \"serviceName\": \"Квартплата\",\"method\": {\"id\": 2, \"name\": \"fixSum\",\"view\": \"Фиксированная сумма\"}}]";
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
}



