package com.example.demo.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KitInwardForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String blcPo;
    private Date blcPoDate;
    private String assemblyCode;
    private String stage;
    private String machineName;
    private String ccn;
    private String inwardstatus;
    private int inwardQty;
    private String inwardBy;
    private String description;
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
    public Date getBlcPoDate() {
        return blcPoDate;
    }
    public void setBlcPoDate(Date blcPoDate) {
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
    public String getCcn() {
        return ccn;
    }
    public void setCcn(String ccn) {
        this.ccn = ccn;
    }
    public String getInwardstatus() {
        return inwardstatus;
    }
    public void setInwardstatus(String inwardstatus) {
        this.inwardstatus = inwardstatus;
    }
    public int getInwardQty() {
        return inwardQty;
    }
    public void setInwardQty(int inwardQty) {
        this.inwardQty = inwardQty;
    }
    public String getInwardBy() {
        return inwardBy;
    }
    public void setInwardBy(String inwardBy) {
        this.inwardBy = inwardBy;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters
}


