package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.KitInwardForm;

public interface KitInwardRepsitory extends JpaRepository<KitInwardForm, Long> {
@Query("SELECT k FROM KitInwardForm k WHERE k.blcPo LIKE %:blcPo%")
List<KitInwardForm> findByBlcPoContaining(@Param("blcPo") String blcPo);

boolean existsByBlcPo(String blcPo);
}


