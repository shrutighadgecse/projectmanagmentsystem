package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "km_location_options")
public class KMLocationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "km_location", nullable = false)
    private String kmLocation;

    // Default constructor
    public KMLocationOption() {}

    // Constructor with parameter
    public KMLocationOption(String kmLocation) {
        this.kmLocation = kmLocation;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKMLocationOption() {
        return kmLocation;
    }

    public void setKMLocationOption(String kmLocation) {
        this.kmLocation = kmLocation;
    }
}

