package com.compay.json.Login;

import com.compay.json.adminResponses.userList.List;

import java.util.ArrayList;

public class LoginResponseEntity {
    public String authToken;
    public boolean isAdmin;
    public boolean isUser;
    public ArrayList currentObject;

    public LoginResponseEntity(String authToken, boolean isAdmin, boolean isUser, ArrayList currentObject) {
        this.authToken = authToken;
        this.isAdmin = isAdmin;
        this.isUser = isUser;
        this.currentObject = currentObject;
    }
}