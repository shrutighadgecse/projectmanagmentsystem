package com.example.demo.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "machine_options")
public class MachineOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "machine_name", nullable = false)
    private String machineName;

    // Default constructor
    public MachineOption() {}

    // Constructor with parameter
    public MachineOption(String machineName) {
        this.machineName = machineName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

  
    public void save(MachineOption machineOption) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public void deleteByMachineName(String option) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteByMachineName'");
    }

    public Collection<String> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
