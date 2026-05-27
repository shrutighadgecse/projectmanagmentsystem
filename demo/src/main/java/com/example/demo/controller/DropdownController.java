package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FormData;
import com.example.demo.service.DropdownService;
import com.example.demo.service.FormService;


@RestController
@RequestMapping("/api/dropdown")
public class DropdownController {

    @Autowired
    private DropdownService dropdownService;

    @Autowired
    private FormService formDataService;

    // Machine Name Options
    @GetMapping("/machine/options")
    public List<String> getMachineOptions() {
        return dropdownService.getMachineOptions();
    }

    @PostMapping("/machine/add")
    public ResponseEntity<String> addMachineOption(@RequestBody String option) {
        dropdownService.addMachineOption(option);
        return ResponseEntity.ok("Option processed");
    }

    @DeleteMapping("/machine/delete/{option}")
    public ResponseEntity<String> deleteMachineOption(@PathVariable String option) {
        dropdownService.deleteMachineOption(option);
        return ResponseEntity.ok("Option deleted");
    }

    // Assy Location Options
    @GetMapping("/lm-location/options")
    public List<String> getLMLocationOptions() {
        return dropdownService.getLMLocationOptions();
    }

    @PostMapping("/lm-location/add")
    public ResponseEntity<String> addLMLocationOption(@RequestBody String option) {
        dropdownService.addLMLocationOption(option);
        return ResponseEntity.ok("Option processed");
    }

    @DeleteMapping("/lm-location/delete/{option}")
    public ResponseEntity<String> deleteLMLocationOption(@PathVariable String option) {
        dropdownService.deleteLMLocationOption(option);
        return ResponseEntity.ok("Option deleted");
    }

       // Assembly Code Options
       @GetMapping("/assembly-code/options")
       public List<String> getAssemblyCode() {
           return dropdownService.getAssemblyCode();
       }
   
       @PostMapping("/assembly-code/add")
       public ResponseEntity<String> addAssemblyCode(@RequestBody String option) {
           dropdownService.addAssemblyCode(option);
           return ResponseEntity.ok("Option processed");
       }
   
       @DeleteMapping("/assembly-code/delete/{option}")
       public ResponseEntity<String> deleteAssemblyCode(@PathVariable String option) {
           dropdownService.deleteAssemblyCode(option);
           return ResponseEntity.ok("Option deleted");
       }
    // Form submission endpoint using formDataService
    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody FormData formData) {
        formDataService.processFormData(formData);
        return ResponseEntity.ok("Form submitted successfully");
    }


     // Assy Location Options
     @GetMapping("/km-location/options")
     public List<String> getKMLocationOptions() {
         return dropdownService.getKMLocationOptions();
     }
 
     @PostMapping("/km-location/add")
     public ResponseEntity<String> addKMLocationOption(@RequestBody String option) {
         dropdownService.addKMLocationOption(option);
         return ResponseEntity.ok("Option processed");
     }
 
     @DeleteMapping("/km-location/delete/{option}")
     public ResponseEntity<String> deleteKMLocationOption(@PathVariable String option) {
         dropdownService.deleteKMLocationOption(option);
         return ResponseEntity.ok("Option deleted");
     }
 
}
