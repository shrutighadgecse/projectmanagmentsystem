package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lm_location_options")
public class LMLocationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lm_location", nullable = false)
    private String lmLocation;

    // Default constructor
    public LMLocationOption() {}

    // Constructor with parameter
    public LMLocationOption(String lmLocation) {
        this.lmLocation = lmLocation;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLMLocation() {
        return lmLocation;
    }

    public void setLMLocation(String lmLocation) {
        this.lmLocation = lmLocation;
    }
}

