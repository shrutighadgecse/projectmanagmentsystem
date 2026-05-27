package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order_report")
public class PurchaseOrderReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recordNo;
    private LocalDateTime timestamp;
    private String ccn;
    private String salesPerson;
    private String poReferenceNumber;
    private String poDate;
    private String poDeliveryDate;
    private Double poValueLacs;
    private String customerName;
    private String machineName;
    private String modelName;
    private String machineCategory;
    private String orderType;
    private String country;
    private String state;
    private String city;
    private String addressLine1;
    private String addressLine2;
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRecordNo() { return recordNo; }
    public void setRecordNo(String recordNo) { this.recordNo = recordNo; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getCcn() { return ccn; }
    public void setCcn(String ccn) { this.ccn = ccn; }
    public String getSalesPerson() { return salesPerson; }
    public void setSalesPerson(String salesPerson) { this.salesPerson = salesPerson; }
    public String getPoReferenceNumber() { return poReferenceNumber; }
    public void setPoReferenceNumber(String poReferenceNumber) { this.poReferenceNumber = poReferenceNumber; }
    public String getPoDate() { return poDate; }
    public void setPoDate(String poDate) { this.poDate = poDate; }
    public String getPoDeliveryDate() { return poDeliveryDate; }
    public void setPoDeliveryDate(String poDeliveryDate) { this.poDeliveryDate = poDeliveryDate; }
    public Double getPoValueLacs() { return poValueLacs; }
    public void setPoValueLacs(Double poValueLacs) { this.poValueLacs = poValueLacs; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getMachineName() { return machineName; }
    public void setMachineName(String machineName) { this.machineName = machineName; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public String getMachineCategory() { return machineCategory; }
    public void setMachineCategory(String machineCategory) { this.machineCategory = machineCategory; }
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
}