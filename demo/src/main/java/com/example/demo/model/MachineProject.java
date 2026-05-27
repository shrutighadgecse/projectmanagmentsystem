package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "machine_project")
public class MachineProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ccn;

    // Plan dates from Kickoff (for reference display alongside actual dates)
    private LocalDate planCommercialClearanceDate;
    private LocalDate planAdvPaymentRecdDate;
    private LocalDate planBalPaymentRecdDate;
    private LocalDate planMachineLayoutApprovalDate;
    private LocalDate planRoomLayoutApprovalDate;
    private LocalDate planCpLayoutApprovalDate;
    private LocalDate planOaGenerationDate;
    private LocalDate planCpOaGenerationDate;
    private LocalDate planDispatchClearanceRecdDate;
    private LocalDate planLoaBasicReleaseDate;
    private LocalDate planLoaOptionalReleaseDate;
    private LocalDate planDqReleaseDate;
    private LocalDate planIfatProtocolReleaseDate;
    private LocalDate planPmrSubmissionDate;
    private LocalDate planProductReceiptDate;
    private LocalDate planPackagingReceiptDate;
    private LocalDate planComplianceDate;
    private LocalDate planPackingClearanceRecdDate;
    private LocalDate planPpcBasicMaterialDate;
    private LocalDate planPpcOptionalMaterialDate;
    private LocalDate planCpDesignReleaseDate;
    private LocalDate planMachineCpManufacturingDate;
    private LocalDate planMachineOptionalCpManufacturingDate;
    private LocalDate planBasicMaterialHandedOverAssyDate;
    private LocalDate planOptionalMaterialHandedOverAssyDate;
    private LocalDate planBasicMaterialReceiptDate;
    private LocalDate planCustomizationMaterialReceiptDate;
    private LocalDate planAssyStartDate;
    private LocalDate planMachineBasicCompletion;
    private LocalDate planMachineCustomizationCompletion;
    private LocalDate planIfatStdCpDate;
    private LocalDate planIfatCustomerCpDate;
    private LocalDate planCfatDate;
    private LocalDate planHandedToDispatchDate;
    private LocalDate planDispatchDate;
    private LocalDate planOptionalCpLayoutApprovalDate;

    // Basic fields
    private String dnNumber;
    private String salesOrderNumber;
    @Lob
    private String remarksSales;
    @Lob
    private String mcnRemarksShortfall;
    private Integer totalBasicAssemblyAttached;
    private Integer totalOptionalAssemblyAttached;
    private Integer totalAdditionalNewAssemblyAfterCfat;
    private LocalDate additionalNewAssemblyAfterCfatDate;
    @Lob
    private String assembliesReleasedBy; // JSON array
    @Lob
    private String remarksDesign;
    private String mcnNumber;
    private Boolean mcnWithShortfall;
    @Lob
    private String productRemark;
    @Lob
    private String productPackagingRemarks;

    // CP Trial Table data (JSON)
    @Lob
    private String cpTrialData; // JSON array of objects
    @Lob
    private String cpTrialAdditionalRemarks;

    // More fields
    private Integer machineCpTrialCompleted;
    private Integer machineOptionalCpTrialCompleted;
    private LocalDate mcnShortfallClosureDate;
    private LocalDate actualPpcNewMaterialDateAfterCfat;
    @Lob
    private String materialProgressStatus;
    @Lob
    private String remarksPpc;
    private Integer delayInDaysBasicMaterial;
    private Integer delayInOptionalBasicMaterial;
    private Integer delayInDaysMachineCp;
    private Integer delayInDaysOptionalCp;

    // Actual dates (corresponding to planned from Kickoff)
    private LocalDate actualMachineLayoutApprovalDate;
    private LocalDate actualRoomLayoutApprovalDate;
    private LocalDate actualCpLayoutApprovalDate;
    private LocalDate actualCommercialClearanceDate;
    private LocalDate actualOptionalCpLayoutApprovalDate;
    private LocalDate actualOaGenerationDate;
    private LocalDate actualCpOaGenerationDate;
    private LocalDate actualAdvPaymentRecdDate;
    private LocalDate actualBalPaymentRecdDate;
    private LocalDate actualDispatchClearanceRecdDate;
    private LocalDate actualLoaBasicReleaseDate;
    private LocalDate actualLoaOptionalReleaseDate;
    private LocalDate actualDqReleaseDate;
    private LocalDate actualIfatProtocolReleaseDate;
    private LocalDate actualPmrSubmissionDate;
    private LocalDate actualProductReceiptDate;
    private LocalDate actualPackagingReceiptDate;
    private LocalDate actualComplianceDate;
    private LocalDate actualPackingClearanceRecdDate;
    private LocalDate actualPpcBasicMaterialDate;
    private LocalDate actualPpcOptionalMaterialDate;
    private LocalDate actualCpDesignReleaseDate;
    private LocalDate actualMachineCpManufacturingDate;
    private LocalDate actualMachineOptionalCpManufacturingDate;
    private LocalDate actualBasicMaterialHandedOverAssyDate;
    private LocalDate actualOptionalMaterialHandedOverAssyDate;
    private LocalDate actualBasicMaterialReceiptDate;
    private LocalDate actualCustomizationMaterialReceiptDate;
    private LocalDate actualAssyStartDate;
    private LocalDate actualMachineBasicCompletion;
    private LocalDate actualMachineCustomizationCompletion;
    private LocalDate actualIfatStdCpDate;
    private LocalDate actualIfatCustomerCpDate;
    private LocalDate actualCfatDate;
    private LocalDate actualHandedToDispatchDate;
    private LocalDate actualDispatchDate;

    // Files (store paths)
    private String cpImagePath;
    private String machineImageStoresPath;
    private String machineImageAssemblyPath;

    // Dispatch & Stores
    @Lob
    private String remarksStores;
    private String invoiceNumber;
    private String lrNo;
    private LocalDate lrDate;
    private LocalDate podDate;
    private String ccnStatus; // LIVE / CLOSE
    @Lob
    private String progressStatus;

    // Assembly Section
    @Lob
    private String workmen; // JSON array
    @Lob
    private String assemblyProgressStatus;
    private String pendingFollowUpMech;
    private String pendingFollowUpElect;
    private String pendingFollowUpCp;
    @Lob
    private String delayAnalysis;
    private Integer stdTaskOfMachine;
    private Integer taskCompleted;
    private Double machinePercentage;
    private String liveStatus;
    private Double projectPercentage;

    // Final Section
    private LocalDate lastUpdatedDataAsOn;
    @Lob
    private String lastMeetingReview;
    @Lob
    private String latestReview;
    private String dynamicDispatchMonth; // YYYY-MM

    // Checkbox flags
    private Boolean validationUploaded;
    private Boolean matpsUploaded;
    private Boolean videoUploaded;
    private Boolean twelveDaysProject;
    private Boolean ccnInterchangedMc;

    // Additional fields from user requirements
    private String interUnitReceipt;
    private String interUnitTransfer;
    private String issueReturnQty;
    private Integer stepCompletionStatus; // Track which steps are complete (bitmask or JSON)
    private LocalDate formLastSavedDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCcn() { return ccn; }
    public void setCcn(String ccn) { this.ccn = ccn; }

    public String getDnNumber() { return dnNumber; }
    public void setDnNumber(String dnNumber) { this.dnNumber = dnNumber; }

    public String getSalesOrderNumber() { return salesOrderNumber; }
    public void setSalesOrderNumber(String salesOrderNumber) { this.salesOrderNumber = salesOrderNumber; }

    public String getRemarksSales() { return remarksSales; }
    public void setRemarksSales(String remarksSales) { this.remarksSales = remarksSales; }

    public String getMcnRemarksShortfall() { return mcnRemarksShortfall; }
    public void setMcnRemarksShortfall(String mcnRemarksShortfall) { this.mcnRemarksShortfall = mcnRemarksShortfall; }

    public Integer getTotalBasicAssemblyAttached() { return totalBasicAssemblyAttached; }
    public void setTotalBasicAssemblyAttached(Integer totalBasicAssemblyAttached) { this.totalBasicAssemblyAttached = totalBasicAssemblyAttached; }

    public Integer getTotalOptionalAssemblyAttached() { return totalOptionalAssemblyAttached; }
    public void setTotalOptionalAssemblyAttached(Integer totalOptionalAssemblyAttached) { this.totalOptionalAssemblyAttached = totalOptionalAssemblyAttached; }

    public Integer getTotalAdditionalNewAssemblyAfterCfat() { return totalAdditionalNewAssemblyAfterCfat; }
    public void setTotalAdditionalNewAssemblyAfterCfat(Integer totalAdditionalNewAssemblyAfterCfat) { this.totalAdditionalNewAssemblyAfterCfat = totalAdditionalNewAssemblyAfterCfat; }

    public LocalDate getAdditionalNewAssemblyAfterCfatDate() { return additionalNewAssemblyAfterCfatDate; }
    public void setAdditionalNewAssemblyAfterCfatDate(LocalDate additionalNewAssemblyAfterCfatDate) { this.additionalNewAssemblyAfterCfatDate = additionalNewAssemblyAfterCfatDate; }

    public String getAssembliesReleasedBy() { return assembliesReleasedBy; }
    public void setAssembliesReleasedBy(String assembliesReleasedBy) { this.assembliesReleasedBy = assembliesReleasedBy; }

    public String getRemarksDesign() { return remarksDesign; }
    public void setRemarksDesign(String remarksDesign) { this.remarksDesign = remarksDesign; }

    public String getMcnNumber() { return mcnNumber; }
    public void setMcnNumber(String mcnNumber) { this.mcnNumber = mcnNumber; }

    public Boolean getMcnWithShortfall() { return mcnWithShortfall; }
    public void setMcnWithShortfall(Boolean mcnWithShortfall) { this.mcnWithShortfall = mcnWithShortfall; }

    public String getProductRemark() { return productRemark; }
    public void setProductRemark(String productRemark) { this.productRemark = productRemark; }

    public String getProductPackagingRemarks() { return productPackagingRemarks; }
    public void setProductPackagingRemarks(String productPackagingRemarks) { this.productPackagingRemarks = productPackagingRemarks; }

    public String getCpTrialData() { return cpTrialData; }
    public void setCpTrialData(String cpTrialData) { this.cpTrialData = cpTrialData; }

    public String getCpTrialAdditionalRemarks() { return cpTrialAdditionalRemarks; }
    public void setCpTrialAdditionalRemarks(String cpTrialAdditionalRemarks) { this.cpTrialAdditionalRemarks = cpTrialAdditionalRemarks; }

    public Integer getMachineCpTrialCompleted() { return machineCpTrialCompleted; }
    public void setMachineCpTrialCompleted(Integer machineCpTrialCompleted) { this.machineCpTrialCompleted = machineCpTrialCompleted; }

    public Integer getMachineOptionalCpTrialCompleted() { return machineOptionalCpTrialCompleted; }
    public void setMachineOptionalCpTrialCompleted(Integer machineOptionalCpTrialCompleted) { this.machineOptionalCpTrialCompleted = machineOptionalCpTrialCompleted; }

    public LocalDate getMcnShortfallClosureDate() { return mcnShortfallClosureDate; }
    public void setMcnShortfallClosureDate(LocalDate mcnShortfallClosureDate) { this.mcnShortfallClosureDate = mcnShortfallClosureDate; }

    public LocalDate getActualPpcNewMaterialDateAfterCfat() { return actualPpcNewMaterialDateAfterCfat; }
    public void setActualPpcNewMaterialDateAfterCfat(LocalDate actualPpcNewMaterialDateAfterCfat) { this.actualPpcNewMaterialDateAfterCfat = actualPpcNewMaterialDateAfterCfat; }

    public String getMaterialProgressStatus() { return materialProgressStatus; }
    public void setMaterialProgressStatus(String materialProgressStatus) { this.materialProgressStatus = materialProgressStatus; }

    public String getRemarksPpc() { return remarksPpc; }
    public void setRemarksPpc(String remarksPpc) { this.remarksPpc = remarksPpc; }

    public Integer getDelayInDaysBasicMaterial() { return delayInDaysBasicMaterial; }
    public void setDelayInDaysBasicMaterial(Integer delayInDaysBasicMaterial) { this.delayInDaysBasicMaterial = delayInDaysBasicMaterial; }

    public Integer getDelayInOptionalBasicMaterial() { return delayInOptionalBasicMaterial; }
    public void setDelayInOptionalBasicMaterial(Integer delayInOptionalBasicMaterial) { this.delayInOptionalBasicMaterial = delayInOptionalBasicMaterial; }

    public Integer getDelayInDaysMachineCp() { return delayInDaysMachineCp; }
    public void setDelayInDaysMachineCp(Integer delayInDaysMachineCp) { this.delayInDaysMachineCp = delayInDaysMachineCp; }

    public Integer getDelayInDaysOptionalCp() { return delayInDaysOptionalCp; }
    public void setDelayInDaysOptionalCp(Integer delayInDaysOptionalCp) { this.delayInDaysOptionalCp = delayInDaysOptionalCp; }

    public LocalDate getActualMachineLayoutApprovalDate() { return actualMachineLayoutApprovalDate; }
    public void setActualMachineLayoutApprovalDate(LocalDate actualMachineLayoutApprovalDate) { this.actualMachineLayoutApprovalDate = actualMachineLayoutApprovalDate; }

    public LocalDate getActualRoomLayoutApprovalDate() { return actualRoomLayoutApprovalDate; }
    public void setActualRoomLayoutApprovalDate(LocalDate actualRoomLayoutApprovalDate) { this.actualRoomLayoutApprovalDate = actualRoomLayoutApprovalDate; }

    public LocalDate getActualCpLayoutApprovalDate() { return actualCpLayoutApprovalDate; }
    public void setActualCpLayoutApprovalDate(LocalDate actualCpLayoutApprovalDate) { this.actualCpLayoutApprovalDate = actualCpLayoutApprovalDate; }

    public LocalDate getActualCommercialClearanceDate() { return actualCommercialClearanceDate; }
    public void setActualCommercialClearanceDate(LocalDate actualCommercialClearanceDate) { this.actualCommercialClearanceDate = actualCommercialClearanceDate; }

    public LocalDate getActualOptionalCpLayoutApprovalDate() { return actualOptionalCpLayoutApprovalDate; }
    public void setActualOptionalCpLayoutApprovalDate(LocalDate actualOptionalCpLayoutApprovalDate) { this.actualOptionalCpLayoutApprovalDate = actualOptionalCpLayoutApprovalDate; }

    public LocalDate getActualOaGenerationDate() { return actualOaGenerationDate; }
    public void setActualOaGenerationDate(LocalDate actualOaGenerationDate) { this.actualOaGenerationDate = actualOaGenerationDate; }

    public LocalDate getActualCpOaGenerationDate() { return actualCpOaGenerationDate; }
    public void setActualCpOaGenerationDate(LocalDate actualCpOaGenerationDate) { this.actualCpOaGenerationDate = actualCpOaGenerationDate; }

    public LocalDate getActualAdvPaymentRecdDate() { return actualAdvPaymentRecdDate; }
    public void setActualAdvPaymentRecdDate(LocalDate actualAdvPaymentRecdDate) { this.actualAdvPaymentRecdDate = actualAdvPaymentRecdDate; }

    public LocalDate getActualBalPaymentRecdDate() { return actualBalPaymentRecdDate; }
    public void setActualBalPaymentRecdDate(LocalDate actualBalPaymentRecdDate) { this.actualBalPaymentRecdDate = actualBalPaymentRecdDate; }

    public LocalDate getActualDispatchClearanceRecdDate() { return actualDispatchClearanceRecdDate; }
    public void setActualDispatchClearanceRecdDate(LocalDate actualDispatchClearanceRecdDate) { this.actualDispatchClearanceRecdDate = actualDispatchClearanceRecdDate; }

    public LocalDate getActualLoaBasicReleaseDate() { return actualLoaBasicReleaseDate; }
    public void setActualLoaBasicReleaseDate(LocalDate actualLoaBasicReleaseDate) { this.actualLoaBasicReleaseDate = actualLoaBasicReleaseDate; }

    public LocalDate getActualLoaOptionalReleaseDate() { return actualLoaOptionalReleaseDate; }
    public void setActualLoaOptionalReleaseDate(LocalDate actualLoaOptionalReleaseDate) { this.actualLoaOptionalReleaseDate = actualLoaOptionalReleaseDate; }

    public LocalDate getActualDqReleaseDate() { return actualDqReleaseDate; }
    public void setActualDqReleaseDate(LocalDate actualDqReleaseDate) { this.actualDqReleaseDate = actualDqReleaseDate; }

    public LocalDate getActualIfatProtocolReleaseDate() { return actualIfatProtocolReleaseDate; }
    public void setActualIfatProtocolReleaseDate(LocalDate actualIfatProtocolReleaseDate) { this.actualIfatProtocolReleaseDate = actualIfatProtocolReleaseDate; }

    public LocalDate getActualPmrSubmissionDate() { return actualPmrSubmissionDate; }
    public void setActualPmrSubmissionDate(LocalDate actualPmrSubmissionDate) { this.actualPmrSubmissionDate = actualPmrSubmissionDate; }

    public LocalDate getActualProductReceiptDate() { return actualProductReceiptDate; }
    public void setActualProductReceiptDate(LocalDate actualProductReceiptDate) { this.actualProductReceiptDate = actualProductReceiptDate; }

    public LocalDate getActualPackagingReceiptDate() { return actualPackagingReceiptDate; }
    public void setActualPackagingReceiptDate(LocalDate actualPackagingReceiptDate) { this.actualPackagingReceiptDate = actualPackagingReceiptDate; }

    public LocalDate getActualComplianceDate() { return actualComplianceDate; }
    public void setActualComplianceDate(LocalDate actualComplianceDate) { this.actualComplianceDate = actualComplianceDate; }

    public LocalDate getActualPackingClearanceRecdDate() { return actualPackingClearanceRecdDate; }
    public void setActualPackingClearanceRecdDate(LocalDate actualPackingClearanceRecdDate) { this.actualPackingClearanceRecdDate = actualPackingClearanceRecdDate; }

    public LocalDate getActualPpcBasicMaterialDate() { return actualPpcBasicMaterialDate; }
    public void setActualPpcBasicMaterialDate(LocalDate actualPpcBasicMaterialDate) { this.actualPpcBasicMaterialDate = actualPpcBasicMaterialDate; }

    public LocalDate getActualPpcOptionalMaterialDate() { return actualPpcOptionalMaterialDate; }
    public void setActualPpcOptionalMaterialDate(LocalDate actualPpcOptionalMaterialDate) { this.actualPpcOptionalMaterialDate = actualPpcOptionalMaterialDate; }

    public LocalDate getActualCpDesignReleaseDate() { return actualCpDesignReleaseDate; }
    public void setActualCpDesignReleaseDate(LocalDate actualCpDesignReleaseDate) { this.actualCpDesignReleaseDate = actualCpDesignReleaseDate; }

    public LocalDate getActualMachineCpManufacturingDate() { return actualMachineCpManufacturingDate; }
    public void setActualMachineCpManufacturingDate(LocalDate actualMachineCpManufacturingDate) { this.actualMachineCpManufacturingDate = actualMachineCpManufacturingDate; }

    public LocalDate getActualMachineOptionalCpManufacturingDate() { return actualMachineOptionalCpManufacturingDate; }
    public void setActualMachineOptionalCpManufacturingDate(LocalDate actualMachineOptionalCpManufacturingDate) { this.actualMachineOptionalCpManufacturingDate = actualMachineOptionalCpManufacturingDate; }

    public LocalDate getActualBasicMaterialHandedOverAssyDate() { return actualBasicMaterialHandedOverAssyDate; }
    public void setActualBasicMaterialHandedOverAssyDate(LocalDate actualBasicMaterialHandedOverAssyDate) { this.actualBasicMaterialHandedOverAssyDate = actualBasicMaterialHandedOverAssyDate; }

    public LocalDate getActualOptionalMaterialHandedOverAssyDate() { return actualOptionalMaterialHandedOverAssyDate; }
    public void setActualOptionalMaterialHandedOverAssyDate(LocalDate actualOptionalMaterialHandedOverAssyDate) { this.actualOptionalMaterialHandedOverAssyDate = actualOptionalMaterialHandedOverAssyDate; }

    public LocalDate getActualBasicMaterialReceiptDate() { return actualBasicMaterialReceiptDate; }
    public void setActualBasicMaterialReceiptDate(LocalDate actualBasicMaterialReceiptDate) { this.actualBasicMaterialReceiptDate = actualBasicMaterialReceiptDate; }

    public LocalDate getActualCustomizationMaterialReceiptDate() { return actualCustomizationMaterialReceiptDate; }
    public void setActualCustomizationMaterialReceiptDate(LocalDate actualCustomizationMaterialReceiptDate) { this.actualCustomizationMaterialReceiptDate = actualCustomizationMaterialReceiptDate; }

    public LocalDate getActualAssyStartDate() { return actualAssyStartDate; }
    public void setActualAssyStartDate(LocalDate actualAssyStartDate) { this.actualAssyStartDate = actualAssyStartDate; }

    public LocalDate getActualMachineBasicCompletion() { return actualMachineBasicCompletion; }
    public void setActualMachineBasicCompletion(LocalDate actualMachineBasicCompletion) { this.actualMachineBasicCompletion = actualMachineBasicCompletion; }

    public LocalDate getActualMachineCustomizationCompletion() { return actualMachineCustomizationCompletion; }
    public void setActualMachineCustomizationCompletion(LocalDate actualMachineCustomizationCompletion) { this.actualMachineCustomizationCompletion = actualMachineCustomizationCompletion; }

    public LocalDate getActualIfatStdCpDate() { return actualIfatStdCpDate; }
    public void setActualIfatStdCpDate(LocalDate actualIfatStdCpDate) { this.actualIfatStdCpDate = actualIfatStdCpDate; }

    public LocalDate getActualIfatCustomerCpDate() { return actualIfatCustomerCpDate; }
    public void setActualIfatCustomerCpDate(LocalDate actualIfatCustomerCpDate) { this.actualIfatCustomerCpDate = actualIfatCustomerCpDate; }

    public LocalDate getActualCfatDate() { return actualCfatDate; }
    public void setActualCfatDate(LocalDate actualCfatDate) { this.actualCfatDate = actualCfatDate; }

    public LocalDate getActualHandedToDispatchDate() { return actualHandedToDispatchDate; }
    public void setActualHandedToDispatchDate(LocalDate actualHandedToDispatchDate) { this.actualHandedToDispatchDate = actualHandedToDispatchDate; }

    public LocalDate getActualDispatchDate() { return actualDispatchDate; }
    public void setActualDispatchDate(LocalDate actualDispatchDate) { this.actualDispatchDate = actualDispatchDate; }

    public String getCpImagePath() { return cpImagePath; }
    public void setCpImagePath(String cpImagePath) { this.cpImagePath = cpImagePath; }

    public String getMachineImageStoresPath() { return machineImageStoresPath; }
    public void setMachineImageStoresPath(String machineImageStoresPath) { this.machineImageStoresPath = machineImageStoresPath; }

    public String getMachineImageAssemblyPath() { return machineImageAssemblyPath; }
    public void setMachineImageAssemblyPath(String machineImageAssemblyPath) { this.machineImageAssemblyPath = machineImageAssemblyPath; }

    public String getRemarksStores() { return remarksStores; }
    public void setRemarksStores(String remarksStores) { this.remarksStores = remarksStores; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getLrNo() { return lrNo; }
    public void setLrNo(String lrNo) { this.lrNo = lrNo; }

    public LocalDate getLrDate() { return lrDate; }
    public void setLrDate(LocalDate lrDate) { this.lrDate = lrDate; }

    public LocalDate getPodDate() { return podDate; }
    public void setPodDate(LocalDate podDate) { this.podDate = podDate; }

    public String getCcnStatus() { return ccnStatus; }
    public void setCcnStatus(String ccnStatus) { this.ccnStatus = ccnStatus; }

    public String getProgressStatus() { return progressStatus; }
    public void setProgressStatus(String progressStatus) { this.progressStatus = progressStatus; }

    public String getWorkmen() { return workmen; }
    public void setWorkmen(String workmen) { this.workmen = workmen; }

    public String getAssemblyProgressStatus() { return assemblyProgressStatus; }
    public void setAssemblyProgressStatus(String assemblyProgressStatus) { this.assemblyProgressStatus = assemblyProgressStatus; }

    public String getPendingFollowUpMech() { return pendingFollowUpMech; }
    public void setPendingFollowUpMech(String pendingFollowUpMech) { this.pendingFollowUpMech = pendingFollowUpMech; }

    public String getPendingFollowUpElect() { return pendingFollowUpElect; }
    public void setPendingFollowUpElect(String pendingFollowUpElect) { this.pendingFollowUpElect = pendingFollowUpElect; }

    public String getPendingFollowUpCp() { return pendingFollowUpCp; }
    public void setPendingFollowUpCp(String pendingFollowUpCp) { this.pendingFollowUpCp = pendingFollowUpCp; }

    public String getDelayAnalysis() { return delayAnalysis; }
    public void setDelayAnalysis(String delayAnalysis) { this.delayAnalysis = delayAnalysis; }

    public LocalDate getLastUpdatedDataAsOn() { return lastUpdatedDataAsOn; }
    public void setLastUpdatedDataAsOn(LocalDate lastUpdatedDataAsOn) { this.lastUpdatedDataAsOn = lastUpdatedDataAsOn; }

    public String getLastMeetingReview() { return lastMeetingReview; }
    public void setLastMeetingReview(String lastMeetingReview) { this.lastMeetingReview = lastMeetingReview; }

    public String getLatestReview() { return latestReview; }
    public void setLatestReview(String latestReview) { this.latestReview = latestReview; }

    public String getDynamicDispatchMonth() { return dynamicDispatchMonth; }
    public void setDynamicDispatchMonth(String dynamicDispatchMonth) { this.dynamicDispatchMonth = dynamicDispatchMonth; }

    public Boolean getValidationUploaded() { return validationUploaded; }
    public void setValidationUploaded(Boolean validationUploaded) { this.validationUploaded = validationUploaded; }

    public Boolean getMatpsUploaded() { return matpsUploaded; }
    public void setMatpsUploaded(Boolean matpsUploaded) { this.matpsUploaded = matpsUploaded; }

    public Boolean getVideoUploaded() { return videoUploaded; }
    public void setVideoUploaded(Boolean videoUploaded) { this.videoUploaded = videoUploaded; }

    public Boolean getTwelveDaysProject() { return twelveDaysProject; }
    public void setTwelveDaysProject(Boolean twelveDaysProject) { this.twelveDaysProject = twelveDaysProject; }

    public Boolean getCcnInterchangedMc() { return ccnInterchangedMc; }
    public void setCcnInterchangedMc(Boolean ccnInterchangedMc) { this.ccnInterchangedMc = ccnInterchangedMc; }

    public Integer getStdTaskOfMachine() { return stdTaskOfMachine; }
    public void setStdTaskOfMachine(Integer stdTaskOfMachine) { this.stdTaskOfMachine = stdTaskOfMachine; }

    public Integer getTaskCompleted() { return taskCompleted; }
    public void setTaskCompleted(Integer taskCompleted) { this.taskCompleted = taskCompleted; }

    public Double getMachinePercentage() { return machinePercentage; }
    public void setMachinePercentage(Double machinePercentage) { this.machinePercentage = machinePercentage; }

    public String getLiveStatus() { return liveStatus; }
    public void setLiveStatus(String liveStatus) { this.liveStatus = liveStatus; }

    public Double getProjectPercentage() { return projectPercentage; }
    public void setProjectPercentage(Double projectPercentage) { this.projectPercentage = projectPercentage; }

    // Plan date getters and setters
    public LocalDate getPlanCommercialClearanceDate() { return planCommercialClearanceDate; }
    public void setPlanCommercialClearanceDate(LocalDate planCommercialClearanceDate) { this.planCommercialClearanceDate = planCommercialClearanceDate; }

    public LocalDate getPlanAdvPaymentRecdDate() { return planAdvPaymentRecdDate; }
    public void setPlanAdvPaymentRecdDate(LocalDate planAdvPaymentRecdDate) { this.planAdvPaymentRecdDate = planAdvPaymentRecdDate; }

    public LocalDate getPlanBalPaymentRecdDate() { return planBalPaymentRecdDate; }
    public void setPlanBalPaymentRecdDate(LocalDate planBalPaymentRecdDate) { this.planBalPaymentRecdDate = planBalPaymentRecdDate; }

    public LocalDate getPlanMachineLayoutApprovalDate() { return planMachineLayoutApprovalDate; }
    public void setPlanMachineLayoutApprovalDate(LocalDate planMachineLayoutApprovalDate) { this.planMachineLayoutApprovalDate = planMachineLayoutApprovalDate; }

    public LocalDate getPlanRoomLayoutApprovalDate() { return planRoomLayoutApprovalDate; }
    public void setPlanRoomLayoutApprovalDate(LocalDate planRoomLayoutApprovalDate) { this.planRoomLayoutApprovalDate = planRoomLayoutApprovalDate; }

    public LocalDate getPlanCpLayoutApprovalDate() { return planCpLayoutApprovalDate; }
    public void setPlanCpLayoutApprovalDate(LocalDate planCpLayoutApprovalDate) { this.planCpLayoutApprovalDate = planCpLayoutApprovalDate; }

    public LocalDate getPlanOaGenerationDate() { return planOaGenerationDate; }
    public void setPlanOaGenerationDate(LocalDate planOaGenerationDate) { this.planOaGenerationDate = planOaGenerationDate; }

    public LocalDate getPlanCpOaGenerationDate() { return planCpOaGenerationDate; }
    public void setPlanCpOaGenerationDate(LocalDate planCpOaGenerationDate) { this.planCpOaGenerationDate = planCpOaGenerationDate; }

    public LocalDate getPlanDispatchClearanceRecdDate() { return planDispatchClearanceRecdDate; }
    public void setPlanDispatchClearanceRecdDate(LocalDate planDispatchClearanceRecdDate) { this.planDispatchClearanceRecdDate = planDispatchClearanceRecdDate; }

    public LocalDate getPlanLoaBasicReleaseDate() { return planLoaBasicReleaseDate; }
    public void setPlanLoaBasicReleaseDate(LocalDate planLoaBasicReleaseDate) { this.planLoaBasicReleaseDate = planLoaBasicReleaseDate; }

    public LocalDate getPlanLoaOptionalReleaseDate() { return planLoaOptionalReleaseDate; }
    public void setPlanLoaOptionalReleaseDate(LocalDate planLoaOptionalReleaseDate) { this.planLoaOptionalReleaseDate = planLoaOptionalReleaseDate; }

    public LocalDate getPlanDqReleaseDate() { return planDqReleaseDate; }
    public void setPlanDqReleaseDate(LocalDate planDqReleaseDate) { this.planDqReleaseDate = planDqReleaseDate; }

    public LocalDate getPlanIfatProtocolReleaseDate() { return planIfatProtocolReleaseDate; }
    public void setPlanIfatProtocolReleaseDate(LocalDate planIfatProtocolReleaseDate) { this.planIfatProtocolReleaseDate = planIfatProtocolReleaseDate; }

    public LocalDate getPlanPmrSubmissionDate() { return planPmrSubmissionDate; }
    public void setPlanPmrSubmissionDate(LocalDate planPmrSubmissionDate) { this.planPmrSubmissionDate = planPmrSubmissionDate; }

    public LocalDate getPlanProductReceiptDate() { return planProductReceiptDate; }
    public void setPlanProductReceiptDate(LocalDate planProductReceiptDate) { this.planProductReceiptDate = planProductReceiptDate; }

    public LocalDate getPlanPackagingReceiptDate() { return planPackagingReceiptDate; }
    public void setPlanPackagingReceiptDate(LocalDate planPackagingReceiptDate) { this.planPackagingReceiptDate = planPackagingReceiptDate; }

    public LocalDate getPlanComplianceDate() { return planComplianceDate; }
    public void setPlanComplianceDate(LocalDate planComplianceDate) { this.planComplianceDate = planComplianceDate; }

    public LocalDate getPlanPackingClearanceRecdDate() { return planPackingClearanceRecdDate; }
    public void setPlanPackingClearanceRecdDate(LocalDate planPackingClearanceRecdDate) { this.planPackingClearanceRecdDate = planPackingClearanceRecdDate; }

    public LocalDate getPlanPpcBasicMaterialDate() { return planPpcBasicMaterialDate; }
    public void setPlanPpcBasicMaterialDate(LocalDate planPpcBasicMaterialDate) { this.planPpcBasicMaterialDate = planPpcBasicMaterialDate; }

    public LocalDate getPlanPpcOptionalMaterialDate() { return planPpcOptionalMaterialDate; }
    public void setPlanPpcOptionalMaterialDate(LocalDate planPpcOptionalMaterialDate) { this.planPpcOptionalMaterialDate = planPpcOptionalMaterialDate; }

    public LocalDate getPlanCpDesignReleaseDate() { return planCpDesignReleaseDate; }
    public void setPlanCpDesignReleaseDate(LocalDate planCpDesignReleaseDate) { this.planCpDesignReleaseDate = planCpDesignReleaseDate; }

    public LocalDate getPlanMachineCpManufacturingDate() { return planMachineCpManufacturingDate; }
    public void setPlanMachineCpManufacturingDate(LocalDate planMachineCpManufacturingDate) { this.planMachineCpManufacturingDate = planMachineCpManufacturingDate; }

    public LocalDate getPlanMachineOptionalCpManufacturingDate() { return planMachineOptionalCpManufacturingDate; }
    public void setPlanMachineOptionalCpManufacturingDate(LocalDate planMachineOptionalCpManufacturingDate) { this.planMachineOptionalCpManufacturingDate = planMachineOptionalCpManufacturingDate; }

    public LocalDate getPlanBasicMaterialHandedOverAssyDate() { return planBasicMaterialHandedOverAssyDate; }
    public void setPlanBasicMaterialHandedOverAssyDate(LocalDate planBasicMaterialHandedOverAssyDate) { this.planBasicMaterialHandedOverAssyDate = planBasicMaterialHandedOverAssyDate; }

    public LocalDate getPlanOptionalMaterialHandedOverAssyDate() { return planOptionalMaterialHandedOverAssyDate; }
    public void setPlanOptionalMaterialHandedOverAssyDate(LocalDate planOptionalMaterialHandedOverAssyDate) { this.planOptionalMaterialHandedOverAssyDate = planOptionalMaterialHandedOverAssyDate; }

    public LocalDate getPlanBasicMaterialReceiptDate() { return planBasicMaterialReceiptDate; }
    public void setPlanBasicMaterialReceiptDate(LocalDate planBasicMaterialReceiptDate) { this.planBasicMaterialReceiptDate = planBasicMaterialReceiptDate; }

    public LocalDate getPlanCustomizationMaterialReceiptDate() { return planCustomizationMaterialReceiptDate; }
    public void setPlanCustomizationMaterialReceiptDate(LocalDate planCustomizationMaterialReceiptDate) { this.planCustomizationMaterialReceiptDate = planCustomizationMaterialReceiptDate; }

    public LocalDate getPlanAssyStartDate() { return planAssyStartDate; }
    public void setPlanAssyStartDate(LocalDate planAssyStartDate) { this.planAssyStartDate = planAssyStartDate; }

    public LocalDate getPlanMachineBasicCompletion() { return planMachineBasicCompletion; }
    public void setPlanMachineBasicCompletion(LocalDate planMachineBasicCompletion) { this.planMachineBasicCompletion = planMachineBasicCompletion; }

    public LocalDate getPlanMachineCustomizationCompletion() { return planMachineCustomizationCompletion; }
    public void setPlanMachineCustomizationCompletion(LocalDate planMachineCustomizationCompletion) { this.planMachineCustomizationCompletion = planMachineCustomizationCompletion; }

    public LocalDate getPlanIfatStdCpDate() { return planIfatStdCpDate; }
    public void setPlanIfatStdCpDate(LocalDate planIfatStdCpDate) { this.planIfatStdCpDate = planIfatStdCpDate; }

    public LocalDate getPlanIfatCustomerCpDate() { return planIfatCustomerCpDate; }
    public void setPlanIfatCustomerCpDate(LocalDate planIfatCustomerCpDate) { this.planIfatCustomerCpDate = planIfatCustomerCpDate; }

    public LocalDate getPlanCfatDate() { return planCfatDate; }
    public void setPlanCfatDate(LocalDate planCfatDate) { this.planCfatDate = planCfatDate; }

    public LocalDate getPlanHandedToDispatchDate() { return planHandedToDispatchDate; }
    public void setPlanHandedToDispatchDate(LocalDate planHandedToDispatchDate) { this.planHandedToDispatchDate = planHandedToDispatchDate; }

    public LocalDate getPlanDispatchDate() { return planDispatchDate; }
    public void setPlanDispatchDate(LocalDate planDispatchDate) { this.planDispatchDate = planDispatchDate; }

    public LocalDate getPlanOptionalCpLayoutApprovalDate() { return planOptionalCpLayoutApprovalDate; }
    public void setPlanOptionalCpLayoutApprovalDate(LocalDate planOptionalCpLayoutApprovalDate) { this.planOptionalCpLayoutApprovalDate = planOptionalCpLayoutApprovalDate; }

    // Additional field getters and setters
    public String getInterUnitReceipt() { return interUnitReceipt; }
    public void setInterUnitReceipt(String interUnitReceipt) { this.interUnitReceipt = interUnitReceipt; }

    public String getInterUnitTransfer() { return interUnitTransfer; }
    public void setInterUnitTransfer(String interUnitTransfer) { this.interUnitTransfer = interUnitTransfer; }

    public String getIssueReturnQty() { return issueReturnQty; }
    public void setIssueReturnQty(String issueReturnQty) { this.issueReturnQty = issueReturnQty; }

    public Integer getStepCompletionStatus() { return stepCompletionStatus; }
    public void setStepCompletionStatus(Integer stepCompletionStatus) { this.stepCompletionStatus = stepCompletionStatus; }

    public LocalDate getFormLastSavedDate() { return formLastSavedDate; }
    public void setFormLastSavedDate(LocalDate formLastSavedDate) { this.formLastSavedDate = formLastSavedDate; }
}