package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BhoomikaForm;
public interface BhoomikaDataRepository extends JpaRepository<BhoomikaForm, Long> {
    Optional<BhoomikaForm> findByReference(String reference);
    boolean existsByReference(String reference);
    List<BhoomikaForm> findByBlcStatus(String status);
    List<BhoomikaForm> findByBlcStatusNot(String status);
}
