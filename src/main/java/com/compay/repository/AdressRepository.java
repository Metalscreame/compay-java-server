package com.compay.repository;

import com.compay.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress,Integer> {
}
