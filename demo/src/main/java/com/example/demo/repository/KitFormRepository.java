package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BLCPO;
import com.example.demo.model.KitForm;

@Repository
public interface KitFormRepository extends JpaRepository<KitForm, Long> {
    void save(BLCPO newBLCPO);

    @Query("SELECT COUNT(k) FROM KitForm k WHERE k.status = :status")
    int countByStatus(@Param("status") String status); 
    
    @Query("SELECT k FROM KitForm k WHERE k.status = :status ORDER BY k.dateTime DESC")
    List<KitForm> findByStatus(@Param("status") String status);

    @Query("SELECT DISTINCT b.blcPo FROM KitForm b WHERE LOWER(b.blcPo) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<String> findByBlcPoContaining(@Param("query") String query);

}
