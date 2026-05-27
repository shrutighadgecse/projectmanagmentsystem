package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class KickoffForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String komNo;
    private LocalDateTime timestamp;
    private String venue;
    private String ccn;
    private String customerName;
    private String machineName;
    private String machineModel;
    private String machineCategory;
    private String orderType;
    // Inter Unit Change field
    private String interUnitChange;
    private String ccnStatus;
    private String address;
 

    private String poDate;

    private String poDeliveryDate;
    @Column(name = "members_present", columnDefinition = "LONGTEXT")
    private String membersPresent;
    // New fields for Machine CP Qty and Machine Optional CP Qty
    private Integer machineCpQty;
    private Integer machineOptionalCpQty;
    // Store as JSON string
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String machineBasics;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String machineFeatures;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String additionalOptions;
    @Lob
    private String projectKickoffDetails;
    // Workflow fields
    private String po_receipt_date;
    private String oa_generation_date;
    private String kom_date;
    private String advance_payment_receipt_date;
    private String balance_payment_receipt_date;
    private String cp_layout_approvals;
    private String machine_layout_approvals;
    private String room_layout_approvals;
    private String dispatch_clearance;
    private String loa_optional;
    private String loa_basic;
    private String dq_release;
    private String ppc_basic_date;
    private String ppc_optional_date;
    private String cp_design_release_date;
    private String cp_manufacturing_date;
    private String machine_manufacturing_basic;
    private String machine_manufacturing_customisation;
    private String assy_start_date;
    private String ifat_std_cp;
    private String ifat_customer_cp;
    private String pmr_genration_submission_date;
    private String products_receipt_date;
    private String packaging_receipt_date;
    private String ifat_protocol_release;
    private String cfat;
    private String compliance;
    private String planned_dispatch_date;
    private String unit;
    @Column(name = "machine_family")
    private String machineFamily;
    private String  handed_dispatch_date;
    private String  cp_oa_genration_date;
    
    private String optional_matl_handed_over_Assy_date;
    private String  basic_matl_handed_over_Assy_date;
    private String optional_cp_manufacturing_date;
    private String commercial_clearance;
    private String optional_cp_layout_approval_date;
    
    public String getCommercial_clearance() {
        return commercial_clearance;
    }

    public void setCommercial_clearance(String commercial_clearance) {
        this.commercial_clearance = commercial_clearance;
    }

    public String getOptional_cp_layout_approval_date() {
        return optional_cp_layout_approval_date;
    }

    public void setOptional_cp_layout_approval_date(String optional_cp_layout_approval_date) {
        this.optional_cp_layout_approval_date = optional_cp_layout_approval_date;
    }

    public String getPackaging_clearance_rec_date() {
        return packaging_clearance_rec_date;
    }

    public void setPackaging_clearance_rec_date(String packaging_clearance_rec_date) {
        this.packaging_clearance_rec_date = packaging_clearance_rec_date;
    }

    private String packaging_clearance_rec_date;


    public String getOptional_cp_manufacturing_date() {
        return optional_cp_manufacturing_date;
    }

    public void setOptional_cp_manufacturing_date(String optional_cp_manufacturing_date) {
        this.optional_cp_manufacturing_date = optional_cp_manufacturing_date;
    }

    public String getBasic_matl_handed_over_Assy_date() {
        return basic_matl_handed_over_Assy_date;
    }

    public void setBasic_matl_handed_over_Assy_date(String basic_matl_handed_over_Assy_date) {
        this.basic_matl_handed_over_Assy_date = basic_matl_handed_over_Assy_date;
    }

    public String getOptional_matl_handed_over_Assy_date() {
        return optional_matl_handed_over_Assy_date;
    }

    public void setOptional_matl_handed_over_Assy_date(String optional_matl_handed_over_Assy_date) {
        this.optional_matl_handed_over_Assy_date = optional_matl_handed_over_Assy_date;
    }

    

    public String getCp_oa_genration_date() {
        return cp_oa_genration_date;
    }

    public void setCp_oa_genration_date(String cp_oa_genration_date) {
        this.cp_oa_genration_date = cp_oa_genration_date;
    }

    public String getHanded_dispatch_date() {
    return handed_dispatch_date;
}

public void setHanded_dispatch_date(String handed_dispatch_date) {
    this.handed_dispatch_date = handed_dispatch_date;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKomNo() {
        return komNo;
    }

    public void setKomNo(String komNo) {
        this.komNo = komNo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }

    public String getMachineCategory() {
        return machineCategory;
    }
    public void setMachineCategory(String machineCategory) {
        this.machineCategory = machineCategory;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getInterUnitChange() {
        return interUnitChange;
    }
    public void setInterUnitChange(String interUnitChange) {
        this.interUnitChange = interUnitChange;
    }
    public String getCcnStatus() {
        return ccnStatus;
    }
    public void setCcnStatus(String ccnStatus) {
        this.ccnStatus = ccnStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPo_receipt_date() {
        return po_receipt_date;
    }

    public void setPo_receipt_date(String po_receipt_date) {
        this.po_receipt_date = po_receipt_date;
    }

    public String getOa_generation_date() {
        return oa_generation_date;
    }

    public void setOa_generation_date(String oa_generation_date) {
        this.oa_generation_date = oa_generation_date;
    }

    public String getKom_date() {
        return kom_date;
    }

    public void setKom_date(String kom_date) {
        this.kom_date = kom_date;
    }

    public String getAdvance_payment_receipt_date() {
        return advance_payment_receipt_date;
    }

    public void setAdvance_payment_receipt_date(String advance_payment_receipt_date) {
        this.advance_payment_receipt_date = advance_payment_receipt_date;
    }

    public String getBalance_payment_receipt_date() {
        return balance_payment_receipt_date;
    }

    public void setBalance_payment_receipt_date(String balance_payment_receipt_date) {
        this.balance_payment_receipt_date = balance_payment_receipt_date;
    }

    public String getCp_layout_approvals() {
        return cp_layout_approvals;
    }

    public void setCp_layout_approvals(String cp_layout_approvals) {
        this.cp_layout_approvals = cp_layout_approvals;
    }

    public String getMachine_layout_approvals() {
        return machine_layout_approvals;
    }

    public void setMachine_layout_approvals(String machine_layout_approvals) {
        this.machine_layout_approvals = machine_layout_approvals;
    }

    public String getRoom_layout_approvals() {
        return room_layout_approvals;
    }

    public void setRoom_layout_approvals(String room_layout_approvals) {
        this.room_layout_approvals = room_layout_approvals;
    }

    public String getDispatch_clearance() {
        return dispatch_clearance;
    }

    public void setDispatch_clearance(String dispatch_clearance) {
        this.dispatch_clearance = dispatch_clearance;
    }

    public String getLoa_optional() {
        return loa_optional;
    }

    public void setLoa_optional(String loa_optional) {
        this.loa_optional = loa_optional;
    }

    public String getLoa_basic() {
        return loa_basic;
    }

    public void setLoa_basic(String loa_basic) {
        this.loa_basic = loa_basic;
    }

    public String getDq_release() {
        return dq_release;
    }

    public void setDq_release(String dq_release) {
        this.dq_release = dq_release;
    }

    public String getPpc_basic_date() {
        return ppc_basic_date;
    }

    public void setPpc_basic_date(String ppc_basic_date) {
        this.ppc_basic_date = ppc_basic_date;
    }

    public String getPpc_optional_date() {
        return ppc_optional_date;
    }

    public void setPpc_optional_date(String ppc_optional_date) {
        this.ppc_optional_date = ppc_optional_date;
    }

    public String getCp_design_release_date() {
        return cp_design_release_date;
    }

    public void setCp_design_release_date(String cp_design_release_date) {
        this.cp_design_release_date = cp_design_release_date;
    }

    public String getCp_manufacturing_date() {
        return cp_manufacturing_date;
    }

    public void setCp_manufacturing_date(String cp_manufacturing_date) {
        this.cp_manufacturing_date = cp_manufacturing_date;
    }

    public String getMachine_manufacturing_basic() {
        return machine_manufacturing_basic;
    }

    public void setMachine_manufacturing_basic(String machine_manufacturing_basic) {
        this.machine_manufacturing_basic = machine_manufacturing_basic;
    }

    public String getMachine_manufacturing_customisation() {
        return machine_manufacturing_customisation;
    }

    public void setMachine_manufacturing_customisation(String machine_manufacturing_customisation) {
        this.machine_manufacturing_customisation = machine_manufacturing_customisation;
    }
    public String getAssy_start_date() {
        return assy_start_date;
    }

    public void setAssy_start_date(String assy_start_date) {
        this.assy_start_date = assy_start_date;
    }
    public String getIfat_std_cp() {
        return ifat_std_cp;
    }

    public void setIfat_std_cp(String ifat_std_cp) {
        this.ifat_std_cp = ifat_std_cp;
    }

    public String getIfat_customer_cp() {
        return ifat_customer_cp;
    }

    public void setIfat_customer_cp(String ifat_customer_cp) {
        this.ifat_customer_cp = ifat_customer_cp;
    }

    public String getPmr_genration_submission_date() {
        return pmr_genration_submission_date;
    }

    public void setPmr_genration_submission_date(String pmr_genration_submission_date) {
        this.pmr_genration_submission_date = pmr_genration_submission_date;
    }

    public String getProducts_receipt_date() {
        return products_receipt_date;
    }

    public void setProducts_receipt_date(String products_receipt_date) {
        this.products_receipt_date = products_receipt_date;
    }

    public String getPackaging_receipt_date() {
        return packaging_receipt_date;
    }

    public void setPackaging_receipt_date(String packaging_receipt_date) {
        this.packaging_receipt_date = packaging_receipt_date;
    }

    public String getIfat_protocol_release() {
        return ifat_protocol_release;
    }

    public void setIfat_protocol_release(String ifat_protocol_release) {
        this.ifat_protocol_release = ifat_protocol_release;
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

    public String getPlanned_dispatch_date() {
        return planned_dispatch_date;
    }

    public void setPlanned_dispatch_date(String planned_dispatch_date) {
        this.planned_dispatch_date = planned_dispatch_date;
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMachineFamily() {
        return machineFamily;
    }

    public void setMachineFamily(String machineFamily) {
        this.machineFamily = machineFamily;
    }

   
    
}
