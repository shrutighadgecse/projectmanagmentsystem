package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status")
public class BhoomikaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String reference;
    private String blcPo;
    private String blcPoDate;
    private String ccn;
    private String machineName;
    private String assemblyCode;
    private int inwardQty;
    private String inwardBy;
    private String stage;
    private String kitRecordDate;
    private String kitAcknowledgeBy;
    private String kitAcknowledgeDate;
    private String assyStartDate;
    private String bhomikaEngineer;
    private String liveStatus;
    private String blcStatus;
    private String invoiceNumber;
    private String invoiceDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
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
    public String getCcn() {
        return ccn;
    }
    public void setCcn(String ccn) {
        this.ccn = ccn;
    }
    public String getMachineName() {
        return machineName;
    }
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getAssemblyCode() {
        return assemblyCode;
    }
    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
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
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getKitRecordDate() {
        return kitRecordDate;
    }
    public void setKitRecordDate(String kitRecordDate) {
        this.kitRecordDate = kitRecordDate;
    }
    public String getKitAcknowledgeBy() {
        return kitAcknowledgeBy;
    }
    public void setKitAcknowledgeBy(String kitAcknowledgeBy) {
        this.kitAcknowledgeBy = kitAcknowledgeBy;
    }
    public String getKitAcknowledgeDate() {
        return kitAcknowledgeDate;
    }
    public void setKitAcknowledgeDate(String kitAcknowledgeDate) {
        this.kitAcknowledgeDate = kitAcknowledgeDate;
    }
    public String getAssyStartDate() {
        return assyStartDate;
    }
    public void setAssyStartDate(String assyStartDate) {
        this.assyStartDate = assyStartDate;
    }
    public String getBhomikaEngineer() {
        return bhomikaEngineer;
    }
    public void setBhomikaEngineer(String bhomikaEngineer) {
        this.bhomikaEngineer = bhomikaEngineer;
    }
    public String getLiveStatus() {
        return liveStatus;
    }
    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }
    public String getBlcStatus() {
        return blcStatus;
    }
    public void setBlcStatus(String blcStatus) {
        this.blcStatus = blcStatus;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    // Getters and Setters
}