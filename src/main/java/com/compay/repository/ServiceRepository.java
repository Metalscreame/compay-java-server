package com.compay.repository;

import com.compay.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services,Integer> {
}
