package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ReferenceSequences;

public interface ReferenceSequencesRepository extends JpaRepository<ReferenceSequences, Long> {
}