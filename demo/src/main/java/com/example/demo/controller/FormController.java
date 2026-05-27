package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.FormData;
import com.example.demo.model.InwardStockLimbodagari;
import com.example.demo.model.ReleaseToCCNStock;
import com.example.demo.model.ScannerFormData;
import com.example.demo.model.StockTransitRecord;
import com.example.demo.model.StockReleaseLimbodagari;
import com.example.demo.repository.FormDataRepository;
import com.example.demo.repository.InwardStockLimbodagariRepository;
import com.example.demo.repository.ScannerFormDataRepository;
import com.example.demo.repository.StockTransitRecordRepository;
import com.example.demo.repository.ReleaseToCCNStockRepository;
import com.example.demo.service.FormService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class FormController {

    private final FormService formService;
    private final FormDataRepository formRepository; // Assuming you have a FormDataRepository for FormData entity
    private final ScannerFormDataRepository scannerFormDataRepository;
    private final StockTransitRecordRepository stockTransitRecordRepository;
    private final InwardStockLimbodagariRepository inwardStockLimbodagariRepository;
    private final ReleaseToCCNStockRepository releaseToCCNStockRepository;


    public FormController(FormService formService , FormDataRepository formRepository,
                          ScannerFormDataRepository scannerFormDataRepository,
                          InwardStockLimbodagariRepository inwardStockLimbodagariRepository,
                          ReleaseToCCNStockRepository releaseToCCNStockRepository,
                          StockTransitRecordRepository stockTransitRecordRepository) {
        this.inwardStockLimbodagariRepository = inwardStockLimbodagariRepository;
        this.releaseToCCNStockRepository = releaseToCCNStockRepository;
        this.stockTransitRecordRepository = stockTransitRecordRepository;
        this.formService = formService;
        this.scannerFormDataRepository = scannerFormDataRepository;
        this.formRepository = formRepository;
    }

    @GetMapping("/generateReferenceId")
    public ResponseEntity<Map<String, String>> generateReferenceId() {
        String newReferenceId = formService.generateReferenceId();
        Map<String, String> response = new HashMap<>();
        response.put("referenceId", newReferenceId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody Map<String, String> formData) {
        try {
            // Retrieve form data from the request body
            String assemblyCode = formData.get("assembly_code");
            String machineName = formData.get("machine_name");
            String lmLocation = formData.get("lm_location");
            String inwardLocation = formData.get("inward_location");
            String stage = formData.get("stage");
            String openingStock = formData.get("opening_stock");
            String openingStockKM = formData.get("opening_stock_km");
            String reorderLevel = formData.get("reorder_level");
            String reorderQty = formData.get("reorder_qty");
            String kmLocation = formData.get("km_location");

            String description = formData.get("description");

            // Validate the input (you can add more validations as needed)
            if (assemblyCode == null || machineName == null) {
                return ResponseEntity.badRequest().body("Required fields are missing.");
            }

            // Call service layer to handle database insert
            formService.saveFormData(assemblyCode, machineName, lmLocation,  inwardLocation, 
                                      stage, openingStock,openingStockKM, reorderLevel, reorderQty,kmLocation, description);
                                      
            String newReferenceId = formService.getCurrentReferenceId(); // Get the current reference ID

            return ResponseEntity.ok("Form submitted successfully with Reference ID: " + newReferenceId);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception (consider using a logger in production)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while submitting the form.");
        }
    }

    @PostMapping("/submitGRN")
    public ResponseEntity<Map<String, String>> submitGRN(@RequestBody Map<String, String> grnData) {
        try {
            System.out.println("Received GRN data: " + grnData); // Debug log

            String assemblyCode = grnData.get("assemblyCode");
            if (assemblyCode == null || assemblyCode.isEmpty()) {
                System.out.println("Assembly Code is missing."); // Debug log
                return ResponseEntity.badRequest().body(Map.of("message", "Assembly Code is required."));
            }

            boolean updated = formService.updateStockInward(assemblyCode);
            if (updated) {
                System.out.println("Stock Inward updated successfully."); // Debug log
                return ResponseEntity.ok(Map.of("message", "Stock Inward updated successfully."));
            } else {
                System.out.println("Assembly Code not found in the Kila Maidan table."); // Debug log
                return ResponseEntity.badRequest().body(Map.of("message", "Assembly Code not found in the Kila Maidan table."));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while updating Stock Inward."));
        }
    }

    @GetMapping("/checkAssemblyCode")
    public ResponseEntity<Map<String, Object>> checkAssemblyCode(@RequestParam String assemblyCode) {
        boolean exists = formService.assemblyCodeExists(assemblyCode);
        List<String> suggestions = exists ? Collections.emptyList() : formService.getAssemblyCodeSuggestions(assemblyCode);
        
        Map<String, Object> response = new HashMap<>();
        response.put("exists", exists);
        response.put("suggestions", suggestions);
        
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getKilaMaidanData")
    public ResponseEntity<List<Map<String, Object>>> getKilaMaidanData() {
        List<Map<String, Object>> kilaMaidanData = formService.getKilaMaidanData();
        return ResponseEntity.ok(kilaMaidanData);
    }

    @GetMapping("/getLimbodagariData")
    public ResponseEntity<List<Map<String, Object>>> getLimbodagariData() {
        List<Map<String, Object>> limbodagariData = formService.getLimbodagariData();
        return ResponseEntity.ok(limbodagariData);
    }

    @PostMapping("/submitStockTransfer")
    public ResponseEntity<Map<String, String>> submitStockTransfer(@RequestBody Map<String, String> transferData) {
        try {
            String referenceId = transferData.get("referenceId");
            String assemblyCode = transferData.get("assemblyCode");
            String date = transferData.get("date");

            if (referenceId == null || assemblyCode == null || date == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Reference ID and Assembly Code are required."));
            }

            boolean saved = formService.saveStockTransitRecord(referenceId, assemblyCode);
            if (saved) {
                return ResponseEntity.ok(Map.of("message", "Stock Transfer updated successfully."));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Duplicate Reference ID. Stock Transfer not saved."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while processing the stock transfer."));
        }
    }

    @Transactional
    @PostMapping("/submitInwardLimbodagari")
    public ResponseEntity<Map<String, String>> submitInwardLimbodagari(@RequestBody Map<String, String> inwardData) {
        try {
            String referenceId = inwardData.get("referenceId");
            String assemblyCode = inwardData.get("assemblyCode");
            String inwardReceiptNumber = inwardData.get("inwardReceiptNumber");
            String receiptDate = inwardData.get("receiptDate");

            if (referenceId == null || assemblyCode == null || inwardReceiptNumber == null || receiptDate == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required."));
            }

            // Check and remove the record from StockTransitRecord table if it exists
            if (stockTransitRecordRepository.existsByReferenceId(referenceId)) {
                stockTransitRecordRepository.deleteByReferenceId(referenceId);
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Reference ID not found in Stock Transit Record."));
            }

            // Save the record to the Limbodagari table
            boolean saved = formService.saveLimbodagariScannerForm(referenceId, assemblyCode, inwardReceiptNumber, receiptDate);
            if (saved) {
                return ResponseEntity.ok(Map.of("message", "Form submitted successfully."));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Duplicate Reference ID. Form not saved."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while submitting the form: " + e.getMessage()));
        }
    }

    @PostMapping("/submitScannerForm")
    public ResponseEntity<Map<String, String>> submitScannerForm(@RequestBody Map<String, String> scannerData) {
        try {
            String referenceId = scannerData.get("referenceId");
            String assemblyCode = scannerData.get("assemblyCode");
            String kmLocation = scannerData.get("kmLocation");

            String grnNumber = scannerData.get("grnNumber");
            String grnDate = scannerData.get("grnDate");

            if (referenceId == null || assemblyCode == null || grnNumber == null || grnDate == null || kmLocation == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required."));
            }

            boolean saved = formService.saveScannerFormData(referenceId, assemblyCode,kmLocation, grnNumber, grnDate);
            if (saved) {
                return ResponseEntity.ok(Map.of("message", "Form submitted successfully."));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Duplicate Reference ID. Form not saved."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while submitting the form."));
        }
    }

    @PostMapping("/submitLimbodagariScannerForm")
    public ResponseEntity<Map<String, String>> submitLimbodagariScannerForm(@RequestBody Map<String, String> scannerData) {
        try {
            String referenceId = scannerData.get("referenceId");
            String assemblyCode = scannerData.get("assemblyCode");
            String inwardReceiptNumber = scannerData.get("inwardReceiptNumber");
            String receiptDate = scannerData.get("receiptDate");

            if (referenceId == null || assemblyCode == null || inwardReceiptNumber == null || receiptDate == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required."));
            }

            boolean saved = formService.saveLimbodagariScannerForm(referenceId, assemblyCode, inwardReceiptNumber, receiptDate);
            if (saved) {
                return ResponseEntity.ok(Map.of("message", "Form submitted successfully."));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Duplicate Reference ID. Form not saved."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while submitting the form."));
        }
    }

    @GetMapping("/getAssemblyCodeDetails")
    public ResponseEntity<Map<String, Object>> getAssemblyCodeDetails(@RequestParam String assemblyCode) {
        Map<String, Object> response = new HashMap<>();

        // Fetch data from ScannerFormData table
        List<ScannerFormData> scannerData = scannerFormDataRepository.findByAssemblyCode(assemblyCode);
        response.put("scannerData", scannerData);

        // Fetch data from StockTransitRecord table
        List<StockTransitRecord> stockTransitData = stockTransitRecordRepository.findByAssemblyCode(assemblyCode);
        response.put("stockTransitData", stockTransitData);

        // Fetch data from StockReleaseLimbodagari table
        List<StockReleaseLimbodagari> stockReleaseData = formService.getStockReleaseRecords(assemblyCode);
        response.put("stockReleaseData", stockReleaseData);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getStockReleaseRecords")
    public ResponseEntity<List<StockReleaseLimbodagari>> getStockReleaseRecords(@RequestParam String assemblyCode) {
        List<StockReleaseLimbodagari> records = formService.getStockReleaseRecords(assemblyCode);
        return ResponseEntity.ok(records);
    }

    @PostMapping("/submitReleaseToCcn")
    @Transactional
    public ResponseEntity<Map<String, String>> submitReleaseToCcn(@RequestBody Map<String, String> releaseData) {
        try {
            String referenceId = releaseData.get("referenceId");
            String assemblyCode = releaseData.get("assemblyCode");
            String ccn = releaseData.get("ccn");
            String releaseDate = releaseData.get("releaseDate");
    
            if (referenceId == null || assemblyCode == null || ccn == null || releaseDate == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required."));
            }
    
            boolean saved = formService.saveReleaseToCcn(referenceId, assemblyCode, ccn, releaseDate);
            if (saved) {
                return ResponseEntity.ok(Map.of("message", "Record saved successfully."));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Duplicate Reference ID or invalid data. Record not saved."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while saving the record."));
        }
    }

    @GetMapping("/getAssemblyCodeDetailsLimbodagari")
    public ResponseEntity<Map<String, Object>> getAssemblyCodeDetailsLimbodagari(@RequestParam String assemblyCode) {
        Map<String, Object> response = new HashMap<>();

        // Fetch data from ScannerFormData table
        List<InwardStockLimbodagari> inwardStock = inwardStockLimbodagariRepository.findByAssemblyCode(assemblyCode);
        response.put("inwardStock", inwardStock);

        // Fetch data from StockTransitRecord table
        List<ReleaseToCCNStock> releaseToCCNStock = releaseToCCNStockRepository.findByAssemblyCode(assemblyCode);
        response.put("releaseToCCNStock", releaseToCCNStock);



        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateRecord")
public ResponseEntity<String> updateRecord(@RequestBody FormData updatedData) {
    try {
        // Call the service layer to update the record
        formService.updateFormData(updatedData);
        return ResponseEntity.ok("Record updated successfully");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the record.");
    }
}
@GetMapping("/getRecord/{referenceId}")
public ResponseEntity<FormData> getRecord(@PathVariable String referenceId) {
    Optional<FormData> formData = formRepository.findByReferenceId(referenceId);
    return formData.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
}
@GetMapping("/getAllFormData")
public ResponseEntity<List<Map<String, Object>>> getAllFormData() {
    List<Map<String, Object>> formData = formService.getAllFormData();
    return ResponseEntity.ok(formData);
}
}