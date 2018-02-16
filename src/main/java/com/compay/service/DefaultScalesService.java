package com.compay.service;

import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;

import java.util.List;

public interface DefaultScalesService {
    DefaultScales findDefaultScalesById(Integer id);

    DefaultScales create(DefaultScales defaultScales);

    List<DefaultScales> findByDefaultRates(DefaultRates defaultRates);
}
