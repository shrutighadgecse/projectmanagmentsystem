package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ccn;
    private String machineName;
    private String model;
    private String customerName;
    private String recordNo;
    private String poRefNo;
    private String poDate;
    private String poDeliveryDate;
    private Double poValueLacs;
    private String salesPerson;
    private String orderType;
    private String address;
    private String venue;
    private String komNo;
    @Lob
    private String membersPresent;
    private Integer machineCpQty;
    private Integer machineOptionalCpQty;
    private String poReceiptDate;
    private String oaGenerationDate;
    private String komDate;
    private String advancePaymentReceiptDate;
    private String balancePaymentReceiptDate;
    private String cpLayoutApprovals;
    private String machineLayoutApprovals;
    private String roomLayoutApprovals;
  
    private String dqRelease;
    private String ppcBasicDate;
    private String ppcOptionalDate;
    private String cpDesignReleaseDate;
    private String machineManufacturingBasic;
    private String machineManufacturingCustomisation;
    private String cpManufacturingDate;
    private String pmrGenrationSubmissionDate;
    private String productsReceiptDate;
    private String packagingReceiptDate;
    private String ifatProtocolRelease;
    private String ifatStdCp;
    private String ifatCustomerCp;
    private String cfat;
    private String compliance;
    private String dispatchClearance;
    private String plannedDispatchDate;
    private String machineCategory;
    @Lob
    private String machineBasics;
    @Lob
    private String machineFeatures;
    @Lob
    private String additionalOptions;
    @Lob
    private String projectKickoffDetails;
    private String ccnStatus;
    @Lob
    private String workmen;
    private String stdTaskOfMachine;
    private Integer taskCompleted;
    private String liveStatus;
    private String lastUpdatedData;
    private String selfPullingMaterial;
    private String cpInterchanged;
    @Lob
    private String shortDetailMech;
    @Lob
    private String shortDetailElectrical;
    @Lob
    private String shortDetailCp;
    @Lob
    private String remarks;
    @Lob
    private String delayAnalysis;
    private String imagePath;
    private String actualMaterialReceiptDate;
    private String actualDynamicManufacturingPlan;
    private String actualDynamicDispatchPlan;
    private String actualAssyStartDate;
    private String actualIfatDate;
    private String actualCpDate;
    private String actualCfatDate;
    private String complianceClosureDate;
    @Lob
    private String mechNonIssuance;
    private Integer electNonIssuance;
    private String handedToDispatchDept;
    @Lob
    private String weeklyReview;
    private String rescheduledIfatDate;
    private String rescheduledCfatDate;
    private String dispatchedMonth;
    private String invoiceDate;
    private String timestamp;
    private Double machinePercentage;
    private Double projectPercentage;
    // NEW: store CP Trial dropdown (Yes/No)
    private String cpTrialOnMachine;
    // NEW: remark for CP Trial; stored as LOB to allow long text
    @Lob
    private String cpTrialOnMachineRemark;
      @Lob
         @Column(name = "machine_family")
         @JsonProperty("machineFamily")
         private String machineFamily;
            @Lob
    @Column(name = "unit", columnDefinition = "TEXT")
    private String unit;
      private String loaBasic;
    private String loaOptional;
    public String getUnit() {
        return unit;
    }
      public void setUnit(String unit) {
          this.unit = unit;
      }
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
    public String getMachineName() {
        return machineName;
    }
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getRecordNo() {
        return recordNo;
    }
    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }
    public String getPoRefNo() {
        return poRefNo;
    }
    public void setPoRefNo(String poRefNo) {
        this.poRefNo = poRefNo;
    }
    public String getPoDate() {
        return poDate;
    }
    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }
    public String getPoDeliveryDate() {
        return poDeliveryDate;
    }
    public void setPoDeliveryDate(String poDeliveryDate) {
        this.poDeliveryDate = poDeliveryDate;
    }
    public Double getPoValueLacs() {
        return poValueLacs;
    }
    public void setPoValueLacs(Double poValueLacs) {
        this.poValueLacs = poValueLacs;
    }
    public String getSalesPerson() {
        return salesPerson;
    }
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getKomNo() {
        return komNo;
    }
    public void setKomNo(String komNo) {
        this.komNo = komNo;
    }
    public String getMembersPresent() {
        return membersPresent;
    }
    public void setMembersPresent(String membersPresent) {
        this.membersPresent = membersPresent;
    }
    public Integer getMachineCpQty() {
        return machineCpQty;
    }
    public void setMachineCpQty(Integer machineCpQty) {
        this.machineCpQty = machineCpQty;
    }
    public Integer getMachineOptionalCpQty() {
        return machineOptionalCpQty;
    }
    public void setMachineOptionalCpQty(Integer machineOptionalCpQty) {
        this.machineOptionalCpQty = machineOptionalCpQty;
    }
    public String getPoReceiptDate() {
        return poReceiptDate;
    }
    public void setPoReceiptDate(String poReceiptDate) {
        this.poReceiptDate = poReceiptDate;
    }
    public String getOaGenerationDate() {
        return oaGenerationDate;
    }
    public void setOaGenerationDate(String oaGenerationDate) {
        this.oaGenerationDate = oaGenerationDate;
    }
    public String getKomDate() {
        return komDate;
    }
    public void setKomDate(String komDate) {
        this.komDate = komDate;
    }
    public String getAdvancePaymentReceiptDate() {
        return advancePaymentReceiptDate;
    }
    public void setAdvancePaymentReceiptDate(String advancePaymentReceiptDate) {
        this.advancePaymentReceiptDate = advancePaymentReceiptDate;
    }
    public String getBalancePaymentReceiptDate() {
        return balancePaymentReceiptDate;
    }
    public void setBalancePaymentReceiptDate(String balancePaymentReceiptDate) {
        this.balancePaymentReceiptDate = balancePaymentReceiptDate;
    }
    public String getCpLayoutApprovals() {
        return cpLayoutApprovals;
    }
    public void setCpLayoutApprovals(String cpLayoutApprovals) {
        this.cpLayoutApprovals = cpLayoutApprovals;
    }
    public String getMachineLayoutApprovals() {
        return machineLayoutApprovals;
    }
    public void setMachineLayoutApprovals(String machineLayoutApprovals) {
        this.machineLayoutApprovals = machineLayoutApprovals;
    }
    public String getRoomLayoutApprovals() {
        return roomLayoutApprovals;
    }
    public void setRoomLayoutApprovals(String roomLayoutApprovals) {
        this.roomLayoutApprovals = roomLayoutApprovals;
    }
        public String getLoaBasic() {
        return loaBasic;
    }
    public void setLoaBasic(String loaBasic) {
        this.loaBasic = loaBasic;
    }
        public String getLoaOptional() {
        return loaOptional;
    }
    public void setLoaOptional(String loaOptional) {
        this.loaOptional = loaOptional;
    }
    public String getDqRelease() {
        return dqRelease;
    }
    public void setDqRelease(String dqRelease) {
        this.dqRelease = dqRelease;
    }
    public String getPpcBasicDate() {
        return ppcBasicDate;
    }
    public void setPpcBasicDate(String ppcBasicDate) {
        this.ppcBasicDate = ppcBasicDate;
    }
    public String getPpcOptionalDate() {
        return ppcOptionalDate;
    }
    public void setPpcOptionalDate(String ppcOptionalDate) {
        this.ppcOptionalDate = ppcOptionalDate;
    }
    public String getCpDesignReleaseDate() {
        return cpDesignReleaseDate;
    }
    public void setCpDesignReleaseDate(String cpDesignReleaseDate) {
        this.cpDesignReleaseDate = cpDesignReleaseDate;
    }
    public String getMachineManufacturingBasic() {
        return machineManufacturingBasic;
    }
    public void setMachineManufacturingBasic(String machineManufacturingBasic) {
        this.machineManufacturingBasic = machineManufacturingBasic;
    }
    public String getMachineManufacturingCustomisation() {
        return machineManufacturingCustomisation;
    }
    public void setMachineManufacturingCustomisation(String machineManufacturingCustomisation) {
        this.machineManufacturingCustomisation = machineManufacturingCustomisation;
    }
    public String getCpManufacturingDate() {
        return cpManufacturingDate;
    }
    public void setCpManufacturingDate(String cpManufacturingDate) {
        this.cpManufacturingDate = cpManufacturingDate;
    }
    public String getPmrGenrationSubmissionDate() {
        return pmrGenrationSubmissionDate;
    }
    public void setPmrGenrationSubmissionDate(String pmrGenrationSubmissionDate) {
        this.pmrGenrationSubmissionDate = pmrGenrationSubmissionDate;
    }
    public String getProductsReceiptDate() {
        return productsReceiptDate;
    }
    public void setProductsReceiptDate(String productsReceiptDate) {
        this.productsReceiptDate = productsReceiptDate;
    }
    public String getPackagingReceiptDate() {
        return packagingReceiptDate;
    }
    public void setPackagingReceiptDate(String packagingReceiptDate) {
        this.packagingReceiptDate = packagingReceiptDate;
    }
    public String getIfatProtocolRelease() {
        return ifatProtocolRelease;
    }
    public void setIfatProtocolRelease(String ifatProtocolRelease) {
        this.ifatProtocolRelease = ifatProtocolRelease;
    }
    public String getIfatStdCp() {
        return ifatStdCp;
    }
    public void setIfatStdCp(String ifatStdCp) {
        this.ifatStdCp = ifatStdCp;
    }
    public String getIfatCustomerCp() {
        return ifatCustomerCp;
    }
    public void setIfatCustomerCp(String ifatCustomerCp) {
        this.ifatCustomerCp = ifatCustomerCp;
    }
    public String getCfat() {
        return cfat;
    }
    public void setCfat(String cfat) {
        this.cfat = cfat;
    }
    public String getCompliance() {
        return compliance;
    }
    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
    public String getDispatchClearance() {
        return dispatchClearance;
    }
    public void setDispatchClearance(String dispatchClearance) {
        this.dispatchClearance = dispatchClearance;
    }
    public String getPlannedDispatchDate() {
        return plannedDispatchDate;
    }
    public void setPlannedDispatchDate(String plannedDispatchDate) {
        this.plannedDispatchDate = plannedDispatchDate;
    }
    public String getMachineCategory() {
        return machineCategory;
    }
    public void setMachineCategory(String machineCategory) {
        this.machineCategory = machineCategory;
    }
    public String getMachineBasics() {
        return machineBasics;
    }
    public void setMachineBasics(String machineBasics) {
        this.machineBasics = machineBasics;
    }
    public String getMachineFeatures() {
        return machineFeatures;
    }
    public void setMachineFeatures(String machineFeatures) {
        this.machineFeatures = machineFeatures;
    }
    public String getAdditionalOptions() {
        return additionalOptions;
    }
    public void setAdditionalOptions(String additionalOptions) {
        this.additionalOptions = additionalOptions;
    }
    public String getProjectKickoffDetails() {
        return projectKickoffDetails;
    }
    public void setProjectKickoffDetails(String projectKickoffDetails) {
        this.projectKickoffDetails = projectKickoffDetails;
    }
    public String getCcnStatus() {
        return ccnStatus;
    }
    public void setCcnStatus(String ccnStatus) {
        this.ccnStatus = ccnStatus;
    }
    public String getWorkmen() {
        return workmen;
    }
    public void setWorkmen(String workmen) {
        this.workmen = workmen;
    }
    public String getStdTaskOfMachine() {
        return stdTaskOfMachine;
    }
    public void setStdTaskOfMachine(String stdTaskOfMachine) {
        this.stdTaskOfMachine = stdTaskOfMachine;
    }
    public Integer getTaskCompleted() {
        return taskCompleted;
    }
    public void setTaskCompleted(Integer taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
    public String getLiveStatus() {
        return liveStatus;
    }
    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }
    public String getLastUpdatedData() {
        return lastUpdatedData;
    }
    public void setLastUpdatedData(String lastUpdatedData) {
        this.lastUpdatedData = lastUpdatedData;
    }
   
    public String getSelfPullingMaterial() {
        return selfPullingMaterial;
    }
    public void setSelfPullingMaterial(String selfPullingMaterial) {
        this.selfPullingMaterial = selfPullingMaterial;
    }
    public String getCpInterchanged() {
        return cpInterchanged;
    }
    public void setCpInterchanged(String cpInterchanged) {
        this.cpInterchanged = cpInterchanged;
    }
    public String getShortDetailMech() {
        return shortDetailMech;
    }
    public void setShortDetailMech(String shortDetailMech) {
        this.shortDetailMech = shortDetailMech;
    }
    public String getShortDetailElectrical() {
        return shortDetailElectrical;
    }
    public void setShortDetailElectrical(String shortDetailElectrical) {
        this.shortDetailElectrical = shortDetailElectrical;
    }
    public String getShortDetailCp() {
        return shortDetailCp;
    }
    public void setShortDetailCp(String shortDetailCp) {
        this.shortDetailCp = shortDetailCp;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getDelayAnalysis() {
        return delayAnalysis;
    }
    public void setDelayAnalysis(String delayAnalysis) {
        this.delayAnalysis = delayAnalysis;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getActualMaterialReceiptDate() {
        return actualMaterialReceiptDate;
    }
    public void setActualMaterialReceiptDate(String actualMaterialReceiptDate) {
        this.actualMaterialReceiptDate = actualMaterialReceiptDate;
    }
    public String getActualDynamicManufacturingPlan() {
        return actualDynamicManufacturingPlan;
    }
    public void setActualDynamicManufacturingPlan(String actualDynamicManufacturingPlan) {
        this.actualDynamicManufacturingPlan = actualDynamicManufacturingPlan;
    }
    public String getActualDynamicDispatchPlan() {
        return actualDynamicDispatchPlan;
    }
    public void setActualDynamicDispatchPlan(String actualDynamicDispatchPlan) {
        this.actualDynamicDispatchPlan = actualDynamicDispatchPlan;
    }
    public String getActualAssyStartDate() {
        return actualAssyStartDate;
    }
    public void setActualAssyStartDate(String actualAssyStartDate) {
        this.actualAssyStartDate = actualAssyStartDate;
    }
    public String getActualIfatDate() {
        return actualIfatDate;
    }
    public void setActualIfatDate(String actualIfatDate) {
        this.actualIfatDate = actualIfatDate;
    }
    public String getActualCpDate() {
        return actualCpDate;
    }
    public void setActualCpDate(String actualCpDate) {
        this.actualCpDate = actualCpDate;
    }
    public String getActualCfatDate() {
        return actualCfatDate;
    }
    public void setActualCfatDate(String actualCfatDate) {
        this.actualCfatDate = actualCfatDate;
    }
    public String getComplianceClosureDate() {
        return complianceClosureDate;
    }
    public void setComplianceClosureDate(String complianceClosureDate) {
        this.complianceClosureDate = complianceClosureDate;
    }
    public String getMechNonIssuance() {
        return mechNonIssuance;
    }
    public void setMechNonIssuance(String mechNonIssuance) {
        this.mechNonIssuance = mechNonIssuance;
    }
    public Integer getElectNonIssuance() {
        return electNonIssuance;
    }
    public void setElectNonIssuance(Integer electNonIssuance) {
        this.electNonIssuance = electNonIssuance;
    }
    public String getHandedToDispatchDept() {
        return handedToDispatchDept;
    }
    public void setHandedToDispatchDept(String handedToDispatchDept) {
        this.handedToDispatchDept = handedToDispatchDept;
    }
    public String getWeeklyReview() {
        return weeklyReview;
    }
    public void setWeeklyReview(String weeklyReview) {
        this.weeklyReview = weeklyReview;
    }
    public String getRescheduledIfatDate() {
        return rescheduledIfatDate;
    }
    public void setRescheduledIfatDate(String rescheduledIfatDate) {
        this.rescheduledIfatDate = rescheduledIfatDate;
    }
    public String getRescheduledCfatDate() {
        return rescheduledCfatDate;
    }
    public void setRescheduledCfatDate(String rescheduledCfatDate) {
        this.rescheduledCfatDate = rescheduledCfatDate;
    }
    public String getDispatchedMonth() {
        return dispatchedMonth;
    }
    public void setDispatchedMonth(String dispatchedMonth) {
        this.dispatchedMonth = dispatchedMonth;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public Double getMachinePercentage() {
        return machinePercentage;
    }
    public void setMachinePercentage(Double machinePercentage) {
        this.machinePercentage = machinePercentage;
    }
    public Double getProjectPercentage() {
        return projectPercentage;
    }
    public void setProjectPercentage(Double projectPercentage) {
        this.projectPercentage = projectPercentage;
    }

    // NEW getters/setters
    public String getCpTrialOnMachine() {
        return cpTrialOnMachine;
    }
    public void setCpTrialOnMachine(String cpTrialOnMachine) {
        this.cpTrialOnMachine = cpTrialOnMachine;
    }
    public String getCpTrialOnMachineRemark() {
        return cpTrialOnMachineRemark;
    }
    public void setCpTrialOnMachineRemark(String cpTrialOnMachineRemark) {
        this.cpTrialOnMachineRemark = cpTrialOnMachineRemark;
    }

     public String getMachineFamily() {
        return machineFamily;
    }
    public void setMachineFamily(String machineFamily) {
        this.machineFamily = machineFamily;
    }
}
