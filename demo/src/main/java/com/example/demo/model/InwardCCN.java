package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class InwardCCN {

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


    private String machineName;
    private String assyLocation;
    private String assemblyCode;


    private String stage;
  
 
    private String inwardCcn;
     private String inward_location;
    
 
    private Integer stockQty;
    
 



 


    private String description;
    
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    private String referenceId; // Field for the auto-generated reference ID

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private Date timestamp;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssemblyCode() {
        return assemblyCode;
    }

    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }

   

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getAssyLocation() {
        return assyLocation;
    }

    public void setAssyLocation(String assyLocation) {
        this.assyLocation = assyLocation;
    }

   

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getInward_location() {
        return inward_location;
    }

    public void setInward_location(String inward_location) {
        this.inward_location = inward_location;
    }   
     public String getInwardCcn() {
        return inwardCcn;
    }

    public void setInwardCcn(String inwardCcn) {
        this.inwardCcn = inwardCcn;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(String timeStamp2) {
        throw new UnsupportedOperationException("Unimplemented method 'setTimestamp'");
    }

    public String getAssemblycode() {
        throw new UnsupportedOperationException("Unimplemented method 'getAssemblycode'");
    }
   

}
