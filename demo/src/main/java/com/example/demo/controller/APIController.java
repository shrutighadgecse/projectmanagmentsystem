package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class APIController {

    private int sequence = 1; // Sequence for reference number

    @GetMapping("/api/reference")
    public String getReferenceNumber() {
        String year = new SimpleDateFormat("yyyy").format(new Date());
        String referenceNumber = "SAK-%02d-%s-%02d".formatted(sequence, year, sequence);
        sequence++;
        return referenceNumber;
    }
    
    @GetMapping("/api/date")
    public String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
