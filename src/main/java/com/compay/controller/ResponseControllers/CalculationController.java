package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.json.calculation.CalcServicesArrList;
import com.compay.json.calculation.CalculationBuilder;
import com.compay.json.calculation.CalculationEntity;
import com.compay.json.calculation.electricity.MethodElectricity;
import com.compay.json.calculation.electricity.ScaleElectr;
import com.compay.json.calculation.flatPay.MethodFlat;
import com.compay.json.calculation.garbage.MethodGarbage;
import com.compay.json.calculation.gas.MethodGas;
import com.compay.json.calculation.heat.Formula;
import com.compay.json.calculation.heat.MethodHeat;
import com.compay.json.calculation.lift.MethodLift;
import com.compay.json.calculation.water.MethodWater;
import com.compay.repository.*;
import com.compay.service.AdressService;
import com.compay.service.TokenService;
import com.compay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;


    @Autowired
    private CalculationsRepository calculationsRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ScalesRepository scalesRepository;

    @Autowired
    private RatesRepository ratesRepository;

    @Autowired
    private MethodsRepository methodsRepository;

    @Autowired
    private ArgumentsRepository argumentsRepository;

    @Autowired
    private AdressArgumentsRepository adressArgumentsRepository;


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
            Adress adress = adressService.findAdressById(objectID);
            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail())|| adress==null) {
                throw new WrongDataExc();
            }

            List<Object[]> resultQuery = calculationsRepository.findAllByUserAdressPeriod(objectID, period);

            ArrayList services = new ArrayList();
            CalculationEntity entity = new CalculationEntity(period, services);
            CalculationBuilder builder = new CalculationBuilder();
             /*rQ[0] ADRESSID
            rQ[1] COUNTCURRENT
            rQ[2] COUNTLAST
            rQ[3] PERIOD
            rQ[4] SUM
            rQ[5] SERVICEID
            rQ[6] USER_ID
            rQ[7] FORMULA
            rQ[8] MAINRATE
            rQ[9] METHODID
            rQ[10] NAME
            rQ[11] VIEW
            rQ[12] USERSCALE
            rQ[13] RATES_ID*/

            ArrayList<CalcServicesArrList> calcServicesArrayList = new ArrayList<CalcServicesArrList>();

            for (Object[] rQ : resultQuery) {
                Methods methods = methodsRepository.findOne((int) rQ[9]);
                switch ((int) rQ[5]) { //Electricity
                    case 1:
                        List<Scales> scalesEntityList = scalesRepository.findAllByRate(ratesRepository.findOne((int) rQ[13]));
                        ArrayList<ScaleElectr> scaleArrayList = new ArrayList<ScaleElectr>();
                        ScaleElectr scaleElectr = new ScaleElectr();
                        for (Scales scalesEntity : scalesEntityList) {
                            scaleElectr.setMainRate(scalesEntity.getMinValue());
                            scaleElectr.setMaxValue(scalesEntity.getMaxValue());
                            scaleElectr.setMainRate(scalesEntity.getMainRate());
                            scaleArrayList.add(scaleElectr);
                        }
                        MethodElectricity methodElectricity = new MethodElectricity((int) rQ[9], methods.getName(), methods.getView(), scaleArrayList);

                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodElectricity, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                    case 2: //Wate
                        BigDecimal bigDecimal = (BigDecimal) rQ[8];
                        MethodWater methodWater = new MethodWater((int) rQ[9], methods.getName(), methods.getView(),bigDecimal.floatValue ());
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodWater, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                    case 3: //Gas
                        MethodGas methodGas = new MethodGas((int) rQ[9], methods.getName(), methods.getView(), (float) rQ[8]);
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodGas, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                    case 4: //Heat
                        Arguments arguments = argumentsRepository.findByName("livingArea");
                        List<AdressArguments> adressArgumentsList = adressArgumentsRepository.findAllByArgument(arguments);
                        Double livingAreaValue = 0.0;
                        for (AdressArguments adressArg : adressArgumentsList) {
                            if (adressArg.getAdress().equals(adressRepository.findOne((int) rQ[0]))) {
                                livingAreaValue = adressArg.getValue();
                                break;
                            }
                        }

                        Formula formula = new Formula((String) rQ[7], "", livingAreaValue, (float) rQ[8]);
                        MethodHeat methodHeat = new MethodHeat((int) rQ[9], methods.getName(), methods.getView(), formula);
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodHeat, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                    case 5: //Flat
                        MethodFlat methodFlat = new MethodFlat((int) rQ[9], methods.getName(), methods.getView());
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodFlat, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;

                    case 6: //Garbage
                        MethodGarbage methodGarbage = new MethodGarbage((int) rQ[9], methods.getName(), methods.getView(), (float) rQ[4]);
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodGarbage, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                    case 7: //Lift MethodLift
                        MethodLift methodLift = new MethodLift((int) rQ[9], methods.getName(), methods.getView(), (float) rQ[4]);
                        calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], methodLift, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
                        break;
                }
                //arrayList.add(calcServicesArrList);
            }

            result = builder.createJson(new CalculationEntity(period, calcServicesArrayList));
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
}
