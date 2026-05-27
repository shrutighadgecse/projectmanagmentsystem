package com.example.demo.service;

import com.example.demo.model.QrCard;
import com.example.demo.model.StockTransferKila;
import com.example.demo.repository.QrCardRepository;
import com.example.demo.repository.StockTransferKilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QrCardService {
    
    @Autowired
    private QrCardRepository qrCardRepository;

    @Autowired
    private StockTransferKilaRepository stockTransferKilaRepository;

    // Method to transfer QR cards based on reference IDs
    public void transferQrCards(List<String> referenceIds) {
        // Fetch QR cards by their reference IDs
        List<QrCard> qrCards = qrCardRepository.findByReferenceIdIn(referenceIds);
        
        // Create a list to hold stock transfer records
        List<StockTransferKila> stockTransfers = new ArrayList<>();

        // Populate the stock transfer records from the fetched QR cards
        for (QrCard qrCard : qrCards) {
            StockTransferKila stockTransfer = new StockTransferKila();
            stockTransfer.setReferenceId(qrCard.getReferenceId());
            stockTransfer.setAssemblyCode(qrCard.getAssemblyCode());
            stockTransfer.setDescription(qrCard.getDescription());
            stockTransfer.setMachineName(qrCard.getMachineName());
            stockTransfer.setStage(qrCard.getStage());
            stockTransfer.setLimbodagriLocation(qrCard.getLimbodagriLocation());
            stockTransfer.setKmLocation(qrCard.getKmLocation());
            stockTransfers.add(stockTransfer);
        }

        // Save all stock transfer records to the database
        stockTransferKilaRepository.saveAll(stockTransfers);

        // Delete the transferred QR cards from the original repository
        qrCardRepository.deleteAll(qrCards);
    }

    // Method to retrieve all QR cards
    public List<QrCard> getAllQrCards() {
        return qrCardRepository.findAll();
    }

    public QrCard saveQrCard(QrCard qrCard) {
        return qrCardRepository.save(qrCard);
    }
}