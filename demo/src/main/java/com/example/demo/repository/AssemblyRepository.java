package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Assembly;

@Repository
public interface AssemblyRepository extends JpaRepository<Assembly, Long> {
    void deleteByAssemblyCode(String assemblyCode);


    void deleteByAssemblyCodeIgnoreCase(String option);

    boolean existsByAssemblyCodeIgnoreCase(String option);


    







} 