package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.InwardStockLimbodagari;

import java.util.Optional;
import java.util.List;

@Repository
public interface InwardStockLimbodagariRepository extends JpaRepository<InwardStockLimbodagari, Long> {
    Optional<InwardStockLimbodagari> findByReferenceId(String referenceId);
    List<InwardStockLimbodagari> findByAssemblyCode(String assemblyCode);
}
