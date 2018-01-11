package com.compay.service;

import com.compay.entity.Token;
import com.compay.entity.User;
import com.compay.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Token create(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public void delete(String token) {
        tokenRepository.deleteByToken(token);
    }

    @Override
    public Token findByUsrPssHash(String usrPssHash) {
        return tokenRepository.findByUserPlusPassHash(usrPssHash);
    }
}
