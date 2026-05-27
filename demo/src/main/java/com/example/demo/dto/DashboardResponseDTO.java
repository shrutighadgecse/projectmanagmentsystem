package com.example.demo.dto;

import java.util.Map;

public class DashboardResponseDTO {
    private String filterYear;
    private Integer selectedYear;
    private long liveStockCount;
    private long liveCustomizedCount;
    private long expectedOrdersCount;
    private long ifatDoneCount;
    private long cfatDoneCount;
    private long awaitingClearanceCount;
    private String totalPoValueLacs;
    private Double avgBuildTimeDays;
    private Map<String, Object> amounts;

    public String getFilterYear() {
        return filterYear;
    }

    public void setFilterYear(String filterYear) {
        this.filterYear = filterYear;
    }

    public Integer getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    public long getLiveStockCount() {
        return liveStockCount;
    }

    public void setLiveStockCount(long liveStockCount) {
        this.liveStockCount = liveStockCount;
    }

    public long getLiveCustomizedCount() {
        return liveCustomizedCount;
    }

    public void setLiveCustomizedCount(long liveCustomizedCount) {
        this.liveCustomizedCount = liveCustomizedCount;
    }

    public long getExpectedOrdersCount() {
        return expectedOrdersCount;
    }

    public void setExpectedOrdersCount(long expectedOrdersCount) {
        this.expectedOrdersCount = expectedOrdersCount;
    }

    public long getIfatDoneCount() {
        return ifatDoneCount;
    }

    public void setIfatDoneCount(long ifatDoneCount) {
        this.ifatDoneCount = ifatDoneCount;
    }

    public long getCfatDoneCount() {
        return cfatDoneCount;
    }

    public void setCfatDoneCount(long cfatDoneCount) {
        this.cfatDoneCount = cfatDoneCount;
    }

    public long getAwaitingClearanceCount() {
        return awaitingClearanceCount;
    }

    public void setAwaitingClearanceCount(long awaitingClearanceCount) {
        this.awaitingClearanceCount = awaitingClearanceCount;
    }

    public String getTotalPoValueLacs() {
        return totalPoValueLacs;
    }

    public void setTotalPoValueLacs(String totalPoValueLacs) {
        this.totalPoValueLacs = totalPoValueLacs;
    }

    public Double getAvgBuildTimeDays() {
        return avgBuildTimeDays;
    }

    public void setAvgBuildTimeDays(Double avgBuildTimeDays) {
        this.avgBuildTimeDays = avgBuildTimeDays;
    }

    public Map<String, Object> getAmounts() {
        return amounts;
    }

    public void setAmounts(Map<String, Object> amounts) {
        this.amounts = amounts;
    }
}
