package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.model.KitInwardForm;
import com.example.demo.repository.FormDataRepository;
import com.example.demo.repository.KitInwardRepsitory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kit-inward")
public class KitInwardController {


    @Autowired
        private  FormDataRepository formRepository;

    @Autowired
    private KitInwardRepsitory kitInwardRepsitory;

    @GetMapping("/api/kit-inward/suggestions")
public ResponseEntity<List<KitInwardForm>> getBLCPOAutocompleteSuggestions(@RequestParam String blcPo) {
    List<KitInwardForm> suggestions = kitInwardRepsitory.findByBlcPoContaining(blcPo);
    return ResponseEntity.ok(suggestions);
}
@GetMapping("/api/kit-inward/check-unique")
public ResponseEntity<String> checkBLCPOUnique(@RequestParam String blcPo) {
    boolean isUnique = isBLCPOUnique(blcPo);
        if (!isUnique) {
            return ResponseEntity.badRequest().body("This BLC PO already exists.");
        }
        return ResponseEntity.ok("Unique");
    }
    
    private boolean isBLCPOUnique(String blcPo) {
        throw new UnsupportedOperationException("Unimplemented method 'isBLCPOUnique'");
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
        } else {
            response.put("stage", ""); // Empty values if not found
            response.put("machineName", "");
        }
        
        return response;
    }


    @PostMapping("/submit")
    public KitInwardForm submitForm(@RequestBody KitInwardForm kitInwardForm) {
        return kitInwardRepsitory.save(kitInwardForm);
    }
}
