package com.compay.service;

import com.compay.entity.Rates;
import com.compay.entity.Scales;
import java.util.ArrayList;

public interface ScalesService {
    Scales findScaleById(Integer id);
    Scales create(Scales scales);
    ArrayList<Scales> findAllByRate(Rates rates);
    void update(Scales scales);
}
