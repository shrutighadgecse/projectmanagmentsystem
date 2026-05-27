package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DropdownService2;

@RestController
@RequestMapping("/api/dropdowns")
public class DropdownController2 {

    @Autowired
    private DropdownService2 dropdownService2;

    @GetMapping("/ccn/option")
    public ResponseEntity<List<String>> getCcnOptions() {
        return ResponseEntity.ok(dropdownService2.getCcnOptions());
    }

    @PostMapping("/ccn/add")
    public ResponseEntity<String> addCcnOption(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name != null && !name.trim().isEmpty()) {
            dropdownService2.addCcnOption(name);
            return ResponseEntity.ok("Ccn option added");
        }
        return ResponseEntity.badRequest().body("Invalid Ccn name");
    }
   

    @GetMapping("/inward-by/option")
    public ResponseEntity<List<String>> getInwardByOptions() {
        return ResponseEntity.ok(dropdownService2.getInwardByOptions());
    }

    @PostMapping("/inward-by/add")
    public ResponseEntity<String> addInwardByOption(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name != null && !name.trim().isEmpty()) {
            dropdownService2.addInwardByOption(name);
            return ResponseEntity.ok("InwardBy option added");
        }
        return ResponseEntity.badRequest().body("Invalid InwardBy name");
    }
}

