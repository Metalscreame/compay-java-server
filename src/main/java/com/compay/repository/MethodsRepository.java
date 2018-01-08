package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.Methods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodsRepository extends JpaRepository<Methods, Integer> {
    Methods findByName(String name);
}
