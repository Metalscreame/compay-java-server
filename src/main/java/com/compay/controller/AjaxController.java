package com.compay.controller;

import com.compay.json.AjaxResponseBody;
import com.compay.json.SearchCriteria;
import com.compay.json.UserJSON;
import com.compay.json.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
public class AjaxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    List<UserJSON> userJSONS;

//    @Autowired
//    UserService svc;
    // @ResponseBody, not necessary, since class is annotated with @RestController
    // @RequestBody - Convert the com.compay.json data into object (SearchCriteria) mapped by field name.
    // @JsonView(Views.Public.class) - Optional, filters com.compay.json data to display.
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search/api/getSearchResult")
    public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
        LOGGER.error("Method getSearchResultViaAjax is invoked");
        AjaxResponseBody result = new AjaxResponseBody();

        if (isValidSearchCriteria(search)) {
            List<UserJSON> userJSONS = findByUserNameOrEmail(search.getName(), search.getEmail());

            if (userJSONS.size() > 0) {
                result.setCode(SC_OK);
                result.setMsg(StringUtils.EMPTY);
                result.setResult(userJSONS);
            } else {
                result.setCode(SC_NO_CONTENT);
                result.setMsg("No user!");
            }

        } else {
            result.setCode(SC_BAD_REQUEST);
            result.setMsg("Search criteria is empty!");
        }

        //AjaxResponseBody will be converted into json format and send back to the request.
        return result;

    }

    private boolean isValidSearchCriteria(SearchCriteria search) {
        boolean result = false;
        if (search != null) {
            if (StringUtils.isNoneBlank(search.getName()) && StringUtils.isNoneBlank(search.getEmail())) {
                result = true;
            }
        }
        return result;
    }

    // Init some userJSONS for testing
    @PostConstruct
    private void iniDataForTesting() {
        userJSONS = new ArrayList<UserJSON>();

        //--- скорее всего сюда сетить то, что будем брать с базы , чтобы потом оно шло на json
        UserJSON userJSONS1 = new UserJSON("root", "mkyong@yahoo.com", "root");
        UserJSON userJSONS2 = new UserJSON("root", "mkyong2@yahoo.com", "root2");
        UserJSON userJSONS3 = new UserJSON("root", "mkyong3@yahoo.com", "root3");

        userJSONS.add(userJSONS1);
        userJSONS.add(userJSONS2);
        userJSONS.add(userJSONS3);
    }

    // Simulate the search function
    private List<UserJSON> findByUserNameOrEmail(String username, String email) {
        final List<UserJSON> result = new ArrayList<>();
        for (UserJSON user : userJSONS) {
            if (StringUtils.equals(username, user.getName())
                    || StringUtils.equals(email, user.getEmail())) {
                result.add(user);
            }
        }
        return result;
    }
}
