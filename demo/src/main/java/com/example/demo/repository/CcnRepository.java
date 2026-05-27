package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ccn;

@Repository
public interface CcnRepository extends JpaRepository<Ccn, Long> {


    void deleteByCcnNameIgnoreCase(String option);

    boolean existsByCcnNameIgnoreCase(String ccnName);    
}
