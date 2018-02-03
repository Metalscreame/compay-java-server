package com.compay.service;

import com.compay.entity.Token;
import com.compay.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.compay.global.Constants.ADMIN;

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

    @Override
    public boolean authChek(String authTokenToCheck) {
        Token tokenObj = tokenRepository.findByToken(authTokenToCheck);
        if (tokenObj == null) return false;
        else {
            if (tokenObj.getUser().getRole().equals(ADMIN)) return true;
            else {
                //expired check, 8.64 == 24 hrs
                if ((System.currentTimeMillis() - tokenObj.getTokenCreateDate().getTime()) >= 8.64e7) {
                    return false;
                } else return true;
            }
        }

    }
}
