package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "machineccn_project")
public class MachineCcnProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ccn;

    @Column(name = "machine_category")
    private String machineCategory;

    private LocalDate actualOaGenerationDate;
    private LocalDate actualCpOaGenerationDate;
    private LocalDate actualAdvPaymentRecdDate;
    private LocalDate actualBalPaymentRecdDate;
    private LocalDate actualCpLayoutApprovalDate;
    private LocalDate actualOptionalCpLayoutApprovalDate;
    private LocalDate actualMachineLayoutApprovalDate;
    private LocalDate actualRoomLayoutApprovalDate;
    private LocalDate actualDispatchClearanceRecdDate;
    private LocalDate actualCommercialClearanceDate;
    private LocalDate actualDqReleaseDate;
    private LocalDate actualPpcBasicMaterialDate;
    private LocalDate actualPpcOptionalMaterialDate;
    private LocalDate actualCpDesignReleaseDate;
    private LocalDate actualMachineCpManufacturingDate;
    private LocalDate actualMachineOptionalCpManufacturingDate;
    private LocalDate actualMachineBasicCompletion;
    private LocalDate actualMachineCustomizationCompletion;
    private LocalDate actualAssyStartDate;
    private LocalDate actualIfatStdCpDate;
    private LocalDate actualIfatCustomerCpDate;
    private LocalDate actualPmrSubmissionDate;
    private LocalDate actualProductReceiptDate;
    private LocalDate actualPackagingReceiptDate;
    private LocalDate actualIfatProtocolReleaseDate;
    private LocalDate actualCfatDate;
    private LocalDate actualComplianceDate;
    private LocalDate actualPackingClearanceRecdDate;
    private LocalDate actualBasicMaterialHandedOverAssyDate;
    private LocalDate actualOptionalMaterialHandedOverAssyDate;
    private LocalDate actualBasicMaterialReceiptDate;
    private LocalDate actualCustomizationMaterialReceiptDate;
    private LocalDate actualHandedToDispatchDate;
    private LocalDate actualDispatchDate;
    private LocalDate actualPpcBasicDate;
    private LocalDate actualPpcOptionalDate;
    private LocalDate actualLoaBasic;
    private LocalDate actualLoaOptional;
    private LocalDate formLastSavedDate;

    // New fields for Machine CCN Project
    private String dnNumber;
    private String salesOrderNumber;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String remarksSales;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String mcnRemarksShortfall;
    private Integer totalBasicAssemblyAttached;
    private Integer totalOptionalAssemblyAttached;
    private Integer totalAdditionalNewAssemblyAttachedAfterCfat;
    private LocalDate additionalNewAssemblyAttachedAfterCfatDate;
    private String assembliesReleasedBy;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String remarksDesign;
    private String mcnNumber;
    private Boolean mcnWithShortfall;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String productRemark;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String productPackagingRemarks;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String cpTrialRemarksTable;
    private Integer machineCpTrialCompleted;
    private Integer machineOptionalCpTrialCompleted;
    private LocalDate mcnShortfallClosureDate;
    private LocalDate actualPpcNewMaterialDateAfterCfat;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String materialProgressStatus;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String remarksPpc;
    private Integer delayInDaysBasicMaterial;
    private Integer delayInDaysOptionalBasicMaterial;
    private Integer delayInDaysMachineCp;
    private Integer delayInDaysOptionalCp;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String cpProgressStatusTable;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String remarksCpCell;
    private String cpImageUpload;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String remarksStores;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String lrNo;
    private LocalDate lrDate;
    private LocalDate podDate;
    private String ccnStatus;
    private String machineName;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String progressStatus;
    private String machineImageStoresUpload;
    private LocalDate lastUpdatedDataAsOn;
    private String workmen;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String assemblyProgressStatus;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String pendingFollowUpMech;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String pendingFollowUpElect;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String pendingFollowUpCp;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String delayAnalysis;
    private String machineImageAssemblyUpload;
    private Boolean validationUploaded;
    private Boolean matpsUploaded;
    private Boolean videoUploaded;
    private Boolean twelveDaysProject;
    private Boolean ccnInterchangedMc;
    private String liveStatus;
    private Integer stdTask;
    private Integer taskCompleted;
    private Double machineCompletionPercentage;
    private Double projectCompletionPercentage;
    private Boolean interUnitReceipt;
    private Boolean interUnitTransfer;
    private Boolean issueReturnQty;

    // Final Date Lock and Alert System Fields
    // For each date field, we have: Final (boolean), AlertSent (boolean), LastAlertDate (LocalDate)

    // OA and Payment dates
    private Boolean actualOaGenerationDateFinal;
    private Boolean actualOaGenerationDateAlertSent;
    private LocalDate actualOaGenerationDateLastAlertDate;

    private Boolean actualCpOaGenerationDateFinal;
    private Boolean actualCpOaGenerationDateAlertSent;
    private LocalDate actualCpOaGenerationDateLastAlertDate;

    private Boolean actualAdvPaymentRecdDateFinal;
    private Boolean actualAdvPaymentRecdDateAlertSent;
    private LocalDate actualAdvPaymentRecdDateLastAlertDate;

    private Boolean actualBalPaymentRecdDateFinal;
    private Boolean actualBalPaymentRecdDateAlertSent;
    private LocalDate actualBalPaymentRecdDateLastAlertDate;

    // Layout approval dates
    private Boolean actualCpLayoutApprovalDateFinal;
    private Boolean actualCpLayoutApprovalDateAlertSent;
    private LocalDate actualCpLayoutApprovalDateLastAlertDate;

    private Boolean actualOptionalCpLayoutApprovalDateFinal;
    private Boolean actualOptionalCpLayoutApprovalDateAlertSent;
    private LocalDate actualOptionalCpLayoutApprovalDateLastAlertDate;

    private Boolean actualMachineLayoutApprovalDateFinal;
    private Boolean actualMachineLayoutApprovalDateAlertSent;
    private LocalDate actualMachineLayoutApprovalDateLastAlertDate;

    private Boolean actualRoomLayoutApprovalDateFinal;
    private Boolean actualRoomLayoutApprovalDateAlertSent;
    private LocalDate actualRoomLayoutApprovalDateLastAlertDate;

    // Clearance and release dates
    private Boolean actualDispatchClearanceRecdDateFinal;
    private Boolean actualDispatchClearanceRecdDateAlertSent;
    private LocalDate actualDispatchClearanceRecdDateLastAlertDate;

    private Boolean actualCommercialClearanceDateFinal;
    private Boolean actualCommercialClearanceDateAlertSent;
    private LocalDate actualCommercialClearanceDateLastAlertDate;

    private Boolean actualDqReleaseDateFinal;
    private Boolean actualDqReleaseDateAlertSent;
    private LocalDate actualDqReleaseDateLastAlertDate;

    // PPC dates
    private Boolean actualPpcBasicMaterialDateFinal;
    private Boolean actualPpcBasicMaterialDateAlertSent;
    private LocalDate actualPpcBasicMaterialDateLastAlertDate;

    private Boolean actualPpcOptionalMaterialDateFinal;
    private Boolean actualPpcOptionalMaterialDateAlertSent;
    private LocalDate actualPpcOptionalMaterialDateLastAlertDate;

    private Boolean actualCpDesignReleaseDateFinal;
    private Boolean actualCpDesignReleaseDateAlertSent;
    private LocalDate actualCpDesignReleaseDateLastAlertDate;

    // Manufacturing dates
    private Boolean actualMachineCpManufacturingDateFinal;
    private Boolean actualMachineCpManufacturingDateAlertSent;
    private LocalDate actualMachineCpManufacturingDateLastAlertDate;

    private Boolean actualMachineOptionalCpManufacturingDateFinal;
    private Boolean actualMachineOptionalCpManufacturingDateAlertSent;
    private LocalDate actualMachineOptionalCpManufacturingDateLastAlertDate;

    private Boolean actualMachineBasicCompletionFinal;
    private Boolean actualMachineBasicCompletionAlertSent;
    private LocalDate actualMachineBasicCompletionLastAlertDate;

    private Boolean actualMachineCustomizationCompletionFinal;
    private Boolean actualMachineCustomizationCompletionAlertSent;
    private LocalDate actualMachineCustomizationCompletionLastAlertDate;

    // Assembly dates
    private Boolean actualAssyStartDateFinal;
    private Boolean actualAssyStartDateAlertSent;
    private LocalDate actualAssyStartDateLastAlertDate;

    private Boolean actualIfatStdCpDateFinal;
    private Boolean actualIfatStdCpDateAlertSent;
    private LocalDate actualIfatStdCpDateLastAlertDate;

    private Boolean actualIfatCustomerCpDateFinal;
    private Boolean actualIfatCustomerCpDateAlertSent;
    private LocalDate actualIfatCustomerCpDateLastAlertDate;

    // Testing and compliance dates
    private Boolean actualPmrSubmissionDateFinal;
    private Boolean actualPmrSubmissionDateAlertSent;
    private LocalDate actualPmrSubmissionDateLastAlertDate;

    private Boolean actualProductReceiptDateFinal;
    private Boolean actualProductReceiptDateAlertSent;
    private LocalDate actualProductReceiptDateLastAlertDate;

    private Boolean actualPackagingReceiptDateFinal;
    private Boolean actualPackagingReceiptDateAlertSent;
    private LocalDate actualPackagingReceiptDateLastAlertDate;

    private Boolean actualIfatProtocolReleaseDateFinal;
    private Boolean actualIfatProtocolReleaseDateAlertSent;
    private LocalDate actualIfatProtocolReleaseDateLastAlertDate;

    private Boolean actualCfatDateFinal;
    private Boolean actualCfatDateAlertSent;
    private LocalDate actualCfatDateLastAlertDate;

    private Boolean actualComplianceDateFinal;
    private Boolean actualComplianceDateAlertSent;
    private LocalDate actualComplianceDateLastAlertDate;

    private Boolean actualPackingClearanceRecdDateFinal;
    private Boolean actualPackingClearanceRecdDateAlertSent;
    private LocalDate actualPackingClearanceRecdDateLastAlertDate;

    // Material handover dates
    private Boolean actualBasicMaterialHandedOverAssyDateFinal;
    private Boolean actualBasicMaterialHandedOverAssyDateAlertSent;
    private LocalDate actualBasicMaterialHandedOverAssyDateLastAlertDate;

    private Boolean actualOptionalMaterialHandedOverAssyDateFinal;
    private Boolean actualOptionalMaterialHandedOverAssyDateAlertSent;
    private LocalDate actualOptionalMaterialHandedOverAssyDateLastAlertDate;

    private Boolean actualBasicMaterialReceiptDateFinal;
    private Boolean actualBasicMaterialReceiptDateAlertSent;
    private LocalDate actualBasicMaterialReceiptDateLastAlertDate;

    private Boolean actualCustomizationMaterialReceiptDateFinal;
    private Boolean actualCustomizationMaterialReceiptDateAlertSent;
    private LocalDate actualCustomizationMaterialReceiptDateLastAlertDate;

    // Dispatch dates
    private Boolean actualHandedToDispatchDateFinal;
    private Boolean actualHandedToDispatchDateAlertSent;
    private LocalDate actualHandedToDispatchDateLastAlertDate;

    private Boolean actualDispatchDateFinal;
    private Boolean actualDispatchDateAlertSent;
    private LocalDate actualDispatchDateLastAlertDate;

    // Additional PPC dates
    private Boolean actualPpcBasicDateFinal;
    private Boolean actualPpcBasicDateAlertSent;
    private LocalDate actualPpcBasicDateLastAlertDate;

    private Boolean actualPpcOptionalDateFinal;
    private Boolean actualPpcOptionalDateAlertSent;
    private LocalDate actualPpcOptionalDateLastAlertDate;

    private Boolean actualLoaBasicFinal;
    private Boolean actualLoaBasicAlertSent;
    private LocalDate actualLoaBasicLastAlertDate;

    private Boolean actualLoaOptionalFinal;
    private Boolean actualLoaOptionalAlertSent;
    private LocalDate actualLoaOptionalLastAlertDate;

    // Additional dates
    private Boolean additionalNewAssemblyAttachedAfterCfatDateFinal;
    private Boolean additionalNewAssemblyAttachedAfterCfatDateAlertSent;
    private LocalDate additionalNewAssemblyAttachedAfterCfatDateLastAlertDate;

    private Boolean mcnShortfallClosureDateFinal;
    private Boolean mcnShortfallClosureDateAlertSent;
    private LocalDate mcnShortfallClosureDateLastAlertDate;

    private Boolean actualPpcNewMaterialDateAfterCfatFinal;
    private Boolean actualPpcNewMaterialDateAfterCfatAlertSent;
    private LocalDate actualPpcNewMaterialDateAfterCfatLastAlertDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public String getMachineCategory() {
        return machineCategory;
    }

    public void setMachineCategory(String machineCategory) {
        this.machineCategory = machineCategory;
    }

    public LocalDate getActualOaGenerationDate() {
        return actualOaGenerationDate;
    }

    public void setActualOaGenerationDate(LocalDate actualOaGenerationDate) {
        this.actualOaGenerationDate = actualOaGenerationDate;
    }

    public LocalDate getActualCpOaGenerationDate() {
        return actualCpOaGenerationDate;
    }

    public void setActualCpOaGenerationDate(LocalDate actualCpOaGenerationDate) {
        this.actualCpOaGenerationDate = actualCpOaGenerationDate;
    }

    public LocalDate getActualAdvPaymentRecdDate() {
        return actualAdvPaymentRecdDate;
    }

    public void setActualAdvPaymentRecdDate(LocalDate actualAdvPaymentRecdDate) {
        this.actualAdvPaymentRecdDate = actualAdvPaymentRecdDate;
    }

    public LocalDate getActualBalPaymentRecdDate() {
        return actualBalPaymentRecdDate;
    }

    public void setActualBalPaymentRecdDate(LocalDate actualBalPaymentRecdDate) {
        this.actualBalPaymentRecdDate = actualBalPaymentRecdDate;
    }

    public LocalDate getActualCpLayoutApprovalDate() {
        return actualCpLayoutApprovalDate;
    }

    public void setActualCpLayoutApprovalDate(LocalDate actualCpLayoutApprovalDate) {
        this.actualCpLayoutApprovalDate = actualCpLayoutApprovalDate;
    }

    public LocalDate getActualOptionalCpLayoutApprovalDate() {
        return actualOptionalCpLayoutApprovalDate;
    }

    public void setActualOptionalCpLayoutApprovalDate(LocalDate actualOptionalCpLayoutApprovalDate) {
        this.actualOptionalCpLayoutApprovalDate = actualOptionalCpLayoutApprovalDate;
    }

    public LocalDate getActualMachineLayoutApprovalDate() {
        return actualMachineLayoutApprovalDate;
    }

    public void setActualMachineLayoutApprovalDate(LocalDate actualMachineLayoutApprovalDate) {
        this.actualMachineLayoutApprovalDate = actualMachineLayoutApprovalDate;
    }

    public LocalDate getActualRoomLayoutApprovalDate() {
        return actualRoomLayoutApprovalDate;
    }

    public void setActualRoomLayoutApprovalDate(LocalDate actualRoomLayoutApprovalDate) {
        this.actualRoomLayoutApprovalDate = actualRoomLayoutApprovalDate;
    }

    public LocalDate getActualDispatchClearanceRecdDate() {
        return actualDispatchClearanceRecdDate;
    }

    public void setActualDispatchClearanceRecdDate(LocalDate actualDispatchClearanceRecdDate) {
        this.actualDispatchClearanceRecdDate = actualDispatchClearanceRecdDate;
    }

    public LocalDate getActualCommercialClearanceDate() {
        return actualCommercialClearanceDate;
    }

    public void setActualCommercialClearanceDate(LocalDate actualCommercialClearanceDate) {
        this.actualCommercialClearanceDate = actualCommercialClearanceDate;
    }

    public LocalDate getActualDqReleaseDate() {
        return actualDqReleaseDate;
    }

    public void setActualDqReleaseDate(LocalDate actualDqReleaseDate) {
        this.actualDqReleaseDate = actualDqReleaseDate;
    }

    public LocalDate getActualPpcBasicMaterialDate() {
        return actualPpcBasicMaterialDate;
    }

    public void setActualPpcBasicMaterialDate(LocalDate actualPpcBasicMaterialDate) {
        this.actualPpcBasicMaterialDate = actualPpcBasicMaterialDate;
    }

    public LocalDate getActualPpcOptionalMaterialDate() {
        return actualPpcOptionalMaterialDate;
    }

    public void setActualPpcOptionalMaterialDate(LocalDate actualPpcOptionalMaterialDate) {
        this.actualPpcOptionalMaterialDate = actualPpcOptionalMaterialDate;
    }

    public LocalDate getActualCpDesignReleaseDate() {
        return actualCpDesignReleaseDate;
    }

    public void setActualCpDesignReleaseDate(LocalDate actualCpDesignReleaseDate) {
        this.actualCpDesignReleaseDate = actualCpDesignReleaseDate;
    }

    public LocalDate getActualMachineCpManufacturingDate() {
        return actualMachineCpManufacturingDate;
    }

    public void setActualMachineCpManufacturingDate(LocalDate actualMachineCpManufacturingDate) {
        this.actualMachineCpManufacturingDate = actualMachineCpManufacturingDate;
    }

    public LocalDate getActualMachineOptionalCpManufacturingDate() {
        return actualMachineOptionalCpManufacturingDate;
    }

    public void setActualMachineOptionalCpManufacturingDate(LocalDate actualMachineOptionalCpManufacturingDate) {
        this.actualMachineOptionalCpManufacturingDate = actualMachineOptionalCpManufacturingDate;
    }

    public LocalDate getActualMachineBasicCompletion() {
        return actualMachineBasicCompletion;
    }

    public void setActualMachineBasicCompletion(LocalDate actualMachineBasicCompletion) {
        this.actualMachineBasicCompletion = actualMachineBasicCompletion;
    }

    public LocalDate getActualMachineCustomizationCompletion() {
        return actualMachineCustomizationCompletion;
    }

    public void setActualMachineCustomizationCompletion(LocalDate actualMachineCustomizationCompletion) {
        this.actualMachineCustomizationCompletion = actualMachineCustomizationCompletion;
    }

    public LocalDate getActualAssyStartDate() {
        return actualAssyStartDate;
    }

    public void setActualAssyStartDate(LocalDate actualAssyStartDate) {
        this.actualAssyStartDate = actualAssyStartDate;
    }

    public LocalDate getActualIfatStdCpDate() {
        return actualIfatStdCpDate;
    }

    public void setActualIfatStdCpDate(LocalDate actualIfatStdCpDate) {
        this.actualIfatStdCpDate = actualIfatStdCpDate;
    }

    public LocalDate getActualIfatCustomerCpDate() {
        return actualIfatCustomerCpDate;
    }

    public void setActualIfatCustomerCpDate(LocalDate actualIfatCustomerCpDate) {
        this.actualIfatCustomerCpDate = actualIfatCustomerCpDate;
    }

    public LocalDate getActualPmrSubmissionDate() {
        return actualPmrSubmissionDate;
    }

    public void setActualPmrSubmissionDate(LocalDate actualPmrSubmissionDate) {
        this.actualPmrSubmissionDate = actualPmrSubmissionDate;
    }

    public LocalDate getActualProductReceiptDate() {
        return actualProductReceiptDate;
    }

    public void setActualProductReceiptDate(LocalDate actualProductReceiptDate) {
        this.actualProductReceiptDate = actualProductReceiptDate;
    }

    public LocalDate getActualPackagingReceiptDate() {
        return actualPackagingReceiptDate;
    }

    public void setActualPackagingReceiptDate(LocalDate actualPackagingReceiptDate) {
        this.actualPackagingReceiptDate = actualPackagingReceiptDate;
    }

    public LocalDate getActualIfatProtocolReleaseDate() {
        return actualIfatProtocolReleaseDate;
    }

    public void setActualIfatProtocolReleaseDate(LocalDate actualIfatProtocolReleaseDate) {
        this.actualIfatProtocolReleaseDate = actualIfatProtocolReleaseDate;
    }

    public LocalDate getActualCfatDate() {
        return actualCfatDate;
    }

    public void setActualCfatDate(LocalDate actualCfatDate) {
        this.actualCfatDate = actualCfatDate;
    }

    public LocalDate getActualComplianceDate() {
        return actualComplianceDate;
    }

    public void setActualComplianceDate(LocalDate actualComplianceDate) {
        this.actualComplianceDate = actualComplianceDate;
    }

    public LocalDate getActualPackingClearanceRecdDate() {
        return actualPackingClearanceRecdDate;
    }

    public void setActualPackingClearanceRecdDate(LocalDate actualPackingClearanceRecdDate) {
        this.actualPackingClearanceRecdDate = actualPackingClearanceRecdDate;
    }

    public LocalDate getActualBasicMaterialHandedOverAssyDate() {
        return actualBasicMaterialHandedOverAssyDate;
    }

    public void setActualBasicMaterialHandedOverAssyDate(LocalDate actualBasicMaterialHandedOverAssyDate) {
        this.actualBasicMaterialHandedOverAssyDate = actualBasicMaterialHandedOverAssyDate;
    }

    public LocalDate getActualOptionalMaterialHandedOverAssyDate() {
        return actualOptionalMaterialHandedOverAssyDate;
    }

    public void setActualOptionalMaterialHandedOverAssyDate(LocalDate actualOptionalMaterialHandedOverAssyDate) {
        this.actualOptionalMaterialHandedOverAssyDate = actualOptionalMaterialHandedOverAssyDate;
    }

    public LocalDate getActualBasicMaterialReceiptDate() {
        return actualBasicMaterialReceiptDate;
    }

    public void setActualBasicMaterialReceiptDate(LocalDate actualBasicMaterialReceiptDate) {
        this.actualBasicMaterialReceiptDate = actualBasicMaterialReceiptDate;
    }

    public LocalDate getActualCustomizationMaterialReceiptDate() {
        return actualCustomizationMaterialReceiptDate;
    }

    public void setActualCustomizationMaterialReceiptDate(LocalDate actualCustomizationMaterialReceiptDate) {
        this.actualCustomizationMaterialReceiptDate = actualCustomizationMaterialReceiptDate;
    }

    public LocalDate getActualHandedToDispatchDate() {
        return actualHandedToDispatchDate;
    }

    public void setActualHandedToDispatchDate(LocalDate actualHandedToDispatchDate) {
        this.actualHandedToDispatchDate = actualHandedToDispatchDate;
    }

    public LocalDate getActualDispatchDate() {
        return actualDispatchDate;
    }

    public void setActualDispatchDate(LocalDate actualDispatchDate) {
        this.actualDispatchDate = actualDispatchDate;
    }

    public LocalDate getActualPpcBasicDate() {
        return actualPpcBasicDate;
    }

    public void setActualPpcBasicDate(LocalDate actualPpcBasicDate) {
        this.actualPpcBasicDate = actualPpcBasicDate;
    }

    public LocalDate getActualPpcOptionalDate() {
        return actualPpcOptionalDate;
    }

    public void setActualPpcOptionalDate(LocalDate actualPpcOptionalDate) {
        this.actualPpcOptionalDate = actualPpcOptionalDate;
    }

    public LocalDate getActualLoaBasic() {
        return actualLoaBasic;
    }

    public void setActualLoaBasic(LocalDate actualLoaBasic) {
        this.actualLoaBasic = actualLoaBasic;
    }

    public LocalDate getActualLoaOptional() {
        return actualLoaOptional;
    }

    public void setActualLoaOptional(LocalDate actualLoaOptional) {
        this.actualLoaOptional = actualLoaOptional;
    }

    public LocalDate getFormLastSavedDate() {
        return formLastSavedDate;
    }

    public void setFormLastSavedDate(LocalDate formLastSavedDate) {
        this.formLastSavedDate = formLastSavedDate;
    }

    // Getters and Setters for new fields
    public String getDnNumber() {
        return dnNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getRemarksSales() {
        return remarksSales;
    }

    public void setRemarksSales(String remarksSales) {
        this.remarksSales = remarksSales;
    }

    public String getMcnRemarksShortfall() {
        return mcnRemarksShortfall;
    }

    public void setMcnRemarksShortfall(String mcnRemarksShortfall) {
        this.mcnRemarksShortfall = mcnRemarksShortfall;
    }

    public Integer getTotalBasicAssemblyAttached() {
        return totalBasicAssemblyAttached;
    }

    public void setTotalBasicAssemblyAttached(Integer totalBasicAssemblyAttached) {
        this.totalBasicAssemblyAttached = totalBasicAssemblyAttached;
    }

    public Integer getTotalOptionalAssemblyAttached() {
        return totalOptionalAssemblyAttached;
    }

    public void setTotalOptionalAssemblyAttached(Integer totalOptionalAssemblyAttached) {
        this.totalOptionalAssemblyAttached = totalOptionalAssemblyAttached;
    }

    public Integer getTotalAdditionalNewAssemblyAttachedAfterCfat() {
        return totalAdditionalNewAssemblyAttachedAfterCfat;
    }

    public void setTotalAdditionalNewAssemblyAttachedAfterCfat(Integer totalAdditionalNewAssemblyAttachedAfterCfat) {
        this.totalAdditionalNewAssemblyAttachedAfterCfat = totalAdditionalNewAssemblyAttachedAfterCfat;
    }

    public LocalDate getAdditionalNewAssemblyAttachedAfterCfatDate() {
        return additionalNewAssemblyAttachedAfterCfatDate;
    }

    public void setAdditionalNewAssemblyAttachedAfterCfatDate(LocalDate additionalNewAssemblyAttachedAfterCfatDate) {
        this.additionalNewAssemblyAttachedAfterCfatDate = additionalNewAssemblyAttachedAfterCfatDate;
    }

    public String getAssembliesReleasedBy() {
        return assembliesReleasedBy;
    }

    public void setAssembliesReleasedBy(String assembliesReleasedBy) {
        this.assembliesReleasedBy = assembliesReleasedBy;
    }

    public String getRemarksDesign() {
        return remarksDesign;
    }

    public void setRemarksDesign(String remarksDesign) {
        this.remarksDesign = remarksDesign;
    }

    public String getMcnNumber() {
        return mcnNumber;
    }

    public void setMcnNumber(String mcnNumber) {
        this.mcnNumber = mcnNumber;
    }

    public Boolean getMcnWithShortfall() {
        return mcnWithShortfall;
    }

    public void setMcnWithShortfall(Boolean mcnWithShortfall) {
        this.mcnWithShortfall = mcnWithShortfall;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    public String getProductPackagingRemarks() {
        return productPackagingRemarks;
    }

    public void setProductPackagingRemarks(String productPackagingRemarks) {
        this.productPackagingRemarks = productPackagingRemarks;
    }

    public String getCpTrialRemarksTable() {
        return cpTrialRemarksTable;
    }

    public void setCpTrialRemarksTable(String cpTrialRemarksTable) {
        this.cpTrialRemarksTable = cpTrialRemarksTable;
    }

    public Integer getMachineCpTrialCompleted() {
        return machineCpTrialCompleted;
    }

    public void setMachineCpTrialCompleted(Integer machineCpTrialCompleted) {
        this.machineCpTrialCompleted = machineCpTrialCompleted;
    }

    public Integer getMachineOptionalCpTrialCompleted() {
        return machineOptionalCpTrialCompleted;
    }

    public void setMachineOptionalCpTrialCompleted(Integer machineOptionalCpTrialCompleted) {
        this.machineOptionalCpTrialCompleted = machineOptionalCpTrialCompleted;
    }

    public LocalDate getMcnShortfallClosureDate() {
        return mcnShortfallClosureDate;
    }

    public void setMcnShortfallClosureDate(LocalDate mcnShortfallClosureDate) {
        this.mcnShortfallClosureDate = mcnShortfallClosureDate;
    }

    public LocalDate getActualPpcNewMaterialDateAfterCfat() {
        return actualPpcNewMaterialDateAfterCfat;
    }

    public void setActualPpcNewMaterialDateAfterCfat(LocalDate actualPpcNewMaterialDateAfterCfat) {
        this.actualPpcNewMaterialDateAfterCfat = actualPpcNewMaterialDateAfterCfat;
    }

    public String getMaterialProgressStatus() {
        return materialProgressStatus;
    }

    public void setMaterialProgressStatus(String materialProgressStatus) {
        this.materialProgressStatus = materialProgressStatus;
    }

    public String getRemarksPpc() {
        return remarksPpc;
    }

    public void setRemarksPpc(String remarksPpc) {
        this.remarksPpc = remarksPpc;
    }

    public Integer getDelayInDaysBasicMaterial() {
        return delayInDaysBasicMaterial;
    }

    public void setDelayInDaysBasicMaterial(Integer delayInDaysBasicMaterial) {
        this.delayInDaysBasicMaterial = delayInDaysBasicMaterial;
    }

    public Integer getDelayInDaysOptionalBasicMaterial() {
        return delayInDaysOptionalBasicMaterial;
    }

    public void setDelayInDaysOptionalBasicMaterial(Integer delayInDaysOptionalBasicMaterial) {
        this.delayInDaysOptionalBasicMaterial = delayInDaysOptionalBasicMaterial;
    }

    public Integer getDelayInDaysMachineCp() {
        return delayInDaysMachineCp;
    }

    public void setDelayInDaysMachineCp(Integer delayInDaysMachineCp) {
        this.delayInDaysMachineCp = delayInDaysMachineCp;
    }

    public Integer getDelayInDaysOptionalCp() {
        return delayInDaysOptionalCp;
    }

    public void setDelayInDaysOptionalCp(Integer delayInDaysOptionalCp) {
        this.delayInDaysOptionalCp = delayInDaysOptionalCp;
    }

    public String getCpProgressStatusTable() {
        return cpProgressStatusTable;
    }

    public void setCpProgressStatusTable(String cpProgressStatusTable) {
        this.cpProgressStatusTable = cpProgressStatusTable;
    }

    public String getRemarksCpCell() {
        return remarksCpCell;
    }

    public void setRemarksCpCell(String remarksCpCell) {
        this.remarksCpCell = remarksCpCell;
    }

    public String getCpImageUpload() {
        return cpImageUpload;
    }

    public void setCpImageUpload(String cpImageUpload) {
        this.cpImageUpload = cpImageUpload;
    }

    public String getRemarksStores() {
        return remarksStores;
    }

    public void setRemarksStores(String remarksStores) {
        this.remarksStores = remarksStores;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getLrNo() {
        return lrNo;
    }

    public void setLrNo(String lrNo) {
        this.lrNo = lrNo;
    }

    public LocalDate getLrDate() {
        return lrDate;
    }

    public void setLrDate(LocalDate lrDate) {
        this.lrDate = lrDate;
    }

    public LocalDate getPodDate() {
        return podDate;
    }

    public void setPodDate(LocalDate podDate) {
        this.podDate = podDate;
    }

    public String getCcnStatus() {
        return ccnStatus;
    }

    public void setCcnStatus(String ccnStatus) {
        this.ccnStatus = ccnStatus;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getMachineImageStoresUpload() {
        return machineImageStoresUpload;
    }

    public void setMachineImageStoresUpload(String machineImageStoresUpload) {
        this.machineImageStoresUpload = machineImageStoresUpload;
    }

    public LocalDate getLastUpdatedDataAsOn() {
        return lastUpdatedDataAsOn;
    }

    public void setLastUpdatedDataAsOn(LocalDate lastUpdatedDataAsOn) {
        this.lastUpdatedDataAsOn = lastUpdatedDataAsOn;
    }

    public String getWorkmen() {
        return workmen;
    }

    public void setWorkmen(String workmen) {
        this.workmen = workmen;
    }

    public String getAssemblyProgressStatus() {
        return assemblyProgressStatus;
    }

    public void setAssemblyProgressStatus(String assemblyProgressStatus) {
        this.assemblyProgressStatus = assemblyProgressStatus;
    }

    public String getPendingFollowUpMech() {
        return pendingFollowUpMech;
    }

    public void setPendingFollowUpMech(String pendingFollowUpMech) {
        this.pendingFollowUpMech = pendingFollowUpMech;
    }

    public String getPendingFollowUpElect() {
        return pendingFollowUpElect;
    }

    public void setPendingFollowUpElect(String pendingFollowUpElect) {
        this.pendingFollowUpElect = pendingFollowUpElect;
    }

    public String getPendingFollowUpCp() {
        return pendingFollowUpCp;
    }

    public void setPendingFollowUpCp(String pendingFollowUpCp) {
        this.pendingFollowUpCp = pendingFollowUpCp;
    }

    public String getDelayAnalysis() {
        return delayAnalysis;
    }

    public void setDelayAnalysis(String delayAnalysis) {
        this.delayAnalysis = delayAnalysis;
    }

    public String getMachineImageAssemblyUpload() {
        return machineImageAssemblyUpload;
    }

    public void setMachineImageAssemblyUpload(String machineImageAssemblyUpload) {
        this.machineImageAssemblyUpload = machineImageAssemblyUpload;
    }

    public Boolean getValidationUploaded() {
        return validationUploaded;
    }

    public void setValidationUploaded(Boolean validationUploaded) {
        this.validationUploaded = validationUploaded;
    }

    public Boolean getMatpsUploaded() {
        return matpsUploaded;
    }

    public void setMatpsUploaded(Boolean matpsUploaded) {
        this.matpsUploaded = matpsUploaded;
    }

    public Boolean getVideoUploaded() {
        return videoUploaded;
    }

    public void setVideoUploaded(Boolean videoUploaded) {
        this.videoUploaded = videoUploaded;
    }

    public Boolean getTwelveDaysProject() {
        return twelveDaysProject;
    }

    public void setTwelveDaysProject(Boolean twelveDaysProject) {
        this.twelveDaysProject = twelveDaysProject;
    }

    public Boolean getCcnInterchangedMc() {
        return ccnInterchangedMc;
    }

    public void setCcnInterchangedMc(Boolean ccnInterchangedMc) {
        this.ccnInterchangedMc = ccnInterchangedMc;
    }

    public Boolean getInterUnitReceipt() {
        return interUnitReceipt;
    }

    public void setInterUnitReceipt(Boolean interUnitReceipt) {
        this.interUnitReceipt = interUnitReceipt;
    }

    public Boolean getInterUnitTransfer() {
        return interUnitTransfer;
    }

    public void setInterUnitTransfer(Boolean interUnitTransfer) {
        this.interUnitTransfer = interUnitTransfer;
    }

    public Boolean getIssueReturnQty() {
        return issueReturnQty;
    }

    public void setIssueReturnQty(Boolean issueReturnQty) {
        this.issueReturnQty = issueReturnQty;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public Integer getStdTask() {
        return stdTask;
    }

    public void setStdTask(Integer stdTask) {
        this.stdTask = stdTask;
    }

    public Integer getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Integer taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public Double getMachineCompletionPercentage() {
        return machineCompletionPercentage;
    }

    public void setMachineCompletionPercentage(Double machineCompletionPercentage) {
        this.machineCompletionPercentage = machineCompletionPercentage;
    }

    public Double getProjectCompletionPercentage() {
        return projectCompletionPercentage;
    }

    public void setProjectCompletionPercentage(Double projectCompletionPercentage) {
        this.projectCompletionPercentage = projectCompletionPercentage;
    }

    // Getter and Setter methods for Final Date Lock and Alert System Fields

    // OA and Payment dates
    public Boolean getActualOaGenerationDateFinal() {
        return actualOaGenerationDateFinal;
    }

    public void setActualOaGenerationDateFinal(Boolean actualOaGenerationDateFinal) {
        this.actualOaGenerationDateFinal = actualOaGenerationDateFinal;
    }

    public Boolean getActualOaGenerationDateAlertSent() {
        return actualOaGenerationDateAlertSent;
    }

    public void setActualOaGenerationDateAlertSent(Boolean actualOaGenerationDateAlertSent) {
        this.actualOaGenerationDateAlertSent = actualOaGenerationDateAlertSent;
    }

    public LocalDate getActualOaGenerationDateLastAlertDate() {
        return actualOaGenerationDateLastAlertDate;
    }

    public void setActualOaGenerationDateLastAlertDate(LocalDate actualOaGenerationDateLastAlertDate) {
        this.actualOaGenerationDateLastAlertDate = actualOaGenerationDateLastAlertDate;
    }

    public Boolean getActualCpOaGenerationDateFinal() {
        return actualCpOaGenerationDateFinal;
    }

    public void setActualCpOaGenerationDateFinal(Boolean actualCpOaGenerationDateFinal) {
        this.actualCpOaGenerationDateFinal = actualCpOaGenerationDateFinal;
    }

    public Boolean getActualCpOaGenerationDateAlertSent() {
        return actualCpOaGenerationDateAlertSent;
    }

    public void setActualCpOaGenerationDateAlertSent(Boolean actualCpOaGenerationDateAlertSent) {
        this.actualCpOaGenerationDateAlertSent = actualCpOaGenerationDateAlertSent;
    }

    public LocalDate getActualCpOaGenerationDateLastAlertDate() {
        return actualCpOaGenerationDateLastAlertDate;
    }

    public void setActualCpOaGenerationDateLastAlertDate(LocalDate actualCpOaGenerationDateLastAlertDate) {
        this.actualCpOaGenerationDateLastAlertDate = actualCpOaGenerationDateLastAlertDate;
    }

    public Boolean getActualAdvPaymentRecdDateFinal() {
        return actualAdvPaymentRecdDateFinal;
    }

    public void setActualAdvPaymentRecdDateFinal(Boolean actualAdvPaymentRecdDateFinal) {
        this.actualAdvPaymentRecdDateFinal = actualAdvPaymentRecdDateFinal;
    }

    public Boolean getActualAdvPaymentRecdDateAlertSent() {
        return actualAdvPaymentRecdDateAlertSent;
    }

    public void setActualAdvPaymentRecdDateAlertSent(Boolean actualAdvPaymentRecdDateAlertSent) {
        this.actualAdvPaymentRecdDateAlertSent = actualAdvPaymentRecdDateAlertSent;
    }

    public LocalDate getActualAdvPaymentRecdDateLastAlertDate() {
        return actualAdvPaymentRecdDateLastAlertDate;
    }

    public void setActualAdvPaymentRecdDateLastAlertDate(LocalDate actualAdvPaymentRecdDateLastAlertDate) {
        this.actualAdvPaymentRecdDateLastAlertDate = actualAdvPaymentRecdDateLastAlertDate;
    }

    public Boolean getActualBalPaymentRecdDateFinal() {
        return actualBalPaymentRecdDateFinal;
    }

    public void setActualBalPaymentRecdDateFinal(Boolean actualBalPaymentRecdDateFinal) {
        this.actualBalPaymentRecdDateFinal = actualBalPaymentRecdDateFinal;
    }

    public Boolean getActualBalPaymentRecdDateAlertSent() {
        return actualBalPaymentRecdDateAlertSent;
    }

    public void setActualBalPaymentRecdDateAlertSent(Boolean actualBalPaymentRecdDateAlertSent) {
        this.actualBalPaymentRecdDateAlertSent = actualBalPaymentRecdDateAlertSent;
    }

    public LocalDate getActualBalPaymentRecdDateLastAlertDate() {
        return actualBalPaymentRecdDateLastAlertDate;
    }

    public void setActualBalPaymentRecdDateLastAlertDate(LocalDate actualBalPaymentRecdDateLastAlertDate) {
        this.actualBalPaymentRecdDateLastAlertDate = actualBalPaymentRecdDateLastAlertDate;
    }

    // Layout approval dates
    public Boolean getActualCpLayoutApprovalDateFinal() {
        return actualCpLayoutApprovalDateFinal;
    }

    public void setActualCpLayoutApprovalDateFinal(Boolean actualCpLayoutApprovalDateFinal) {
        this.actualCpLayoutApprovalDateFinal = actualCpLayoutApprovalDateFinal;
    }

    public Boolean getActualCpLayoutApprovalDateAlertSent() {
        return actualCpLayoutApprovalDateAlertSent;
    }

    public void setActualCpLayoutApprovalDateAlertSent(Boolean actualCpLayoutApprovalDateAlertSent) {
        this.actualCpLayoutApprovalDateAlertSent = actualCpLayoutApprovalDateAlertSent;
    }

    public LocalDate getActualCpLayoutApprovalDateLastAlertDate() {
        return actualCpLayoutApprovalDateLastAlertDate;
    }

    public void setActualCpLayoutApprovalDateLastAlertDate(LocalDate actualCpLayoutApprovalDateLastAlertDate) {
        this.actualCpLayoutApprovalDateLastAlertDate = actualCpLayoutApprovalDateLastAlertDate;
    }

    public Boolean getActualOptionalCpLayoutApprovalDateFinal() {
        return actualOptionalCpLayoutApprovalDateFinal;
    }

    public void setActualOptionalCpLayoutApprovalDateFinal(Boolean actualOptionalCpLayoutApprovalDateFinal) {
        this.actualOptionalCpLayoutApprovalDateFinal = actualOptionalCpLayoutApprovalDateFinal;
    }

    public Boolean getActualOptionalCpLayoutApprovalDateAlertSent() {
        return actualOptionalCpLayoutApprovalDateAlertSent;
    }

    public void setActualOptionalCpLayoutApprovalDateAlertSent(Boolean actualOptionalCpLayoutApprovalDateAlertSent) {
        this.actualOptionalCpLayoutApprovalDateAlertSent = actualOptionalCpLayoutApprovalDateAlertSent;
    }

    public LocalDate getActualOptionalCpLayoutApprovalDateLastAlertDate() {
        return actualOptionalCpLayoutApprovalDateLastAlertDate;
    }

    public void setActualOptionalCpLayoutApprovalDateLastAlertDate(LocalDate actualOptionalCpLayoutApprovalDateLastAlertDate) {
        this.actualOptionalCpLayoutApprovalDateLastAlertDate = actualOptionalCpLayoutApprovalDateLastAlertDate;
    }

    public Boolean getActualMachineLayoutApprovalDateFinal() {
        return actualMachineLayoutApprovalDateFinal;
    }

    public void setActualMachineLayoutApprovalDateFinal(Boolean actualMachineLayoutApprovalDateFinal) {
        this.actualMachineLayoutApprovalDateFinal = actualMachineLayoutApprovalDateFinal;
    }

    public Boolean getActualMachineLayoutApprovalDateAlertSent() {
        return actualMachineLayoutApprovalDateAlertSent;
    }

    public void setActualMachineLayoutApprovalDateAlertSent(Boolean actualMachineLayoutApprovalDateAlertSent) {
        this.actualMachineLayoutApprovalDateAlertSent = actualMachineLayoutApprovalDateAlertSent;
    }

    public LocalDate getActualMachineLayoutApprovalDateLastAlertDate() {
        return actualMachineLayoutApprovalDateLastAlertDate;
    }

    public void setActualMachineLayoutApprovalDateLastAlertDate(LocalDate actualMachineLayoutApprovalDateLastAlertDate) {
        this.actualMachineLayoutApprovalDateLastAlertDate = actualMachineLayoutApprovalDateLastAlertDate;
    }

    public Boolean getActualRoomLayoutApprovalDateFinal() {
        return actualRoomLayoutApprovalDateFinal;
    }

    public void setActualRoomLayoutApprovalDateFinal(Boolean actualRoomLayoutApprovalDateFinal) {
        this.actualRoomLayoutApprovalDateFinal = actualRoomLayoutApprovalDateFinal;
    }

    public Boolean getActualRoomLayoutApprovalDateAlertSent() {
        return actualRoomLayoutApprovalDateAlertSent;
    }

    public void setActualRoomLayoutApprovalDateAlertSent(Boolean actualRoomLayoutApprovalDateAlertSent) {
        this.actualRoomLayoutApprovalDateAlertSent = actualRoomLayoutApprovalDateAlertSent;
    }

    public LocalDate getActualRoomLayoutApprovalDateLastAlertDate() {
        return actualRoomLayoutApprovalDateLastAlertDate;
    }

    public void setActualRoomLayoutApprovalDateLastAlertDate(LocalDate actualRoomLayoutApprovalDateLastAlertDate) {
        this.actualRoomLayoutApprovalDateLastAlertDate = actualRoomLayoutApprovalDateLastAlertDate;
    }

    // Clearance and release dates
    public Boolean getActualDispatchClearanceRecdDateFinal() {
        return actualDispatchClearanceRecdDateFinal;
    }

    public void setActualDispatchClearanceRecdDateFinal(Boolean actualDispatchClearanceRecdDateFinal) {
        this.actualDispatchClearanceRecdDateFinal = actualDispatchClearanceRecdDateFinal;
    }

    public Boolean getActualDispatchClearanceRecdDateAlertSent() {
        return actualDispatchClearanceRecdDateAlertSent;
    }

    public void setActualDispatchClearanceRecdDateAlertSent(Boolean actualDispatchClearanceRecdDateAlertSent) {
        this.actualDispatchClearanceRecdDateAlertSent = actualDispatchClearanceRecdDateAlertSent;
    }

    public LocalDate getActualDispatchClearanceRecdDateLastAlertDate() {
        return actualDispatchClearanceRecdDateLastAlertDate;
    }

    public void setActualDispatchClearanceRecdDateLastAlertDate(LocalDate actualDispatchClearanceRecdDateLastAlertDate) {
        this.actualDispatchClearanceRecdDateLastAlertDate = actualDispatchClearanceRecdDateLastAlertDate;
    }

    public Boolean getActualCommercialClearanceDateFinal() {
        return actualCommercialClearanceDateFinal;
    }

    public void setActualCommercialClearanceDateFinal(Boolean actualCommercialClearanceDateFinal) {
        this.actualCommercialClearanceDateFinal = actualCommercialClearanceDateFinal;
    }

    public Boolean getActualCommercialClearanceDateAlertSent() {
        return actualCommercialClearanceDateAlertSent;
    }

    public void setActualCommercialClearanceDateAlertSent(Boolean actualCommercialClearanceDateAlertSent) {
        this.actualCommercialClearanceDateAlertSent = actualCommercialClearanceDateAlertSent;
    }

    public LocalDate getActualCommercialClearanceDateLastAlertDate() {
        return actualCommercialClearanceDateLastAlertDate;
    }

    public void setActualCommercialClearanceDateLastAlertDate(LocalDate actualCommercialClearanceDateLastAlertDate) {
        this.actualCommercialClearanceDateLastAlertDate = actualCommercialClearanceDateLastAlertDate;
    }

    public Boolean getActualDqReleaseDateFinal() {
        return actualDqReleaseDateFinal;
    }

    public void setActualDqReleaseDateFinal(Boolean actualDqReleaseDateFinal) {
        this.actualDqReleaseDateFinal = actualDqReleaseDateFinal;
    }

    public Boolean getActualDqReleaseDateAlertSent() {
        return actualDqReleaseDateAlertSent;
    }

    public void setActualDqReleaseDateAlertSent(Boolean actualDqReleaseDateAlertSent) {
        this.actualDqReleaseDateAlertSent = actualDqReleaseDateAlertSent;
    }

    public LocalDate getActualDqReleaseDateLastAlertDate() {
        return actualDqReleaseDateLastAlertDate;
    }

    public void setActualDqReleaseDateLastAlertDate(LocalDate actualDqReleaseDateLastAlertDate) {
        this.actualDqReleaseDateLastAlertDate = actualDqReleaseDateLastAlertDate;
    }

    // PPC dates
    public Boolean getActualPpcBasicMaterialDateFinal() {
        return actualPpcBasicMaterialDateFinal;
    }

    public void setActualPpcBasicMaterialDateFinal(Boolean actualPpcBasicMaterialDateFinal) {
        this.actualPpcBasicMaterialDateFinal = actualPpcBasicMaterialDateFinal;
    }

    public Boolean getActualPpcBasicMaterialDateAlertSent() {
        return actualPpcBasicMaterialDateAlertSent;
    }

    public void setActualPpcBasicMaterialDateAlertSent(Boolean actualPpcBasicMaterialDateAlertSent) {
        this.actualPpcBasicMaterialDateAlertSent = actualPpcBasicMaterialDateAlertSent;
    }

    public LocalDate getActualPpcBasicMaterialDateLastAlertDate() {
        return actualPpcBasicMaterialDateLastAlertDate;
    }

    public void setActualPpcBasicMaterialDateLastAlertDate(LocalDate actualPpcBasicMaterialDateLastAlertDate) {
        this.actualPpcBasicMaterialDateLastAlertDate = actualPpcBasicMaterialDateLastAlertDate;
    }

    public Boolean getActualPpcOptionalMaterialDateFinal() {
        return actualPpcOptionalMaterialDateFinal;
    }

    public void setActualPpcOptionalMaterialDateFinal(Boolean actualPpcOptionalMaterialDateFinal) {
        this.actualPpcOptionalMaterialDateFinal = actualPpcOptionalMaterialDateFinal;
    }

    public Boolean getActualPpcOptionalMaterialDateAlertSent() {
        return actualPpcOptionalMaterialDateAlertSent;
    }

    public void setActualPpcOptionalMaterialDateAlertSent(Boolean actualPpcOptionalMaterialDateAlertSent) {
        this.actualPpcOptionalMaterialDateAlertSent = actualPpcOptionalMaterialDateAlertSent;
    }

    public LocalDate getActualPpcOptionalMaterialDateLastAlertDate() {
        return actualPpcOptionalMaterialDateLastAlertDate;
    }

    public void setActualPpcOptionalMaterialDateLastAlertDate(LocalDate actualPpcOptionalMaterialDateLastAlertDate) {
        this.actualPpcOptionalMaterialDateLastAlertDate = actualPpcOptionalMaterialDateLastAlertDate;
    }

    public Boolean getActualCpDesignReleaseDateFinal() {
        return actualCpDesignReleaseDateFinal;
    }

    public void setActualCpDesignReleaseDateFinal(Boolean actualCpDesignReleaseDateFinal) {
        this.actualCpDesignReleaseDateFinal = actualCpDesignReleaseDateFinal;
    }

    public Boolean getActualCpDesignReleaseDateAlertSent() {
        return actualCpDesignReleaseDateAlertSent;
    }

    public void setActualCpDesignReleaseDateAlertSent(Boolean actualCpDesignReleaseDateAlertSent) {
        this.actualCpDesignReleaseDateAlertSent = actualCpDesignReleaseDateAlertSent;
    }

    public LocalDate getActualCpDesignReleaseDateLastAlertDate() {
        return actualCpDesignReleaseDateLastAlertDate;
    }

    public void setActualCpDesignReleaseDateLastAlertDate(LocalDate actualCpDesignReleaseDateLastAlertDate) {
        this.actualCpDesignReleaseDateLastAlertDate = actualCpDesignReleaseDateLastAlertDate;
    }

    // Manufacturing dates
    public Boolean getActualMachineCpManufacturingDateFinal() {
        return actualMachineCpManufacturingDateFinal;
    }

    public void setActualMachineCpManufacturingDateFinal(Boolean actualMachineCpManufacturingDateFinal) {
        this.actualMachineCpManufacturingDateFinal = actualMachineCpManufacturingDateFinal;
    }

    public Boolean getActualMachineCpManufacturingDateAlertSent() {
        return actualMachineCpManufacturingDateAlertSent;
    }

    public void setActualMachineCpManufacturingDateAlertSent(Boolean actualMachineCpManufacturingDateAlertSent) {
        this.actualMachineCpManufacturingDateAlertSent = actualMachineCpManufacturingDateAlertSent;
    }

    public LocalDate getActualMachineCpManufacturingDateLastAlertDate() {
        return actualMachineCpManufacturingDateLastAlertDate;
    }

    public void setActualMachineCpManufacturingDateLastAlertDate(LocalDate actualMachineCpManufacturingDateLastAlertDate) {
        this.actualMachineCpManufacturingDateLastAlertDate = actualMachineCpManufacturingDateLastAlertDate;
    }

    public Boolean getActualMachineOptionalCpManufacturingDateFinal() {
        return actualMachineOptionalCpManufacturingDateFinal;
    }

    public void setActualMachineOptionalCpManufacturingDateFinal(Boolean actualMachineOptionalCpManufacturingDateFinal) {
        this.actualMachineOptionalCpManufacturingDateFinal = actualMachineOptionalCpManufacturingDateFinal;
    }

    public Boolean getActualMachineOptionalCpManufacturingDateAlertSent() {
        return actualMachineOptionalCpManufacturingDateAlertSent;
    }

    public void setActualMachineOptionalCpManufacturingDateAlertSent(Boolean actualMachineOptionalCpManufacturingDateAlertSent) {
        this.actualMachineOptionalCpManufacturingDateAlertSent = actualMachineOptionalCpManufacturingDateAlertSent;
    }

    public LocalDate getActualMachineOptionalCpManufacturingDateLastAlertDate() {
        return actualMachineOptionalCpManufacturingDateLastAlertDate;
    }

    public void setActualMachineOptionalCpManufacturingDateLastAlertDate(LocalDate actualMachineOptionalCpManufacturingDateLastAlertDate) {
        this.actualMachineOptionalCpManufacturingDateLastAlertDate = actualMachineOptionalCpManufacturingDateLastAlertDate;
    }

    public Boolean getActualMachineBasicCompletionFinal() {
        return actualMachineBasicCompletionFinal;
    }

    public void setActualMachineBasicCompletionFinal(Boolean actualMachineBasicCompletionFinal) {
        this.actualMachineBasicCompletionFinal = actualMachineBasicCompletionFinal;
    }

    public Boolean getActualMachineBasicCompletionAlertSent() {
        return actualMachineBasicCompletionAlertSent;
    }

    public void setActualMachineBasicCompletionAlertSent(Boolean actualMachineBasicCompletionAlertSent) {
        this.actualMachineBasicCompletionAlertSent = actualMachineBasicCompletionAlertSent;
    }

    public LocalDate getActualMachineBasicCompletionLastAlertDate() {
        return actualMachineBasicCompletionLastAlertDate;
    }

    public void setActualMachineBasicCompletionLastAlertDate(LocalDate actualMachineBasicCompletionLastAlertDate) {
        this.actualMachineBasicCompletionLastAlertDate = actualMachineBasicCompletionLastAlertDate;
    }

    public Boolean getActualMachineCustomizationCompletionFinal() {
        return actualMachineCustomizationCompletionFinal;
    }

    public void setActualMachineCustomizationCompletionFinal(Boolean actualMachineCustomizationCompletionFinal) {
        this.actualMachineCustomizationCompletionFinal = actualMachineCustomizationCompletionFinal;
    }

    public Boolean getActualMachineCustomizationCompletionAlertSent() {
        return actualMachineCustomizationCompletionAlertSent;
    }

    public void setActualMachineCustomizationCompletionAlertSent(Boolean actualMachineCustomizationCompletionAlertSent) {
        this.actualMachineCustomizationCompletionAlertSent = actualMachineCustomizationCompletionAlertSent;
    }

    public LocalDate getActualMachineCustomizationCompletionLastAlertDate() {
        return actualMachineCustomizationCompletionLastAlertDate;
    }

    public void setActualMachineCustomizationCompletionLastAlertDate(LocalDate actualMachineCustomizationCompletionLastAlertDate) {
        this.actualMachineCustomizationCompletionLastAlertDate = actualMachineCustomizationCompletionLastAlertDate;
    }

    // Assembly dates
    public Boolean getActualAssyStartDateFinal() {
        return actualAssyStartDateFinal;
    }

    public void setActualAssyStartDateFinal(Boolean actualAssyStartDateFinal) {
        this.actualAssyStartDateFinal = actualAssyStartDateFinal;
    }

    public Boolean getActualAssyStartDateAlertSent() {
        return actualAssyStartDateAlertSent;
    }

    public void setActualAssyStartDateAlertSent(Boolean actualAssyStartDateAlertSent) {
        this.actualAssyStartDateAlertSent = actualAssyStartDateAlertSent;
    }

    public LocalDate getActualAssyStartDateLastAlertDate() {
        return actualAssyStartDateLastAlertDate;
    }

    public void setActualAssyStartDateLastAlertDate(LocalDate actualAssyStartDateLastAlertDate) {
        this.actualAssyStartDateLastAlertDate = actualAssyStartDateLastAlertDate;
    }

    public Boolean getActualIfatStdCpDateFinal() {
        return actualIfatStdCpDateFinal;
    }

    public void setActualIfatStdCpDateFinal(Boolean actualIfatStdCpDateFinal) {
        this.actualIfatStdCpDateFinal = actualIfatStdCpDateFinal;
    }

    public Boolean getActualIfatStdCpDateAlertSent() {
        return actualIfatStdCpDateAlertSent;
    }

    public void setActualIfatStdCpDateAlertSent(Boolean actualIfatStdCpDateAlertSent) {
        this.actualIfatStdCpDateAlertSent = actualIfatStdCpDateAlertSent;
    }

    public LocalDate getActualIfatStdCpDateLastAlertDate() {
        return actualIfatStdCpDateLastAlertDate;
    }

    public void setActualIfatStdCpDateLastAlertDate(LocalDate actualIfatStdCpDateLastAlertDate) {
        this.actualIfatStdCpDateLastAlertDate = actualIfatStdCpDateLastAlertDate;
    }

    public Boolean getActualIfatCustomerCpDateFinal() {
        return actualIfatCustomerCpDateFinal;
    }

    public void setActualIfatCustomerCpDateFinal(Boolean actualIfatCustomerCpDateFinal) {
        this.actualIfatCustomerCpDateFinal = actualIfatCustomerCpDateFinal;
    }

    public Boolean getActualIfatCustomerCpDateAlertSent() {
        return actualIfatCustomerCpDateAlertSent;
    }

    public void setActualIfatCustomerCpDateAlertSent(Boolean actualIfatCustomerCpDateAlertSent) {
        this.actualIfatCustomerCpDateAlertSent = actualIfatCustomerCpDateAlertSent;
    }

    public LocalDate getActualIfatCustomerCpDateLastAlertDate() {
        return actualIfatCustomerCpDateLastAlertDate;
    }

    public void setActualIfatCustomerCpDateLastAlertDate(LocalDate actualIfatCustomerCpDateLastAlertDate) {
        this.actualIfatCustomerCpDateLastAlertDate = actualIfatCustomerCpDateLastAlertDate;
    }

    // Testing and compliance dates
    public Boolean getActualPmrSubmissionDateFinal() {
        return actualPmrSubmissionDateFinal;
    }

    public void setActualPmrSubmissionDateFinal(Boolean actualPmrSubmissionDateFinal) {
        this.actualPmrSubmissionDateFinal = actualPmrSubmissionDateFinal;
    }

    public Boolean getActualPmrSubmissionDateAlertSent() {
        return actualPmrSubmissionDateAlertSent;
    }

    public void setActualPmrSubmissionDateAlertSent(Boolean actualPmrSubmissionDateAlertSent) {
        this.actualPmrSubmissionDateAlertSent = actualPmrSubmissionDateAlertSent;
    }

    public LocalDate getActualPmrSubmissionDateLastAlertDate() {
        return actualPmrSubmissionDateLastAlertDate;
    }

    public void setActualPmrSubmissionDateLastAlertDate(LocalDate actualPmrSubmissionDateLastAlertDate) {
        this.actualPmrSubmissionDateLastAlertDate = actualPmrSubmissionDateLastAlertDate;
    }

    public Boolean getActualProductReceiptDateFinal() {
        return actualProductReceiptDateFinal;
    }

    public void setActualProductReceiptDateFinal(Boolean actualProductReceiptDateFinal) {
        this.actualProductReceiptDateFinal = actualProductReceiptDateFinal;
    }

    public Boolean getActualProductReceiptDateAlertSent() {
        return actualProductReceiptDateAlertSent;
    }

    public void setActualProductReceiptDateAlertSent(Boolean actualProductReceiptDateAlertSent) {
        this.actualProductReceiptDateAlertSent = actualProductReceiptDateAlertSent;
    }

    public LocalDate getActualProductReceiptDateLastAlertDate() {
        return actualProductReceiptDateLastAlertDate;
    }

    public void setActualProductReceiptDateLastAlertDate(LocalDate actualProductReceiptDateLastAlertDate) {
        this.actualProductReceiptDateLastAlertDate = actualProductReceiptDateLastAlertDate;
    }

    public Boolean getActualPackagingReceiptDateFinal() {
        return actualPackagingReceiptDateFinal;
    }

    public void setActualPackagingReceiptDateFinal(Boolean actualPackagingReceiptDateFinal) {
        this.actualPackagingReceiptDateFinal = actualPackagingReceiptDateFinal;
    }

    public Boolean getActualPackagingReceiptDateAlertSent() {
        return actualPackagingReceiptDateAlertSent;
    }

    public void setActualPackagingReceiptDateAlertSent(Boolean actualPackagingReceiptDateAlertSent) {
        this.actualPackagingReceiptDateAlertSent = actualPackagingReceiptDateAlertSent;
    }

    public LocalDate getActualPackagingReceiptDateLastAlertDate() {
        return actualPackagingReceiptDateLastAlertDate;
    }

    public void setActualPackagingReceiptDateLastAlertDate(LocalDate actualPackagingReceiptDateLastAlertDate) {
        this.actualPackagingReceiptDateLastAlertDate = actualPackagingReceiptDateLastAlertDate;
    }

    public Boolean getActualIfatProtocolReleaseDateFinal() {
        return actualIfatProtocolReleaseDateFinal;
    }

    public void setActualIfatProtocolReleaseDateFinal(Boolean actualIfatProtocolReleaseDateFinal) {
        this.actualIfatProtocolReleaseDateFinal = actualIfatProtocolReleaseDateFinal;
    }

    public Boolean getActualIfatProtocolReleaseDateAlertSent() {
        return actualIfatProtocolReleaseDateAlertSent;
    }

    public void setActualIfatProtocolReleaseDateAlertSent(Boolean actualIfatProtocolReleaseDateAlertSent) {
        this.actualIfatProtocolReleaseDateAlertSent = actualIfatProtocolReleaseDateAlertSent;
    }

    public LocalDate getActualIfatProtocolReleaseDateLastAlertDate() {
        return actualIfatProtocolReleaseDateLastAlertDate;
    }

    public void setActualIfatProtocolReleaseDateLastAlertDate(LocalDate actualIfatProtocolReleaseDateLastAlertDate) {
        this.actualIfatProtocolReleaseDateLastAlertDate = actualIfatProtocolReleaseDateLastAlertDate;
    }

    public Boolean getActualCfatDateFinal() {
        return actualCfatDateFinal;
    }

    public void setActualCfatDateFinal(Boolean actualCfatDateFinal) {
        this.actualCfatDateFinal = actualCfatDateFinal;
    }

    public Boolean getActualCfatDateAlertSent() {
        return actualCfatDateAlertSent;
    }

    public void setActualCfatDateAlertSent(Boolean actualCfatDateAlertSent) {
        this.actualCfatDateAlertSent = actualCfatDateAlertSent;
    }

    public LocalDate getActualCfatDateLastAlertDate() {
        return actualCfatDateLastAlertDate;
    }

    public void setActualCfatDateLastAlertDate(LocalDate actualCfatDateLastAlertDate) {
        this.actualCfatDateLastAlertDate = actualCfatDateLastAlertDate;
    }

    public Boolean getActualComplianceDateFinal() {
        return actualComplianceDateFinal;
    }

    public void setActualComplianceDateFinal(Boolean actualComplianceDateFinal) {
        this.actualComplianceDateFinal = actualComplianceDateFinal;
    }

    public Boolean getActualComplianceDateAlertSent() {
        return actualComplianceDateAlertSent;
    }

    public void setActualComplianceDateAlertSent(Boolean actualComplianceDateAlertSent) {
        this.actualComplianceDateAlertSent = actualComplianceDateAlertSent;
    }

    public LocalDate getActualComplianceDateLastAlertDate() {
        return actualComplianceDateLastAlertDate;
    }

    public void setActualComplianceDateLastAlertDate(LocalDate actualComplianceDateLastAlertDate) {
        this.actualComplianceDateLastAlertDate = actualComplianceDateLastAlertDate;
    }

    public Boolean getActualPackingClearanceRecdDateFinal() {
        return actualPackingClearanceRecdDateFinal;
    }

    public void setActualPackingClearanceRecdDateFinal(Boolean actualPackingClearanceRecdDateFinal) {
        this.actualPackingClearanceRecdDateFinal = actualPackingClearanceRecdDateFinal;
    }

    public Boolean getActualPackingClearanceRecdDateAlertSent() {
        return actualPackingClearanceRecdDateAlertSent;
    }

    public void setActualPackingClearanceRecdDateAlertSent(Boolean actualPackingClearanceRecdDateAlertSent) {
        this.actualPackingClearanceRecdDateAlertSent = actualPackingClearanceRecdDateAlertSent;
    }

    public LocalDate getActualPackingClearanceRecdDateLastAlertDate() {
        return actualPackingClearanceRecdDateLastAlertDate;
    }

    public void setActualPackingClearanceRecdDateLastAlertDate(LocalDate actualPackingClearanceRecdDateLastAlertDate) {
        this.actualPackingClearanceRecdDateLastAlertDate = actualPackingClearanceRecdDateLastAlertDate;
    }

    // Material handover dates
    public Boolean getActualBasicMaterialHandedOverAssyDateFinal() {
        return actualBasicMaterialHandedOverAssyDateFinal;
    }

    public void setActualBasicMaterialHandedOverAssyDateFinal(Boolean actualBasicMaterialHandedOverAssyDateFinal) {
        this.actualBasicMaterialHandedOverAssyDateFinal = actualBasicMaterialHandedOverAssyDateFinal;
    }

    public Boolean getActualBasicMaterialHandedOverAssyDateAlertSent() {
        return actualBasicMaterialHandedOverAssyDateAlertSent;
    }

    public void setActualBasicMaterialHandedOverAssyDateAlertSent(Boolean actualBasicMaterialHandedOverAssyDateAlertSent) {
        this.actualBasicMaterialHandedOverAssyDateAlertSent = actualBasicMaterialHandedOverAssyDateAlertSent;
    }

    public LocalDate getActualBasicMaterialHandedOverAssyDateLastAlertDate() {
        return actualBasicMaterialHandedOverAssyDateLastAlertDate;
    }

    public void setActualBasicMaterialHandedOverAssyDateLastAlertDate(LocalDate actualBasicMaterialHandedOverAssyDateLastAlertDate) {
        this.actualBasicMaterialHandedOverAssyDateLastAlertDate = actualBasicMaterialHandedOverAssyDateLastAlertDate;
    }

    public Boolean getActualOptionalMaterialHandedOverAssyDateFinal() {
        return actualOptionalMaterialHandedOverAssyDateFinal;
    }

    public void setActualOptionalMaterialHandedOverAssyDateFinal(Boolean actualOptionalMaterialHandedOverAssyDateFinal) {
        this.actualOptionalMaterialHandedOverAssyDateFinal = actualOptionalMaterialHandedOverAssyDateFinal;
    }

    public Boolean getActualOptionalMaterialHandedOverAssyDateAlertSent() {
        return actualOptionalMaterialHandedOverAssyDateAlertSent;
    }

    public void setActualOptionalMaterialHandedOverAssyDateAlertSent(Boolean actualOptionalMaterialHandedOverAssyDateAlertSent) {
        this.actualOptionalMaterialHandedOverAssyDateAlertSent = actualOptionalMaterialHandedOverAssyDateAlertSent;
    }

    public LocalDate getActualOptionalMaterialHandedOverAssyDateLastAlertDate() {
        return actualOptionalMaterialHandedOverAssyDateLastAlertDate;
    }

    public void setActualOptionalMaterialHandedOverAssyDateLastAlertDate(LocalDate actualOptionalMaterialHandedOverAssyDateLastAlertDate) {
        this.actualOptionalMaterialHandedOverAssyDateLastAlertDate = actualOptionalMaterialHandedOverAssyDateLastAlertDate;
    }

    public Boolean getActualBasicMaterialReceiptDateFinal() {
        return actualBasicMaterialReceiptDateFinal;
    }

    public void setActualBasicMaterialReceiptDateFinal(Boolean actualBasicMaterialReceiptDateFinal) {
        this.actualBasicMaterialReceiptDateFinal = actualBasicMaterialReceiptDateFinal;
    }

    public Boolean getActualBasicMaterialReceiptDateAlertSent() {
        return actualBasicMaterialReceiptDateAlertSent;
    }

    public void setActualBasicMaterialReceiptDateAlertSent(Boolean actualBasicMaterialReceiptDateAlertSent) {
        this.actualBasicMaterialReceiptDateAlertSent = actualBasicMaterialReceiptDateAlertSent;
    }

    public LocalDate getActualBasicMaterialReceiptDateLastAlertDate() {
        return actualBasicMaterialReceiptDateLastAlertDate;
    }

    public void setActualBasicMaterialReceiptDateLastAlertDate(LocalDate actualBasicMaterialReceiptDateLastAlertDate) {
        this.actualBasicMaterialReceiptDateLastAlertDate = actualBasicMaterialReceiptDateLastAlertDate;
    }

    public Boolean getActualCustomizationMaterialReceiptDateFinal() {
        return actualCustomizationMaterialReceiptDateFinal;
    }

    public void setActualCustomizationMaterialReceiptDateFinal(Boolean actualCustomizationMaterialReceiptDateFinal) {
        this.actualCustomizationMaterialReceiptDateFinal = actualCustomizationMaterialReceiptDateFinal;
    }

    public Boolean getActualCustomizationMaterialReceiptDateAlertSent() {
        return actualCustomizationMaterialReceiptDateAlertSent;
    }

    public void setActualCustomizationMaterialReceiptDateAlertSent(Boolean actualCustomizationMaterialReceiptDateAlertSent) {
        this.actualCustomizationMaterialReceiptDateAlertSent = actualCustomizationMaterialReceiptDateAlertSent;
    }

    public LocalDate getActualCustomizationMaterialReceiptDateLastAlertDate() {
        return actualCustomizationMaterialReceiptDateLastAlertDate;
    }

    public void setActualCustomizationMaterialReceiptDateLastAlertDate(LocalDate actualCustomizationMaterialReceiptDateLastAlertDate) {
        this.actualCustomizationMaterialReceiptDateLastAlertDate = actualCustomizationMaterialReceiptDateLastAlertDate;
    }

    // Dispatch dates
    public Boolean getActualHandedToDispatchDateFinal() {
        return actualHandedToDispatchDateFinal;
    }

    public void setActualHandedToDispatchDateFinal(Boolean actualHandedToDispatchDateFinal) {
        this.actualHandedToDispatchDateFinal = actualHandedToDispatchDateFinal;
    }

    public Boolean getActualHandedToDispatchDateAlertSent() {
        return actualHandedToDispatchDateAlertSent;
    }

    public void setActualHandedToDispatchDateAlertSent(Boolean actualHandedToDispatchDateAlertSent) {
        this.actualHandedToDispatchDateAlertSent = actualHandedToDispatchDateAlertSent;
    }

    public LocalDate getActualHandedToDispatchDateLastAlertDate() {
        return actualHandedToDispatchDateLastAlertDate;
    }

    public void setActualHandedToDispatchDateLastAlertDate(LocalDate actualHandedToDispatchDateLastAlertDate) {
        this.actualHandedToDispatchDateLastAlertDate = actualHandedToDispatchDateLastAlertDate;
    }

    public Boolean getActualDispatchDateFinal() {
        return actualDispatchDateFinal;
    }

    public void setActualDispatchDateFinal(Boolean actualDispatchDateFinal) {
        this.actualDispatchDateFinal = actualDispatchDateFinal;
    }

    public Boolean getActualDispatchDateAlertSent() {
        return actualDispatchDateAlertSent;
    }

    public void setActualDispatchDateAlertSent(Boolean actualDispatchDateAlertSent) {
        this.actualDispatchDateAlertSent = actualDispatchDateAlertSent;
    }

    public LocalDate getActualDispatchDateLastAlertDate() {
        return actualDispatchDateLastAlertDate;
    }

    public void setActualDispatchDateLastAlertDate(LocalDate actualDispatchDateLastAlertDate) {
        this.actualDispatchDateLastAlertDate = actualDispatchDateLastAlertDate;
    }

    // Additional PPC dates
    public Boolean getActualPpcBasicDateFinal() {
        return actualPpcBasicDateFinal;
    }

    public void setActualPpcBasicDateFinal(Boolean actualPpcBasicDateFinal) {
        this.actualPpcBasicDateFinal = actualPpcBasicDateFinal;
    }

    public Boolean getActualPpcBasicDateAlertSent() {
        return actualPpcBasicDateAlertSent;
    }

    public void setActualPpcBasicDateAlertSent(Boolean actualPpcBasicDateAlertSent) {
        this.actualPpcBasicDateAlertSent = actualPpcBasicDateAlertSent;
    }

    public LocalDate getActualPpcBasicDateLastAlertDate() {
        return actualPpcBasicDateLastAlertDate;
    }

    public void setActualPpcBasicDateLastAlertDate(LocalDate actualPpcBasicDateLastAlertDate) {
        this.actualPpcBasicDateLastAlertDate = actualPpcBasicDateLastAlertDate;
    }

    public Boolean getActualPpcOptionalDateFinal() {
        return actualPpcOptionalDateFinal;
    }

    public void setActualPpcOptionalDateFinal(Boolean actualPpcOptionalDateFinal) {
        this.actualPpcOptionalDateFinal = actualPpcOptionalDateFinal;
    }

    public Boolean getActualPpcOptionalDateAlertSent() {
        return actualPpcOptionalDateAlertSent;
    }

    public void setActualPpcOptionalDateAlertSent(Boolean actualPpcOptionalDateAlertSent) {
        this.actualPpcOptionalDateAlertSent = actualPpcOptionalDateAlertSent;
    }

    public LocalDate getActualPpcOptionalDateLastAlertDate() {
        return actualPpcOptionalDateLastAlertDate;
    }

    public void setActualPpcOptionalDateLastAlertDate(LocalDate actualPpcOptionalDateLastAlertDate) {
        this.actualPpcOptionalDateLastAlertDate = actualPpcOptionalDateLastAlertDate;
    }

    public Boolean getActualLoaBasicFinal() {
        return actualLoaBasicFinal;
    }

    public void setActualLoaBasicFinal(Boolean actualLoaBasicFinal) {
        this.actualLoaBasicFinal = actualLoaBasicFinal;
    }

    public Boolean getActualLoaBasicAlertSent() {
        return actualLoaBasicAlertSent;
    }

    public void setActualLoaBasicAlertSent(Boolean actualLoaBasicAlertSent) {
        this.actualLoaBasicAlertSent = actualLoaBasicAlertSent;
    }

    public LocalDate getActualLoaBasicLastAlertDate() {
        return actualLoaBasicLastAlertDate;
    }

    public void setActualLoaBasicLastAlertDate(LocalDate actualLoaBasicLastAlertDate) {
        this.actualLoaBasicLastAlertDate = actualLoaBasicLastAlertDate;
    }

    public Boolean getActualLoaOptionalFinal() {
        return actualLoaOptionalFinal;
    }

    public void setActualLoaOptionalFinal(Boolean actualLoaOptionalFinal) {
        this.actualLoaOptionalFinal = actualLoaOptionalFinal;
    }

    public Boolean getActualLoaOptionalAlertSent() {
        return actualLoaOptionalAlertSent;
    }

    public void setActualLoaOptionalAlertSent(Boolean actualLoaOptionalAlertSent) {
        this.actualLoaOptionalAlertSent = actualLoaOptionalAlertSent;
    }

    public LocalDate getActualLoaOptionalLastAlertDate() {
        return actualLoaOptionalLastAlertDate;
    }

    public void setActualLoaOptionalLastAlertDate(LocalDate actualLoaOptionalLastAlertDate) {
        this.actualLoaOptionalLastAlertDate = actualLoaOptionalLastAlertDate;
    }

    // Additional dates
    public Boolean getAdditionalNewAssemblyAttachedAfterCfatDateFinal() {
        return additionalNewAssemblyAttachedAfterCfatDateFinal;
    }

    public void setAdditionalNewAssemblyAttachedAfterCfatDateFinal(Boolean additionalNewAssemblyAttachedAfterCfatDateFinal) {
        this.additionalNewAssemblyAttachedAfterCfatDateFinal = additionalNewAssemblyAttachedAfterCfatDateFinal;
    }

    public Boolean getAdditionalNewAssemblyAttachedAfterCfatDateAlertSent() {
        return additionalNewAssemblyAttachedAfterCfatDateAlertSent;
    }

    public void setAdditionalNewAssemblyAttachedAfterCfatDateAlertSent(Boolean additionalNewAssemblyAttachedAfterCfatDateAlertSent) {
        this.additionalNewAssemblyAttachedAfterCfatDateAlertSent = additionalNewAssemblyAttachedAfterCfatDateAlertSent;
    }

    public LocalDate getAdditionalNewAssemblyAttachedAfterCfatDateLastAlertDate() {
        return additionalNewAssemblyAttachedAfterCfatDateLastAlertDate;
    }

    public void setAdditionalNewAssemblyAttachedAfterCfatDateLastAlertDate(LocalDate additionalNewAssemblyAttachedAfterCfatDateLastAlertDate) {
        this.additionalNewAssemblyAttachedAfterCfatDateLastAlertDate = additionalNewAssemblyAttachedAfterCfatDateLastAlertDate;
    }

    public Boolean getMcnShortfallClosureDateFinal() {
        return mcnShortfallClosureDateFinal;
    }

    public void setMcnShortfallClosureDateFinal(Boolean mcnShortfallClosureDateFinal) {
        this.mcnShortfallClosureDateFinal = mcnShortfallClosureDateFinal;
    }

    public Boolean getMcnShortfallClosureDateAlertSent() {
        return mcnShortfallClosureDateAlertSent;
    }

    public void setMcnShortfallClosureDateAlertSent(Boolean mcnShortfallClosureDateAlertSent) {
        this.mcnShortfallClosureDateAlertSent = mcnShortfallClosureDateAlertSent;
    }

    public LocalDate getMcnShortfallClosureDateLastAlertDate() {
        return mcnShortfallClosureDateLastAlertDate;
    }

    public void setMcnShortfallClosureDateLastAlertDate(LocalDate mcnShortfallClosureDateLastAlertDate) {
        this.mcnShortfallClosureDateLastAlertDate = mcnShortfallClosureDateLastAlertDate;
    }

    public Boolean getActualPpcNewMaterialDateAfterCfatFinal() {
        return actualPpcNewMaterialDateAfterCfatFinal;
    }

    public void setActualPpcNewMaterialDateAfterCfatFinal(Boolean actualPpcNewMaterialDateAfterCfatFinal) {
        this.actualPpcNewMaterialDateAfterCfatFinal = actualPpcNewMaterialDateAfterCfatFinal;
    }

    public Boolean getActualPpcNewMaterialDateAfterCfatAlertSent() {
        return actualPpcNewMaterialDateAfterCfatAlertSent;
    }

    public void setActualPpcNewMaterialDateAfterCfatAlertSent(Boolean actualPpcNewMaterialDateAfterCfatAlertSent) {
        this.actualPpcNewMaterialDateAfterCfatAlertSent = actualPpcNewMaterialDateAfterCfatAlertSent;
    }

    public LocalDate getActualPpcNewMaterialDateAfterCfatLastAlertDate() {
        return actualPpcNewMaterialDateAfterCfatLastAlertDate;
    }

    public void setActualPpcNewMaterialDateAfterCfatLastAlertDate(LocalDate actualPpcNewMaterialDateAfterCfatLastAlertDate) {
        this.actualPpcNewMaterialDateAfterCfatLastAlertDate = actualPpcNewMaterialDateAfterCfatLastAlertDate;
    }
}
