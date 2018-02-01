package com.compay.service;

import com.compay.entity.Faq;

import java.util.List;

public interface FaqService {

    List<Faq> findAll();
    Faq create(Faq faq);
    void deleteAll();
}
