package com.example.demo.dto;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

  @Entity
public class KitFormRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String referenceId;
    private String blcPo;
    private String blcPoDate;  
    private String ccn;                                                                                                                
    private String assemblyCode;
    private String stage;
    private String machineName;
    private String status;
    private String inwardBy;
    private String inwardQty;
    private String description;
    private Date dateTime;
    
    // Add appropriate getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBlcPo() {
        return blcPo;
    }

    public void setBlcPo(String blcPo) {
        this.blcPo = blcPo;
    }
    public String getBlcPoDate() {
        return blcPoDate;
    }
    public void setBlcPoDate(String blcPoDate) {
        this.blcPoDate = blcPoDate;
    }
    public String getAssemblyCode() {
        return assemblyCode;
    }
    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getMachineName() {
        return machineName;
    }
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCcn() {
        return ccn;
    }
    public void setCcn(String ccn) {
        this.ccn = ccn;
    }
    public String getInwardQty() {
        return inwardQty;
    }
    public void setInwardQty(String inwardQty) {
        this.inwardQty = inwardQty;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    public String getInwardBy() {
        return inwardBy;
    }
    public void setInwardBy(String inwardBy) {
        this.inwardBy = inwardBy;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}