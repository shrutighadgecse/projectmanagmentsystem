package com.example.demo.controller;

import com.example.demo.dto.MachineCcnProjectResponseDTO;
import com.example.demo.model.MachineCcnProject;
import com.example.demo.service.MachineCcnProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/machineccnproject")
@CrossOrigin(origins = "*")
public class MachineCcnProjectController {

    @Autowired
    private MachineCcnProjectService service;

    @Autowired
    private com.example.demo.repository.MachineCcnProjectRepository repository;

    private static final Logger logger = Logger.getLogger(MachineCcnProjectController.class.getName());

    @GetMapping("/by-ccn")
    public ResponseEntity<?> getByCcn(@RequestParam String ccn) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("CCN is required"));
            }
            MachineCcnProjectResponseDTO dto = service.getByCcn(ccn);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching Machine CCN Project: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch data"));
        }
    }

    @GetMapping("/summaries")
    public ResponseEntity<?> getProjectSummaries() {
        try {
            return ResponseEntity.ok(service.getAllProjectSummaries());
        } catch (Exception e) {
            logger.severe("Error fetching Machine CCN Project summaries: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch project summaries"));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MachineCcnProject machineCcnProject) {
        try {
            if (machineCcnProject == null || machineCcnProject.getCcn() == null || machineCcnProject.getCcn().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("CCN is mandatory"));
            }
            MachineCcnProject saved = service.save(machineCcnProject);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Machine CCN Project saved successfully");
            response.put("data", saved);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error saving Machine CCN Project: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to save Machine CCN Project"));
        }
    }

    @GetMapping("/count/live-stock")
    public ResponseEntity<?> getLiveStockMachinesCount() {
        try {
            long count = service.getLiveStockMachinesCount();
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("category", "stock");
            response.put("status", "live");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching live stock machines count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/count/live-customized")
    public ResponseEntity<?> getLiveCustomizedMachinesCount() {
        try {
            long count = service.getLiveCustomizedMachinesCount();
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("category", "customized");
            response.put("status", "live");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching live customized machines count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/count/live-expected")
    public ResponseEntity<?> getExpectedOrderMachinesCount() {
        try {
            long count = service.getExpectedOrderMachinesCount();
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("category", "expected");
            response.put("status", "live");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching expected order machines count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/count/ifat-done")
    public ResponseEntity<?> getIfatDoneMachinesCount(@RequestParam(required = false) Integer year) {
        try {
            long count = service.getIfatDoneMachinesCount(year);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("type", "ifat-done");
            response.put("year", year);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching IFAT done machines count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/count/cfat-done")
    public ResponseEntity<?> getCfatDoneMachinesCount(@RequestParam(required = false) Integer year) {
        try {
            long count = service.getCfatDoneMachinesCount(year);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("type", "cfat-done");
            response.put("year", year);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching CFAT done machines count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/count/awaiting-clearance")
    public ResponseEntity<?> getAwaitingCustomerClearanceCount(@RequestParam(required = false) Integer year) {
        try {
            long count = service.getAwaitingCustomerClearanceCount(year);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("type", "awaiting-clearance");
            response.put("year", year);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching awaiting customer clearance count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/years")
    public ResponseEntity<?> getAvailableYears() {
        try {
            return ResponseEntity.ok(service.getAvailableYears());
        } catch (Exception e) {
            logger.severe("Error fetching available years: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch years"));
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardSummary(@RequestParam(required = false) String year) {
        try {
            return ResponseEntity.ok(service.getDashboardSummary(year));
        } catch (Exception e) {
            logger.severe("Error fetching dashboard summary: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch dashboard data"));
        }
    }

    @GetMapping("/count/by-status-category")
    public ResponseEntity<?> getCountByStatusAndCategory(
            @RequestParam String ccnStatus,
            @RequestParam String machineCategory) {
        try {
            long count = service.getCountByCcnStatusAndMachineCategory(ccnStatus, machineCategory);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("status", ccnStatus);
            response.put("category", machineCategory);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching count: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch count"));
        }
    }

    @GetMapping("/total-po-value/by-status-category")
    public ResponseEntity<?> getTotalPoValueByStatusAndCategory(
            @RequestParam String ccnStatus,
            @RequestParam String machineCategory) {
        try {
            Map<String, Object> result = service.getTotalPoValueByCategoryAndStatus(ccnStatus, machineCategory);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching total PO value: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch total PO value"));
        }
    }

    @GetMapping("/total-po-value/live-customized")
    public ResponseEntity<?> getTotalPoValueLiveCustomized() {
        try {
            Map<String, Object> result = service.getTotalPoValueByCategoryAndStatus("live", "customized");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.severe("Error fetching total PO value for live customized: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch total PO value"));
        }
    }

    @GetMapping("/debug/all-statuses-and-categories")
    public ResponseEntity<?> getDebugInfo() {
        try {
            List<MachineCcnProject> allRecords = repository.findAll();
            Set<String> statuses = new java.util.HashSet<>();
            Set<String> categories = new java.util.HashSet<>();

            allRecords.forEach(m -> {
                if (m.getCcnStatus() != null) statuses.add(m.getCcnStatus());
                if (m.getMachineCategory() != null) categories.add(m.getMachineCategory());
            });

            Map<String, Object> response = new HashMap<>();
            response.put("totalRecords", allRecords.size());
            response.put("uniqueStatuses", statuses);
            response.put("uniqueCategories", categories);
            response.put("recordCount", allRecords.size());

            // Count each combination
            Map<String, Long> combinations = new HashMap<>();
            statuses.forEach(status -> {
                categories.forEach(category -> {
                    long count = repository.countByCcnStatusAndMachineCategory(status, category);
                    if (count > 0) {
                        combinations.put(status + " - " + category, count);
                    }
                });
            });
            response.put("countByCombination", combinations);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error getting debug info: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch debug info"));
        }
    }

    // =========================
    // FINAL DATE LOCK ENDPOINTS
    // =========================

    @PostMapping("/mark-final/{ccn}")
    public ResponseEntity<?> markDateFieldAsFinal(
            @PathVariable String ccn,
            @RequestParam String dateField) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("CCN is required"));
            }
            if (dateField == null || dateField.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("Date field is required"));
            }

            logger.info("Attempting to mark " + dateField + " as final for CCN: " + ccn);
            
            MachineCcnProject project = repository.findByCcnIgnoreCase(ccn.trim()).orElse(null);
            if (project == null) {
                logger.warning("Project not found for CCN: " + ccn);
                return ResponseEntity.notFound().build();
            }

            service.markDateFieldAsFinal(project, dateField.trim());
            MachineCcnProject saved = repository.save(project);
            
            logger.info("Successfully marked " + dateField + " as final for CCN: " + ccn);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Date field marked as final");
            response.put("ccn", ccn);
            response.put("field", dateField);
            response.put("savedProject", saved);

            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            logger.warning("IllegalStateException marking final: " + e.getMessage());
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error marking date as final: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(error("Unable to mark date as final: " + e.getMessage()));
        }
    }

    @GetMapping("/is-final/{ccn}")
    public ResponseEntity<?> checkIfDateFieldIsFinal(
            @PathVariable String ccn,
            @RequestParam String dateField) {
        try {
            if (ccn == null || ccn.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("CCN is required"));
            }
            if (dateField == null || dateField.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(error("Date field is required"));
            }

            MachineCcnProject project = repository.findByCcnIgnoreCase(ccn.trim()).orElse(null);
            if (project == null) {
                return ResponseEntity.notFound().build();
            }

            boolean isFinal = service.isDateFieldFinal(project, dateField.trim());

            Map<String, Object> response = new HashMap<>();
            response.put("ccn", ccn);
            response.put("field", dateField);
            response.put("isFinal", isFinal);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error checking final status: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to check final status"));
        }
    }

    private Map<String, Object> error(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", message);
        return response;
    }

    @GetMapping("/wip-dashboard")
    public ResponseEntity<?> getAssemblyWipDashboard(@RequestParam(required = false) String filterYear) {
        try {
            LocalDate filterDate = LocalDate.now().withDayOfYear(1);
            if (filterYear != null && !filterYear.isEmpty()) {
                try {
                    int year = Integer.parseInt(filterYear);
                    filterDate = LocalDate.of(year, 1, 1);
                } catch (NumberFormatException e) {
                    logger.warning("Invalid filter year format: " + filterYear);
                }
            }
            
            List<com.example.demo.dto.AssemblyWipDto> wipData = service.getAssemblyWipData(filterDate);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("machines", wipData);
            response.put("filterYear", filterDate.getYear());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching WIP dashboard: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch WIP dashboard"));
        }
    }

    @GetMapping("/wip-details")
    public ResponseEntity<?> getWipDetails(@RequestParam String type, @RequestParam int filterYear) {
        try {
            Map<String, java.util.List<java.util.Map<String, Object>>> result = service.getAssemblyWipDetails(type, filterYear);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            logger.severe("Invalid WIP details type: " + e.getMessage());
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching WIP details: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch WIP details"));
        }
    }

    @GetMapping("/manufacturing-data")
    public ResponseEntity<?> getManufacturingData(@RequestParam int year) {
        try {
            int[] data = service.getManufacturingData(year);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            logger.severe("Error fetching manufacturing data: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch manufacturing data"));
        }
    }

    @GetMapping("/dispatch-data")
    public ResponseEntity<?> getDispatchData(@RequestParam int year) {
        try {
            int[] data = service.getDispatchData(year);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            logger.severe("Error fetching dispatch data: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch dispatch data"));
        }
    }

    @GetMapping("/commercial-dispatch-counts")
    public ResponseEntity<?> getCommercialDispatchCounts(@RequestParam int year) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("dispatchPlan", service.getDispatchPlanCounts(year));
            response.put("dynamicDispatch", service.getDispatchActualCounts(year));
            response.put("actual", service.getDispatchFinalCounts(year));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching commercial dispatch counts: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch commercial dispatch counts"));
        }
    }

    @GetMapping("/commercial-dispatch-details")
    public ResponseEntity<?> getCommercialDispatchDetails(
            @RequestParam int year,
            @RequestParam String type,
            @RequestParam(required = false) Integer month) {
        try {
            Map<String, Object> response = service.getCommercialDispatchDetails(year, type, month);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.severe("Invalid commercial dispatch type: " + e.getMessage());
            return ResponseEntity.badRequest().body(error(e.getMessage()));
        } catch (Exception e) {
            logger.severe("Error fetching commercial dispatch details: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch commercial dispatch details"));
        }
    }

    @GetMapping("/manufacturing-details")
    public ResponseEntity<?> getManufacturingDetails(@RequestParam int year, @RequestParam(required = false) Integer month) {
        try {
            List<Map<String, Object>> details = service.getManufacturingDetails(year, month);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            logger.severe("Error fetching manufacturing details: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch manufacturing details"));
        }
    }

    @GetMapping("/dispatch-details")
    public ResponseEntity<?> getDispatchDetails(@RequestParam int year, @RequestParam(required = false) Integer month) {
        try {
            Map<String, Object> response = service.getCommercialDispatchDetails(year, "actual", month);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error fetching dispatch details: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch dispatch details"));
        }
    }

    // ── SUGGESTIONS FOR DROPDOWN FIELDS ──────────────────────────────────
    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions(@RequestParam String field, @RequestParam String q) {
        try {
            List<String> suggestions = service.getSuggestions(field, q);
            return ResponseEntity.ok(suggestions);
        } catch (Exception e) {
            logger.severe("Error fetching suggestions: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to fetch suggestions"));
        }
    }

    // ── ADD NEW VALUE FOR DROPDOWN FIELDS ────────────────────────────────
    @PostMapping("/add-new")
    public ResponseEntity<?> addNewValue(@RequestParam String field, @RequestParam String value, @RequestParam String ccn) {
        try {
            boolean added = service.addNewValue(field, value, ccn);
            if (added) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Value added successfully"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Value already exists or invalid field"));
            }
        } catch (Exception e) {
            logger.severe("Error adding new value: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error("Unable to add value"));
        }
    }
}
