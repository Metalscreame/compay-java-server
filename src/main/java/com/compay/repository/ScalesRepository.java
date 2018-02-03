package com.compay.repository;

import com.compay.entity.Rates;
import com.compay.entity.Scales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface ScalesRepository extends JpaRepository<Scales, Integer> {
    ArrayList<Scales> findAllByRate(Rates rates);
}
