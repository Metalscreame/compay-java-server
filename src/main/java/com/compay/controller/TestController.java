package com.compay.controller;

import com.compay.entity.*;
import com.compay.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/*
Controller to test the stuff
 */


@Controller
public class TestController{
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

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    @ResponseBody
    public String saveTest(){
        User user = new User();

        Random random = new Random();
        int rand=random.nextInt();
        user.setName("romka");
        user.setPassword("040593");
        user.setEmail("test"+rand+"@test.test");
        user.setLastName("Kosiy");
        user.setSurrName("Stanislavovich");
        svc.create(user);
        return "User " + user.getName() + "with " + user.getEmail() + " email has been saved";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {

//        ArrayList<String> buffer = new ArrayList<>();
//
//        try (Stream<User> stream = svc.findAll()) {
//            stream.forEach((k)->buffer.add("User : " + k));
//        }
        return "The first user in the database is : "+svc.findUserById(1).getEmail();

        //return "redirect:index.jsp/";
//        return buffer.get(1);
    }

    @RequestMapping(value = "/testName",method = RequestMethod.GET)
    @ResponseBody
    public String testNameFinder() {

        List testList = svc.findByName("romka");//сетаем то, что мы будем искать
        User firstUserWIthName = (User) testList.get(0);//Возвращает первую запись

        return firstUserWIthName.getName();
    }

    @RequestMapping(value = "/testEmail",method = RequestMethod.GET)
    @ResponseBody
    public String testEmailFind() {

        List testList = svc.findByEmail("test@test.test");//сетаем то, что мы будем искать
        User firstUserWithMail = (User) testList.get(0);//Возвращает первую запись

        return "The user with " + firstUserWithMail.getEmail()+ " has ID: "+ firstUserWithMail.getId() + ", Name : "  + firstUserWithMail.getName()  + ", Password :  " + firstUserWithMail.getPassword();
    }



    @RequestMapping(value = "/testJson",method = RequestMethod.GET)
    @ResponseBody
    public String testJson() {
        List testList = svc.findByName("romka");
        User firstUserWIthName = (User) testList.get(0);//Возвращает первую запись
        Gson gs = new Gson();
        String str = gs.toJson(firstUserWIthName);
        return "status 200"+str;
    }

    //Find all users
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){
        String result="";
        List<User> users = new ArrayList<>();

        users=svc.findAll();
        for (User ob: users) {
            result =result+ob.getId()+" "+ ob.getName() + " " + ob.getLastName() + " " + ob.getSurrName() + " " + ob.getEmail() +" " + ob.getPassword() + "\r\n";
        }
        return result;
    }


    @RequestMapping(value = "/time",method = RequestMethod.GET)
    @ResponseBody
    public String testTimeTOken() {
       Token token = new Token();
        User user = svc.findUserById(1);
        token.setId(2);
        token.setToken("asdas");
        token.setUser(user);
        token.setTokenCreateDate();
        tokenService.create(token);
        return "token create time created";
    }



    @RequestMapping(value = "/testInitializeDataBase",method = RequestMethod.GET)
    @ResponseBody
    public String testInitializeDataBase(){
        User root = new User();//root test user

        root.setName("root");
        root.setPassword("root");
        root.setEmail("root@root.root");
        root.setLastName("rootLname");
        root.setSurrName("rootSurrname");
        svc.create(root);

        String message = "Initial filling of tables:";
        User user = svc.findUserById(1);

        //Token - root
        java.util.Date utilDate = new java.util.Date();
        Token token = new Token();
        token.setId(1);
        token.setToken("root");
        token.setUser(user);
        token.setTokenCreateDate();
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
        servicesService.create(service1);

        Services service2 = new Services();
        //Водоснабжение
        service2.setId(2);
        service2.setServiceName("Водоснабжение");
        service2.setLink("water");
        servicesService.create(service2);

        Services service3 = new Services();
        //Газоснабжение
        service3.setId(3);
        service3.setServiceName("Газоснабжение");
        service3.setLink("gas");
        servicesService.create(service3);

        Services service4 = new Services();
        //Теплоснабжение
        service4.setId(4);
        service4.setServiceName("Теплоснабжение");
        service4.setLink("hate");
        servicesService.create(service4);

        Services service5 = new Services();
        //Квартплата
        service5.setId(5);
        service5.setServiceName("Квартплата");
        service5.setLink("house");
        servicesService.create(service5);

        Services service6 = new Services();
        //Вывоз мусора
        service6.setId(6);
        service6.setServiceName("Вывоз мусора");
        service6.setLink("garbage");
        servicesService.create(service6);

        Services service7 = new Services();
        //Лифтовое хозяйство
        service7.setId(7);
        service7.setServiceName("Лифтовое хозяйство");
        service7.setLink("lift");
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

        Arguments argument2 = new Arguments();
        argument2.setId(2);
        argument2.setName("registeredPersons");
        argument2.setView("Прописано человек, чел");

        argumentsService.create(argument2);

        Arguments argument3 = new Arguments();
        argument3.setId(3);
        argument3.setName("mainRate");
        argument3.setView("Тариф, грн");

        argumentsService.create(argument3);

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
        //adressService.setAdressID(1);
        //adressService.setServiceID(1);
        adressService1.setAdress(adressId1);
        adressService1.setService(service1);

        adressServicesService.create(adressService1);

        AdressServices adressService2 = new AdressServices();
        adressService2.setId(2);
        //adressService.setAdressID(1);
        //adressService.setServiceID(2);
        adressService2.setAdress(adressId1);
        adressService2.setService(service2);

        adressServicesService.create(adressService2);

        AdressServices adressService3 = new AdressServices();
        adressService3.setId(3);
        //adressService.setAdressID(1);
        //adressService.setServiceID(5);
        adressService3.setAdress(adressId1);
        adressService3.setService(service5);

        adressServicesService.create(adressService3);

        message += " AdressServices;";

        ///////////////////////////////////////////////Rates
        Rates rateId1 = new Rates();
        rateId1.setId(1);
        rateId1.setPeriodFrom(new Date(2017, 1, 1));
        //rateId1.setPeriodTill();
        //rateId1.setMethodID(4);
        rateId1.setMethod(methodId4);
        rateId1.setMainRate(0);
        rateId1.setAdressService(adressService1);
        rateId1.setUserScale(true);
        ratesService.create(rateId1);

        Rates rateId2 = new Rates();
        rateId2.setId(2);
        rateId2.setPeriodFrom(new Date(2017, 1, 1));
        //rates.setPeriodTill();
        //rateId2.setMethodID(3);
        rateId2.setMethod(methodId3);
        rateId2.setMainRate(8.50);
        rateId2.setAdressService(adressService2);
        rateId2.setUserScale(false);
        ratesService.create(rateId2);

        Rates rateId3 = new Rates();
        rateId3.setId(3);
        rateId3.setPeriodFrom(new Date(2017, 5, 1));
        //rates.setPeriodTill();
        rateId3.setMethod(methodId2);
        rateId3.setMainRate(12.30);
        rateId3.setAdressService(adressService3);
        rateId3.setUserScale(false);
        rateId3.setFormula("mainRate * livingArea");
        ratesService.create(rateId3);

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

        message += " Scales;";

        return message;

    }
}