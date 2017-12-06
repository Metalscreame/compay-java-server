package com.compay.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    String msg;

    @JsonView(Views.Public.class)
    int code;

    @JsonView(Views.Public.class)
    List<UserJSON> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<UserJSON> getResult() {
        return result;
    }

    public void setResult(List<UserJSON> result) {
        this.result = result;
    }
}
