package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.BLCPOService;

import java.util.List;

@RestController
@RequestMapping("/api/blcpo")
public class BLCPOController {

    @Autowired
    private BLCPOService blcpoService;

    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
        List<String> suggestions = blcpoService.findSuggestions(query);
        return ResponseEntity.ok(suggestions);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveBLCPO(@RequestBody String blcPo) {
        try {
            blcpoService.saveBLCPO(blcPo);
            return ResponseEntity.ok("BLC_PO saved successfully!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate value not allowed!");
        }
    }
}

