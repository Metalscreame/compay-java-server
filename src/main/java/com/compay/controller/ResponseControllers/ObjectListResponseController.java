package com.compay.controller.ResponseControllers;

import com.compay.json.ObjectList.ObjectListEntity;
import com.compay.json.ObjectList.ObjectListJsonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ObjectListResponseController {

    @RequestMapping(value = "/objectList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    //TODO скорее всего сюда еще придется загонять токен или его првоерку
    public String responseBody(HttpServletResponse response) {
        String result = null;

        //TODO тут сделать вычитку из юзера
        //TODO тут будет проверка на то есть ли зарегестрированные обжекты (если нет, то после елса)
        if (true) {// пока стоит заглушка, так как еще нету метода для сверки из базы
            ObjectListJsonBuilder builder = new ObjectListJsonBuilder();

            //TODO здесь будет метод, который будет вытягивать данные объектов учета, зарегистрированных у пользователя:
            // т.е. нужно подвязываться к пользователю. по токену.
            builder.addInfo(new ObjectListEntity(123, "Тест квартира № 666", true));
            builder.addInfo(new ObjectListEntity(124, "Тест Гараж, Кирова 20", false));
            //а пока выдаем тестовую информацию

            try {
                result = builder.createJson();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            response.setHeader("headers", "{ 'Content-Type': 'application/json' }");
            return result;
        }
        //Если у пользователя нет зарегистрированных объектов, то в ответ сервер должен отдать JSON с пустым массивом:
        else {
            return "{accountObjects: []}";
        }
        //TODO проверка токена и все вытекающие
    }
}
