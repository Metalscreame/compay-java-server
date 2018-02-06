package com.compay.json.Login;

public class LoginResponseEntity {
    public String authToken;
    public boolean isAdmin;
    public boolean isUser;
    public Object currentObject;

    public LoginResponseEntity(String authToken, boolean isAdmin, boolean isUser, Object currentObject) {
        this.authToken = authToken;
        this.isAdmin = isAdmin;
        this.isUser = isUser;
        this.currentObject = currentObject;
    }
}
