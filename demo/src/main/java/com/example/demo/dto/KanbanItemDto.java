package com.example.demo.dto;

public class KanbanItemDto {
    private String assemblyCode;
    private String machineName;
    private String assemblyLocation;
    private String cupboard;
    private int openingStock;
    private int reorderLevel;
    private int reorderQty;
    private String description;
    private String stage;
    private String  inwardLocation;



    public String getInwardLocation() {
        return inwardLocation;
    }

    public void setInwardLocation(String inwardLocation) {
        this.inwardLocation = inwardLocation;
    }

    // Getters and Setters
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

    public String getAssemblyLocation() {
        return assemblyLocation;
    }

    public void setAssemblyLocation(String assemblyLocation) {
        this.assemblyLocation = assemblyLocation;
    }

    public String getCupboard() {
        return cupboard;
    }

    public void setCupboard(String cupboard) {
        this.cupboard = cupboard;
    }

    public int getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(int openingStock) {
        this.openingStock = openingStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(int reorderQty) {
        this.reorderQty = reorderQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

  
}

