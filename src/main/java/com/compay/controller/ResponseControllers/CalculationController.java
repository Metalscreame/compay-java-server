package com.compay.controller.ResponseControllers;

import com.compay.entity.*;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.calculation.*;
import com.compay.json.calculation.electricity.ScaleElectr;
import com.compay.repository.*;
import com.compay.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class CalculationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private ArgumentsService argumentsService;

    @Autowired
    private AdressArgumentsService adressArgumentsService;

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

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private CalculationsService calculationsService;

    @RequestMapping(value = "/calculation", method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBodyAdd(@RequestHeader(value = CONTENT_TYPE) String type,
                                  @RequestHeader(value = AUTHORIZATION) String authToken,
                                  @RequestBody CalculationEntity calculationEntity,
                                  HttpServletResponse response) throws ParseException {
        try {
            String result = null;
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            if (calculationEntity == null) throw new WrongDataExc();
            User currentUser = tokenService.findByToken(authToken).getUser();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Adress adress = adressRepository.findOne(calculationEntity.getObjectID());

            Timestamp timestamp = new java.sql.Timestamp(dateFormat.parse(calculationEntity.getPeriod()).getTime());

            List<Calculations> listCalculations = calculationsRepository.findAllByAdressPeriod(adress, timestamp);

            for (Calculations calculations : listCalculations) {
                calculationsRepository.delete(calculations);
            }

            for (CalcServicesArrList calcServ : calculationEntity.getServices()) {

                Services service = servicesService.findServicesById(calcServ.getServiceID());

                Calculations calculations = new Calculations();
                calculations.setAdress(adress);
                calculations.setCountLast(calcServ.getLastCounter());
                calculations.setCountCurrent(calcServ.currentCounter);
                calculations.setSum(calcServ.currentSum);
                calculations.setPeriod(timestamp);
                calculations.setService(service);
                calculations.setUser(currentUser);
                calculationsService.create(calculations);
            }

            response.setStatus(200);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"info\": \"Данные учета успешно добавлены\"}";

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


    @RequestMapping(value = "/calculations/{objectID}/{period}", method = RequestMethod.GET, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response, @PathVariable("objectID") int objectID, @PathVariable("period") String period) throws JsonProcessingException, ParseException {

        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();
            String result = null;

            //checking for correct objectID
            Adress adress = adressService.findAdressById(objectID);
            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail()) || adress == null) {
                throw new WrongDataExc();
            }

            Timestamp periodTimestamp = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(period).getTime());

            List<Object[]> resultQuery = calculationsRepository.findAllByUserAdressPeriod(objectID, periodTimestamp, adress.getUser().getId());

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

                List<Scales> scalesEntityList = scalesRepository.findAllByRate(ratesRepository.findOne((int) rQ[13]));
                ArrayList<ScaleElectr> scaleArrayList = new ArrayList<ScaleElectr>();

                for (Scales scalesEntity : scalesEntityList) {
                    scaleArrayList.add(new ScaleElectr(scalesEntity.getMinValue(), scalesEntity.getMaxValue(), scalesEntity.getMainRate()));
                }

                Method method = new Method((int) rQ[9], methods.getName(), methods.getView(), scaleArrayList, (float) rQ[8]);

                String formulaView = (String) rQ[7];

                if (formulaView != null && !formulaView.isEmpty()) {
                    String[] strings = formulaView.split(" ");

                    formulaView = convertFormula(formulaView);

                    List<AdressArguments> adressArgumentsList = adressArgumentsService.findAllByAdress(adress);
                    Formula formula = new Formula();
                    for (AdressArguments adressArguments : adressArgumentsList) {

                        String nameArgument = adressArguments.getArgument().getName();
                        String viewArgument = adressArguments.getArgument().getView();

                        for (String t : strings) {
                            if (nameArgument.equals(t)) {
                                switch (t) {
                                    case "livingArea":
                                        formula.setLivingArea(adressArguments.getValue());
                                        formula.setMainRate((float) rQ[8]);
                                        formula.setValue((String) rQ[7]);
                                        formula.setView(formulaView);
                                        break;
                                    case "registeredPersons":

                                        break;
                                }
                            }
                        }
                    }
                }
                //Formula formula = new Formula((String) rQ[7], "", livingAreaValue, (float) rQ[8]);
                calcServicesArrayList.add(new CalcServicesArrList((int) rQ[5], (String) rQ[10], method, (int) rQ[2], (int) rQ[1], (float) rQ[4]));
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

    public String convertFormula(String formula) {

        List<Arguments> argumentsList = argumentsService.findAll();

        for (Arguments arguments : argumentsList) {
            formula = formula.replaceAll(arguments.getName(), "[" + arguments.getView() + "]");
        }
        return formula;
    }
}
