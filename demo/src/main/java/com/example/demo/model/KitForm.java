package com.example.demo.model;
import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

  @Entity
public class KitForm {
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
        private LocalDateTime completionDate; // Add this field

    // Getters and Setters for completionDate
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private String dateTime; // or LocalDateTime if you're using that

    // Getter and Setter for dateTime
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Date getInwardDate() {
        return inwardDate;
    }
    public void setInwardDate(Date inwardDate) {
        this.inwardDate = inwardDate;
    }
    private Date inwardDate;
   
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

  
    

 
   
}