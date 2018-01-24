package com.compay.controller.ResponseControllers;



import com.compay.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TablesResponseControllers {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/get{name_table}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseTableData(HttpServletResponse response, @PathVariable("name_table") String name_table) {
        String message = "";

        List l = em.createQuery(
                "SELECT t FROM " + name_table + " t")
                .getResultList();
        message += "Size: " + l.size() + "\n";

        for (Object p : l) {
            message += printResult(p);
        }

        return message;
    }

    @RequestMapping(value = "/get/All", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseTablesData(HttpServletResponse response) {
        String message = "";

        String[] nameTables = {"User", "Token", "Services", "Scales", "Rates", "Methods", "Calculations", "Arguments", "AdressServices",
                "AdressArguments", "Adress"};

        for (String name:nameTables){
            List l = em.createQuery(
                "SELECT t FROM " + name + " t")
                .getResultList();
            message += "Size: " + l.size() + "\n";

            for (Object p : l) {
                message += printResult(p);
            }
        }

        return message;
    }

    private String printResult(Object result) {
        String message = "";
        if (result == null) {
            message += " NULL \n";
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            message += "[";
            for (int i = 0; i < row.length; i++) {
                message += printResult(row[i]);
            }
            message += "]";
        } else if (result instanceof Long || result instanceof Double
                || result instanceof String) {
            if(result.getClass().getName() == "User") {
                message += result.getClass().getName() + ": " + (User)result;
            }else {
                message += result.getClass().getName() + ": " + result.toString();
            }

        } else {
            message += result;
        }
        return message += "\n";
    }
}

