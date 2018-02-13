package com.compay.controller;

import com.compay.entity.Adress;
import com.compay.entity.AdressArguments;
import com.compay.entity.AdressServices;
import com.compay.entity.Arguments;
import com.compay.entity.Calculations;
import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;
import com.compay.entity.Methods;
import com.compay.entity.Rates;
import com.compay.entity.Scales;
import com.compay.entity.Services;
import com.compay.entity.Token;
import com.compay.entity.User;
import com.compay.service.*;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.compay.global.Constants.ADMIN;
import static com.compay.global.Constants.USER;

/*
Controller to test the stuff
 */

@Controller
public class TestController {
    @Autowired
    UserService svc;

    @Autowired
    ServicesService servicesService;

    @Autowired
    AdressService adressService;

    @Autowired
    MethodsService methodsService;

    @Autowired
    ArgumentsService argumentsService;

    @Autowired
    AdressArgumentsService adressArgumentsService;

    @Autowired
    AdressServicesService adressServicesService;

    @Autowired
    RatesService ratesService;

    @Autowired
    ScalesService scalesService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CalculationsService calculationsService;

    @Autowired
    DefaultRatesService defaultRatesService;

    @Autowired
    DefaultScalesService defaultScalesService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String saveTest() {
        User user = new User();

        Random random = new Random();
        int rand = random.nextInt();
        user.setName("romka");
        user.setPassword("040593");
        user.setEmail("test" + rand + "@test.test");
        user.setLastName("Kosiy");
        user.setRole("user");
        svc.create(user);
        return "User " + user.getName() + "with " + user.getEmail() + " email has been saved";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "The first user in the database is : " + svc.findUserById(1).getEmail();
        //return "redirect:index.jsp/";
//        return buffer.get(1);
    }

    @RequestMapping(value = "/testName", method = RequestMethod.GET)
    @ResponseBody
    public String testNameFinder() {
        List testList = svc.findByName("romka");//сетаем то, что мы будем искать
        User firstUserWIthName = (User) testList.get(0);//Возвращает первую запись
        return firstUserWIthName.getName();
    }

    @RequestMapping(value = "/testEmail", method = RequestMethod.GET)
    @ResponseBody
    public String testEmailFind() {
        User firstUserWithMail = svc.findByEmail("test@test.test");//сетаем то, что мы будем искать
        return "The user with " + firstUserWithMail.getEmail() + " has ID: " + firstUserWithMail.getId() + ", Name : " + firstUserWithMail.getName() + ", Password :  " + firstUserWithMail.getPassword();
    }

    @RequestMapping(value = "/testJson", method = RequestMethod.GET)
    @ResponseBody
    public String testJson() {
        List testList = svc.findByName("romka");
        User firstUserWIthName = (User) testList.get(0);//Возвращает первую запись
        Gson gs = new Gson();
        String str = gs.toJson(firstUserWIthName);
        return "status 200" + str;
    }

    //Find all users
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers() {
        String result = "";
        List<User> users = new ArrayList<>();

        users = svc.findAll();
        for (User ob : users) {
            result = result + ob.getId() + " " + ob.getName() + " " + ob.getLastName() + " " + ob.getEmail() + " " + ob.getPassword() + "\r\n";
        }
        return result;
    }

    @Autowired
    TruncateDataBase truncateDataBase;

    @RequestMapping(value = "/dropAll", method = RequestMethod.GET)
    @ResponseBody
    public String truncateDB() throws Exception {
        truncateDataBase.truncate();
        return "tables has been dropped";
    }

    @RequestMapping(value = "/testInitializeDataBase", method = RequestMethod.GET)
    @ResponseBody
    public String testInitializeDataBase() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        User root = new User();//root test user

        root.setName("admin");
        root.setPassword("admin");
        root.setEmail("admin@gmail.com");
        root.setLastName("AdminLname");
        root.setRole(ADMIN);
        svc.create(root);
        User newUsr = new User();
        newUsr.setEmail("dima@dima.dima");
        newUsr.setName("Дмитрий");
        newUsr.setRole(USER);
        newUsr.setLastName("Павлочич");
        newUsr.setPassword("040593");
        svc.create(newUsr);

        String message = "Initial filling of tables:";
        User user = svc.findUserById(1);

        //Token - root

        message += "Admin acc; User acc; Token;";
        Token token = new Token();
        token.setId(1);
        token.setUser(user);
        String sha = DigestUtils.sha1Hex(user.getEmail() + user.getPassword());
        token.setUserPlusPassHash(sha);
        token.setTokenCreateDate();
        token.setToken();
        tokenService.create(token);
        ///////////////////////////////////////////////Adress
        //Flat
        Adress adressId1 = new Adress();
        adressId1.setId(1);
        adressId1.setUser(user);
        adressId1.setAppartmentNumber("165");
        adressId1.setCity("Днепр");
        adressId1.setHouseNumber((short) 15);
        adressId1.setRegion("");
        adressId1.setType("Квартира");
        adressId1.setStreet("Гагарина");
        adressId1.setObjectDefault(true);
        adressService.create(adressId1);
        //Garage
        Adress adressId2 = new Adress();
        adressId2.setId(2);
        adressId2.setUser(user);
        adressId2.setAppartmentNumber("");
        adressId2.setCity("Днепр");
        adressId2.setHouseNumber((short) 100);
        adressId2.setRegion("");
        adressId2.setType("Гараж");
        adressId2.setStreet("Гаражная");
        adressId2.setObjectDefault(false);
        adressService.create(adressId2);
        //Country house
        Adress adressId3 = new Adress();
        adressId3.setId(3);
        adressId3.setUser(user);
        adressId3.setAppartmentNumber("");
        adressId3.setCity("Днепр");
        adressId3.setHouseNumber((short) 100);
        adressId3.setRegion("");
        adressId3.setType("Дача");
        adressId3.setStreet("Суворова");
        adressId3.setObjectDefault(false);
        adressService.create(adressId3);

        message += " Adress;";
        ///////////////////////////////////////////////Services
        Services service1 = new Services();
        //Электроснабжение
        service1.setId(1);
        service1.setServiceName("Электроснабжение");
        service1.setLink("electric");
        service1.setUnits("грн/(кВт*час)");
        servicesService.create(service1);

        Services service2 = new Services();
        //Водоснабжение
        service2.setId(2);
        service2.setServiceName("Водоснабжение");
        service2.setLink("water");
        service2.setUnits("грн/м3");
        servicesService.create(service2);

        Services service3 = new Services();
        //Газоснабжение
        service3.setId(3);
        service3.setServiceName("Газоснабжение");
        service3.setLink("gas");
        service3.setUnits("грн/м3");
        servicesService.create(service3);

        Services service4 = new Services();
        //Теплоснабжение
        service4.setId(4);
        service4.setServiceName("Теплоснабжение");
        service4.setLink("hate");
        service4.setUnits("(грн/м2)/мес");
        servicesService.create(service4);

        Services service5 = new Services();
        //Квартплата
        service5.setId(5);
        service5.setServiceName("Квартплата");
        service5.setLink("house");
        service5.setUnits("(грн/м2)/мес");
        servicesService.create(service5);

        Services service6 = new Services();
        //Вывоз мусора
        service6.setId(6);
        service6.setServiceName("Вывоз мусора");
        service6.setLink("garbage");
        service6.setUnits("грн/мес");
        servicesService.create(service6);

        Services service7 = new Services();
        //Лифтовое хозяйство
        service7.setId(7);
        service7.setServiceName("Лифтовое хозяйство");
        service7.setLink("lift");
        service7.setUnits("грн/мес");
        servicesService.create(service7);

        message += " Services;";


        ///////////////////////////////////////////////Methods
        Methods methodId1 = new Methods();

        methodId1.setId(1);
        methodId1.setName("fixSum");
        methodId1.setView("Фиксированная сумма");

        methodsService.create(methodId1);

        Methods methodId2 = new Methods();
        methodId2.setId(2);
        methodId2.setName("fixFormula");
        methodId2.setView("Фиксированная формула");

        methodsService.create(methodId2);

        Methods methodId3 = new Methods();
        methodId3.setId(3);
        methodId3.setName("counter");
        methodId3.setView("По счетчику");

        methodsService.create(methodId3);

        Methods methodId4 = new Methods();
        methodId4.setId(4);
        methodId4.setName("counterScale");
        methodId4.setView("По счетчику с применением шкалы");

        methodsService.create(methodId4);

        Methods methodId5 = new Methods();
        methodId5.setId(5);
        methodId5.setName("manual");
        methodId5.setView("Ручной ввод");

        methodsService.create(methodId5);

        message += " Methods;";

        ///////////////////////////////////////////////Arguments
        Arguments argument1 = new Arguments();

        argument1.setId(1);
        argument1.setName("livingArea");
        argument1.setView("Жилая площадь, м2");

        argumentsService.create(argument1);



        Arguments argument3 = new Arguments();
        argument3.setId(2);
        argument3.setName("mainRate");
        argument3.setView("Тариф, грн");

        argumentsService.create(argument3);

        Arguments argument2 = new Arguments();
        argument2.setId(3);
        argument2.setName("registeredPersons");
        argument2.setView("Прописано человек, чел");

        argumentsService.create(argument2);


        message += " Arguments;";

        ///////////////////////////////////////////////AdressArguments
        AdressArguments adressArgument = new AdressArguments();

        adressArgument.setId(1);
        //adressArgument.setAdressID(1);
        adressArgument.setAdress(adressId1);
        //adressArgument.setArgumentID(1);
        adressArgument.setArgument(argument1);
        adressArgument.setValue(44.9);

        adressArgumentsService.create(adressArgument);

        adressArgument.setId(2);
        //adressArgument.setAdressID(1);
        adressArgument.setAdress(adressId1);
        //adressArgument.setArgumentID(2);
        adressArgument.setArgument(argument2);
        adressArgument.setValue(2);

        adressArgumentsService.create(adressArgument);

        adressArgument.setId(3);
        //adressArgument.setAdressID(2);
        adressArgument.setAdress(adressId2);
        //adressArgument.setArgumentID(1);
        adressArgument.setArgument(argument1);
        adressArgument.setValue(0);

        adressArgumentsService.create(adressArgument);

        adressArgument.setId(4);
        //adressArgument.setAdressID(2);
        adressArgument.setAdress(adressId2);
        //adressArgument.setArgumentID(2);
        adressArgument.setArgument(argument2);
        adressArgument.setValue(0);

        adressArgumentsService.create(adressArgument);

        adressArgument.setId(5);
        //adressArgument.setAdressID(3);
        adressArgument.setAdress(adressId3);
        //adressArgument.setArgumentID(1);
        adressArgument.setArgument(argument1);
        adressArgument.setValue(240.5);

        adressArgumentsService.create(adressArgument);

        adressArgument.setId(6);
        //adressArgument.setAdressID(3);
        adressArgument.setAdress(adressId3);
        //adressArgument.setArgumentID(2);
        adressArgument.setArgument(argument2);
        adressArgument.setValue(0);

        adressArgumentsService.create(adressArgument);

        message += " AdressArguments;";

        ///////////////////////////////////////////////AdressServices
        AdressServices adressService1 = new AdressServices();
        adressService1.setId(1);
        adressService1.setAdress(adressId1);
        adressService1.setService(service1);
        adressService1.setPersAcc(123456);
        adressService1.setCheckAcc(260065789876890L);
        adressService1.setMFO(309802);
        adressService1.setEGRPO(12324232);
        adressServicesService.create(adressService1);

        AdressServices adressService2 = new AdressServices();
        adressService2.setId(2);
        adressService2.setAdress(adressId1);
        adressService2.setService(service2);
        adressService2.setPersAcc(123456);
        adressService2.setCheckAcc(260065789876890L);
        adressService2.setMFO(309802);
        adressService2.setEGRPO(12324232);
        adressServicesService.create(adressService2);

        AdressServices adressService3 = new AdressServices();
        adressService3.setId(3);
        adressService3.setAdress(adressId1);
        adressService3.setService(service5);
        adressService3.setPersAcc(123456);
        adressService3.setCheckAcc(260065789876890L);
        adressService3.setMFO(309802);
        adressService3.setEGRPO(12324232);
        adressServicesService.create(adressService3);

        message += " AdressServices;";

        ///////////////////////////////////////////////Rates
        Rates rateId1 = new Rates();
        rateId1.setId(1);
        rateId1.setPeriodFrom(new java.sql.Timestamp(dateFormat.parse("2017-01-01").getTime()));
        //rateId1.setPeriodTill();
        //rateId1.setMethodID(4);
        rateId1.setMethod(methodId4);
        rateId1.setMainRate(0.0d);
        rateId1.setAdressService(adressService1);
        rateId1.setUserScale(true);
        ratesService.create(rateId1);

        Rates rateId2 = new Rates();
        rateId2.setId(2);
        rateId2.setPeriodFrom(new java.sql.Timestamp(dateFormat.parse("2017-01-01").getTime()));//2017-01-01
        //rates.setPeriodTill();
        rateId2.setMethod(methodId3);
        rateId2.setMainRate(8.50);
        rateId2.setAdressService(adressService2);
        rateId2.setUserScale(false);
        ratesService.create(rateId2);

        Rates rateId3 = new Rates();
        rateId3.setId(3);
        rateId3.setPeriodFrom(new java.sql.Timestamp(dateFormat.parse("2017-05-01").getTime()));//2017-05-01
        //rates.setPeriodTill();
        rateId3.setMethod(methodId2);
        rateId3.setMainRate(12.30);
        rateId3.setAdressService(adressService3);
        rateId3.setUserScale(false);
        rateId3.setFormula("mainRate * livingArea");
        ratesService.create(rateId3);

        Rates rateId4 = new Rates();
        rateId4.setId(4);
        rateId4.setPeriodFrom(new java.sql.Timestamp(dateFormat.parse("2016-11-01").getTime()));
        rateId4.setMethod(methodId4);
        rateId4.setMainRate(0.0d);
        rateId4.setAdressService(adressService1);
        rateId4.setUserScale(true);
        ratesService.create(rateId4);

        Rates rateId5 = new Rates();
        rateId5.setId(5);
        rateId5.setPeriodFrom(new java.sql.Timestamp(dateFormat.parse("2016-11-01").getTime()));//2016-11-01
        //rates.setPeriodTill();
        rateId5.setMethod(methodId3);
        rateId5.setMainRate(6.90);
        rateId5.setAdressService(adressService2);
        rateId5.setUserScale(false);
        ratesService.create(rateId5);

        message += " Rates;";


        ///////////////////////////////////////////////Scales
        Scales scale = new Scales();

        scale.setId(1);
        scale.setMainRate(0.95);//0.95
        scale.setMaxValue(100);
        scale.setMinValue(0);
        scale.setRate(rateId1);
        scalesService.create(scale);

        scale.setId(2);
        scale.setMainRate(1.20);//1.20
        scale.setMaxValue(600);
        scale.setMinValue(101);
        scale.setRate(rateId1);
        scalesService.create(scale);

        scale.setId(3);
        scale.setMainRate(1.88);//1.88
        scale.setMaxValue(0);
        scale.setMinValue(601);
        scale.setRate(rateId1);
        scalesService.create(scale);

        scale.setId(4);
        scale.setMainRate(0.95);//0.95
        scale.setMaxValue(100);
        scale.setMinValue(0);
        scale.setRate(rateId4);
        scalesService.create(scale);

        scale.setId(5);
        scale.setMainRate(1.20);//1.20
        scale.setMaxValue(600);
        scale.setMinValue(101);
        scale.setRate(rateId4);
        scalesService.create(scale);

        scale.setId(6);
        scale.setMainRate(1.88);//1.88
        scale.setMaxValue(0);
        scale.setMinValue(601);
        scale.setRate(rateId4);
        scalesService.create(scale);

        message += " Scales;";


        Calculations calculations = new Calculations();
        calculations.setId(1);
        calculations.setAdress(adressId1);
        calculations.setCountCurrent(231);
        calculations.setCountLast(125);


        String datestr1 = "2017-09-01";
        Date parsedDate1 = dateFormat.parse(datestr1);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate1.getTime());
        calculations.setPeriod(timestamp);
        calculations.setSum(104);
        calculations.setService(service1);
        calculations.setUser(user);
        calculationsService.create(calculations);

        calculations.setId(2);
        calculations.setAdress(adressId1);
        calculations.setCountCurrent(510);
        calculations.setCountLast(231);
        String datestr2 = "2017-11-01";
        Date parsedDate2 = dateFormat.parse(datestr2);
        Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
        calculations.setPeriod(timestamp2);
        calculations.setSum(224);
        calculations.setService(service1);
        calculations.setUser(user);
        calculationsService.create(calculations);

        calculations.setId(3);
        calculations.setAdress(adressId1);
        calculations.setCountCurrent(615);
        calculations.setCountLast(510);

        String datestr3 = "2017-12-01";
        Date parsedDate3 = dateFormat.parse(datestr3);
        Timestamp timestamp3 = new java.sql.Timestamp(parsedDate3.getTime());
        calculations.setPeriod(timestamp3);
        calculations.setSum(102.5d);
        calculations.setService(service1);
        calculations.setUser(user);
        calculationsService.create(calculations);

        calculations.setId(4);
        calculations.setAdress(adressId1);
        calculations.setCountCurrent(510);
        calculations.setCountLast(500);
        String datestr4 = "2018-01-01";

        Date parsedDate = dateFormat.parse(datestr4);
        Timestamp timestamp4 = new java.sql.Timestamp(parsedDate.getTime());
        calculations.setPeriod(timestamp4);
        calculations.setSum(12.30d);
        calculations.setService(service2);
        calculations.setUser(user);
        calculationsService.create(calculations);

        calculations.setId(5);
        calculations.setAdress(adressId1);
        calculations.setCountCurrent(435);
        calculations.setCountLast(215);

        String datestr5 = "2017-10-01";
        Date parsedDate5 = dateFormat.parse(datestr5);
        Timestamp timestamp5 = new java.sql.Timestamp(parsedDate5.getTime());
        calculations.setPeriod(timestamp5);
        calculations.setSum(1518);
        calculations.setService(service3);
        calculations.setUser(user);
        calculationsService.create(calculations);


        ///////////////////////////////////////////////DefaultRates

        DefaultRates defaultRatesId1 = new DefaultRates();
        defaultRatesId1.setId(1);
        defaultRatesId1.setService(service1);
        defaultRatesId1.setMethod(methodId4);
        defaultRatesId1.setMainRate(0.0);
        defaultRatesId1.setUserScale(true);
        defaultRatesService.create(defaultRatesId1);

        DefaultRates defaultRatesId2 = new DefaultRates();
        defaultRatesId2.setId(2);
        defaultRatesId2.setService(service2);
        defaultRatesId2.setMethod(methodId3);
        defaultRatesId2.setMainRate(12.3);
        defaultRatesId2.setUserScale(false);
        defaultRatesService.create(defaultRatesId2);

        DefaultRates defaultRatesId3 = new DefaultRates();
        defaultRatesId3.setId(3);
        defaultRatesId3.setService(service3);
        defaultRatesId3.setMethod(methodId3);
        defaultRatesId3.setMainRate(12.3);
        defaultRatesId3.setUserScale(false);
        defaultRatesService.create(defaultRatesId3);

        DefaultRates defaultRatesId4 = new DefaultRates();
        defaultRatesId4.setId(4);
        defaultRatesId4.setService(service4);
        defaultRatesId4.setMethod(methodId5);
        defaultRatesId4.setMainRate(0.0);
        defaultRatesId4.setUserScale(false);
        defaultRatesService.create(defaultRatesId4);

        DefaultRates defaultRatesId5 = new DefaultRates();
        defaultRatesId5.setId(5);
        defaultRatesId5.setFormula("mainRate * livingArea");
        defaultRatesId5.setService(service5);
        defaultRatesId5.setMethod(methodId2);
        defaultRatesId5.setMainRate(12.5);
        defaultRatesId5.setUserScale(false);
        defaultRatesService.create(defaultRatesId5);

        DefaultRates defaultRatesId6 = new DefaultRates();
        defaultRatesId6.setId(6);
        defaultRatesId6.setService(service6);
        defaultRatesId6.setMethod(methodId1);
        defaultRatesId6.setMainRate(16.0);
        defaultRatesId6.setUserScale(false);
        defaultRatesService.create(defaultRatesId6);

        DefaultRates defaultRatesId7 = new DefaultRates();
        defaultRatesId7.setId(7);
        defaultRatesId7.setService(service7);
        defaultRatesId7.setMethod(methodId1);
        defaultRatesId7.setMainRate(22.5);
        defaultRatesId7.setUserScale(false);
        defaultRatesService.create(defaultRatesId7);

        message += " DefaultRates;";


        ///////////////////////////////////////////////Scales
        DefaultScales defaultScale = new DefaultScales();

        defaultScale.setId(1);
        defaultScale.setMainRate(0.95);//0.95
        defaultScale.setMaxValue(100);
        defaultScale.setMinValue(0);
        defaultScale.setRate(defaultRatesId1);
        defaultScalesService.create(defaultScale);

        defaultScale.setId(2);
        defaultScale.setMainRate(1.20);//1.20
        defaultScale.setMaxValue(600);
        defaultScale.setMinValue(101);
        defaultScale.setRate(defaultRatesId1);
        defaultScalesService.create(defaultScale);

        defaultScale.setId(3);
        defaultScale.setMainRate(1.88);//1.88
        defaultScale.setMaxValue(0);
        defaultScale.setMinValue(601);
        defaultScale.setRate(defaultRatesId1);
        defaultScalesService.create(defaultScale);

        message += " DefaultScales;";


        return message;

    }
}