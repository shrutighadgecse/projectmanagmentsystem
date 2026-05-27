package com.example.demo.repository;

import com.example.demo.model.KickoffForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface KickoffFormRepository extends JpaRepository<KickoffForm, Long> {
    Optional<KickoffForm> findByCcn(String ccn);
    
    @Query("SELECT DISTINCT k.ccn FROM KickoffForm k WHERE k.ccn IS NOT NULL AND k.ccn != ''")
    java.util.List<String> findDistinctCcns();

    Optional<KickoffForm> findByCcnIgnoreCase(String ccn);
}
