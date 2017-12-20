package com.compay.controller;

import com.compay.entity.User;
import com.compay.json.AjaxResponseBody;
import com.compay.json.SearchCriteria;
import com.compay.json.UserJSON;
import com.compay.json.Views;
import com.compay.service.UserService;
import com.compay.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class AjaxController {
    List<UserJSON> userJSONS;

    @Autowired
    UserService svc;

    // @ResponseBody, not necessary, since class is annotated with @RestController
    // @RequestBody - Convert the com.com.compay.json data into object (SearchCriteria) mapped by field name.
    // @JsonView(Views.Public.class) - Optional, filters com.com.compay.json data to display.
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search/api/getSearchResult")
    public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
        AjaxResponseBody result = new AjaxResponseBody();
        if (isValidSearchCriteria(search)) {
            List<UserJSON> userJSONS = findByUserNameOrEmail(search.getName(), search.getEmail());
            if (userJSONS.size() > 0) {
                result.setCode("200");
                result.setMsg("SomeMessage... HELLO FROM UKRAINE");
                result.setResult(userJSONS);
            } else {
                result.setCode("204");
                result.setMsg("No user!");
            }

        } else {
            result.setCode("400");
            result.setMsg("Search criteria is empty!");
        }
        //AjaxResponseBody will be converted into json format and send back to the request.
        return result;
    }

    private boolean isValidSearchCriteria(SearchCriteria search) {
        boolean valid = true;
        if (search == null) {
            valid = false;
        }
        if ((StringUtils.isEmpty(search.getName())) && (StringUtils.isEmpty(search.getEmail()))) {
            valid = false;
        }
        return valid;
    }

    // Init some userJSONS for testing
    @PostConstruct
    private void iniDataForTesting() {
        //User user = new User();

//        Random random = new Random();
//        int rand=random.nextInt();
//        user.setName("romka");
//        user.setPassword("040593");
//        user.setEmail("test"+rand+"@test.test");
//        user.setLastName("Kosiy");
//        user.setSurrName("Stanislavovich");
//        svc.create(user);
//        userJSONS = new ArrayList<UserJSON>();

       // List testList = svc.findByEmail("test@test.test");//сетаем то, что мы будем искать

        //TODO генерирует ошибку (контейнер не стартует!!), т.к. не всегда существует вервая запись
        //TODO комментировать перед заливанием в мастер.
//        User firstUserWithMail = (User) testList.get(0);//Возвращает первую запись
//
//        String email = firstUserWithMail.getEmail();
//        String name = firstUserWithMail.getName();
//        String password = firstUserWithMail.getPassword();
//        //--- скорее всего сюда сетаить то, что будем брать с базы , чтобы потом оно шло на json
//        UserJSON userJSONS1 = new UserJSON(password, email, name);
       UserJSON userJSONS2 = new UserJSON("root", "mkyong2@yahoo.com", "root2");
        UserJSON userJSONS3 = new UserJSON("root", "mkyong3@yahoo.com", "root3");
//
//        userJSONS.add(userJSONS1);
       userJSONS.add(userJSONS2);
       userJSONS.add(userJSONS3);
    }
    // Simulate the search function
    private List<UserJSON> findByUserNameOrEmail(String username, String email) {
        List<UserJSON> result = new ArrayList<UserJSON>();
        for (UserJSON user : userJSONS) {
            if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {
                if (username.equals(user.getName()) && email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                } else {
                    continue;
                }
            }
            if (!StringUtils.isEmpty(username)) {
                if (username.equals(user.getName())) {
                    result.add(user);
                    continue;
                }
            }

            if (!StringUtils.isEmpty(email)) {
                if (email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                }
            }
        }
        return result;
    }
}
