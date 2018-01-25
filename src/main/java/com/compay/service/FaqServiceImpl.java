package com.compay.service;

import com.compay.entity.Faq;
import com.compay.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FaqServiceImpl implements FaqService{

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public List<Faq> findAll() {
        return faqRepository.findAll();
    }

    @Override
    public Faq create(Faq faq) {
        return faqRepository.save(faq);
    }
}
