package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.BhoomikaForm;
import com.example.demo.model.FormData;
import com.example.demo.service.BhoomikaDataService;

@RestController
@RequestMapping("/api/form")
public class BhoomikaDataController {

    @Autowired
    private BhoomikaDataService bhoomikaDataService;
   

    @PostMapping("/save")
    public ResponseEntity<?> saveForm(@RequestBody BhoomikaForm bhoomikaForm) {
        // Check if the reference already exists
        if (bhoomikaDataService.referenceExists(bhoomikaForm.getReference())) {
            return ResponseEntity.badRequest().body("This reference form already exists.");
        }
        
        BhoomikaForm savedData = bhoomikaDataService.saveBhoomikaForm(bhoomikaForm);
        return ResponseEntity.ok(savedData);
    }

    

   @GetMapping("/close")
    public ResponseEntity<List<BhoomikaForm>> getCloseStatus() {
        List<BhoomikaForm> closeForms = bhoomikaDataService.findByBlcStatus("Closed");
        return ResponseEntity.ok(closeForms);
    }

    @GetMapping("/live")
    public ResponseEntity<List<BhoomikaForm>> getLiveStatus() {
        List<BhoomikaForm> liveForms = bhoomikaDataService.findByBlcStatusNot("Closed");
        return ResponseEntity.ok(liveForms);
    }
    @GetMapping("/{referenceId}")
    public ResponseEntity<BhoomikaForm> getRecord(@PathVariable String referenceId) {
        BhoomikaForm form = bhoomikaDataService.findByReference(referenceId);
        if (form != null) {
            return ResponseEntity.ok(form);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/locations/{assemblyCode}")
public ResponseEntity<Map<String, String>> getLocations(@PathVariable String assemblyCode) {
    Optional<FormData> formData = bhoomikaDataService.findLocationsByAssemblyCode(assemblyCode);
    if (formData.isPresent()) {
        Map<String, String> locations = new HashMap<>();
        locations.put("kmLocation", formData.get().getKmLocation());
        locations.put("limbodagriLocation", formData.get().getLmLocation());
        return ResponseEntity.ok(locations);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    
    @PutMapping("/update/{referenceId}")
    public ResponseEntity<BhoomikaForm> updateRecord(@PathVariable String referenceId, @RequestBody BhoomikaForm updatedForm) {
        BhoomikaForm form = bhoomikaDataService.updateBhoomikaForm(referenceId, updatedForm);
        if (form != null) {
            return ResponseEntity.ok(form);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
public ResponseEntity<List<BhoomikaForm>> getAllRecords() {
    List<BhoomikaForm> allForms = bhoomikaDataService.findAll();
    return ResponseEntity.ok(allForms);
}



}