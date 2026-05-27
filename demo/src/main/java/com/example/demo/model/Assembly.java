package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assembly_code")

public class Assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assembly_code", nullable = false)
    private String assemblyCode;

  // Default constructor
  public Assembly() {}

  // Constructor with parameter
  public Assembly(String assemblyCode) {
      this.assemblyCode = assemblyCode;
  }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }     

    public String getAssemblycode() {
        return assemblyCode;
    }

    public void setAssemblycode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }

}

