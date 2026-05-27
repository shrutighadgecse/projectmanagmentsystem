package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PurchaseOrder;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByCcn(String ccn);
    // In PurchaseOrderRepository.java
Optional<PurchaseOrder> findByCcnIgnoreCase(String ccn);
}
