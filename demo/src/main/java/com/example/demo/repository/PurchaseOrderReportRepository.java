package com.example.demo.repository;

import com.example.demo.model.PurchaseOrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseOrderReportRepository extends JpaRepository<PurchaseOrderReport, Long> {
    List<PurchaseOrderReport> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}