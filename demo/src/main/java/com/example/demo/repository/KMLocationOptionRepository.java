package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.KMLocationOption;


@Repository
public interface KMLocationOptionRepository extends JpaRepository<KMLocationOption, Long> {
    void deleteByKmLocation(String kmLocation); // Updated method name

    void deleteByKmLocationIgnoreCase(String kmLocation); // Updated method name

    boolean existsByKmLocationIgnoreCase(String kmLocation); // Updated method name
}

