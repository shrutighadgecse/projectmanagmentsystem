package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class StockTransferKila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referenceId;
    private String assemblyCode;
    private String description;
    private String machineName;
    private String stage;
    private String limbodagriLocation;
    private String kmLocation;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMachineName() {
        return machineName;
    }
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getLimbodagriLocation() {
        return limbodagriLocation;
    }
    public void setLimbodagriLocation(String limbodagriLocation) {
        this.limbodagriLocation = limbodagriLocation;
    }
    public String getKmLocation() {
        return kmLocation;
    }
    public void setKmLocation(String kmLocation) {
        this.kmLocation = kmLocation;
    }

    // Getters and setters...
}