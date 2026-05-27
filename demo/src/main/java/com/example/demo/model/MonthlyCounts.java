package com.example.demo.model;


import java.util.HashMap;
import java.util.Map;

public class MonthlyCounts {
    private Map<String, Map<String, Integer>> counts = new HashMap<>();

    public Map<String, Map<String, Integer>> getCounts() {
        return counts;
    }

    public void setCounts(Map<String, Map<String, Integer>> counts) {
        this.counts = counts;
    }
}