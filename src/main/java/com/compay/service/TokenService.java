package com.compay.service;

import com.compay.entity.Token;
import javafx.scene.paint.Stop;

import java.util.List;

public interface TokenService {
    Token findByToken(String token);
    Token create(Token token);
    void delete(String token);
    Token findByUsrPssHash(String usrPssHash);
}
