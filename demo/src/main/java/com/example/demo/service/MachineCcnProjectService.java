package com.example.demo.service;

import com.example.demo.dto.DashboardResponseDTO;
import com.example.demo.dto.MachineCcnProjectResponseDTO;
import com.example.demo.model.KickoffForm;
import com.example.demo.model.MachineCcnProject;
import com.example.demo.model.PurchaseOrder;
import com.example.demo.repository.KickoffFormRepository;
import com.example.demo.repository.MachineCcnProjectRepository;
import com.example.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MachineCcnProjectService {

    @Autowired
    private MachineCcnProjectRepository machineCcnProjectRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private KickoffFormRepository kickoffFormRepository;

    private static final Logger logger = Logger.getLogger(MachineCcnProjectService.class.getName());

    public MachineCcnProjectResponseDTO getByCcn(String ccn) {
        if (ccn == null || ccn.trim().isEmpty()) {
            throw new IllegalArgumentException("CCN cannot be null or empty");
        }
        String normalized = ccn.trim();
        logger.info("Fetching Machine CCN Project data for CCN: " + normalized);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByCcnIgnoreCase(normalized).orElse(null);
        KickoffForm kickoffForm = kickoffFormRepository.findByCcnIgnoreCase(normalized).orElse(null);
        MachineCcnProject machineCcnProject = machineCcnProjectRepository.findByCcnIgnoreCase(normalized).orElse(null);

        if (machineCcnProject == null) {
            machineCcnProject = new MachineCcnProject();
            machineCcnProject.setCcn(normalized);
            machineCcnProject.setFormLastSavedDate(LocalDate.now());
        }

        return new MachineCcnProjectResponseDTO(machineCcnProject, purchaseOrder, kickoffForm);
    }

    public MachineCcnProject save(MachineCcnProject machineCcnProject) {
        if (machineCcnProject == null || machineCcnProject.getCcn() == null || machineCcnProject.getCcn().trim().isEmpty()) {
            throw new IllegalArgumentException("CCN is mandatory and cannot be empty");
        }

        String normalized = machineCcnProject.getCcn().trim();
        logger.info("Saving Machine CCN Project for CCN: " + normalized);

        validateFinalDateLocks(machineCcnProject);
        handleDateExtensions(machineCcnProject);

        Optional<MachineCcnProject> existing = machineCcnProjectRepository.findByCcnIgnoreCase(normalized);
        if (existing.isPresent()) {
            MachineCcnProject stored = existing.get();
            stored.setActualOaGenerationDate(machineCcnProject.getActualOaGenerationDate());
            stored.setActualCpOaGenerationDate(machineCcnProject.getActualCpOaGenerationDate());
            stored.setActualAdvPaymentRecdDate(machineCcnProject.getActualAdvPaymentRecdDate());
            stored.setActualBalPaymentRecdDate(machineCcnProject.getActualBalPaymentRecdDate());
            stored.setActualCpLayoutApprovalDate(machineCcnProject.getActualCpLayoutApprovalDate());
            stored.setActualOptionalCpLayoutApprovalDate(machineCcnProject.getActualOptionalCpLayoutApprovalDate());
            stored.setActualMachineLayoutApprovalDate(machineCcnProject.getActualMachineLayoutApprovalDate());
            stored.setActualRoomLayoutApprovalDate(machineCcnProject.getActualRoomLayoutApprovalDate());
            stored.setActualDispatchClearanceRecdDate(machineCcnProject.getActualDispatchClearanceRecdDate());
            stored.setActualCommercialClearanceDate(machineCcnProject.getActualCommercialClearanceDate());
            stored.setActualDqReleaseDate(machineCcnProject.getActualDqReleaseDate());
            stored.setActualPpcBasicMaterialDate(machineCcnProject.getActualPpcBasicMaterialDate());
            stored.setActualPpcOptionalMaterialDate(machineCcnProject.getActualPpcOptionalMaterialDate());
            stored.setActualCpDesignReleaseDate(machineCcnProject.getActualCpDesignReleaseDate());
            stored.setActualMachineCpManufacturingDate(machineCcnProject.getActualMachineCpManufacturingDate());
            stored.setActualMachineOptionalCpManufacturingDate(machineCcnProject.getActualMachineOptionalCpManufacturingDate());
            stored.setActualMachineBasicCompletion(machineCcnProject.getActualMachineBasicCompletion());
            stored.setActualMachineCustomizationCompletion(machineCcnProject.getActualMachineCustomizationCompletion());
            stored.setActualAssyStartDate(machineCcnProject.getActualAssyStartDate());
            stored.setActualIfatStdCpDate(machineCcnProject.getActualIfatStdCpDate());
            stored.setActualIfatCustomerCpDate(machineCcnProject.getActualIfatCustomerCpDate());
            stored.setActualPmrSubmissionDate(machineCcnProject.getActualPmrSubmissionDate());
            stored.setActualProductReceiptDate(machineCcnProject.getActualProductReceiptDate());
            stored.setActualPackagingReceiptDate(machineCcnProject.getActualPackagingReceiptDate());
            stored.setActualIfatProtocolReleaseDate(machineCcnProject.getActualIfatProtocolReleaseDate());
            stored.setActualCfatDate(machineCcnProject.getActualCfatDate());
            stored.setActualComplianceDate(machineCcnProject.getActualComplianceDate());
            stored.setActualPackingClearanceRecdDate(machineCcnProject.getActualPackingClearanceRecdDate());
            stored.setActualBasicMaterialHandedOverAssyDate(machineCcnProject.getActualBasicMaterialHandedOverAssyDate());
            stored.setActualOptionalMaterialHandedOverAssyDate(machineCcnProject.getActualOptionalMaterialHandedOverAssyDate());
            stored.setActualBasicMaterialReceiptDate(machineCcnProject.getActualBasicMaterialReceiptDate());
            stored.setActualCustomizationMaterialReceiptDate(machineCcnProject.getActualCustomizationMaterialReceiptDate());
            stored.setActualHandedToDispatchDate(machineCcnProject.getActualHandedToDispatchDate());
            stored.setActualDispatchDate(machineCcnProject.getActualDispatchDate());
            if (machineCcnProject.getActualDispatchDateFinal() != null) {
                stored.setActualDispatchDateFinal(machineCcnProject.getActualDispatchDateFinal());
            }
            stored.setActualPpcBasicDate(machineCcnProject.getActualPpcBasicDate());
            stored.setActualPpcOptionalDate(machineCcnProject.getActualPpcOptionalDate());
            stored.setActualLoaBasic(machineCcnProject.getActualLoaBasic());
            stored.setActualLoaOptional(machineCcnProject.getActualLoaOptional());
            stored.setDnNumber(machineCcnProject.getDnNumber());
            stored.setSalesOrderNumber(machineCcnProject.getSalesOrderNumber());
            stored.setMachineCategory(machineCcnProject.getMachineCategory());
            stored.setRemarksSales(machineCcnProject.getRemarksSales());
            stored.setMcnRemarksShortfall(machineCcnProject.getMcnRemarksShortfall());
            stored.setTotalBasicAssemblyAttached(machineCcnProject.getTotalBasicAssemblyAttached());
            stored.setTotalOptionalAssemblyAttached(machineCcnProject.getTotalOptionalAssemblyAttached());
            stored.setTotalAdditionalNewAssemblyAttachedAfterCfat(machineCcnProject.getTotalAdditionalNewAssemblyAttachedAfterCfat());
            stored.setAdditionalNewAssemblyAttachedAfterCfatDate(machineCcnProject.getAdditionalNewAssemblyAttachedAfterCfatDate());
            stored.setAssembliesReleasedBy(machineCcnProject.getAssembliesReleasedBy());
            stored.setRemarksDesign(machineCcnProject.getRemarksDesign());
            stored.setMcnNumber(machineCcnProject.getMcnNumber());
            stored.setMcnWithShortfall(machineCcnProject.getMcnWithShortfall());
            stored.setProductRemark(machineCcnProject.getProductRemark());
            stored.setProductPackagingRemarks(machineCcnProject.getProductPackagingRemarks());
            stored.setCpTrialRemarksTable(machineCcnProject.getCpTrialRemarksTable());
            stored.setMachineCpTrialCompleted(machineCcnProject.getMachineCpTrialCompleted());
            stored.setMachineOptionalCpTrialCompleted(machineCcnProject.getMachineOptionalCpTrialCompleted());
            stored.setMcnShortfallClosureDate(machineCcnProject.getMcnShortfallClosureDate());
            stored.setActualPpcNewMaterialDateAfterCfat(machineCcnProject.getActualPpcNewMaterialDateAfterCfat());
            stored.setMaterialProgressStatus(machineCcnProject.getMaterialProgressStatus());
            stored.setRemarksPpc(machineCcnProject.getRemarksPpc());
            stored.setDelayInDaysBasicMaterial(machineCcnProject.getDelayInDaysBasicMaterial());
            stored.setDelayInDaysOptionalBasicMaterial(machineCcnProject.getDelayInDaysOptionalBasicMaterial());
            stored.setDelayInDaysMachineCp(machineCcnProject.getDelayInDaysMachineCp());
            stored.setDelayInDaysOptionalCp(machineCcnProject.getDelayInDaysOptionalCp());
            stored.setCpProgressStatusTable(machineCcnProject.getCpProgressStatusTable());
            stored.setRemarksCpCell(machineCcnProject.getRemarksCpCell());
            stored.setCpImageUpload(machineCcnProject.getCpImageUpload());
            stored.setRemarksStores(machineCcnProject.getRemarksStores());
            stored.setInvoiceNumber(machineCcnProject.getInvoiceNumber());
            stored.setInvoiceDate(machineCcnProject.getInvoiceDate());
            stored.setLrNo(machineCcnProject.getLrNo());
            stored.setLrDate(machineCcnProject.getLrDate());
            stored.setPodDate(machineCcnProject.getPodDate());
            String incomingStatus = machineCcnProject.getCcnStatus();
            if (incomingStatus != null && (incomingStatus.equalsIgnoreCase("close") || incomingStatus.equalsIgnoreCase("closed"))) {
                boolean hasInvoice = machineCcnProject.getInvoiceDate() != null;
                boolean hasDispatchDate = machineCcnProject.getActualDispatchDate() != null;
                if (!hasInvoice || !hasDispatchDate) {
                    throw new IllegalArgumentException("Cannot set CCN status to 'closed' unless Invoice Date and Actual Dispatch Date are set.");
                }
                stored.setActualDispatchDateFinal(true);
            }
            stored.setCcnStatus(machineCcnProject.getCcnStatus());
            stored.setMachineName(machineCcnProject.getMachineName());
            stored.setProgressStatus(machineCcnProject.getProgressStatus());
            stored.setMachineImageStoresUpload(machineCcnProject.getMachineImageStoresUpload());
            stored.setLastUpdatedDataAsOn(machineCcnProject.getLastUpdatedDataAsOn());
            stored.setWorkmen(machineCcnProject.getWorkmen());
            stored.setAssemblyProgressStatus(machineCcnProject.getAssemblyProgressStatus());
            stored.setPendingFollowUpMech(machineCcnProject.getPendingFollowUpMech());
            stored.setPendingFollowUpElect(machineCcnProject.getPendingFollowUpElect());
            stored.setPendingFollowUpCp(machineCcnProject.getPendingFollowUpCp());
            stored.setDelayAnalysis(machineCcnProject.getDelayAnalysis());
            stored.setMachineImageAssemblyUpload(machineCcnProject.getMachineImageAssemblyUpload());
            stored.setValidationUploaded(machineCcnProject.getValidationUploaded());
            stored.setMatpsUploaded(machineCcnProject.getMatpsUploaded());
            stored.setVideoUploaded(machineCcnProject.getVideoUploaded());
            stored.setTwelveDaysProject(machineCcnProject.getTwelveDaysProject());
            stored.setCcnInterchangedMc(machineCcnProject.getCcnInterchangedMc());
            stored.setInterUnitReceipt(machineCcnProject.getInterUnitReceipt());
            stored.setInterUnitTransfer(machineCcnProject.getInterUnitTransfer());
            stored.setIssueReturnQty(machineCcnProject.getIssueReturnQty());
            stored.setLiveStatus(machineCcnProject.getLiveStatus());
            stored.setStdTask(machineCcnProject.getStdTask());
            stored.setTaskCompleted(machineCcnProject.getTaskCompleted());
            stored.setMachineCompletionPercentage(machineCcnProject.getMachineCompletionPercentage());
            stored.setProjectCompletionPercentage(machineCcnProject.getProjectCompletionPercentage());
            stored.setFormLastSavedDate(LocalDate.now());
            return machineCcnProjectRepository.save(stored);
        }

        machineCcnProject.setCcn(normalized);
        machineCcnProject.setFormLastSavedDate(LocalDate.now());
        String incomingStatus = machineCcnProject.getCcnStatus();
        if (incomingStatus != null && (incomingStatus.equalsIgnoreCase("close") || incomingStatus.equalsIgnoreCase("closed"))) {
            boolean hasInvoice = machineCcnProject.getInvoiceDate() != null;
            boolean hasDispatchDate = machineCcnProject.getActualDispatchDate() != null;
            if (!hasInvoice || !hasDispatchDate) {
                throw new IllegalArgumentException("Cannot set CCN status to 'closed' unless Invoice Date and Actual Dispatch Date are set.");
            }
            machineCcnProject.setActualDispatchDateFinal(true);
        }
        return machineCcnProjectRepository.save(machineCcnProject);
    }

    public long getLiveStockMachinesCount() {
        return machineCcnProjectRepository.countByCcnStatusAndMachineCategory("live", "stock");
    }

    public long getLiveCustomizedMachinesCount() {
        return machineCcnProjectRepository.countByCcnStatusAndMachineCategory("live", "customized");
    }

    public long getExpectedOrderMachinesCount() {
        return machineCcnProjectRepository.countByCcnStatusAndMachineCategory("live", "expected");
    }

    public long getIfatDoneMachinesCount() {
        return machineCcnProjectRepository.countByIfatFinalDateTrue();
    }

    public long getIfatDoneMachinesCount(Integer year) {
        if (year == null) return getIfatDoneMachinesCount();
        LocalDate yearStart = LocalDate.of(year, 1, 1);
        LocalDate yearEnd = LocalDate.of(year, 12, 31);
        return machineCcnProjectRepository.countIfatDoneByYear(yearStart, yearEnd);
    }

    public long getCfatDoneMachinesCount() {
        return machineCcnProjectRepository.countByCfatFinalDateTrue();
    }

    public long getCfatDoneMachinesCount(Integer year) {
        if (year == null) return getCfatDoneMachinesCount();
        LocalDate yearStart = LocalDate.of(year, 1, 1);
        LocalDate yearEnd = LocalDate.of(year, 12, 31);
        return machineCcnProjectRepository.countCfatDoneByYear(yearStart, yearEnd);
    }

    public long getAwaitingCustomerClearanceCount() {
        return machineCcnProjectRepository.countByActualHandedToDispatchDateFinalTrue();
    }

    public long getAwaitingCustomerClearanceCount(Integer year) {
        if (year == null) return getAwaitingCustomerClearanceCount();
        LocalDate yearStart = LocalDate.of(year, 1, 1);
        LocalDate yearEnd = LocalDate.of(year, 12, 31);
        return machineCcnProjectRepository.countAwaitingClearanceByYear(yearStart, yearEnd);
    }

    public long getCountByCcnStatusAndMachineCategory(String ccnStatus, String machineCategory) {
        if (ccnStatus == null || ccnStatus.trim().isEmpty()) throw new IllegalArgumentException("CCN Status cannot be null or empty");
        if (machineCategory == null || machineCategory.trim().isEmpty()) throw new IllegalArgumentException("Machine Category cannot be null or empty");
        return machineCcnProjectRepository.countByCcnStatusAndMachineCategory(ccnStatus.trim(), machineCategory.trim());
    }

    public Map<String, Object> getTotalPoValueByCategoryAndStatus(String ccnStatus, String machineCategory) {
        if (ccnStatus == null || ccnStatus.trim().isEmpty()) throw new IllegalArgumentException("CCN Status cannot be null or empty");
        if (machineCategory == null || machineCategory.trim().isEmpty()) throw new IllegalArgumentException("Machine Category cannot be null or empty");
        List<String> ccns = machineCcnProjectRepository.findCcnsByStatusAndCategory(ccnStatus.trim(), machineCategory.trim());
        double totalValue = 0.0;
        int poCount = 0;
        for (String ccn : ccns) {
            if (ccn != null && !ccn.trim().isEmpty()) {
                Optional<PurchaseOrder> po = purchaseOrderRepository.findByCcnIgnoreCase(ccn.trim());
                if (po.isPresent() && po.get().getPoValueLacs() != null) {
                    totalValue += po.get().getPoValueLacs();
                    poCount++;
                }
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("totalPoValueLacs", String.format("%.2f", totalValue));
        result.put("totalValue", totalValue);
        result.put("machineCategoryStatus", ccnStatus);
        result.put("machineCategory", machineCategory);
        result.put("poCount", poCount);
        result.put("machineCount", ccns.size());
        return result;
    }

    public List<Integer> getAvailableYears() {
        return machineCcnProjectRepository.findDistinctYearsByActualAssyStartDate();
    }

    public DashboardResponseDTO getDashboardSummary(String yearFilter) {
        Integer selectedYear = parseDashboardYear(yearFilter);
        LocalDate yearStart = null, yearEnd = null;
        if (selectedYear != null) {
            yearStart = LocalDate.of(selectedYear, 1, 1);
            yearEnd = LocalDate.of(selectedYear, 12, 31);
        }
        long liveStockCount = machineCcnProjectRepository.countByStatusAndCategoryAndActualAssyStartDateBetween("live", "stock", yearStart, yearEnd);
        long liveCustomizedCount = machineCcnProjectRepository.countByStatusAndCategoryAndActualAssyStartDateBetween("live", "customized", yearStart, yearEnd);
        long expectedOrdersCount = machineCcnProjectRepository.countByStatusAndCategoryAndActualAssyStartDateBetween("live", "expected", yearStart, yearEnd);
        long ifatDoneCount = selectedYear == null ? getIfatDoneMachinesCount() : getIfatDoneMachinesCount(selectedYear);
        long cfatDoneCount = selectedYear == null ? getCfatDoneMachinesCount() : getCfatDoneMachinesCount(selectedYear);
        long awaitingClearanceCount = selectedYear == null ? getAwaitingCustomerClearanceCount() : getAwaitingCustomerClearanceCount(selectedYear);
        Map<String, Object> totalPoValue = calculateTotalPoValueByStatusAndCategory("live", "customized", selectedYear);

        DashboardResponseDTO response = new DashboardResponseDTO();
        response.setFilterYear(selectedYear == null ? "ALL" : String.valueOf(selectedYear));
        response.setSelectedYear(selectedYear);
        response.setLiveStockCount(liveStockCount);
        response.setLiveCustomizedCount(liveCustomizedCount);
        response.setExpectedOrdersCount(expectedOrdersCount);
        response.setIfatDoneCount(ifatDoneCount);
        response.setCfatDoneCount(cfatDoneCount);
        response.setAwaitingClearanceCount(awaitingClearanceCount);
        response.setTotalPoValueLacs((String) totalPoValue.getOrDefault("totalPoValueLacs", "0.00"));
        response.setAmounts(totalPoValue);
        response.setAvgBuildTimeDays(calculateAverageBuildTime(selectedYear));
        return response;
    }

    public List<Map<String, Object>> getAllProjectSummaries() {
        List<MachineCcnProject> projects = machineCcnProjectRepository.findAll();
        List<Map<String, Object>> summaries = new ArrayList<>();
        for (MachineCcnProject project : projects) {
            if (project == null || project.getCcn() == null) continue;
            String ccn = project.getCcn().trim();
            if (ccn.isEmpty()) continue;
            String machineModel = null, customerName = null, unit = null;
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findByCcnIgnoreCase(ccn).orElse(null);
            KickoffForm kickoffForm = kickoffFormRepository.findByCcnIgnoreCase(ccn).orElse(null);
            
            if (purchaseOrder != null) {
                machineModel = purchaseOrder.getModelName();
                customerName = purchaseOrder.getCustomerName();
            }
            if (customerName == null || customerName.isEmpty()) {
                if (kickoffForm != null) customerName = kickoffForm.getCustomerName();
            }
            if (kickoffForm != null) {
                unit = kickoffForm.getUnit();
            }
            
            Map<String, Object> summary = new HashMap<>();
            summary.put("ccn", ccn);
            summary.put("machineName", project.getMachineName());
            summary.put("machineModel", machineModel);
            summary.put("customerName", customerName);
            summary.put("unit", unit);
            summary.put("liveStatus", project.getLiveStatus());
            summary.put("ccnStatus", project.getCcnStatus());
            
            // Add all date fields for rendering
            summary.put("actualIfatStdCpDate", project.getActualIfatStdCpDate());
            summary.put("actualCfatDate", project.getActualCfatDate());
            summary.put("actualPpcBasicDate", project.getActualPpcBasicDate());
            summary.put("actualPpcOptionalDate", project.getActualPpcOptionalDate());
            summary.put("actualMachineCpManufacturingDate", project.getActualMachineCpManufacturingDate());
            summary.put("actualMachineOptionalCpManufacturingDate", project.getActualMachineOptionalCpManufacturingDate());
            summary.put("actualProductReceiptDate", project.getActualProductReceiptDate());
            summary.put("actualPackagingReceiptDate", project.getActualPackagingReceiptDate());
            summary.put("actualAssyStartDate", project.getActualAssyStartDate());
            // include dispatch date and machine category so UI can show month-by-dispatch and category
            summary.put("actualDispatchDate", project.getActualDispatchDate());
            summary.put("machineCategory", project.getMachineCategory());
            // If dispatch date is final, treat CCN as closed for dashboard purposes
            if (project.getActualDispatchDateFinal() != null && project.getActualDispatchDateFinal()) {
                summary.put("ccnStatus", "closed");
            }
            
            summaries.add(summary);
        }
        return summaries;
    }

    private Integer parseDashboardYear(String yearFilter) {
        if (yearFilter == null || yearFilter.trim().isEmpty() || "ALL".equalsIgnoreCase(yearFilter.trim())) return null;
        try { return Integer.parseInt(yearFilter.trim()); }
        catch (NumberFormatException e) { logger.warning("Unable to parse dashboard year filter: " + yearFilter); return null; }
    }

    private Map<String, Object> calculateTotalPoValueByStatusAndCategory(String ccnStatus, String machineCategory, Integer year) {
        LocalDate yearStart = null, yearEnd = null;
        if (year != null) { yearStart = LocalDate.of(year, 1, 1); yearEnd = LocalDate.of(year, 12, 31); }
        List<String> ccns = (year == null)
                ? machineCcnProjectRepository.findCcnsByStatusAndCategory(ccnStatus.trim(), machineCategory.trim())
                : machineCcnProjectRepository.findCcnsByStatusAndCategoryAndActualAssyStartDateBetween(ccnStatus.trim(), machineCategory.trim(), yearStart, yearEnd);
        double totalValue = 0.0; int poCount = 0;
        for (String ccn : ccns) {
            if (ccn != null && !ccn.trim().isEmpty()) {
                Optional<PurchaseOrder> po = purchaseOrderRepository.findByCcnIgnoreCase(ccn.trim());
                if (po.isPresent() && po.get().getPoValueLacs() != null) { totalValue += po.get().getPoValueLacs(); poCount++; }
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("totalPoValueLacs", String.format("%.2f", totalValue));
        result.put("totalValue", totalValue);
        result.put("machineCategoryStatus", ccnStatus);
        result.put("machineCategory", machineCategory);
        result.put("poCount", poCount);
        result.put("machineCount", ccns.size());
        return result;
    }

    private Double calculateAverageBuildTime(Integer year) { return null; }

    // =========================
    // FINAL DATE LOCK METHODS
    // =========================

    public void validateDateFieldNotFinal(MachineCcnProject project, String dateFieldName) {
        try {
            Boolean isFinal = getFinalFlagValue(project, dateFieldName);
            if (Boolean.TRUE.equals(isFinal)) throw new IllegalStateException("Cannot update " + formatFieldName(dateFieldName) + " - date is marked as final");
        } catch (Exception e) {
            logger.severe("Error validating final flag for field " + dateFieldName + ": " + e.getMessage());
            throw new RuntimeException("Validation error for final date check");
        }
    }

    public void markDateFieldAsFinal(MachineCcnProject project, String dateFieldName) {
        try {
            setFinalFlagValue(project, dateFieldName, true);
            logger.info("Marked " + dateFieldName + " as final for CCN: " + project.getCcn());
            if ("actualDispatchDate".equals(dateFieldName)) {
                LocalDate dispatchDate = getDateFieldValue(project, dateFieldName);
                project.setInvoiceDate(dispatchDate);
                logger.info("Auto-set invoice date to " + dispatchDate + " for CCN: " + project.getCcn());
            }
        } catch (Exception e) {
            logger.severe("Error marking field as final " + dateFieldName + ": " + e.getMessage());
            throw new RuntimeException("Error marking date as final");
        }
    }

    public boolean isDateFieldFinal(MachineCcnProject project, String dateFieldName) {
        try {
            Boolean isFinal = getFinalFlagValue(project, dateFieldName);
            return Boolean.TRUE.equals(isFinal);
        } catch (Exception e) {
            logger.severe("Error checking final flag for field " + dateFieldName + ": " + e.getMessage());
            return false;
        }
    }

    public void handleDateExtension(MachineCcnProject project, String dateFieldName, LocalDate newDate) {
        try {
            LocalDate today = LocalDate.now();
            if (newDate != null && newDate.isAfter(today)) {
                setAlertSentFlagValue(project, dateFieldName, false);
                setLastAlertDateValue(project, dateFieldName, null);
                logger.info("Reset alert flags for " + dateFieldName + " - date extended to future");
            }
        } catch (Exception e) {
            logger.severe("Error handling date extension for field " + dateFieldName + ": " + e.getMessage());
        }
    }

    // =========================
    // REFLECTION HELPERS
    // =========================

    private Boolean getFinalFlagValue(MachineCcnProject project, String dateField) throws Exception {
        String finalFieldName = dateField + "Final";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(finalFieldName);
        field.setAccessible(true);
        return (Boolean) field.get(project);
    }

    private void setFinalFlagValue(MachineCcnProject project, String dateField, Boolean value) throws Exception {
        String finalFieldName = dateField + "Final";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(finalFieldName);
        field.setAccessible(true);
        field.set(project, value);
    }

    private Boolean getAlertSentFlagValue(MachineCcnProject project, String dateField) throws Exception {
        String alertFieldName = dateField + "AlertSent";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(alertFieldName);
        field.setAccessible(true);
        return (Boolean) field.get(project);
    }

    private void setAlertSentFlagValue(MachineCcnProject project, String dateField, Boolean value) throws Exception {
        String alertFieldName = dateField + "AlertSent";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(alertFieldName);
        field.setAccessible(true);
        field.set(project, value);
    }

    private LocalDate getLastAlertDateValue(MachineCcnProject project, String dateField) throws Exception {
        String alertDateFieldName = dateField + "LastAlertDate";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(alertDateFieldName);
        field.setAccessible(true);
        return (LocalDate) field.get(project);
    }

    private void setLastAlertDateValue(MachineCcnProject project, String dateField, LocalDate value) throws Exception {
        String alertDateFieldName = dateField + "LastAlertDate";
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(alertDateFieldName);
        field.setAccessible(true);
        field.set(project, value);
    }

    private String formatFieldName(String fieldName) {
        String displayName = fieldName.replace("actual", "").replace("Date", "");
        displayName = displayName.replaceAll("([a-z])([A-Z])", "$1 $2");
        if (!displayName.isEmpty()) displayName = displayName.substring(0, 1).toUpperCase() + displayName.substring(1);
        return displayName.trim();
    }

    private void validateFinalDateLocks(MachineCcnProject machineCcnProject) {
        List<String> dateFields = getAllDateFieldNames();
        for (String dateField : dateFields) {
            try {
                Boolean isFinal = getFinalFlagValue(machineCcnProject, dateField);
                if (isFinal != null && isFinal) {
                    LocalDate currentValue = getDateFieldValue(machineCcnProject, dateField);
                    LocalDate storedValue = getStoredDateFieldValue(machineCcnProject.getCcn(), dateField);
                    if (!Objects.equals(currentValue, storedValue)) {
                        throw new IllegalStateException("Cannot update " + formatFieldName(dateField) + " - this date is marked as final");
                    }
                }
            } catch (Exception e) {
                logger.warning("Error validating final date lock for field " + dateField + ": " + e.getMessage());
            }
        }
    }

    private void handleDateExtensions(MachineCcnProject machineCcnProject) {
        List<String> dateFields = getAllDateFieldNames();
        for (String dateField : dateFields) {
            try {
                LocalDate currentValue = getDateFieldValue(machineCcnProject, dateField);
                LocalDate storedValue = getStoredDateFieldValue(machineCcnProject.getCcn(), dateField);
                if (currentValue != null && storedValue != null && currentValue.isAfter(LocalDate.now()) && !currentValue.isEqual(storedValue)) {
                    handleDateExtension(machineCcnProject, dateField, currentValue);
                }
            } catch (Exception e) {
                logger.warning("Error handling date extension for field " + dateField + ": " + e.getMessage());
            }
        }
    }

    private List<String> getAllDateFieldNames() {
        return Arrays.asList(
            "actualKickoffDate", "actualPoDate", "actualIfatStdCpDate", "actualIfatCustomerCpDate",
            "actualPmrSubmissionDate", "actualProductReceiptDate", "actualPackagingReceiptDate",
            "actualIfatProtocolReleaseDate", "actualCfatDate", "actualComplianceDate",
            "actualPackingClearanceRecdDate", "actualBasicMaterialHandedOverAssyDate",
            "actualOptionalMaterialHandedOverAssyDate", "actualBasicMaterialReceiptDate",
            "actualCustomizationMaterialReceiptDate", "actualHandedToDispatchDate",
            "actualDispatchDate", "actualPpcBasicDate", "actualPpcOptionalDate",
            "actualLoaBasic", "actualLoaOptional", "additionalNewAssemblyAttachedAfterCfatDate",
            "mcnShortfallClosureDate"
        );
    }

    private LocalDate getDateFieldValue(MachineCcnProject project, String dateField) throws Exception {
        java.lang.reflect.Field field = MachineCcnProject.class.getDeclaredField(dateField);
        field.setAccessible(true);
        return (LocalDate) field.get(project);
    }

    private LocalDate getStoredDateFieldValue(String ccn, String dateField) {
        try {
            Optional<MachineCcnProject> stored = machineCcnProjectRepository.findByCcnIgnoreCase(ccn);
            if (stored.isPresent()) return getDateFieldValue(stored.get(), dateField);
        } catch (Exception e) {
            logger.warning("Error getting stored date value for " + dateField + ": " + e.getMessage());
        }
        return null;
    }

    // =========================
    // ASSEMBLY WIP DATA
    // =========================

    public List<com.example.demo.dto.AssemblyWipDto> getAssemblyWipData(LocalDate filterYear) {
        try {
            List<String> machineNames = machineCcnProjectRepository.findDistinctMachineNamesByLiveStatus();
            List<com.example.demo.dto.AssemblyWipDto> wipData = new ArrayList<>();

            LocalDate yearStartDate = LocalDate.of(filterYear.getYear(), 1, 1);
            // NEW: yearEnd needed for balance WIP query
            LocalDate yearEndDate = LocalDate.of(filterYear.getYear(), 12, 31);

            for (String machineName : machineNames) {
                if (machineName == null || machineName.trim().isEmpty()) continue;

                long carryForward   = machineCcnProjectRepository.countCarryForwardByMachineNameAndYear(machineName, yearStartDate);
                long machineInBuild = machineCcnProjectRepository.countMachineInBuildByMachineNameAndYear(machineName, yearStartDate);
                long dispatch       = machineCcnProjectRepository.countDispatchByMachineNameAndYear(machineName, yearStartDate);

                // NEW: count balance WIP — started but not dispatched this year
                long balanceWip = machineCcnProjectRepository.countBalanceWipByMachineNameAndYear(
                        machineName, yearStartDate, yearEndDate);

                wipData.add(new com.example.demo.dto.AssemblyWipDto(
                        machineName, carryForward, machineInBuild, dispatch, balanceWip));
            }

            return wipData;
        } catch (Exception e) {
            logger.severe("Error fetching Assembly WIP data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // =========================
    // ASSEMBLY WIP DETAILS
    // =========================

    public Map<String, List<Map<String, Object>>> getAssemblyWipDetails(String type, int filterYear) {
        LocalDate yearStartDate = LocalDate.of(filterYear, 1, 1);
        List<MachineCcnProject> records;

        switch (type.toLowerCase()) {
            case "cf":
                records = machineCcnProjectRepository.findCarryForwardRecordsByYear(yearStartDate);
                break;
            case "machine-in-build":
                records = machineCcnProjectRepository.findMachineInBuildRecordsByYear(yearStartDate);
                break;
            case "dispatch":
                records = machineCcnProjectRepository.findDispatchRecordsByYearStart(yearStartDate);
                break;
            // ── NEW: Balance WIP ──────────────────────────────────────────
            case "balance":
                LocalDate yearEndDate = LocalDate.of(filterYear, 12, 31);
                records = machineCcnProjectRepository.findBalanceWipRecordsByYear(yearStartDate, yearEndDate);
                break;
            // ─────────────────────────────────────────────────────────────
            default:
                throw new IllegalArgumentException("Invalid WIP detail type: " + type);
        }

        Map<String, PurchaseOrder> poByCcn = purchaseOrderRepository.findAll().stream()
                .filter(po -> po.getCcn() != null && !po.getCcn().trim().isEmpty())
                .collect(Collectors.toMap(
                        po -> po.getCcn().trim().toLowerCase(),
                        po -> po,
                        (existing, replacement) -> existing));

        Map<String, List<Map<String, Object>>> grouped = new java.util.LinkedHashMap<>();
        for (MachineCcnProject record : records) {
            String machineName = record.getMachineName();
            if (machineName == null || machineName.trim().isEmpty()) machineName = "Unknown Machine";
            grouped.computeIfAbsent(machineName, k -> new ArrayList<>());

            Map<String, Object> row = new HashMap<>();
            String ccn = record.getCcn();
            row.put("ccn", ccn);
            row.put("ccnStatus", record.getCcnStatus());
            row.put("machineName", machineName);
            row.put("liveStatus", record.getLiveStatus());
            row.put("actualAssyStartDate", record.getActualAssyStartDate());
            row.put("invoiceDate", record.getInvoiceDate());

            PurchaseOrder purchaseOrder = null;
            if (ccn != null && !ccn.trim().isEmpty()) {
                purchaseOrder = poByCcn.get(ccn.trim().toLowerCase());
            }
            row.put("customerName", purchaseOrder != null ? purchaseOrder.getCustomerName() : null);
            row.put("machineCategory", purchaseOrder != null ? purchaseOrder.getMachineCategory() : record.getMachineCategory());
            row.put("machineName", purchaseOrder != null && purchaseOrder.getMachineName() != null ? purchaseOrder.getMachineName() : machineName);
            row.put("poValue", purchaseOrder != null ? purchaseOrder.getPoValueLacs() : null);

            if (record.getActualAssyStartDate() != null && record.getInvoiceDate() != null) {
                long durationDays = ChronoUnit.DAYS.between(record.getActualAssyStartDate(), record.getInvoiceDate());
                row.put("durationDays", durationDays >= 0 ? durationDays : null);
            } else {
                row.put("durationDays", null);
            }

            grouped.get(machineName).add(row);
        }

        return grouped;
    }

    // =========================
    // MANUFACTURING / DISPATCH
    // =========================

    public int[] getManufacturingData(int year) {
        List<Object[]> results = machineCcnProjectRepository.getManufacturingCountsByMonth(year);
        int[] counts = new int[12];
        for (Object[] row : results) {
            int month = (Integer) row[0] - 1;
            long count = (Long) row[1];
            counts[month] = (int) count;
        }
        return counts;
    }

    public int[] getDispatchData(int year) {
        List<Object[]> results = machineCcnProjectRepository.getDispatchCountsByMonth(year);
        return toMonthCounts(results);
    }

    public int[] getDispatchPlanCounts(int year) {
        int[] counts = new int[12];
        for (KickoffForm kickoff : kickoffFormRepository.findAll()) {
            String dateValue = kickoff.getPlanned_dispatch_date();
            if (dateValue == null || dateValue.trim().isEmpty()) continue;
            try {
                LocalDate plannedDate = LocalDate.parse(dateValue.trim());
                if (plannedDate.getYear() == year) counts[plannedDate.getMonthValue() - 1]++;
            } catch (DateTimeParseException e) { /* ignore */ }
        }
        return counts;
    }

    public int[] getDispatchActualCounts(int year) {
        List<Object[]> results = machineCcnProjectRepository.getActualDispatchCountsByMonth(year);
        return toMonthCounts(results);
    }

    public int[] getDispatchFinalCounts(int year) {
        List<Object[]> results = machineCcnProjectRepository.getFinalActualDispatchCountsByMonth(year);
        return toMonthCounts(results);
    }

    public Map<String, Object> getCommercialDispatchDetails(int year, String type, Integer month) {
        if (type == null) type = "plan";
        type = type.trim().toLowerCase();
        List<Map<String, Object>> records;
        switch (type) {
            case "plan":    records = getDispatchPlanRecords(year, month); break;
            case "dynamic": records = getDynamicDispatchRecords(year, month); break;
            case "actual":  records = getActualDispatchRecords(year, month); break;
            default: throw new IllegalArgumentException("Invalid commercial dispatch detail type: " + type);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("type", type);
        response.put("year", year);
        response.put("monthData", groupRecordsByMonth(records));
        return response;
    }

    private List<Map<String, Object>> getDispatchPlanRecords(int year, Integer month) {
        List<Map<String, Object>> records = new ArrayList<>();
        Map<String, PurchaseOrder> poByCcn = buildPurchaseOrderMap();
        Map<String, MachineCcnProject> projectByCcn = buildMachineProjectMap();
        for (KickoffForm kickoff : kickoffFormRepository.findAll()) {
            String dateValue = kickoff.getPlanned_dispatch_date();
            if (dateValue == null || dateValue.trim().isEmpty()) continue;
            try {
                LocalDate plannedDate = LocalDate.parse(dateValue.trim());
                if (plannedDate.getYear() != year) continue;
                if (month != null && plannedDate.getMonthValue() != month) continue;
                LocalDate assyDate = null;
                MachineCcnProject project = null;
                if (kickoff.getCcn() != null && !kickoff.getCcn().trim().isEmpty()) {
                    project = projectByCcn.get(kickoff.getCcn().trim().toLowerCase());
                    if (project != null) assyDate = project.getActualAssyStartDate();
                }
                Map<String, Object> record = new HashMap<>();
                record.put("ccn", kickoff.getCcn());
                record.put("machineName", kickoff.getMachineName());
                record.put("assyStartDate", assyDate != null ? assyDate.toString() : null);
                record.put("dispatchDate", plannedDate.toString());
                record.put("dispatchPlanDate", plannedDate.toString());
                record.put("actualDispatchDate", project != null && project.getActualDispatchDate() != null ? project.getActualDispatchDate().toString() : null);
                record.put("invoiceDate", project != null && project.getInvoiceDate() != null ? project.getInvoiceDate().toString() : null);
                record.put("month", plannedDate.getMonthValue());
                record.put("ccnStatus", project != null ? project.getCcnStatus() : kickoff.getCcnStatus());
                record.put("liveStatus", project != null ? project.getLiveStatus() : null);
                String ccnKey = kickoff.getCcn() != null ? kickoff.getCcn().trim().toLowerCase() : null;
                if (ccnKey != null) {
                    PurchaseOrder po = poByCcn.get(ccnKey);
                    if (po != null) {
                        record.put("customerName", po.getCustomerName());
                        record.put("poDeliveryDate", po.getPoDeliveryDate());
                        record.put("poValue", po.getPoValueLacs());
                    }
                }
                records.add(record);
            } catch (DateTimeParseException e) { /* ignore */ }
        }
        return records;
    }

    private List<Map<String, Object>> getDynamicDispatchRecords(int year, Integer month) {
        List<MachineCcnProject> projects = month != null
                ? machineCcnProjectRepository.findDynamicDispatchRecordsByYearAndMonth(year, month)
                : machineCcnProjectRepository.findDynamicDispatchRecordsByYear(year);
        return buildDispatchRecords(projects);
    }

    private List<Map<String, Object>> getActualDispatchRecords(int year, Integer month) {
        List<MachineCcnProject> projects = month != null
                ? machineCcnProjectRepository.findFinalDispatchRecordsByYearAndMonth(year, month)
                : machineCcnProjectRepository.findFinalDispatchRecordsByYear(year);
        return buildDispatchRecords(projects);
    }

    private List<Map<String, Object>> buildDispatchRecords(List<MachineCcnProject> projects) {
        Map<String, PurchaseOrder> poByCcn = buildPurchaseOrderMap();
        Map<String, KickoffForm> kickoffByCcn = buildKickoffFormMap();
        List<Map<String, Object>> records = new ArrayList<>();
        for (MachineCcnProject project : projects) {
            Map<String, Object> record = new HashMap<>();
            record.put("ccn", project.getCcn());
            record.put("machineName", project.getMachineName());
            record.put("assyStartDate", project.getActualAssyStartDate() != null ? project.getActualAssyStartDate().toString() : null);
            record.put("dispatchDate", project.getActualDispatchDate() != null ? project.getActualDispatchDate().toString() : null);
            record.put("actualDispatchDate", project.getActualDispatchDate() != null ? project.getActualDispatchDate().toString() : null);
            record.put("invoiceDate", project.getInvoiceDate() != null ? project.getInvoiceDate().toString() : null);
            record.put("month", project.getActualDispatchDate() != null ? project.getActualDispatchDate().getMonthValue() : null);
            record.put("ccnStatus", project.getCcnStatus());
            record.put("liveStatus", project.getLiveStatus());
            String ccnKey = project.getCcn() != null ? project.getCcn().trim().toLowerCase() : null;
            if (ccnKey != null) {
                PurchaseOrder po = poByCcn.get(ccnKey);
                KickoffForm kickoff = kickoffByCcn.get(ccnKey);
                if (po != null) {
                    record.put("customerName", po.getCustomerName());
                    record.put("poDeliveryDate", po.getPoDeliveryDate());
                    record.put("poValue", po.getPoValueLacs());
                }
                if (kickoff != null && kickoff.getPlanned_dispatch_date() != null && !kickoff.getPlanned_dispatch_date().trim().isEmpty()) {
                    record.put("dispatchPlanDate", kickoff.getPlanned_dispatch_date().trim());
                }
            }
            records.add(record);
        }
        return records;
    }

    private Map<String, PurchaseOrder> buildPurchaseOrderMap() {
        return purchaseOrderRepository.findAll().stream()
                .filter(po -> po.getCcn() != null && !po.getCcn().trim().isEmpty())
                .collect(Collectors.toMap(po -> po.getCcn().trim().toLowerCase(), po -> po, (e, r) -> e));
    }

    private Map<String, MachineCcnProject> buildMachineProjectMap() {
        return machineCcnProjectRepository.findAll().stream()
                .filter(p -> p.getCcn() != null && !p.getCcn().trim().isEmpty())
                .collect(Collectors.toMap(p -> p.getCcn().trim().toLowerCase(), p -> p, (e, r) -> e));
    }

    private Map<String, KickoffForm> buildKickoffFormMap() {
        return kickoffFormRepository.findAll().stream()
                .filter(k -> k.getCcn() != null && !k.getCcn().trim().isEmpty())
                .collect(Collectors.toMap(k -> k.getCcn().trim().toLowerCase(), k -> k, (e, r) -> e));
    }

    private List<Map<String, Object>> groupRecordsByMonth(List<Map<String, Object>> records) {
        List<Map<String, Object>> monthData = new ArrayList<>();
        for (int m = 1; m <= 12; m++) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("month", m);
            entry.put("records", new ArrayList<>());
            monthData.add(entry);
        }
        for (Map<String, Object> record : records) {
            Object monthValue = record.get("month");
            if (monthValue instanceof Integer) {
                int idx = (Integer) monthValue;
                if (idx >= 1 && idx <= 12) {
                    ((List<Map<String, Object>>) monthData.get(idx - 1).get("records")).add(record);
                }
            }
        }
        return monthData;
    }

    private int[] toMonthCounts(List<Object[]> results) {
        int[] counts = new int[12];
        for (Object[] row : results) {
            int month = (Integer) row[0] - 1;
            long count = (Long) row[1];
            counts[month] = (int) count;
        }
        return counts;
    }

    public List<Map<String, Object>> getManufacturingDetails(int year, Integer month) {
        List<MachineCcnProject> records = (month == null)
                ? getManufacturingYearDetails(year)
                : getManufacturingMonthDetails(year, month);
        Map<String, PurchaseOrder> poByCcn = buildPurchaseOrderMap();
        return records.stream().map(r -> buildManufacturingDetailRow(r, poByCcn)).collect(Collectors.toList());
    }

    private Map<String, Object> buildManufacturingDetailRow(MachineCcnProject record, Map<String, PurchaseOrder> poByCcn) {
        Map<String, Object> row = new HashMap<>();
        String ccn = record.getCcn();
        row.put("ccn", ccn);
        row.put("ccnStatus", record.getCcnStatus());
        row.put("machineName", record.getMachineName());
        row.put("liveStatus", record.getLiveStatus());
        row.put("actualAssyStartDate", record.getActualAssyStartDate());
        row.put("invoiceDate", record.getInvoiceDate());
        PurchaseOrder purchaseOrder = (ccn != null && !ccn.trim().isEmpty()) ? poByCcn.get(ccn.trim().toLowerCase()) : null;
        row.put("customerName", purchaseOrder != null ? purchaseOrder.getCustomerName() : null);
        row.put("machineCategory", purchaseOrder != null ? purchaseOrder.getMachineCategory() : record.getMachineCategory());
        row.put("machineName", purchaseOrder != null && purchaseOrder.getMachineName() != null ? purchaseOrder.getMachineName() : record.getMachineName());
        row.put("poValue", purchaseOrder != null ? purchaseOrder.getPoValueLacs() : null);
        if (record.getActualAssyStartDate() != null && record.getInvoiceDate() != null) {
            long durationDays = ChronoUnit.DAYS.between(record.getActualAssyStartDate(), record.getInvoiceDate());
            row.put("durationDays", durationDays >= 0 ? durationDays : null);
        } else {
            row.put("durationDays", null);
        }
        return row;
    }

    public List<MachineCcnProject> getManufacturingMonthDetails(int year, int month) {
        return machineCcnProjectRepository.findManufacturingRecordsByYearAndMonth(year, month);
    }

    public List<MachineCcnProject> getManufacturingYearDetails(int year) {
        return machineCcnProjectRepository.findManufacturingRecordsByYear(year);
    }

    public List<MachineCcnProject> getDispatchMonthDetails(int year, int month) {
        return machineCcnProjectRepository.findDispatchRecordsByYearAndMonth(year, month);
    }

    public List<MachineCcnProject> getDispatchYearDetails(int year) {
        return machineCcnProjectRepository.findDispatchRecordsByYear(year);
    }

    // =========================
    // SUGGESTIONS / ADD-NEW
    // =========================

    public List<String> getSuggestions(String field, String q) {
        List<MachineCcnProject> all = machineCcnProjectRepository.findAll();
        Set<String> unique = new HashSet<>();
        switch (field) {
            case "assembliesReleasedBy":
                all.forEach(p -> { if (p.getAssembliesReleasedBy() != null && !p.getAssembliesReleasedBy().trim().isEmpty()) unique.add(p.getAssembliesReleasedBy().trim()); });
                break;
            case "workmen":
                all.forEach(p -> { if (p.getWorkmen() != null && !p.getWorkmen().trim().isEmpty()) Arrays.stream(p.getWorkmen().split(",")).map(String::trim).filter(t -> !t.isEmpty()).forEach(unique::add); });
                break;
            default: return new ArrayList<>();
        }
        return unique.stream().filter(v -> v.toLowerCase().contains(q.toLowerCase())).sorted().limit(20).collect(Collectors.toList());
    }

    public boolean addNewValue(String field, String value, String ccn) {
        if (value == null || value.trim().isEmpty() || ccn == null || ccn.trim().isEmpty()) return false;
        final String trimmedValue = value.trim().toUpperCase();
        final String normalizedCcn = ccn.trim();
        Optional<MachineCcnProject> existingOpt = machineCcnProjectRepository.findByCcnIgnoreCase(normalizedCcn);
        if (existingOpt.isEmpty()) return false;
        MachineCcnProject existing = existingOpt.get();
        switch (field) {
            case "assembliesReleasedBy":
                if (existing.getAssembliesReleasedBy() != null && existing.getAssembliesReleasedBy().trim().toUpperCase().equals(trimmedValue)) return false;
                existing.setAssembliesReleasedBy(trimmedValue);
                break;
            case "workmen":
                String currentWorkmen = existing.getWorkmen();
                Set<String> workmenSet = new HashSet<>();
                if (currentWorkmen != null && !currentWorkmen.trim().isEmpty()) {
                    Arrays.stream(currentWorkmen.split(",")).map(String::trim).filter(v -> !v.isEmpty()).map(String::toUpperCase).forEach(workmenSet::add);
                }
                if (workmenSet.contains(trimmedValue)) return false;
                workmenSet.add(trimmedValue);
                existing.setWorkmen(workmenSet.stream().sorted().collect(Collectors.joining(", ")));
                break;
            default: return false;
        }
        try { machineCcnProjectRepository.save(existing); return true; }
        catch (Exception e) { logger.warning("Error adding new value: " + e.getMessage()); return false; }
    }

    // =========================
    // SYNC FROM PO AND KICKOFF
    // =========================

    public void syncFromPurchaseOrder(com.example.demo.model.PurchaseOrder po) {
        if (po == null || po.getCcn() == null || po.getCcn().trim().isEmpty()) return;

        Optional<MachineCcnProject> existingOpt = machineCcnProjectRepository.findByCcnIgnoreCase(po.getCcn().trim());
        if (existingOpt.isEmpty()) return;

        MachineCcnProject project = existingOpt.get();

        // Sync fields from PO to MachineCcnProject
        if (po.getMachineName() != null && !po.getMachineName().trim().isEmpty()) {
            project.setMachineName(po.getMachineName().trim().toUpperCase());
        }
        if (po.getModelName() != null && !po.getModelName().trim().isEmpty()) {
            // Note: MachineCcnProject doesn't have direct model field, so we skip or set via kickoff
        }
        if (po.getCustomerName() != null && !po.getCustomerName().trim().isEmpty()) {
            // Customer name can be synced if needed - depends on your schema
        }
        if (po.getMachineCategory() != null && !po.getMachineCategory().trim().isEmpty()) {
            project.setMachineCategory(po.getMachineCategory().trim().toUpperCase());
        }

        try {
            machineCcnProjectRepository.save(project);
            logger.info("Synced PO updates to MachineCcnProject for CCN: " + po.getCcn());
        } catch (Exception e) {
            logger.warning("Error syncing PO to MachineCcnProject: " + e.getMessage());
        }
    }

    public void syncFromKickoffForm(com.example.demo.model.KickoffForm kickoff) {
        if (kickoff == null || kickoff.getCcn() == null || kickoff.getCcn().trim().isEmpty()) return;

        Optional<MachineCcnProject> existingOpt = machineCcnProjectRepository.findByCcnIgnoreCase(kickoff.getCcn().trim());
        if (existingOpt.isEmpty()) return;

        MachineCcnProject project = existingOpt.get();

        // Sync fields from Kickoff to MachineCcnProject
        if (kickoff.getMachineName() != null && !kickoff.getMachineName().trim().isEmpty()) {
            project.setMachineName(kickoff.getMachineName().trim().toUpperCase());
        }
        if (kickoff.getMachineCategory() != null && !kickoff.getMachineCategory().trim().isEmpty()) {
            project.setMachineCategory(kickoff.getMachineCategory().trim().toUpperCase());
        }

        try {
            machineCcnProjectRepository.save(project);
            logger.info("Synced Kickoff updates to MachineCcnProject for CCN: " + kickoff.getCcn());
        } catch (Exception e) {
            logger.warning("Error syncing Kickoff to MachineCcnProject: " + e.getMessage());
        }
    }
}