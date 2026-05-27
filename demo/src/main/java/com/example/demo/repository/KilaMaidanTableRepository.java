package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.KilaMaidanTable;
import com.example.demo.model.ReferenceSequence;

import java.util.Optional;

@Repository
public interface KilaMaidanTableRepository extends JpaRepository<KilaMaidanTable, Long> {
    // Additional query methods can be added if needed

    Optional<KilaMaidanTable> findByAssemblyCode(String assemblyCode);

    Optional<ReferenceSequence> findByReferenceId(String referenceId);
}
