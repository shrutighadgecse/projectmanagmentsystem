package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DropdownEntity;

@Repository
public interface DropdownRepository extends JpaRepository<DropdownEntity, Long> {
    List<String> findByValueContainingIgnoreCase(String query);
    boolean existsByValue(String value);
}
