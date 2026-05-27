package com.example.demo.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StockTransferKila;
import com.example.demo.model.QrCard;
import com.example.demo.repository.StockTransferKilaRepository;
import com.example.demo.repository.QrCardRepository;

@Service
public class StockTransferKilaService {
    @Autowired
    private StockTransferKilaRepository stockTransferRepository;

    @Autowired
    private QrCardRepository qrCardRepository;

    public List<StockTransferKila> getAllStockTransfers() {
        return stockTransferRepository.findAll();
    }

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
        stockTransferRepository.saveAll(stockTransfers);

        // Delete the transferred QR cards from the original repository
        qrCardRepository.deleteAll(qrCards);
    }
}
