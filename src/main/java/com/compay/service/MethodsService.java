package com.compay.service;

import com.compay.entity.Adress;
import com.compay.entity.Methods;

public interface MethodsService {
    Methods findMethodById(Integer id);
    Methods create(Methods method);
    Methods findMethodByName(String name);
}
