package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BLCPO;

@Repository
public interface BLCPORepository extends JpaRepository<BLCPO, Long> {
    @Query("SELECT b.blcPo FROM BLCPO b WHERE LOWER(b.blcPo) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<String> findByBlcPoContaining(@Param("query") String query);
    
}


