package com.compay.json;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonBuilder<T> {
    void addInfo(T o);

    String createJson() throws JsonProcessingException;
}
