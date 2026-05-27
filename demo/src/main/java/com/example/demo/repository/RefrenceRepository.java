package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ReferenceSequence;

public interface RefrenceRepository extends JpaRepository<ReferenceSequence, Long> {
}