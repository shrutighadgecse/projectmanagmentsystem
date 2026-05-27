package com.example.demo.dto;

import java.time.LocalDate;

public class HoldData {

    private String holdReason;
    private LocalDate holdDate;
    private String holdRemark;
    public String getHoldReason() {
        return holdReason;
    }
    public void setHoldReason(String holdReason) {
        this.holdReason = holdReason;
    }
    public LocalDate getHoldDate() {
        return holdDate;
    }
    public void setHoldDate(LocalDate holdDate) {
        this.holdDate = holdDate;
    }
    public String getHoldRemark() {
        return holdRemark;
    }
    public void setHoldRemark(String holdRemark) {
        this.holdRemark = holdRemark;
    }

    // Getters and Setters
}