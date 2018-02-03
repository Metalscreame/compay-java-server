package com.compay.service;

import com.compay.entity.Arguments;

import java.util.List;

public interface ArgumentsService {
    Arguments findArgumentById(Integer id);
    List<Arguments> findAll();
    Arguments create(Arguments argument);
    Arguments findArgumentByName(String name);
}
