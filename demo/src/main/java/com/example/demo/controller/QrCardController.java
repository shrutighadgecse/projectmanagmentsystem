package com.example.demo.controller;

import com.example.demo.model.QrCard;
import com.example.demo.service.QrCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qr-cards")
public class QrCardController {
    @Autowired
    private QrCardService qrCardService;

    @PostMapping
    public QrCard saveQrCard(@RequestBody QrCard qrCard) {
        return qrCardService.saveQrCard(qrCard);
    }

    @GetMapping
    public List<QrCard> getAllQrCards() {
        return qrCardService.getAllQrCards();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferQrCards(@RequestBody List<String> referenceIds) {
        qrCardService.transferQrCards(referenceIds);
        return ResponseEntity.ok().build();
    }
}