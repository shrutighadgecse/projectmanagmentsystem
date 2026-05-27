package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.InwardBy;

@Repository
public interface InwardByRepsository extends JpaRepository<InwardBy, Long> {


    void deleteByInwardByIgnoreCase(String option);

    boolean existsByInwardByIgnoreCase(String option);
    
}
