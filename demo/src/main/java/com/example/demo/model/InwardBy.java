package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inward_by_options")
public class InwardBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inward_by", nullable = false)
    private String inwardBy;

    // Default constructor
    public InwardBy() {}

    // Constructor with parameter
    public InwardBy(String inwardBy) {
        this.inwardBy = inwardBy;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInwardBy() {
        return inwardBy;
    }

    public void setInwardBy(String inwardBy) {
        this.inwardBy = inwardBy;
    }
}

