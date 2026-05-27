package com.example.demo.repository;

import com.example.demo.model.QrCard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCardRepository extends JpaRepository<QrCard, Long> {
    List<QrCard> findByReferenceIdIn(List<String> referenceIds);
}