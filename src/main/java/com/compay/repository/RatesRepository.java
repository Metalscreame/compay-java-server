package com.compay.repository;

import com.compay.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rates, Integer> {

}
