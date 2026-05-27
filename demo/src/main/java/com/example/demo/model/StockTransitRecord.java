package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_transit_record", uniqueConstraints = {
    @UniqueConstraint(columnNames = "referenceId")
})
public class StockTransitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referenceId;

    @Column(nullable = false)
    private String assemblyCode;


    @Column(nullable = false)
    private String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getAssemblyCode() {
        return assemblyCode;
    }

    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }
}