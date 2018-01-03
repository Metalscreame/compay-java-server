package com.compay.service;

import com.compay.entity.Scales;

public interface ScalesService {
    Scales findScaleById(Integer id);
    Scales create(Scales scales);
}
