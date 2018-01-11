package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.Scales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScalesRepository extends JpaRepository<Scales, Integer> {

}
