package com.example.demo.service;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repository.FormDataRepository;
import com.example.demo.repository.InwardStockLimbodagariRepository;
import com.example.demo.repository.ReferenceSequenceRepository;
import com.example.demo.repository.ReleaseToCCNStockRepository;
import com.example.demo.repository.KilaMaidanTableRepository;
import com.example.demo.repository.LimbodaGariOutputTableRepository;
import com.example.demo.repository.ScannerFormDataRepository;
import com.example.demo.repository.StockTransitRecordRepository;
import com.example.demo.repository.StockReleaseLimbodagariRepository;
import com.example.demo.model.FormData;
import com.example.demo.model.ReferenceSequence;
import com.example.demo.model.ReleaseToCCNStock;
import com.example.demo.model.KilaMaidanTable;
import com.example.demo.model.LimbodaGariOutputTable;
import com.example.demo.model.ScannerFormData;
import com.example.demo.model.StockTransitRecord;
import com.example.demo.model.StockReleaseLimbodagari;
import com.example.demo.model.InwardStockLimbodagari;

@Service
public class FormService {

    private final FormDataRepository formRepository;
    private final KilaMaidanTableRepository kilaMaidanTableRepository;
    private final LimbodaGariOutputTableRepository limbodagariOutputTableRepository;
    private final ReferenceSequenceRepository referenceSequenceRepository;
    private final ScannerFormDataRepository scannerFormDataRepository;
    private final StockTransitRecordRepository stockTransitRecordRepository;
    private final StockReleaseLimbodagariRepository stockReleaseLimbodagariRepository;
    private final InwardStockLimbodagariRepository inwardStockLimbodagariRepository;
    private final ReleaseToCCNStockRepository releaseToCCNStockRepository;
    private int currentSequence; // Current sequence number
    private String currentReferenceId; // Store the current reference ID

    public FormService(FormDataRepository formRepository, 
                       KilaMaidanTableRepository kilaMaidanTableRepository,
                       LimbodaGariOutputTableRepository limbodagariOutputTableRepository,
                       ReferenceSequenceRepository referenceSequenceRepository,
                       ScannerFormDataRepository scannerFormDataRepository,
                       StockTransitRecordRepository stockTransitRecordRepository,
                       StockReleaseLimbodagariRepository stockReleaseLimbodagariRepository,
                                                   InwardStockLimbodagariRepository inwardStockLimbodagariRepository,
                                                   ReleaseToCCNStockRepository releaseToCCNStockRepository) {
                                this.releaseToCCNStockRepository = releaseToCCNStockRepository;
        this.stockReleaseLimbodagariRepository = stockReleaseLimbodagariRepository;
        this.formRepository = formRepository;
        this.kilaMaidanTableRepository = kilaMaidanTableRepository;
        this.limbodagariOutputTableRepository = limbodagariOutputTableRepository;
        this.referenceSequenceRepository = referenceSequenceRepository;
        this.scannerFormDataRepository = scannerFormDataRepository;
        this.stockTransitRecordRepository = stockTransitRecordRepository;
        this.inwardStockLimbodagariRepository = inwardStockLimbodagariRepository;
        loadCurrentSequence(); // Load the current sequence from the database
        loadCurrentSequence(); // Load the current sequence from the database
    }
    private void loadCurrentSequence() {
        ReferenceSequence referenceSequence = referenceSequenceRepository.findById(1L).orElse(new ReferenceSequence());
        this.currentSequence = referenceSequence.getSequence();
    }

    private void saveCurrentSequence() {
        ReferenceSequence referenceSequence = new ReferenceSequence();
        referenceSequence.setSequence(currentSequence);
        referenceSequenceRepository.save(referenceSequence);
    }

    public String generateReferenceId() {
        String prefix = "IMK";
        int year = Year.now().getValue();
        currentReferenceId = "%s-%02d-%d".formatted(prefix, currentSequence, year);
        return currentReferenceId;
    }

    public void saveFormData(String assemblyCode, String machineName, 
                             String lmLocation,  String inwardLocation, 
                             String stage, String openingStock,String openingStockKM, String reorderLevel, 
                             String reorderQty, String kmLocation, String description) {
        // Save to the first table
        FormData formData = new FormData();
        formData.setReferenceId(currentReferenceId); // Use the stored reference ID
        formData.setAssemblyCode(assemblyCode);
        formData.setMachineName(machineName);
        formData.setLmLocation(lmLocation);
        formData.setInward_location(inwardLocation);
        formData.setStage(stage);
        formData.setOpening_stock(Integer.parseInt(openingStock));
        formData.setOpening_stock_km(Integer.parseInt(openingStockKM));
        formData.setReorder_level(Integer.parseInt(reorderLevel));
        formData.setReorder_qty(Integer.parseInt(reorderQty));
        formData.setKmLocation(kmLocation);
        formData.setDescription(description);
        formData.setCreatedAt(new Date()); // Set the current date and time
        formRepository.save(formData);

        // Save to the second table
        KilaMaidanTable kilaMaidanTable = new KilaMaidanTable();
        kilaMaidanTable.setReferenceId(currentReferenceId);
        kilaMaidanTable.setAssemblyCode(assemblyCode);
        kilaMaidanTable.setMachineName(machineName);
        kilaMaidanTable.setLmLocation(lmLocation);
        kilaMaidanTable.setInward_location(inwardLocation);
        kilaMaidanTable.setStage(stage);
        kilaMaidanTable.setOpening_stock(Integer.parseInt(openingStock));
        kilaMaidanTable.setOpening_stock_km(Integer.parseInt(openingStockKM));
        kilaMaidanTable.setReorder_level(Integer.parseInt(reorderLevel));
        kilaMaidanTable.setReorder_qty(Integer.parseInt(reorderQty));
        kilaMaidanTable.setDescription(description);

        kilaMaidanTableRepository.save(kilaMaidanTable);

        // Save to the third table
        LimbodaGariOutputTable limbodagariOutputTable = new LimbodaGariOutputTable();
        limbodagariOutputTable.setReferenceId(currentReferenceId);
        limbodagariOutputTable.setAssemblyCode(assemblyCode);
        limbodagariOutputTable.setMachineName(machineName);
        limbodagariOutputTable.setLmLocation(lmLocation);
        limbodagariOutputTable.setInward_location(inwardLocation);
        limbodagariOutputTable.setStage(stage);
        limbodagariOutputTable.setOpening_stock(Integer.parseInt(openingStock));
        limbodagariOutputTable.setOpening_stock_km(Integer.parseInt(openingStockKM));
        limbodagariOutputTable.setReorder_level(Integer.parseInt(reorderLevel));
        limbodagariOutputTable.setReorder_qty(Integer.parseInt(reorderQty));
        limbodagariOutputTable.setKmLocation(kmLocation);
        limbodagariOutputTable.setDescription(description);

        limbodagariOutputTableRepository.save(limbodagariOutputTable);

        // Increment the sequence only after a successful save
        currentSequence++;
        saveCurrentSequence(); // Save the updated sequence to the database
    }
        // Check if assembly code exists
    public boolean assemblyCodeExists(String assemblyCode) {
        return formRepository.findByAssemblyCode(assemblyCode).isPresent();
    }

    // Get suggestions for assembly codes
    public List<String> getAssemblyCodeSuggestions(String prefix) {
        List<FormData> suggestions = formRepository.findByAssemblyCodeStartingWith(prefix);
        return suggestions.stream().map(FormData::getAssemblyCode).collect(Collectors.toList());
    }

    public String getCurrentReferenceId() {
        return currentReferenceId;
    }

    public void processFormData(FormData formData) {
        throw new UnsupportedOperationException("Unimplemented method 'processFormData'");
    }

    public int getCurrentCounter() {
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentCounter'");
    }

    public void updateCounter(int referenceCounter2) {
        throw new UnsupportedOperationException("Unimplemented method 'updateCounter'");
    }

 

    public List<Map<String, Object>> getKilaMaidanData() {
        return kilaMaidanTableRepository.findAll().stream().map(kilaMaidan -> {
            Map<String, Object> data = new HashMap<>();
            data.put("assemblyCode", kilaMaidan.getAssemblyCode());
            data.put("description", kilaMaidan.getDescription());
            data.put("machineName", kilaMaidan.getMachineName());
            data.put("lmLocation", kilaMaidan.getLmLocation());
            data.put("inwardLocation", kilaMaidan.getInward_location());
            data.put("stage", kilaMaidan.getStage());
            data.put("openingStock", kilaMaidan.getOpening_stock());
            data.put("openingStockKM", kilaMaidan.getOpening_stock_km());
            data.put("stockInward", kilaMaidan.getStockInward());
            data.put("stockTransit", kilaMaidan.getStockTransit()); // Include stock transit
            data.put("stockRelease", kilaMaidan.getStockRelease());
// Calculate closing stock
        int closingStock = kilaMaidan.calculateClosingStock();
        data.put("closingStock", closingStock);
            return data;
        }).collect(Collectors.toList());
    }


    public List<Map<String, Object>> getLimbodagariData() {
        return limbodagariOutputTableRepository.findAll().stream().map(limbodagari -> {
            Map<String, Object> data = new HashMap<>();
            data.put("assemblyCode", limbodagari.getAssemblyCode());
            data.put("description", limbodagari.getDescription());
            data.put("machineName", limbodagari.getMachineName());
            data.put("lmLocation", limbodagari.getLmLocation());
            data.put("kmLocation", limbodagari.getKmLocation());
            data.put("inwardLocation", limbodagari.getInward_location());
            data.put("stage", limbodagari.getStage());
            data.put("openingStock", limbodagari.getOpening_stock());
            data.put("openingStockKM", limbodagari.getOpening_stock_km());
            data.put("reorderLevel", limbodagari.getReorder_level());
            data.put("stockInward", limbodagari.getStockInward());
            data.put("stockTransit", limbodagari.getStockTransit()); // Include stock transit
            data.put("stockRelease", limbodagari.getStockRelease());

        // Calculate closing stock
        int closingStock = limbodagari.calculateClosingStock();
        data.put("closingStock", closingStock);
            return data;

        }).collect(Collectors.toList());
    }

    public boolean updateStockInward(String assemblyCode) {
        try {
            System.out.println("Checking assembly code: " + assemblyCode); // Debug log

            Optional<KilaMaidanTable> optionalKilaMaidan = kilaMaidanTableRepository.findByAssemblyCode(assemblyCode);

            if (optionalKilaMaidan.isPresent()) {
                KilaMaidanTable kilaMaidan = optionalKilaMaidan.get();
                Integer currentStockInward = kilaMaidan.getStockInward();
                if (currentStockInward == null) {
                    currentStockInward = 0; // Initialize to 0 if null
                }
                System.out.println("Current Stock Inward: " + currentStockInward); // Debug log

                kilaMaidan.setStockInward(currentStockInward + 1); // Increment stock inward
                kilaMaidanTableRepository.save(kilaMaidan); // Save updated row

                System.out.println("Stock Inward updated successfully for assembly code: " + assemblyCode); // Debug log
                return true;
            } else {
                System.out.println("Assembly code not found: " + assemblyCode); // Debug log
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return false; // Assembly code not found or error occurred
    }

    public boolean updateStockTransfer(String referenceId, String assemblyCode) {
        try {
            Optional<KilaMaidanTable> optionalKilaMaidan = kilaMaidanTableRepository.findByAssemblyCode(assemblyCode);
            if (optionalKilaMaidan.isPresent()) {
                KilaMaidanTable kilaMaidan = optionalKilaMaidan.get();
                Integer currentStockInward = kilaMaidan.getStockInward();
                Integer currentStockTransit = kilaMaidan.getStockTransit();
    
                if (currentStockInward != null && currentStockInward > 0) {
                    kilaMaidan.setStockInward(currentStockInward - 1); // Decrement stock inward
                    kilaMaidan.setStockTransit(currentStockTransit != null ? currentStockTransit + 1 : 1); // Increment stock transit
                    kilaMaidanTableRepository.save(kilaMaidan); // Save updated row
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Assembly code not found or error occurred
    }


    public boolean updateStockInwardToLimbodagari(String assemblyCode) {
        try {
            System.out.println("Checking assembly code: " + assemblyCode); // Debug log

            Optional<LimbodaGariOutputTable> optionalLimbodagari = limbodagariOutputTableRepository.findByAssemblyCode(assemblyCode);

            if (optionalLimbodagari.isPresent()) {
                LimbodaGariOutputTable limbodaGariOutputTable = optionalLimbodagari.get();
                Integer currentStockInward = limbodaGariOutputTable.getStockInward();
                if (currentStockInward == null) {
                    currentStockInward = 0; // Initialize to 0 if null
                }
                System.out.println("Current Stock Inward: " + currentStockInward); // Debug log

                limbodaGariOutputTable.setStockInward(currentStockInward + 1); // Increment stock inward
                limbodagariOutputTableRepository.save(limbodaGariOutputTable); // Save updated row

                System.out.println("Stock Inward updated successfully for assembly code: " + assemblyCode); // Debug log
                return true;
            } else {
                System.out.println("Assembly code not found: " + assemblyCode); // Debug log
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return false; // Assembly code not found or error occurred
    }

    public boolean saveScannerFormData(String referenceId, String assemblyCode,String kmLocation, String grnNumber, String grnDate) {
        if (scannerFormDataRepository.findByReferenceId(referenceId).isPresent()) {
            return false; // Duplicate referenceId, do not save
        }

        ScannerFormData scannerFormData = new ScannerFormData();
        scannerFormData.setReferenceId(referenceId);
        scannerFormData.setAssemblyCode(assemblyCode);
        scannerFormData.setKmLocation(kmLocation);
        scannerFormData.setGrnNumber(grnNumber);
        scannerFormData.setGrnDate(grnDate);
        scannerFormDataRepository.save(scannerFormData);

        Optional<KilaMaidanTable> optionalKilaMaidan = kilaMaidanTableRepository.findByAssemblyCode(assemblyCode);
        if (optionalKilaMaidan.isPresent()) {
            KilaMaidanTable kilaMaidan = optionalKilaMaidan.get();
            Integer currentStockInward = kilaMaidan.getStockInward();
            if (currentStockInward == null) {
                currentStockInward = 0;
            }
            kilaMaidan.setStockInward(currentStockInward + 1);
            kilaMaidanTableRepository.save(kilaMaidan);
        }

        return true; // Successfully saved
    }

    public boolean saveStockTransitRecord(String referenceId, String assemblyCode) {
        if (stockTransitRecordRepository.existsByReferenceId(referenceId)) {
            return false; // Duplicate referenceId, do not save
        }

        // Remove from scanner form data table
        Optional<ScannerFormData> optionalScannerFormData = scannerFormDataRepository.findByReferenceId(referenceId);
        if (optionalScannerFormData.isPresent()) {
            scannerFormDataRepository.delete(optionalScannerFormData.get()); // Remove the record
        } else {
            return false; // Reference ID not found in scanner form data table
        }

        // Update stock inward and stock transit in Kila Maidan table
        Optional<KilaMaidanTable> optionalKilaMaidan = kilaMaidanTableRepository.findByAssemblyCode(assemblyCode);
        if (optionalKilaMaidan.isPresent()) {
            KilaMaidanTable kilaMaidan = optionalKilaMaidan.get();

            Integer currentStockInward = kilaMaidan.getStockInward();
            Integer currentStockTransit = kilaMaidan.getStockTransit();

            if (currentStockInward != null && currentStockInward > 0) {
                kilaMaidan.setStockInward(currentStockInward - 1); // Decrement stock inward
            } else {
                return false; // No stock inward to decrement
            }

            kilaMaidan.setStockTransit(currentStockTransit != null ? currentStockTransit + 1 : 1); // Increment stock transit
            kilaMaidanTableRepository.save(kilaMaidan); // Save updated row
        } else {
            return false; // Assembly code not found in Kila Maidan table
        }

        // Save to stock transit table
        StockTransitRecord stockTransitRecord = new StockTransitRecord();
        stockTransitRecord.setReferenceId(referenceId);
        stockTransitRecord.setAssemblyCode(assemblyCode);
        stockTransitRecord.setDate(new Date().toString()); // Set the current 
        stockTransitRecordRepository.save(stockTransitRecord);
        

        return true; // Successfully saved
    }

    @Transactional
    public boolean saveLimbodagariScannerForm(String referenceId, String assemblyCode, String inwardReceiptNumber, String receiptDate) {
        if (inwardStockLimbodagariRepository.findByReferenceId(referenceId).isPresent()) {
            return false; // Duplicate referenceId, do not save
        }

        InwardStockLimbodagari inwardStock = new InwardStockLimbodagari();
        inwardStock.setReferenceId(referenceId);
        inwardStock.setAssemblyCode(assemblyCode);
        inwardStock.setInwardReceiptNumber(inwardReceiptNumber);
        inwardStock.setReceiptDate(receiptDate);
        inwardStockLimbodagariRepository.save(inwardStock);

        Optional<KilaMaidanTable> optionalKilaMaidan = kilaMaidanTableRepository.findByAssemblyCode(assemblyCode);
        if (optionalKilaMaidan.isPresent()) {
            KilaMaidanTable kilaMaidan = optionalKilaMaidan.get();
            Integer currentStockTransit = kilaMaidan.getStockTransit();
            Integer currentStockRelease = kilaMaidan.getStockRelease();

            if (currentStockTransit != null && currentStockTransit > 0) {
                kilaMaidan.setStockTransit(currentStockTransit - 1); // Decrement stock transit
                kilaMaidan.setStockRelease(currentStockRelease != null ? currentStockRelease + 1 : 1); // Increment stock release
                kilaMaidanTableRepository.save(kilaMaidan);
            }
        }

        StockReleaseLimbodagari stockRelease = new StockReleaseLimbodagari();
        stockRelease.setReferenceId(referenceId);
        stockRelease.setAssemblyCode(assemblyCode);
        stockRelease.setReleaseDate(receiptDate);
        stockReleaseLimbodagariRepository.save(stockRelease);

        // Remove the record from Stock Transit table
        stockTransitRecordRepository.deleteByReferenceId(referenceId);

        return true; // Successfully saved
    }

    public List<StockReleaseLimbodagari> getStockReleaseRecords(String assemblyCode) {
        return stockReleaseLimbodagariRepository.findByAssemblyCode(assemblyCode);
    }

@Transactional
public boolean saveReleaseToCcn(String referenceId, String assemblyCode, String ccn, String releaseDate) {
    // Check if the reference ID already exists in the ReleaseToCCNStock table
    if (releaseToCCNStockRepository.existsByReferenceId(referenceId)) {
        return false; // Duplicate referenceId, do not save
    }

    // Remove the record from InwardStockLimbodagari table
    Optional<InwardStockLimbodagari> optionalInwardStock = inwardStockLimbodagariRepository.findByReferenceId(referenceId);
    if (optionalInwardStock.isPresent()) {
        inwardStockLimbodagariRepository.delete(optionalInwardStock.get());
    } else {
        return false; // Reference ID not found in InwardStockLimbodagari table
    }

    // Save the record to the ReleaseToCCNStock table
    ReleaseToCCNStock releaseToCCNStock = new ReleaseToCCNStock();
    releaseToCCNStock.setReferenceId(referenceId);
    releaseToCCNStock.setAssemblyCode(assemblyCode);
    releaseToCCNStock.setCcn(ccn);
    releaseToCCNStock.setReleaseDate(releaseDate);
    releaseToCCNStockRepository.save(releaseToCCNStock);

    // Update stock quantities in LimbodagariOutputTable
    Optional<LimbodaGariOutputTable> optionalLimbodagari = limbodagariOutputTableRepository.findByAssemblyCode(assemblyCode);
    if (optionalLimbodagari.isPresent()) {
        LimbodaGariOutputTable limbodagari = optionalLimbodagari.get();
        Integer currentInwardQty = limbodagari.getStockInward();
        Integer currentReleaseQty = limbodagari.getStockRelease();

        if (currentInwardQty != null && currentInwardQty > 0) {
            limbodagari.setStockInward(currentInwardQty - 1); // Decrement inward stock
        } else {
            return false; // No inward stock to decrement
        }

        limbodagari.setStockRelease(currentReleaseQty != null ? currentReleaseQty + 1 : 1); // Increment release stock
        limbodagariOutputTableRepository.save(limbodagari); // Save updated row
    }

    return true; // Successfully saved
}
public void updateFormData(FormData updatedData) {
    Optional<FormData> existingData = formRepository.findById(updatedData.getId());
    if (existingData.isPresent()) {
        FormData formData = existingData.get();
        formData.setAssemblyCode(updatedData.getAssemblyCode());
        formData.setMachineName(updatedData.getMachineName());
        formData.setLmLocation(updatedData.getLmLocation());
        formData.setKmLocation(updatedData.getKmLocation());
        formData.setInward_location(updatedData.getInward_location());
        formData.setStage(updatedData.getStage());
        formData.setOpening_stock(updatedData.getOpening_stock());
        formData.setOpening_stock_km(updatedData.getOpening_stock_km());
        formData.setReorder_level(updatedData.getReorder_level());
        formData.setReorder_qty(updatedData.getReorder_level());
        formRepository.save(formData);
    } else {
        throw new RuntimeException("Record not found");
    }
}
public List<Map<String, Object>> getAllFormData() {
    return formRepository.findAll().stream().map(formData -> {
        Map<String, Object> data = new HashMap<>();
        data.put("referenceId", formData.getReferenceId());
        data.put("assemblyCode", formData.getAssemblyCode());
        data.put("description", formData.getDescription());
        data.put("machineName", formData.getMachineName());
        data.put("lmLocation", formData.getLmLocation());
        data.put("kmLocation", formData.getKmLocation());
        data.put("inwardLocation", formData.getInward_location());
        data.put("stage", formData.getStage());
        data.put("openingStock", formData.getOpening_stock());
        data.put("openingStockKM", formData.getOpening_stock_km());
        data.put("reorderLevel", formData.getReorder_level());
        data.put("reorderQty", formData.getReorder_qty());
        data.put("createdAt", formData.getCreatedAt());
        return data;
    }).collect(Collectors.toList());
}

}