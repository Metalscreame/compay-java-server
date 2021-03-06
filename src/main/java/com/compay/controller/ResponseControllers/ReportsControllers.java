package com.compay.controller.ResponseControllers;

import com.compay.entity.Adress;
import com.compay.entity.AdressServices;
import com.compay.entity.Calculations;
import com.compay.entity.Services;
import com.compay.entity.Rates;
import com.compay.exception.AuthException;
import com.compay.exception.WrongDataExc;
import com.compay.global.Constants;
import com.compay.json.RatesUpdate.RatesUpdate;
import com.compay.json.reports.ReportCalculation;
import com.compay.json.reports.ReportsBuilder;
import com.compay.json.reports.ReportsPOSTBody;
import com.compay.repository.AdressServicesRepository;
import com.compay.repository.CalculationsRepository;
import com.compay.repository.RatesRepository;
import com.compay.service.AdressService;
import com.compay.service.AdressServicesService;
import com.compay.service.ServicesService;
import com.compay.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Controller
public class ReportsControllers {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AdressService adressService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private AdressServicesService adressServicesService;
    @Autowired
    private AdressServicesRepository adressServicesRepository;
    @Autowired
    private RatesRepository ratesRepository;
    @Autowired
    private CalculationsRepository calculationsRepository;

    @RequestMapping(value = "/reports",
            method = RequestMethod.POST, produces = Constants.MimeTypes.UTF_8_PLAIN_TEXT)
    @ResponseBody
    public String responseBody(@RequestHeader(value = CONTENT_TYPE) String type,
                               @RequestHeader(value = AUTHORIZATION) String authToken,
                               HttpServletResponse response,
                               @RequestBody String body) throws ParseException, JsonProcessingException {
        try {
            if (tokenService.authChek(authToken)) {
            } else throw new AuthException();

            //deserialize
            ReportsPOSTBody updateBody;
            try {
                updateBody = new ObjectMapper().readValue(body, ReportsPOSTBody.class);
            } catch (Exception e) {
                response.setStatus(402);
                response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
                return "{\"info\": \"Wrong data\"}";
            }
            //deserialize

            //its a bug fix,so no time for refactor!)
            int objectID = updateBody.getObjectID(), serviceID = updateBody.getServiceID();
            String periodFrom = updateBody.getPeriodFrom();
            String periodTo = updateBody.getPeriodTo();

            //checking for correct objectID
            final Adress adress = adressService.findAdressById(objectID);
            if (!adress.getUser().getEmail().equals(tokenService.findByToken(authToken).getUser().getEmail()) || adress == null) {
                throw new WrongDataExc();
            }

            Timestamp periodFromTimestamp = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom).getTime());
            Timestamp periodToTimestamp = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(periodTo).getTime());

            List<Calculations> calculationsList;
            List<List<Object>> listReportSumDetail = new LinkedList();
            List<List<Object>> listReportCounterDetail = new LinkedList();
            List<List<Object>> listReportSumTotal = new LinkedList();
            List<List<Object>> listReportCounterTotal = new LinkedList();
            List listHeadDetail = new LinkedList();
            List listHeadCourentDetail = new LinkedList();
            List listHeadTotal = new LinkedList();
            SimpleDateFormat sdfDateMonth = new SimpleDateFormat("MMMM");

            if (serviceID == 0) {

                listHeadTotal.add("Услуга");
                listHeadTotal.add("Стоимость");

                listReportSumDetail.add(listHeadDetail);
                listReportSumTotal.add(listHeadTotal);
                listReportCounterTotal.add(listHeadTotal);
                List<Services> servicesActiveList = adressServicesRepository.findAllServiceByActiveServiceAdress(adress);
                calculationsList = calculationsRepository.findAllByAdressPeriodFromPeriodTo(adress, servicesActiveList, periodFromTimestamp, periodToTimestamp);


                TreeMap<Date, List<Calculations>> dateTreeMap =
                        calculationsList.stream().collect(Collectors.groupingBy(Calculations::getPeriod, TreeMap::new, Collectors.toList()));

                List<Services> serviceList = calculationsList.stream().map(Calculations::getService).collect(Collectors.toSet()).stream().sorted(new Comparator<Services>() {
                    @Override
                    public int compare(Services o1, Services o2) {
                        return o1.getId() - o2.getId();
                    }
                }).collect(Collectors.toList());

                //fill header
                listHeadDetail.add("Услуга");
                for (Services service : serviceList) {
                    listHeadDetail.add(service.getServiceName());
                }
                //sumDetailData
                for (List<Calculations> calcList : dateTreeMap.values()) {
                    List listSumDetail = new LinkedList();
                    listSumDetail.add(sdfDateMonth.format(calcList.get(0).getPeriod()));
                    for (Services serv : serviceList) {
                        double sum = 0;
                        for (Calculations calc : calcList) {
                            if (calc.getService().equals(serv)) {
                                sum = Math.max(0, calc.getSum());
                            }
                        }
                        listSumDetail.add(sum);
                    }
                    listReportSumDetail.add(listSumDetail);
                }

                //sumTotalData
                for (Services serv : serviceList) {
                    List listSumTotal = new LinkedList();
                    listSumTotal.add(serv.getServiceName());
                    double sum = 0;
                    for (Calculations calc : calculationsList) {
                        if (calc.getService().equals(serv)) {
                            sum += Math.max(0, calc.getSum());
                        }
                    }
                    listSumTotal.add(sum);
                    listReportSumTotal.add(listSumTotal);
                }

                //create new list services which are not in the counters
                List<Services> serviceCounterList = new LinkedList<>();
                for (Services serv : serviceList) {
                    AdressServices adressServices = adressServicesRepository.findOneByAdressService(adress, serv);

                    List<Rates> ratesList = ratesRepository.findAllByAdressService(adressServices);

                    /*method fixSum, fixFormula, manual - do not return*/
                    boolean outputCounter = false;
                    if (ratesList.size() > 0 && (ratesList.get(0).getMethod().getName().equals("counter")
                            || ratesList.get(0).getMethod().getName().equals("counterScale"))) {
                        serviceCounterList.add(serv);
                    }
                }


                //counterTotalData
                for (Services serv : serviceCounterList) {
                    double counter = 0;
                    List listCounterTotal = new LinkedList();
                    listCounterTotal.add(serv.getServiceName());
                    for (Calculations calc : calculationsList) {
                        if (calc.getService().equals(serv)) {
                            counter += Math.max(0, calc.getCountCurrent() - calc.getCountLast());
                        }
                    }
                    listCounterTotal.add(counter);
                    listReportCounterTotal.add(listCounterTotal);
                }

                //counterDetailData
                listHeadCourentDetail.add("Услуга");
                for (Services serv : serviceCounterList) {
                    listHeadCourentDetail.add(serv.getServiceName());
                }
                listReportCounterDetail.add(listHeadCourentDetail);

                for (List<Calculations> calcList : dateTreeMap.values()) {
                    List listCounterDetail = new LinkedList();
                    listCounterDetail.add(sdfDateMonth.format(calcList.get(0).getPeriod()));
                    for (Services serv : serviceCounterList) {
                        double counter = 0;
                        for (Calculations calc : calcList) {
                            if (calc.getService().equals(serv)) {
                                counter += Math.max(0, calc.getCountCurrent() - calc.getCountLast());
                            }
                        }
                        listCounterDetail.add(counter);
                    }
                    listReportCounterDetail.add(listCounterDetail);
                }

            } else { //one service selected

                final Services service = servicesService.findServicesById(serviceID);
                if (service == null) {
                    throw new WrongDataExc();
                }

                AdressServices adressServices = adressServicesRepository.findOneByAdressService(adress, service);

                List<Rates> ratesList = ratesRepository.findAllByAdressService(adressServices);

                /*method fixSum, fixFormula, manual - do not return*/
                boolean outputCounter = false;
                if (ratesList.size() > 0 && (ratesList.get(0).getMethod().getName().equals("counter")
                        || ratesList.get(0).getMethod().getName().equals("counterScale"))) {
                    outputCounter = true;
                }

                calculationsList = calculationsRepository.findAllByAdressServicePeriodFromPeriodTo(adress, service, periodFromTimestamp, periodToTimestamp);
                listHeadDetail.add("Услуга");
                listHeadDetail.add(service.getServiceName());
                listReportSumDetail.add(listHeadDetail);
                listReportSumTotal.add(listHeadDetail);

                if (outputCounter) {/*method fixSum, fixFormula, manual - do not return*/
                    listReportCounterDetail.add(listHeadDetail);
                    listReportCounterTotal.add(listHeadDetail);
                }


                for (Calculations calc : calculationsList) {
                    //sumDetailData
                    List listSumDetail = new LinkedList();
                    listSumDetail.add(sdfDateMonth.format(calc.getPeriod()));
                    listSumDetail.add(Math.max(0, calc.getSum()));
                    listReportSumDetail.add(listSumDetail);

                    //sumTotalData
                    List listSumTotal = new LinkedList();
                    listSumTotal.add(sdfDateMonth.format(calc.getPeriod()));
                    listSumTotal.add(Math.max(0, calc.getSum()));
                    listReportSumTotal.add(listSumTotal);


                    if (outputCounter) {/*method fixSum, fixFormula, manual - do not return*/
                        //counterDetailData
                        List listCounterDetail = new LinkedList();
                        listCounterDetail.add(sdfDateMonth.format(calc.getPeriod()));
                        listCounterDetail.add(Math.max(0, calc.getCountCurrent() - calc.getCountLast()));
                        listReportCounterDetail.add(listCounterDetail);

                        //counterTotalData
                        List listCounterTotal = new LinkedList();
                        listCounterTotal.add(sdfDateMonth.format(calc.getPeriod()));
                        listCounterTotal.add(Math.max(0, calc.getCountCurrent() - calc.getCountLast()));
                        listReportCounterTotal.add(listCounterTotal);
                    }
                }
            }


            ReportCalculation reportCalculation = new ReportCalculation();
            reportCalculation.setSumDetailData(listReportSumDetail);
            reportCalculation.setCounterDetailData(listReportCounterDetail);
            reportCalculation.setSumTotalData(listReportSumTotal);
            reportCalculation.setcCounterTotalData(listReportCounterTotal);

            ReportsBuilder builder = new ReportsBuilder();
            return builder.createJson(reportCalculation);

        } catch (AuthException e) {
            response.setStatus(401);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Unauthorized\"}";
        } catch (WrongDataExc e) {
            response.setStatus(402);
            response.setHeader("headers", "{\"Content-Type\":\"application/json\"}");
            return "{\"message\": \"Wrong data\"}";
        }
    }
}
