package com.example.demo.dto;


public class MonthlyCountDto {
    private int month;
    private long inProcessCount;
    private long shortfallCount;
    private long completeCount;

    // Constructor
    public MonthlyCountDto(int month, long inProcessCount, long shortfallCount, long completeCount) {
        this.month = month;
        this.inProcessCount = inProcessCount;
        this.shortfallCount = shortfallCount;
        this.completeCount = completeCount;
    }

    // Getters and Setters
    public int getMonth() {
        return month;
    }

    public long getInProcessCount() {
        return inProcessCount;
    }

    public long getShortfallCount() {
        return shortfallCount;
    }

    public long getCompleteCount() {
        return completeCount;
    }
}