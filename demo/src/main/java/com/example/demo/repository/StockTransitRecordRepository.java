package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.StockTransitRecord;

import java.util.List;

@Repository
public interface StockTransitRecordRepository extends JpaRepository<StockTransitRecord, Long> {
    boolean existsByReferenceId(String referenceId);
    List<StockTransitRecord> findByAssemblyCode(String assemblyCode);
    void deleteByReferenceId(String referenceId); // Add this method

}