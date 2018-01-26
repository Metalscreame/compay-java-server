package com.compay.repository;

import com.compay.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository  extends JpaRepository<Faq,Integer> {
    //List<Faq> findAll();
}
