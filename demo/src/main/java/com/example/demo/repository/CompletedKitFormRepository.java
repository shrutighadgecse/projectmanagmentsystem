package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CompletedKitForm;

@Repository
public interface CompletedKitFormRepository extends JpaRepository<CompletedKitForm, Long> {

    Optional<CompletedKitForm> findByReferenceId(String id);


}