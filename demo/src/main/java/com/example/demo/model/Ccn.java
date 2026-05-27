package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ccn")
public class Ccn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ccnName", nullable = false)
    private String ccnName;

    // Default constructor
    public Ccn() {}

    // Constructor with parameter
    public Ccn(String ccnName) {
        this.ccnName = ccnName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCcnName() {
        return ccnName;
    }

    public void setCcnName(String ccnName) {
        this.ccnName = ccnName;
    }
}

