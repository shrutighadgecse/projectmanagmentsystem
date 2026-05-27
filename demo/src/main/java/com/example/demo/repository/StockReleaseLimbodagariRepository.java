package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.StockReleaseLimbodagari;
import java.util.List;

@Repository
public interface StockReleaseLimbodagariRepository extends JpaRepository<StockReleaseLimbodagari, Long> {
    List<StockReleaseLimbodagari> findByAssemblyCode(String assemblyCode);
}