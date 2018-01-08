package com.compay.service;

import com.compay.entity.Arguments;

public interface ArgumentsService {
    Arguments findArgumentById(Integer id);
    Arguments create(Arguments argument);
    Arguments findArgumentByName(String name);
}
