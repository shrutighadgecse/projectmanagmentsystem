package com.example.demo.controller;

import com.example.demo.dto.MachineProjectResponseDTO;
import com.example.demo.model.MachineProject;
import com.example.demo.service.MachineProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/machine-project")
@CrossOrigin(origins = "*")
public class MachineProjectController {

    @Autowired
    private MachineProjectService service;

    private static final Logger logger = Logger.getLogger(MachineProjectController.class.getName());

    /**
     * Get machine project data by CCN (combines PO, Kickoff, and MachineProject)
     */
    @GetMapping("/by-ccn")
    public ResponseEntity<?> getByCcn(@RequestParam String ccn) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("CCN is required"));
            }

            MachineProjectResponseDTO dto = service.getByCcn(ccn);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }

            logger.info("Fetched machine project data for CCN: " + ccn);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid CCN: " + ccn);
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching machine project: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Error fetching data: " + e.getMessage()));
        }
    }

    /**
     * Save machine project with file uploads
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(
            @ModelAttribute MachineProject machineProject,
            @RequestParam(value = "cpImage", required = false) MultipartFile cpImage,
            @RequestParam(value = "machineImageStores", required = false) MultipartFile machineImageStores,
            @RequestParam(value = "machineImageAssembly", required = false) MultipartFile machineImageAssembly) {
        try {
            // Validate CCN is not null or empty
            if (machineProject.getCcn() == null || machineProject.getCcn().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("CCN is mandatory and cannot be empty"));
            }

            logger.info("Saving machine project for CCN: " + machineProject.getCcn());

            MachineProject saved = service.save(machineProject, cpImage, machineImageStores, machineImageAssembly);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Machine project saved successfully");
            response.put("data", saved);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            logger.severe("File upload error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("File upload failed: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.warning("Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Save error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Save failed: " + e.getMessage()));
        }
    }

    /**
     * Update specific stage of machine project
     */
    @PostMapping("/update-stage")
    public ResponseEntity<?> updateStage(
            @RequestParam String ccn,
            @RequestParam Integer stage,
            @ModelAttribute MachineProject machineProject) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("CCN is required"));
            }

            machineProject.setCcn(ccn);
            MachineProject saved = service.save(machineProject, null, null, null);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Stage " + stage + " updated successfully");
            response.put("data", saved);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.severe("Stage update error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Update failed: " + e.getMessage()));
        }
    }

    /**
     * Discard changes and reload original data
     */
    @PostMapping("/discard")
    public ResponseEntity<?> discardChanges(@RequestParam String ccn) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("CCN is required"));
            }

            MachineProjectResponseDTO dto = service.getByCcn(ccn);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Changes discarded, reloading original data");
            response.put("data", dto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.severe("Discard error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Discard failed: " + e.getMessage()));
        }
    }

    /**
     * Get calculation for machine percentage
     */
    @GetMapping("/calculate-machine-percentage")
    public ResponseEntity<?> calculateMachinePercentage(@RequestParam String ccn) {
        try {
            MachineProjectResponseDTO dto = service.getByCcn(ccn);
            if (dto == null || dto.getMachineProject() == null) {
                return ResponseEntity.notFound().build();
            }

            Double percentage = service.calculateMachinePercentage(dto.getMachineProject());
            
            Map<String, Object> response = new HashMap<>();
            response.put("ccn", ccn);
            response.put("stdTask", dto.getMachineProject().getStdTaskOfMachine());
            response.put("taskCompleted", dto.getMachineProject().getTaskCompleted());
            response.put("machinePercentage", percentage);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.severe("Calculation error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Calculation failed: " + e.getMessage()));
        }
    }

    /**
     * Check if all steps are complete
     */
    @GetMapping("/check-completion-status")
    public ResponseEntity<?> checkCompletionStatus(@RequestParam String ccn) {
        try {
            MachineProjectResponseDTO dto = service.getByCcn(ccn);
            if (dto == null || dto.getMachineProject() == null) {
                return ResponseEntity.notFound().build();
            }

            boolean allComplete = service.areAllStepsComplete(dto.getMachineProject());

            Map<String, Object> response = new HashMap<>();
            response.put("ccn", ccn);
            response.put("allStepsComplete", allComplete);
            response.put("ccnStatus", dto.getMachineProject().getCcnStatus());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.severe("Status check error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(createErrorResponse("Status check failed: " + e.getMessage()));
        }
    }

    /**
     * Create error response map
     */
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("error", message);
        return error;
    }
}