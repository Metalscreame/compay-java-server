package com.compay.service;

import com.compay.entity.Services;

import java.util.List;

public interface ServicesService {
    Services findServicesById(Integer id);
    Services create(Services servicesObject);
}
