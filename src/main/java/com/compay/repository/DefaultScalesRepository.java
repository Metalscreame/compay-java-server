package com.compay.repository;

import com.compay.entity.DefaultRates;
import com.compay.entity.DefaultScales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultScalesRepository extends JpaRepository<DefaultScales, Integer> {
    List<DefaultScales> findAllByDefaultRates(DefaultRates defaultRates);
    List<DefaultScales> findAllByDefaultRates_Id(int id);
}
