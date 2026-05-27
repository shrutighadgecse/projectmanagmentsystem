package com.example.demo.controller;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.KickoffForm;
import com.example.demo.model.Machine;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.KickoffFormService;
import com.example.demo.service.MachineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.*;
import java.time.Year;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/machine")
public class MachineController {
    @Autowired
    private PurchaseOrderService poService;
    @Autowired
    private KickoffFormService kickoffService;

    @Autowired
    private MachineService machineService;

    @GetMapping("/by-ccn")
    public Map<String, Object> getByCcn(@RequestParam String ccn) {
        Map<String, Object> result = new HashMap<>();
        final String q = ccn == null ? null : ccn.trim();
        if (q == null || q.isEmpty()) {
            result.put("po", null);
            result.put("kickoff", null);
            result.put("machine", null);
            result.put("poAddress", null);
            result.put("kickoffAddress", null);
            return result;
        }
        PurchaseOrder po = poService.getAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(q))
            .findFirst().orElse(null);
        KickoffForm kickoff = kickoffService.getAll().stream()
            .filter(k -> k.getCcn() != null && k.getCcn().trim().equalsIgnoreCase(q))
            .findFirst().orElse(null);
        Machine machine = machineService.getByCcn(q).orElse(null);

        // build concatenated PO address: Country, State, City, AddressLine1, AddressLine2
        String poAddress = null;
        if (po != null) {
            StringBuilder sb = new StringBuilder();
            if (po.getCountry() != null && !po.getCountry().trim().isEmpty()) sb.append(po.getCountry().trim());
            if (po.getState() != null && !po.getState().trim().isEmpty()) {
                if (sb.length() > 0) sb.append(", "); sb.append(po.getState().trim());
            }
            if (po.getCity() != null && !po.getCity().trim().isEmpty()) {
                if (sb.length() > 0) sb.append(", "); sb.append(po.getCity().trim());
            }
            if (po.getAddressLine1() != null && !po.getAddressLine1().trim().isEmpty()) {
                if (sb.length() > 0) sb.append(", "); sb.append(po.getAddressLine1().trim());
            }
            if (po.getAddressLine2() != null && !po.getAddressLine2().trim().isEmpty()) {
                if (sb.length() > 0) sb.append(", "); sb.append(po.getAddressLine2().trim());
            }
            poAddress = sb.length() > 0 ? sb.toString() : null;
        }
        // If machine exists but has no address, prefer PO concatenated address then kickoff.address
        String kickoffAddress = kickoff != null ? kickoff.getAddress() : null;
        if (machine != null) {
            if ((machine.getAddress() == null || machine.getAddress().trim().isEmpty())) {
                if (poAddress != null) machine.setAddress(poAddress);
                else if (kickoffAddress != null && !kickoffAddress.trim().isEmpty()) machine.setAddress(kickoffAddress);
            }
        }

        result.put("po", po);
        result.put("kickoff", kickoff);
        result.put("machine", machine);
        result.put("poAddress", poAddress);
        result.put("kickoffAddress", kickoffAddress);
        result.put("machineBasics", kickoff != null ? kickoff.getMachineBasics() : null);
        result.put("machineFeatures", kickoff != null ? kickoff.getMachineFeatures() : null);
        result.put("additionalOptions", kickoff != null ? kickoff.getAdditionalOptions() : null);
        return result;
    }

    @PostMapping("/submit")
    public Machine submitMachine(@RequestBody Machine machine) {
        // Find by CCN, update if exists, else create new
        Optional<Machine> existing = machineService.getByCcn(machine.getCcn());
        // If client tries to set CCN to close/closed but invoiceDate or other required fields are missing -> disallow
        boolean wantsClose = machine.getCcnStatus() != null &&
                (machine.getCcnStatus().equalsIgnoreCase("close") || machine.getCcnStatus().equalsIgnoreCase("closed"));
        boolean hasInvoice = machine.getInvoiceDate() != null && !machine.getInvoiceDate().trim().isEmpty();

        // required assy/dispatch fields - treat as strings; adjust if model types differ
        boolean hasActualMaterialReceiptDate = machine.getActualMaterialReceiptDate() != null && !machine.getActualMaterialReceiptDate().trim().isEmpty();
        boolean hasActualDynamicManufacturingPlan = machine.getActualDynamicManufacturingPlan() != null && !machine.getActualDynamicManufacturingPlan().trim().isEmpty();
        boolean hasActualDynamicDispatchPlan = machine.getActualDynamicDispatchPlan() != null && !machine.getActualDynamicDispatchPlan().trim().isEmpty();
        boolean hasActualAssyStartDate = machine.getActualAssyStartDate() != null && !machine.getActualAssyStartDate().trim().isEmpty();
        boolean hasActualIfatDate = machine.getActualIfatDate() != null && !machine.getActualIfatDate().trim().isEmpty();
        boolean hasActualCpDate = machine.getActualCpDate() != null && !machine.getActualCpDate().trim().isEmpty();
        boolean hasActualCfatDate = machine.getActualCfatDate() != null && !machine.getActualCfatDate().trim().isEmpty();
        boolean hasComplianceClosureDate = machine.getComplianceClosureDate() != null && !machine.getComplianceClosureDate().trim().isEmpty();
        boolean hasMechNonIssuance = machine.getMechNonIssuance() != null && !machine.getMechNonIssuance().trim().isEmpty();
        boolean hasElectNonIssuance = machine.getElectNonIssuance() != null && !machine.getElectNonIssuance().toString().trim().isEmpty();
        boolean hasHandedToDispatchDept = machine.getHandedToDispatchDept() != null && !machine.getHandedToDispatchDept().trim().isEmpty();

        boolean hasAllMandatory = hasInvoice
                && hasActualMaterialReceiptDate
                && hasActualDynamicManufacturingPlan
                && hasActualDynamicDispatchPlan
                && hasActualAssyStartDate
                && hasActualIfatDate
                && hasActualCpDate
                && hasActualCfatDate
                && hasComplianceClosureDate
                && hasMechNonIssuance
                && hasElectNonIssuance
                && hasHandedToDispatchDept;

        if (existing.isPresent()) {
            Machine old = existing.get();
            // Ensure CCN isn't lost if incoming payload doesn't include it
            if (machine.getCcn() == null || machine.getCcn().trim().isEmpty()) {
                machine.setCcn(old.getCcn());
            }
            // If trying to close without all mandatory fields, preserve previous ccnStatus
            if (wantsClose && !hasAllMandatory) {
                machine.setCcnStatus(old.getCcnStatus());
            }
            // Copy all incoming properties onto the existing entity, but preserve the id
            // and preserve the original timestamp (do not overwrite it on update)
            org.springframework.beans.BeanUtils.copyProperties(machine, old, "id", "timestamp");
            return machineService.save(old);
        }
        // New record: if client requests close but mandatory fields absent, do not allow closing on creation
        if (wantsClose && !hasAllMandatory) {
            machine.setCcnStatus("Live"); // default to Live for safety
        }
        // New record: save as-is
        return machineService.save(machine);
    }

    @GetMapping("/live-status-summary")
    public Map<String, Integer> getLiveStatusSummary(@RequestParam(required = false) Integer year) {
        String[] allStatuses = {
   "NEW CCN FOR APPROVAL","DESIGN","IN MUMBAI","RECEIVED FROM MUMBAI","PPC",
            "YET TO PLAN","WIP- KANBAN","WIP -BASIC","WIP- OPTIONALS","HOLD - AUTOMATION PENDING",
            "HOLD","STD CP TRIALS","IFAT IN PROGRESS","IFAT DONE","POST IFAT - POINT CLOSURE",
            "READY","AWAITED PRODUCT","AWAITED PACKAGING","AWAITED CP",
            "CUSTOMER CP TRIALS","AWAITED CFAT","CFAT IN PROGRESS","CFAT DONE","RE-CFAT","POST CFAT - POINT CLOSURE","COMPLIANCE - SENT TO CUSTOMER",
            "AWAITING PACKING CLEARLANCE","FOR DISPATCH","AWAITING COMMERCIAL CLEARLANCE","CLOSE TOTAL", "LIVE TOTAL" ,  "STOCK MC - READY"
        };
        // preserve order of canonical statuses
        Map<String, Integer> summary = new java.util.LinkedHashMap<>();
        for (String st : allStatuses) summary.put(st, 0);

        // map canonical lower->original for case-insensitive matching
        Map<String, String> canonicalByLower = new HashMap<>();
        for (String st : allStatuses) canonicalByLower.put(st.trim().toLowerCase(), st);

        // Count live-status occurrences BUT exclude machines whose ccnStatus is close/closed
        List<Machine> machines = machineService.getAll();
        for (Machine m : machines) {
            // Filter by year if provided
            if (year != null && year > 0) {
                int extractedYear = extractYear(m.getInvoiceDate());
                if (extractedYear < 0) extractedYear = extractYear(m.getPoDate());
                if (extractedYear < 0) extractedYear = extractYear(m.getMachineManufacturingBasic());
                if (extractedYear < 0) extractedYear = extractYear(m.getActualAssyStartDate());
                if (extractedYear != year) continue; // Skip if year doesn't match
            }
            
            String cs = m.getCcnStatus();
            if (cs != null && (cs.equalsIgnoreCase("close") || cs.equalsIgnoreCase("closed"))) {
                // skip closed CCNs so they DO NOT appear in any live-status bucket
                continue;
            }
            String ls = m.getLiveStatus();
            if (ls == null) continue;
            String keyLower = ls.trim().toLowerCase();
            if (canonicalByLower.containsKey(keyLower)) {
                String canonical = canonicalByLower.get(keyLower);
                summary.put(canonical, summary.getOrDefault(canonical, 0) + 1);
            } else {
                // unknown status: append / accumulate under its display value
                String display = ls.trim();
                summary.put(display, summary.getOrDefault(display, 0) + 1);
            }
        }

        // liveTotal and closeTotal are calculated from ccnStatus values
        int liveTotal = (int) machines.stream()
            .filter(m -> {
                if (year != null && year > 0) {
                    int extractedYear = extractYear(m.getInvoiceDate());
                    if (extractedYear < 0) extractedYear = extractYear(m.getPoDate());
                    if (extractedYear < 0) extractedYear = extractYear(m.getMachineManufacturingBasic());
                    if (extractedYear < 0) extractedYear = extractYear(m.getActualAssyStartDate());
                    if (extractedYear != year) return false;
                }
                return m.getCcnStatus() != null && m.getCcnStatus().equalsIgnoreCase("live");
            })
            .count();
        int closeTotal = (int) machines.stream()
            .filter(m -> {
                if (year != null && year > 0) {
                    int extractedYear = extractYear(m.getInvoiceDate());
                    if (extractedYear < 0) extractedYear = extractYear(m.getPoDate());
                    if (extractedYear < 0) extractedYear = extractYear(m.getMachineManufacturingBasic());
                    if (extractedYear < 0) extractedYear = extractYear(m.getActualAssyStartDate());
                    if (extractedYear != year) return false;
                }
                String s = m.getCcnStatus();
                return s != null && (s.equalsIgnoreCase("close") || s.equalsIgnoreCase("closed"));
            }).count();
        // ensure the canonical keys are used
        summary.put("LIVE TOTAL", liveTotal);
        summary.put("CLOSE TOTAL", closeTotal);
        return summary;
    }

    @GetMapping("/available-years")
    public List<Integer> getAvailableYears() {
        List<Machine> machines = machineService.getAll();
        java.util.Set<Integer> yearSet = new java.util.TreeSet<>((a, b) -> b.compareTo(a)); // Sort descending
        
        for (Machine m : machines) {
            int year = extractYear(m.getInvoiceDate());
            if (year < 0) year = extractYear(m.getPoDate());
            if (year < 0) year = extractYear(m.getMachineManufacturingBasic());
            if (year < 0) year = extractYear(m.getActualAssyStartDate());
            
            if (year > 0) {
                yearSet.add(year);
            }
        }
        
        return new java.util.ArrayList<>(yearSet);
    }

    @GetMapping("/all")
    public List<Machine> getAllMachinesDesc() {
        return machineService.getAllOrderByTimestampDesc();
    }

    // helper to extract 4-digit year from date-like strings; returns -1 if none
    private int extractYear(String s) {
        if (s == null) return -1;
        Matcher m = Pattern.compile("\\b(20\\d{2}|19\\d{2})\\b").matcher(s);
        if (m.find()) {
            try { return Integer.parseInt(m.group(1)); } catch (Exception e) { return -1; }
        }
        // fallback: parse patterns like yyyy-mm-dd or dd-mm-yyyy by splitting
        String t = s.replace("/", "-");
        String[] parts = t.split("-");
        if (parts.length == 3) {
            // try each part for 4-digit year
            for (String p : parts) {
                if (p.length() == 4) {
                    try { return Integer.parseInt(p); } catch (Exception e) {}
                }
            }
        }
        return -1;
    }

    // helper to extract month (1-12) from a string; returns -1 if unknown
    private int extractMonth(String s) {
        if (s == null) return -1;
        Matcher m = Pattern.compile("\\b(0?[1-9]|1[0-2])\\b").matcher(s);
        // attempt to find month-like token (this is heuristic)
        if (m.find()) {
            try { return Integer.parseInt(m.group(1)); } catch (Exception e) { return -1; }
        }
        // fallback: tokens when format is yyyy-mm-dd or dd-mm-yyyy
        String t = s.replace("/", "-");
        String[] parts = t.split("-");
        if (parts.length == 3) {
            // if first part is year, month is second; if last part is year, month is middle
            if (parts[0].length() == 4) {
                try { return Integer.parseInt(parts[1]); } catch (Exception e) {}
            } else if (parts[2].length() == 4) {
                try { return Integer.parseInt(parts[1]); } catch (Exception e) {}
            }
        }
        return -1;
    }

    @GetMapping("/card-summary")
    public Map<String, Object> getCardSummary(@RequestParam(required = false) Integer year) {
        List<Machine> machines = machineService.getAll();
        Map<String, Object> result = new HashMap<>();
        int targetYear = (year == null) ? Year.now().getValue() : year;

        for (Machine m : machines) {
            String family = m.getMachineFamily();
            if (family == null || family.trim().isEmpty()) family = "Unknown";
            family = family.trim();

            // initialize family card if absent
            @SuppressWarnings("unchecked")
            Map<String, Object> card = (Map<String, Object>) result.get(family);
            if (card == null) {
                card = new HashMap<>();
                card.put("manufacturing", 0);
                card.put("shopfloor", 0);
                card.put("dispatched", 0);
                card.put("stock", 0);
                card.put("customized", 0);
                card.put("monthlyManufacturing", new int[12]); // Jan-Dec
                card.put("monthlyDispatched", new int[12]);
                result.put(family, card);
            }

            // helpers to manipulate counts and arrays
            int[] monthlyManufacturing = (int[]) card.get("monthlyManufacturing");
            int[] monthlyDispatched = (int[]) card.get("monthlyDispatched");

            // MANUFACTURING: based on machine.timestamp (year filter)
            int tsYear = extractYear(m.getActualAssyStartDate());
            if (tsYear == targetYear) {
                card.put("manufacturing", (int) card.get("manufacturing") + 1);
                int mon = extractMonth(m.getActualAssyStartDate());
                if (mon >= 1 && mon <= 12) monthlyManufacturing[mon - 1]++;
            }

            // SHOPFLOOR: machines whose actualAssyStartDate is in targetYear and ccnStatus = live
            int assyYear = extractYear(m.getActualAssyStartDate());
            if (assyYear == targetYear && m.getCcnStatus() != null && m.getCcnStatus().equalsIgnoreCase("live")) {
                card.put("shopfloor", (int) card.get("shopfloor") + 1);
                // customized vs stock split among live shopfloor records
                boolean isStock = false;
                if (m.getOrderType() != null && m.getOrderType().toLowerCase().contains("stock")) isStock = true;
                if (m.getMachineCategory() != null && m.getMachineCategory().toLowerCase().contains("stock")) isStock = true;
                if (isStock) card.put("stock", (int) card.get("stock") + 1);
                else card.put("customized", (int) card.get("customized") + 1);
            }

            // DISPATCHED: use invoiceDate year AND ccnStatus close/closed
            int invYear = extractYear(m.getInvoiceDate());
            String cs = m.getCcnStatus();
            boolean isClosed = cs != null && (cs.equalsIgnoreCase("close") || cs.equalsIgnoreCase("closed"));
            if (invYear == targetYear && isClosed) {
                card.put("dispatched", (int) card.get("dispatched") + 1);
                int mon = extractMonth(m.getInvoiceDate());
                if (mon >= 1 && mon <= 12) monthlyDispatched[mon - 1]++;
            }

            // re-put arrays explicitly
            card.put("monthlyManufacturing", monthlyManufacturing);
            card.put("monthlyDispatched", monthlyDispatched);
        }

        return result;
    }

    // New endpoint: return machines for a family/year/operation (op values: manufacturing, shopfloor, dispatched, customized, stock)
    @GetMapping("/records")
    public List<Machine> getRecords(@RequestParam String family,
                                    @RequestParam(required = false) Integer year,
                                    @RequestParam String op) {
        final int targetYear = (year == null) ? Year.now().getValue() : year;
        final String fam = (family == null || family.trim().isEmpty()) ? "Unknown" : family.trim();

        return machineService.getAll().stream()
                .filter(m -> {
                    String f = m.getMachineFamily();
                    if (f == null || f.trim().isEmpty()) f = "Unknown";
                    return f.trim().equalsIgnoreCase(fam);
                })
                .filter(m -> {
                    String operation = op == null ? "" : op.trim().toLowerCase();
                    switch (operation) {
                        case "manufacturing":
                            return extractYear(m.getTimestamp()) == targetYear;
                        case "shopfloor":
                            return extractYear(m.getActualAssyStartDate()) == targetYear
                                    && m.getCcnStatus() != null && m.getCcnStatus().equalsIgnoreCase("live");
                        case "dispatched":
                            return extractYear(m.getInvoiceDate()) == targetYear
                                    && m.getCcnStatus() != null && (m.getCcnStatus().equalsIgnoreCase("close") || m.getCcnStatus().equalsIgnoreCase("closed"));
                        case "customized":
                            return extractYear(m.getActualAssyStartDate()) == targetYear
                                    && m.getCcnStatus() != null && m.getCcnStatus().equalsIgnoreCase("live")
                                    && !(m.getOrderType() != null && m.getOrderType().toLowerCase().contains("stock"))
                                    && !(m.getMachineCategory() != null && m.getMachineCategory().toLowerCase().contains("stock"));
                        case "stock":
                            return extractYear(m.getActualAssyStartDate()) == targetYear
                                    && m.getCcnStatus() != null && m.getCcnStatus().equalsIgnoreCase("live")
                                    && ((m.getOrderType() != null && m.getOrderType().toLowerCase().contains("stock"))
                                        || (m.getMachineCategory() != null && m.getMachineCategory().toLowerCase().contains("stock")));
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/by-live-status")
    public List<Machine> getMachinesByLiveStatus(@RequestParam String status) {
        final String s = status == null ? "" : status.trim();
        return machineService.getAll().stream()
                // status match
                .filter(m -> m.getLiveStatus() != null && m.getLiveStatus().trim().equalsIgnoreCase(s))
                // exclude closed CCNs so closed machines won't appear under live statuses
                .filter(m -> {
                    String cs = m.getCcnStatus();
                    return cs == null || !(cs.equalsIgnoreCase("close") || cs.equalsIgnoreCase("closed"));
                })
                .collect(Collectors.toList());
    }

    // New: return machines by CCN status (e.g. "live", "close")
    @GetMapping("/by-ccn-status")
    public List<Machine> getMachinesByCcnStatus(@RequestParam String ccnStatus) {
        final String s = ccnStatus == null ? "" : ccnStatus.trim();
        final String sLower = s.toLowerCase();
        // Handle common variants explicitly
        if ("close".equals(sLower) || "closed".equals(sLower)) {
            return machineService.getAll().stream()
                    .filter(m -> {
                        String cs = m.getCcnStatus();
                        return cs != null && (cs.equalsIgnoreCase("close") || cs.equalsIgnoreCase("closed"));
                    })
                    .collect(Collectors.toList());
        }
        if ("live".equals(sLower)) {
            return machineService.getAll().stream()
                    .filter(m -> {
                        String cs = m.getCcnStatus();
                        return cs != null && cs.equalsIgnoreCase("live");
                    })
                    .collect(Collectors.toList());
        }
        // Fallback: case-insensitive equality for any provided value
        return machineService.getAll().stream()
                .filter(m -> {
                    String cs = m.getCcnStatus();
                    return cs != null && cs.trim().equalsIgnoreCase(s);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/plan-comparison")
    public Map<String, List<Map<String, Object>>> getPlanComparison() {
        List<Machine> machines = machineService.getAll();
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        
        // Manufacturing plan comparison
        List<Map<String, Object>> manufacturingComparison = machines.stream()
            .filter(m -> m.getMachineManufacturingBasic() != null && m.getActualAssyStartDate() != null)
            .map(m -> {
                Map<String, Object> data = new HashMap<>();
                data.put("ccn", m.getCcn());
                data.put("planDate", m.getMachineManufacturingBasic());
                data.put("actualDate", m.getActualAssyStartDate());
                // Calculate days difference
                try {
                    LocalDate planDate = LocalDate.parse(m.getMachineManufacturingBasic());
                    LocalDate actualDate = LocalDate.parse(m.getActualAssyStartDate());
                    long days = ChronoUnit.DAYS.between(planDate, actualDate);
                    data.put("days", days);
                } catch (Exception e) {
                    data.put("days", null);
                }
                return data;
            })
            .collect(Collectors.toList());

        // Dispatch plan comparison
        List<Map<String, Object>> dispatchComparison = machines.stream()
            .filter(m -> m.getPlannedDispatchDate() != null && m.getInvoiceDate() != null)
            .map(m -> {
                Map<String, Object> data = new HashMap<>();
                data.put("ccn", m.getCcn());
                data.put("planDate", m.getPlannedDispatchDate());
                data.put("actualDate", m.getInvoiceDate());
                // Calculate days difference
                try {
                    LocalDate planDate = LocalDate.parse(m.getPlannedDispatchDate());
                    LocalDate actualDate = LocalDate.parse(m.getInvoiceDate());
                    long days = ChronoUnit.DAYS.between(planDate, actualDate);
                    data.put("days", days);
                } catch (Exception e) {
                    data.put("days", null);
                }
                return data;
            })
            .collect(Collectors.toList());

        result.put("manufacturing", manufacturingComparison);
        result.put("dispatch", dispatchComparison);
        return result;
    }
}
