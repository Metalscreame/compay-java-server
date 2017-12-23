package com.compay.controller;

import com.compay.entity.User;
import com.compay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
public class TestController{
    @Autowired
    UserService svc;


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

        //TODO сделать функцию, в которую загоняется стринга (результат очереди, и которая возвращает стринг с джсоном или как-то так)
        //return "redirect:index.jsp/";
//        return buffer.get(1);
    }

    @RequestMapping(value = "/testName",method = RequestMethod.GET)
    @ResponseBody
    public String testNameFinder() {

        List testList = svc.findByName("romka");//сетаем то, что мы будем искать
        User firstUserWIthName = (User) testList.get(1);//Возвращает первую запись

        return firstUserWIthName.getName();
    }

    @RequestMapping(value = "/testEmail",method = RequestMethod.GET)
    @ResponseBody
    public String testEmailFind() {

        List testList = svc.findByEmail("test@test.test");//сетаем то, что мы будем искать
        User firstUserWithMail = (User) testList.get(0);//Возвращает первую запись

        return "The user with " + firstUserWithMail.getEmail()+ " has ID: "+ firstUserWithMail.getId() + ", Name : "  + firstUserWithMail.getName()  + ", Password :  " + firstUserWithMail.getPassword();
    }


}