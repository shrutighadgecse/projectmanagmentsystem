package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LimbodaGariOutputTable;
import com.example.demo.model.ReferenceSequence;

@Repository
public interface LimbodaGariOutputTableRepository extends JpaRepository<LimbodaGariOutputTable, Long> {

    Optional<LimbodaGariOutputTable> findByAssemblyCode(String assemblyCode);

    Optional<ReferenceSequence> findByReferenceId(String referenceId);
}
