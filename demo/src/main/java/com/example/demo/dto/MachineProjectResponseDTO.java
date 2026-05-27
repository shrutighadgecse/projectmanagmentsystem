package com.example.demo.dto;

import com.example.demo.model.KickoffForm;
import com.example.demo.model.MachineProject;
import com.example.demo.model.PurchaseOrder;

public class MachineProjectResponseDTO {
    private MachineProject machineProject;
    private PurchaseOrder purchaseOrder;
    private KickoffForm kickoffForm;

    public MachineProjectResponseDTO() {}

    public MachineProjectResponseDTO(MachineProject machineProject, PurchaseOrder purchaseOrder, KickoffForm kickoffForm) {
        this.machineProject = machineProject;
        this.purchaseOrder = purchaseOrder;
        this.kickoffForm = kickoffForm;
    }

    public MachineProject getMachineProject() { return machineProject; }
    public void setMachineProject(MachineProject machineProject) { this.machineProject = machineProject; }

    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }

    public KickoffForm getKickoffForm() { return kickoffForm; }
    public void setKickoffForm(KickoffForm kickoffForm) { this.kickoffForm = kickoffForm; }
}