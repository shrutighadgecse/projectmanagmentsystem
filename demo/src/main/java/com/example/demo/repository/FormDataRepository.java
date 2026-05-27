package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FormData;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {
        // Fetch all unique assembly codes
        Optional<FormData> findByAssemblyCode(String assemblyCode);
            List<FormData> findByAssemblyCodeStartingWith(String prefix);
            Optional<FormData> findByReferenceId(String referenceId);}
            
