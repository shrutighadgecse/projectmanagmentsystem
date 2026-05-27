package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.UpdateFailedException;
import com.example.demo.model.CompletedKitForm;
import com.example.demo.model.FormData;
import com.example.demo.model.KitForm;
import com.example.demo.repository.CompletedKitFormRepository;
import com.example.demo.repository.FormDataRepository;
import com.example.demo.service.CompletedKitFormService;
import com.example.demo.service.KitFormService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kitform")
public class KitFormController {



    @Autowired
        private  FormDataRepository formRepository;
        
        private final KitFormService kitFormService;
        @Autowired
        private CompletedKitFormRepository completedKitFormRepository;
        
        @Autowired
        private CompletedKitFormService completedKitFormService;
        
        public KitFormController(KitFormService kitFormService, CompletedKitFormService completedKitFormService) {
            this.kitFormService = kitFormService;
            this.completedKitFormService = completedKitFormService;
        }

    @GetMapping("/generateReferenceId")
    public ResponseEntity<Map<String, String>> generateReferenceId() {
        String newReferenceId = kitFormService.generateReferenceId();
        Map<String, String> response = new HashMap<>();
        response.put("referenceId", newReferenceId);
        return ResponseEntity.ok(response);
    }


 @GetMapping("/assembly-codes")
public List<String> getAllAssemblyCodes() {
    List<FormData> formDataList = formRepository.findAll();
    return formDataList.stream()
                       .map(FormData::getAssemblyCode)
                       .collect(Collectors.toList());
}
@GetMapping("/fetch-details")
public Map<String, String> getAssemblyDetails(@RequestParam String assemblyCode) {
    Optional<FormData> formDataOpt = formRepository.findByAssemblyCode(assemblyCode);
    Map<String, String> response = new HashMap<>();
    
    if (formDataOpt.isPresent()) {
        FormData formData = formDataOpt.get();
        response.put("stage", formData.getStage());
        response.put("machineName", formData.getMachineName());
        response.put("description", formData.getDescription());
    } else {
        response.put("stage", ""); // Empty values if not found
        response.put("machineName", "");
        response.put("description", ""); // Add empty description if not found
    }
    
    return response;
}
@GetMapping("/blcpo/suggestions")
public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
    List<String> suggestions = kitFormService.findSuggestions(query);
    return ResponseEntity.ok(suggestions);
}

    @PostMapping("/blcpo/save")
    public ResponseEntity<String> saveBLCPO(@RequestBody String blcPo) {
        try {
            kitFormService.saveKitForm(blcPo);
            return ResponseEntity.ok("BLC_PO saved successfully!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate value not allowed!");
        }
    }


@GetMapping("/status/counts")
public ResponseEntity<Map<String, Integer>> getStatusCounts() {
    Map<String, Integer> counts = new HashMap<>();
    counts.put("IN PROCESS", kitFormService.countByStatus("IN PROCESS"));
    counts.put("SHORTFALL", kitFormService.countByStatus("SHORTFALL"));
    counts.put("COMPLETE", kitFormService.countByStatus("COMPLETE"));
    return ResponseEntity.ok(counts);
}
@GetMapping("/status")
public ResponseEntity<List<KitForm>> getEntriesByStatus(@RequestParam String status) {
    List<KitForm> entries = kitFormService.findByStatus(status);
    return ResponseEntity.ok(entries);
}
@GetMapping("/{id}")
public ResponseEntity<KitForm> getKitFormById(@PathVariable Long id) {
    Optional<KitForm> kitForm = kitFormService.findById(id);
    return kitForm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

@PutMapping("/update")
public ResponseEntity<?> updateKitForm(@RequestBody KitForm kitForm) {
    try {
        // Validate the incoming data
        if (kitForm.getId() == null) {
            return ResponseEntity.badRequest().body("ID is required for update.");
        }

        // Perform the update
        kitFormService.updateKitForm(kitForm);

        if ("COMPLETE".equals(kitForm.getStatus())) {
            completedKitFormService.saveCompletedKitForm(kitForm);
        }

        return ResponseEntity.ok("KitForm updated successfully");
    } catch (UpdateFailedException e) {
        e.printStackTrace(); // Log the exception for debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error updating form: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace(); // Log any other exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An unexpected error occurred: " + e.getMessage());
    }
}

@GetMapping("/completed")
public ResponseEntity<List<CompletedKitForm>> getCompletedEntries() {
    List<CompletedKitForm> completedEntries = completedKitFormRepository.findAll();
    return ResponseEntity.ok(completedEntries);
}

@GetMapping("/completed/{referenceId}") // New endpoint to fetch completed entry by referenceId
public ResponseEntity<CompletedKitForm> getCompletedEntryByReferenceId(@PathVariable String referenceId) {
    Optional<CompletedKitForm> completedEntry = completedKitFormRepository.findByReferenceId(referenceId);
    return completedEntry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

@DeleteMapping("/completed/{id}")
public ResponseEntity<String> deleteCompletedEntry(@PathVariable String id) {
    try {
        // Assuming you have a method to find by referenceId
        Optional<CompletedKitForm> entry = completedKitFormRepository.findByReferenceId(id);
        if (entry.isPresent()) {
            completedKitFormRepository.delete(entry.get());
            return ResponseEntity.ok("Completed entry deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting entry: " + e.getMessage());
    }
}
@PostMapping("/save")
public ResponseEntity<String> saveKitForm(@RequestBody KitForm kitForm) {
    try {
        kitFormService.saveKitForm(kitForm);
            // If the status is COMPLETE, save it in the CompletedKitForm repository
            if ("COMPLETE".equals(kitForm.getStatus())) {
                completedKitFormService.saveCompletedKitForm(kitForm);
            }
        return ResponseEntity.ok("Form submitted successfully!");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving form: " + e.getMessage());
    }
}


}
 


