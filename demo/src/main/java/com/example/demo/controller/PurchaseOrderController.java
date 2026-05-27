package com.example.demo.controller;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.KickoffFormService;
import com.example.demo.service.MachineService;
import com.example.demo.service.MachineCcnProjectService;
import com.example.demo.model.SwapRecord;
import com.example.demo.repository.SwapRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/po")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService service;
    @Autowired
    private KickoffFormService kickoffService;
    @Autowired
    private MachineService machineService;
    @Autowired
    private MachineCcnProjectService machineCcnProjectService;
    @Autowired
    private SwapRecordRepository swapRecordRepository;

    // old @PostMapping("/swap") handler replaced with body-based handler to match frontend JSON payload
    @PostMapping("/swap")
    public SwapResponse swapCcns(@RequestBody SwapPayload payload) {
        if (payload == null || payload.leftCcn == null || payload.rightCcn == null) {
            return new SwapResponse(false, "leftCcn and rightCcn required in request body");
        }
        try {
            service.swapCcns(payload.leftCcn, payload.rightCcn, payload.remark);
            return new SwapResponse(true, "Swap completed");
        } catch (IllegalArgumentException e) {
            return new SwapResponse(false, "Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return new SwapResponse(false, "Swap failed: " + e.getMessage());
        }
    }

    // simple DTO for JSON payload from frontend
    public static class SwapPayload {
        public String leftCcn;
        public String rightCcn;
        public String remark;
        // frontend may also send leftPo/rightPo etc. but we only need CCNs + remark for server-side swap
    }

    @GetMapping("/next-record-no")
    public String getNextRecordNo() {
        List<PurchaseOrder> all = service.getAll();
        int currentYear = Year.now().getValue();
        int maxMiddle = -1;
        Pattern pattern = Pattern.compile("PO-(\\d+)-" + currentYear);
        for (PurchaseOrder po : all) {
            if (po.getRecordNo() != null) {
                Matcher m = pattern.matcher(po.getRecordNo());
                if (m.matches()) {
                    int middle = Integer.parseInt(m.group(1));
                    if (middle > maxMiddle) maxMiddle = middle;
                }
            }
        }
        int nextMiddle = maxMiddle + 1;
        String middleStr = "%02d".formatted(nextMiddle);
        return "PO-" + middleStr + "-" + currentYear;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PurchaseOrder po) {
        // Set timestamp to now if not provided
        if (po.getTimestamp() == null) {
            po.setTimestamp(LocalDateTime.now());
        }
        try {
            PurchaseOrder saved = service.save(po);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to save: " + e.getMessage()));
        }
    }

    @GetMapping("/ccn-exists")
    public CCNCheckResponse checkCcnExists(@RequestParam String ccn) {
        List<PurchaseOrder> all = service.getAll();
        boolean exists = all.stream().anyMatch(po -> po.getCcn() != null && po.getCcn().equalsIgnoreCase(ccn));
        // Suggest similar CCNs (case-insensitive, contains input)
        List<String> suggestions = all.stream()
            .map(PurchaseOrder::getCcn)
            .filter(existingCcn -> existingCcn != null && !existingCcn.equalsIgnoreCase(ccn) && existingCcn.toLowerCase().contains(ccn.toLowerCase()))
            .distinct()
            .limit(5)
            .collect(Collectors.toList());
        return new CCNCheckResponse(exists, suggestions);
    }

    @GetMapping("/suggestions")
    public List<String> getSuggestions(@RequestParam String field, @RequestParam String q) {
        List<PurchaseOrder> all = service.getAll();
        Set<String> unique = new HashSet<>();
        switch (field) {
            case "salesPerson":
                all.forEach(po -> { if (po.getSalesPerson() != null) unique.add(po.getSalesPerson()); });
                break;
            case "customerName":
                all.forEach(po -> { if (po.getCustomerName() != null) unique.add(po.getCustomerName()); });
                break;
            case "machineName":
                all.forEach(po -> { if (po.getMachineName() != null) unique.add(po.getMachineName()); });
                break;
            case "ccn":
                all.forEach(po -> { if (po.getCcn() != null) unique.add(po.getCcn()); });
                break;
            case "machineModel":
            case "modelName":
                all.forEach(po -> { if (po.getModelName() != null) unique.add(po.getModelName()); });
                break;
            default:
                return List.of();
        }
        return unique.stream()
            .filter(val -> val.toLowerCase().contains(q.toLowerCase()))
            .sorted()
            .limit(10)
            .collect(Collectors.toList());
    }

    @PostMapping("/add-new")
    public String addNewValue(@RequestParam String field, @RequestParam String value) {
        boolean added = service.addNewValue(field, value);
        if (added) return "OK";
        throw new RuntimeException("Value already exists or invalid field");
    }

    @GetMapping("/by-ccn")
    public PurchaseOrder getByCcn(@RequestParam String ccn) {
        final String q = ccn == null ? null : ccn.trim();
        if (q == null || q.isEmpty()) return null;
        return service.getAll().stream()
            .filter(po -> po.getCcn() != null && po.getCcn().trim().equalsIgnoreCase(q))
            .findFirst()
            .orElse(null);
    }

    @GetMapping
    public List<PurchaseOrder> getAll() {
        List<PurchaseOrder> all = service.getAll();
        return all.stream()
            .filter(po -> {
                String ccn = po.getCcn();
                if (ccn == null || ccn.trim().isEmpty()) return false;
                String t = ccn.trim();
                // exclude if Kickoff has this CCN
                boolean inKickoff = kickoffService.getAll().stream()
                    .anyMatch(k -> k.getCcn() != null && k.getCcn().trim().equalsIgnoreCase(t));
                // exclude if Machine exists with this CCN
                boolean inMachine = machineService.getByCcn(t).isPresent();
                return !inKickoff && !inMachine;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<PurchaseOrder> getAllRaw() {
        // return full list of PurchaseOrders (no additional filtering)
        return service.getAll();
    }

    // Return all swap records (latest first) so clients can poll DB instead of using localStorage
    @GetMapping("/swap-records")
    public List<SwapRecord> getSwapRecords() {
        List<SwapRecord> all = swapRecordRepository.findAll();
        all.sort((a, b) -> {
            if (a.getTimestamp() == null && b.getTimestamp() == null) return 0;
            if (a.getTimestamp() == null) return 1;
            if (b.getTimestamp() == null) return -1;
            return b.getTimestamp().compareTo(a.getTimestamp());
        });
        return all;
    }

    // Return a minimal payload with the latest swap timestamp and CCNs
    @GetMapping("/latest-swap")
    public Map<String, Object> getLatestSwap() {
        List<SwapRecord> all = swapRecordRepository.findAll();
        if (all == null || all.isEmpty()) {
            return Map.of("timestamp", "", "leftCcn", "", "rightCcn", "", "remark", "");
        }
        SwapRecord latest = all.stream()
            .filter(Objects::nonNull)
            .max(Comparator.comparing(SwapRecord::getTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())))
            .orElse(all.get(0));
        Map<String, Object> out = new HashMap<>();
        out.put("timestamp", latest.getTimestamp() != null ? latest.getTimestamp().toString() : "");
        out.put("leftCcn", latest.getLeftCcn() != null ? latest.getLeftCcn() : "");
        out.put("rightCcn", latest.getRightCcn() != null ? latest.getRightCcn() : "");
        out.put("remark", latest.getRemark() != null ? latest.getRemark() : "");
        return out;
    }

    @GetMapping("/monthly-report")
    public List<PurchaseOrder> getMonthlyReport(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        java.time.LocalDate now = java.time.LocalDate.now();
        int y = (year != null) ? year : now.getYear();
        if (month == null) {
            // Return all data for the selected year
            return service.getReportsByYear(y);
        } else {
            return service.getReportsByMonth(y, month);
        }
    }

    // DTO for update request
    public static class UpdateByCcnRequest {
        public String ccn;
        public PurchaseOrder updated;
        public String remark;
    }
    
        class SwapResponse {
            private boolean success;
            private String message;
            public SwapResponse(boolean success, String message) { this.success = success; this.message = message; }
            public boolean isSuccess() { return success; }
            public String getMessage() { return message; }
        }

    @PostMapping("/update-by-ccn")
    public PurchaseOrder updateByCcn(@RequestBody UpdateByCcnRequest req) {
        if (req == null || req.ccn == null || req.ccn.trim().isEmpty()) {
            throw new RuntimeException("ccn required");
        }
        String ccn = req.ccn.trim();
        // find existing PO
        final String ccnTrim = ccn.trim();
        PurchaseOrder existing = service.getAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(ccnTrim))
            .findFirst().orElse(null);
        if (existing == null) {
            throw new RuntimeException("PurchaseOrder with CCN not found: " + ccn);
        }
        PurchaseOrder upd = req.updated != null ? req.updated : new PurchaseOrder();
        // copy fields only if provided (avoid wiping nulls)
        if (upd.getRecordNo() != null) existing.setRecordNo(upd.getRecordNo());
        if (upd.getTimestamp() != null) existing.setTimestamp(upd.getTimestamp());
        if (upd.getSalesPerson() != null) existing.setSalesPerson(upd.getSalesPerson());
        if (upd.getPoReferenceNumber() != null) existing.setPoReferenceNumber(upd.getPoReferenceNumber());
        if (upd.getPoDate() != null) existing.setPoDate(upd.getPoDate());
        if (upd.getPoDeliveryDate() != null) existing.setPoDeliveryDate(upd.getPoDeliveryDate());
        if (upd.getPoValueLacs() != null) existing.setPoValueLacs(upd.getPoValueLacs());
        if (upd.getCustomerName() != null) existing.setCustomerName(upd.getCustomerName());
        if (upd.getMachineName() != null) existing.setMachineName(upd.getMachineName());
        if (upd.getModelName() != null) existing.setModelName(upd.getModelName());
        if (upd.getMachineCategory() != null) existing.setMachineCategory(upd.getMachineCategory());
        if (upd.getOrderType() != null) existing.setOrderType(upd.getOrderType());
        if (upd.getCountry() != null) existing.setCountry(upd.getCountry());
        if (upd.getState() != null) existing.setState(upd.getState());
        if (upd.getCity() != null) existing.setCity(upd.getCity());
        if (upd.getAddressLine1() != null) existing.setAddressLine1(upd.getAddressLine1());
        if (upd.getAddressLine2() != null) existing.setAddressLine2(upd.getAddressLine2());
        if (req.remark != null) existing.setRemarks(req.remark);
        // save PO
        PurchaseOrder savedPo = service.save(existing);

        // Sync PO updates to MachineCcnProject
        machineCcnProjectService.syncFromPurchaseOrder(savedPo);

        // Update KickoffForm if exists (map common fields)
        kickoffService.getAll().stream()
            .filter(k -> k.getCcn() != null && k.getCcn().trim().equalsIgnoreCase(ccnTrim))
            .findFirst().ifPresent(k -> {
                if (savedPo.getCustomerName() != null) k.setCustomerName(savedPo.getCustomerName());
                if (savedPo.getMachineName() != null) k.setMachineName(savedPo.getMachineName());
                if (savedPo.getModelName() != null) k.setMachineModel(savedPo.getModelName());
                if (savedPo.getPoDeliveryDate() != null) k.setPoDeliveryDate(savedPo.getPoDeliveryDate());
                if (savedPo.getOrderType() != null) k.setOrderType(savedPo.getOrderType());
                if (savedPo.getMachineCategory() != null) k.setMachineCategory(savedPo.getMachineCategory());
                // map address from PO to kickoff (concatenate country, state, city, address lines)
                StringBuilder addrSb = new StringBuilder();
                if (savedPo.getCountry() != null && !savedPo.getCountry().trim().isEmpty()) {
                    addrSb.append(savedPo.getCountry().trim());
                }
                if (savedPo.getState() != null && !savedPo.getState().trim().isEmpty()) {
                    if (addrSb.length() > 0) addrSb.append(", ");
                    addrSb.append(savedPo.getState().trim());
                }
                if (savedPo.getCity() != null && !savedPo.getCity().trim().isEmpty()) {
                    if (addrSb.length() > 0) addrSb.append(", ");
                    addrSb.append(savedPo.getCity().trim());
                }
                if (savedPo.getAddressLine1() != null && !savedPo.getAddressLine1().trim().isEmpty()) {
                    if (addrSb.length() > 0) addrSb.append(", ");
                    addrSb.append(savedPo.getAddressLine1().trim());
                }
                if (savedPo.getAddressLine2() != null && !savedPo.getAddressLine2().trim().isEmpty()) {
                    if (addrSb.length() > 0) addrSb.append(", ");
                    addrSb.append(savedPo.getAddressLine2().trim());
                }
                String addr = addrSb.toString();
                if (!addr.isEmpty()) k.setAddress(addr);

                kickoffService.save(k);
            });

       // Update Machine record if exists (or create minimal one)
        machineService.getByCcn(ccn).ifPresent(m -> {
            if (savedPo.getCustomerName() != null) m.setCustomerName(savedPo.getCustomerName());
            if (savedPo.getMachineName() != null) m.setMachineName(savedPo.getMachineName());
            if (savedPo.getModelName() != null) m.setModel(savedPo.getModelName());
            if (savedPo.getPoDeliveryDate() != null) m.setPoDeliveryDate(savedPo.getPoDeliveryDate());
            if (savedPo.getOrderType() != null) m.setOrderType(savedPo.getOrderType());
            if (savedPo.getMachineCategory() != null) m.setMachineCategory(savedPo.getMachineCategory());
            if (savedPo.getPoValueLacs() != null) m.setPoValueLacs(savedPo.getPoValueLacs());
            // build concatenated address: Country, State, City, AddressLine1, AddressLine2
            StringBuilder mAddr = new StringBuilder();
            if (savedPo.getCountry() != null && !savedPo.getCountry().trim().isEmpty()) {
                mAddr.append(savedPo.getCountry().trim());
            }
            if (savedPo.getState() != null && !savedPo.getState().trim().isEmpty()) {
                if (mAddr.length() > 0) mAddr.append(", ");
                mAddr.append(savedPo.getState().trim());
            }
            if (savedPo.getCity() != null && !savedPo.getCity().trim().isEmpty()) {
                if (mAddr.length() > 0) mAddr.append(", ");
                mAddr.append(savedPo.getCity().trim());
            }
            if (savedPo.getAddressLine1() != null && !savedPo.getAddressLine1().trim().isEmpty()) {
                if (mAddr.length() > 0) mAddr.append(", ");
                mAddr.append(savedPo.getAddressLine1().trim());
            }
            if (savedPo.getAddressLine2() != null && !savedPo.getAddressLine2().trim().isEmpty()) {
                if (mAddr.length() > 0) mAddr.append(", ");
                mAddr.append(savedPo.getAddressLine2().trim());
            }
            if (mAddr.length() > 0) m.setAddress(mAddr.toString());
            machineService.save(m);
        });

        return savedPo;
    }
class CCNCheckResponse {
    public boolean exists;
    public List<String> suggestions;
    public CCNCheckResponse(boolean exists, List<String> suggestions) {
        this.exists = exists;
        this.suggestions = suggestions;
    }
}
}
