package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class MachineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String machineName;
    private String machineModel;
    private Integer totalProcessTask;
    private Integer assyStandardTime;
    private Integer newSLA;
    public BigDecimal getStandardSLA() {
        return standardSLA;
    }

    public void setStandardSLA(BigDecimal standardSLA) {
        this.standardSLA = standardSLA;
    }

    private BigDecimal  standardSLA;
    private Integer nonIssuance;
    private String machineGroup;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }

    public Integer getTotalProcessTask() {
        return totalProcessTask;
    }

    public void setTotalProcessTask(Integer totalProcessTask) {
        this.totalProcessTask = totalProcessTask;
    }

    public Integer getAssyStandardTime() {
        return assyStandardTime;
    }

    public void setAssyStandardTime(Integer assyStandardTime) {
        this.assyStandardTime = assyStandardTime;
    }

    public Integer getNewSLA() {
        return newSLA;
    }

    public void setNewSLA(Integer newSLA) {
        this.newSLA = newSLA;
    }


    public Integer getNonIssuance() {
        return nonIssuance;
    }

    public void setNonIssuance(Integer nonIssuance) {
        this.nonIssuance = nonIssuance;
    }

    public String getMachineGroup() {
        return machineGroup;
    }

    public void setMachineGroup(String machineGroup) {
        this.machineGroup = machineGroup;
    }
}