package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ScannerFormData;

import java.util.Optional;
import java.util.List;

@Repository
public interface ScannerFormDataRepository extends JpaRepository<ScannerFormData, Long> {
    Optional<ScannerFormData> findByReferenceId(String referenceId);
    List<ScannerFormData> findByAssemblyCode(String assemblyCode);
}