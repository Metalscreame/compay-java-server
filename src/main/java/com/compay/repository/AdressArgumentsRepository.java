package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.AdressArguments;
import com.compay.entity.Arguments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressArgumentsRepository extends JpaRepository<AdressArguments, Integer> {
    List<AdressArguments> findAllByAdress(Adress adress);
    List<AdressArguments> findAllByArgument(Arguments argument);


    AdressArguments findByAdress_IdAndArgument_Id(int id, int argId);

}
