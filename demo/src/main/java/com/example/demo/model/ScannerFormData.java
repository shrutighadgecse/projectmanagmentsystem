package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scanner_form_data", uniqueConstraints = {
    @UniqueConstraint(columnNames = "referenceId")
})
public class ScannerFormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referenceId;

    @Column(nullable = false)
    private String assemblyCode;

    @Column(nullable = false)
    private String kmLocation;

   

    @Column(nullable = false)
    private String grnNumber;

    @Column(nullable = false)
    private String grnDate;

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

    public String getGrnNumber() {
        return grnNumber;
    }

    public void setGrnNumber(String grnNumber) {
        this.grnNumber = grnNumber;
    }

    public String getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(String grnDate) {
        this.grnDate = grnDate;
    }

    public String getKmLocation() {
        return kmLocation;
    }

    public void setKmLocation(String kmLocation) {
        this.kmLocation = kmLocation;
    }
}