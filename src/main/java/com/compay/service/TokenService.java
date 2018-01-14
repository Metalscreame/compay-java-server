package com.compay.service;

import com.compay.entity.Token;


public interface TokenService {
    Token findByToken(String token);
    Token create(Token token);
    void delete(String token);
    Token findByUsrPssHash(String usrPssHash);
    boolean authChek(String authTokenToCheck);

}
