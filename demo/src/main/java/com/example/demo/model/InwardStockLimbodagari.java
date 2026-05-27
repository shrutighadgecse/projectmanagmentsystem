package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "inward_stock_limbodagari", uniqueConstraints = {
    @UniqueConstraint(columnNames = "referenceId")
})
public class InwardStockLimbodagari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referenceId;

    @Column(nullable = false)
    private String assemblyCode;

    @Column(nullable = false)
    private String inwardReceiptNumber;

    @Column(nullable = false)
    private String receiptDate;


   

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

    public String getInwardReceiptNumber() {
        return inwardReceiptNumber;
    }

    public void setInwardReceiptNumber(String inwardReceiptNumber) {
        this.inwardReceiptNumber = inwardReceiptNumber;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }
}