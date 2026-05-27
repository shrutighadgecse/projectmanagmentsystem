package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LMLocationOption;


@Repository
public interface LMLocationOptionRepository extends JpaRepository<LMLocationOption, Long> {
    void deleteByLmLocation(String lmLocation); // Updated method name

    void deleteByLmLocationIgnoreCase(String lmLocation); // Updated method name

    boolean existsByLmLocationIgnoreCase(String lmLocation); // Updated method name
}

