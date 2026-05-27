package com.example.demo.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bhoomika_data")
public class BhoomikaForm {
 @Id
  
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(unique = true)
        private String reference;
        @Column
private LocalDateTime currentDateTime; // Add this line
        private LocalDate kitFormDate;
        private String blcPo;
        private LocalDate blcPoDate;
        private String ccn;
        private String machineName;
        private String assemblyCode;
        private String inwardBy;
        private int inwardQty;
        private String stage;
        private LocalDate kitRecordDate;
        private String description;
    
        private String kitAcknowledgeBy;
        private LocalDate kitAcknowledgeDate;
        private LocalDate assyStartDate;
        private String bhomikaEngineer;
        private LocalDate assyEndDate;

        private String liveStatus;
        private String blcStatus;
        private String invoiceNumber;
        private LocalDate invoiceDate;
    
// Ensure that the database schema has the necessary columns for holdRemark, holdDate, closeRemark, and closeDate
@Column
private String holdRemark;

@Column
private String holdDate;

@Column
private String closeRemark;

@Column
private String closeDate;

      
        @Column
        private String kmLocation;
        
        @Column
        private String limbodagriLocation;
       
        @Column(columnDefinition = "TEXT")
        private String qrCode;

        // Add getter and setter
        // Getters and Setters
        public String getKmLocation() {
            return kmLocation;
        }
        public void setKmLocation(String kmLocation) {
            this.kmLocation = kmLocation;
        }
        
        public String getLimbodagriLocation() {
            return limbodagriLocation;
        }
        public void setLimbodagriLocation(String limbodagriLocation) {
            this.limbodagriLocation = limbodagriLocation;
        }

        public String getGrnNumber() {
            return grnNumber;
        }
        private String grnNumber;
        public LocalDate getGrnDate() {
            return grnDate;
        }
        private LocalDate grnDate;
        
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getReference() {
            return reference;
        }
        public void setReference(String reference) {
            this.reference = reference;
        }
        public LocalDate getKitFormDate() {
            return kitFormDate;
        }
        public void setKitFormDate(LocalDate kitFormDate) {
            this.kitFormDate = kitFormDate;
        }
        public String getBlcPo() {
            return blcPo;
        }
        public void setBlcPo(String blcPo) {
            this.blcPo = blcPo;
        }
        public LocalDate getBlcPoDate() {
            return blcPoDate;
        }
        public void setBlcPoDate(LocalDate blcPoDate) {
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
        public String getInwardBy() {
            return inwardBy;
        }
        public void setInwardBy(String inwardBy) {
            this.inwardBy = inwardBy;
        }
        public int getInwardQty() {
            return inwardQty;
        }
        public void setInwardQty(int inwardQty) {
            this.inwardQty = inwardQty;
        }
        public String getStage() {
            return stage;
        }
        public void setStage(String stage) {
            this.stage = stage;
        }
        public LocalDate getKitRecordDate() {
            return kitRecordDate;
        }
        public void setKitRecordDate(LocalDate kitRecordDate) {
            this.kitRecordDate = kitRecordDate;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getKitAcknowledgeBy() {
            return kitAcknowledgeBy;
        }
        public void setKitAcknowledgeBy(String kitAcknowledgeBy) {
            this.kitAcknowledgeBy = kitAcknowledgeBy;
        }
        public LocalDate getKitAcknowledgeDate() {
            return kitAcknowledgeDate;
        }
        public void setKitAcknowledgeDate(LocalDate kitAcknowledgeDate) {
            this.kitAcknowledgeDate = kitAcknowledgeDate;
        }
        public LocalDate getAssyStartDate() {
            return assyStartDate;
        }
        public void setAssyStartDate(LocalDate assyStartDate) {
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

        public LocalDate getAssyEndDate() {
            return assyEndDate;
        }
        public void setAssyEndDate(LocalDate assyEndDate) {
            this.assyEndDate = assyEndDate;
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
        public LocalDate getInvoiceDate() {
            return invoiceDate;
        }
        public void setInvoiceDate(LocalDate invoiceDate) {
            this.invoiceDate = invoiceDate;
        }
       
        public void setGrnNumber(String grnNumber) {
            throw new UnsupportedOperationException("Unimplemented method 'setGrnNumber'");
        }
        public void setGrnDate(LocalDate grnDate) {
            throw new UnsupportedOperationException("Unimplemented method 'setGrnDate'");
        }
        public LocalDateTime getCurrentDateTime() {
            return currentDateTime;
        }
        public void setCurrentDateTime(LocalDateTime currentDateTime) {
            this.currentDateTime = currentDateTime;
        }
        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
        // Getters and Setters
        public String getHoldRemark() {
            return holdRemark;
        }
        public void setHoldRemark(String holdRemark) {
            this.holdRemark = holdRemark;
        }
        public String getHoldDate() {
            return holdDate;
        }
        public void setHoldDate(String holdDate) {
            this.holdDate = holdDate;
        }
        public String getCloseDate() {
            return closeDate;
        }
        public void setCloseDate(String closeDate) {
            this.closeDate = closeDate;
        } public String getCloseRemark() {
            return closeRemark;
        }
        public void setCloseRemark(String closeRemark) {
            this.closeRemark = closeRemark;
        }
    
    }