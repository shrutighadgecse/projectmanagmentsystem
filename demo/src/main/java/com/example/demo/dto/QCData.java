package com.example.demo.dto;

import java.time.LocalDate;

public class QCData {

    
    private String qcAction;
    private LocalDate qcCloseDate;
    private String qcCloseRemark;
    public String getQcAction() {
        return qcAction;
    }
    public void setQcAction(String qcAction) {
        this.qcAction = qcAction;
    }
    public LocalDate getQcCloseDate() {
        return qcCloseDate;
    }
    public void setQcCloseDate(LocalDate qcCloseDate) {
        this.qcCloseDate = qcCloseDate;
    }
    public String getQcCloseRemark() {
        return qcCloseRemark;
    }
    public void setQcCloseRemark(String qcCloseRemark) {
        this.qcCloseRemark = qcCloseRemark;
    }

    // Getters and Setters
}