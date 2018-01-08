package com.compay.service;

import com.compay.entity.Token;

import java.util.List;

public interface TokenService {
    Token findByToken(String token);
    Token create(Token token);
}
