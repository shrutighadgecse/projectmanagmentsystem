package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReleaseToCCNStock;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReleaseToCCNStockRepository extends JpaRepository<ReleaseToCCNStock, Long> {
    Optional<ReleaseToCCNStock> findByReferenceId(String referenceId);
    List<ReleaseToCCNStock> findByAssemblyCode(String assemblyCode);
    boolean existsByReferenceId(String referenceId);
}
