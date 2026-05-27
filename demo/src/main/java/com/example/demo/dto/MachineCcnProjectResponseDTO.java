package com.example.demo.dto;

import com.example.demo.model.KickoffForm;
import com.example.demo.model.MachineCcnProject;
import com.example.demo.model.PurchaseOrder;

public class MachineCcnProjectResponseDTO {
    private MachineCcnProject machineCcnProject;
    private PurchaseOrder purchaseOrder;
    private KickoffForm kickoffForm;

    public MachineCcnProjectResponseDTO() {}

    public MachineCcnProjectResponseDTO(MachineCcnProject machineCcnProject, PurchaseOrder purchaseOrder, KickoffForm kickoffForm) {
        this.machineCcnProject = machineCcnProject;
        this.purchaseOrder = purchaseOrder;
        this.kickoffForm = kickoffForm;
    }

    public MachineCcnProject getMachineCcnProject() {
        return machineCcnProject;
    }

    public void setMachineCcnProject(MachineCcnProject machineCcnProject) {
        this.machineCcnProject = machineCcnProject;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public KickoffForm getKickoffForm() {
        return kickoffForm;
    }

    public void setKickoffForm(KickoffForm kickoffForm) {
        this.kickoffForm = kickoffForm;
    }
}
