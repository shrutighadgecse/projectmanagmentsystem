package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SwapRecord;

public interface SwapRecordRepository extends JpaRepository<SwapRecord, Long> {
}
