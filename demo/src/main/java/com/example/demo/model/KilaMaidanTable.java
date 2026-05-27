package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "secondary_kanbanmasterdata")
public class KilaMaidanTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String machineName;
 
    private String lmLocation;
    private String assemblyCode;


    private String stage;
  

    private String inward_location;
    
 
    private Integer opening_stock;

    private Integer opening_stock_km;

    private Integer reorder_level;
    
 

    private Integer reorder_qty;
    
    private Integer stockInward;
     private Integer stockRelease;
   


     private Integer stockTransit;
    

    public String getLmLocation() {
        return lmLocation;
    }

    public void setLmLocation(String lmLocation) {
        this.lmLocation = lmLocation;
    }


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
    public Integer getOpening_stock() {
        return opening_stock;
    }

    public void setOpening_stock(Integer opening_stock) {
        this.opening_stock = opening_stock;
    }

    public Integer getOpening_stock_km() {
        return opening_stock_km;
    }

    public void setOpening_stock_km(Integer opening_stock_km) {
        this.opening_stock_km = opening_stock_km;
    }

    public Integer getReorder_level() {
        return reorder_level;
    }

    public void setReorder_level(Integer reorder_level) {
        this.reorder_level = reorder_level;
    }

    public Integer getReorder_qty() {
        return reorder_qty;
    }

    public void setReorder_qty(Integer reorder_qty) {
        this.reorder_qty = reorder_qty;
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
    public Integer getStockTransit() {
        return stockTransit;
    }

     public void setStockTransit(Integer stockTransit) {
         this.stockTransit = stockTransit;
     }


     private Integer closingStock;

    public Integer getClosingStock() {
        return closingStock;
    }

     public void setClosingStock(Integer closingStock) {
         this.closingStock = closingStock;
     }

     public Integer calculateClosingStock() {
        int openingStockKM = this.opening_stock_km != null ? this.opening_stock_km : 0;
        int inwardStock = this.stockInward != null ? this.stockInward : 0;
        int stockRelease = this.stockRelease != null ? this.stockRelease : 0;
    
        return openingStockKM + inwardStock - stockRelease;
    }

    public Integer getStockInward() {
        return stockInward;
    }

    public void setStockInward(Integer stockInward) {
        this.stockInward = stockInward;
    }


    public Integer getStockRelease() {
        return stockRelease;
    }

     public void setStockRelease(Integer stockRelease) {
         this.stockRelease = stockRelease;
     }
       

    // Getters and setters for createdAt
 
}
