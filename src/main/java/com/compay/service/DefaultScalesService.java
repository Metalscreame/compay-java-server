package com.compay.service;

import com.compay.entity.DefaultScales;

public interface DefaultScalesService {
    DefaultScales findDefaultScalesById(Integer id);
    DefaultScales create(DefaultScales defaultScales);
}
