package com.example.demo.controller;

import com.example.demo.model.KickoffForm;
import com.example.demo.model.Machine;
import com.example.demo.service.KickoffFormService;
import com.example.demo.service.MachineService;
import com.example.demo.service.MachineCcnProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/kickoff")
public class KickoffFormController {
    @Autowired
    private KickoffFormService service;

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineCcnProjectService machineCcnProjectService;

    @GetMapping("/next-kom-no")
    public String getNextKomNo() {
        List<KickoffForm> all = service.getAll();
        int currentYear = Year.now().getValue();
        int maxMiddle = -1;
        Pattern pattern = Pattern.compile("KOM-(\\d+)-" + currentYear);
        for (KickoffForm form : all) {
            if (form.getKomNo() != null) {
                Matcher m = pattern.matcher(form.getKomNo());
                if (m.matches()) {
                    int middle = Integer.parseInt(m.group(1));
                    if (middle > maxMiddle) maxMiddle = middle;
                }
            }
        }
        int nextMiddle = maxMiddle + 1;
        String middleStr = "%02d".formatted(nextMiddle);
        return "KOM-" + middleStr + "-" + currentYear;
    }

    @PostMapping
    public KickoffForm save(@RequestBody KickoffForm form) {
        KickoffForm saved = service.save(form);
        syncKickoffToMachine(saved);
        machineCcnProjectService.syncFromKickoffForm(saved);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<KickoffForm> update(@PathVariable Long id, @RequestBody KickoffForm form) {
        return service.getById(id)
                .map(existing -> {
                    form.setId(id);
                    KickoffForm saved = service.save(form);
                    syncKickoffToMachine(saved);
                    machineCcnProjectService.syncFromKickoffForm(saved);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private void syncKickoffToMachine(KickoffForm kickoff) {
        if (kickoff == null || kickoff.getCcn() == null || kickoff.getCcn().trim().isEmpty()) {
            return;
        }

        String ccn = kickoff.getCcn().trim();
        Machine machine = machineService.getByCcn(ccn).orElseGet(() -> {
            Machine m = new Machine();
            m.setCcn(ccn);
            return m;
        });

        // Sync all fields that are sourced from kickoff form and should reflect into machine form.
        // Note: this will overwrite the corresponding machine fields with the kickoff values.
        machine.setMachineName(kickoff.getMachineName());
        machine.setModel(kickoff.getMachineModel());
        machine.setCustomerName(kickoff.getCustomerName());
        machine.setPoDate(kickoff.getPoDate());
        machine.setPoDeliveryDate(kickoff.getPoDeliveryDate());
        machine.setOrderType(kickoff.getOrderType());
        machine.setAddress(kickoff.getAddress());
        machine.setVenue(kickoff.getVenue());
        machine.setKomNo(kickoff.getKomNo());
        machine.setMembersPresent(kickoff.getMembersPresent());
        machine.setMachineCpQty(kickoff.getMachineCpQty());
        machine.setMachineOptionalCpQty(kickoff.getMachineOptionalCpQty());
        machine.setMachineFamily(kickoff.getMachineFamily());
        machine.setUnit(kickoff.getUnit());
        machine.setMachineCategory(kickoff.getMachineCategory());

        machine.setMachineBasics(kickoff.getMachineBasics());
        machine.setMachineFeatures(kickoff.getMachineFeatures());
        machine.setAdditionalOptions(kickoff.getAdditionalOptions());
        machine.setProjectKickoffDetails(kickoff.getProjectKickoffDetails());

        machine.setCcnStatus(kickoff.getCcnStatus());

        machine.setPoReceiptDate(kickoff.getPo_receipt_date());
        machine.setOaGenerationDate(kickoff.getOa_generation_date());
        machine.setKomDate(kickoff.getKom_date());
        machine.setAdvancePaymentReceiptDate(kickoff.getAdvance_payment_receipt_date());
        machine.setBalancePaymentReceiptDate(kickoff.getBalance_payment_receipt_date());
        machine.setCpLayoutApprovals(kickoff.getCp_layout_approvals());
        machine.setMachineLayoutApprovals(kickoff.getMachine_layout_approvals());
        machine.setRoomLayoutApprovals(kickoff.getRoom_layout_approvals());
        machine.setDqRelease(kickoff.getDq_release());
        machine.setPpcBasicDate(kickoff.getPpc_basic_date());
        machine.setPpcOptionalDate(kickoff.getPpc_optional_date());
        machine.setCpDesignReleaseDate(kickoff.getCp_design_release_date());
        machine.setCpManufacturingDate(kickoff.getCp_manufacturing_date());
        machine.setMachineManufacturingBasic(kickoff.getMachine_manufacturing_basic());
        machine.setMachineManufacturingCustomisation(kickoff.getMachine_manufacturing_customisation());
       machine.setActualAssyStartDate(kickoff.getAssy_start_date());

        machine.setPmrGenrationSubmissionDate(kickoff.getPmr_genration_submission_date());
        machine.setProductsReceiptDate(kickoff.getProducts_receipt_date());
        machine.setPackagingReceiptDate(kickoff.getPackaging_receipt_date());
        machine.setIfatProtocolRelease(kickoff.getIfat_protocol_release());
        machine.setIfatStdCp(kickoff.getIfat_std_cp());
        machine.setIfatCustomerCp(kickoff.getIfat_customer_cp());
        machine.setCfat(kickoff.getCfat());
        machine.setCompliance(kickoff.getCompliance());
        machine.setDispatchClearance(kickoff.getDispatch_clearance());
        machine.setPlannedDispatchDate(kickoff.getPlanned_dispatch_date());

        machineService.save(machine);
    }

    @GetMapping
    public List<KickoffForm> getAll() {
        // Return all kickoff records (do not hide entries based on machine table).
        return service.getAll();
    }

    @GetMapping("/members-present-options")
    public List<String> getUniqueMembersPresent() {
        return service.getUniqueMembersPresent();
    }

    @GetMapping("/machine-family-options")
    public List<String> getUniqueMachineFamilies() {
        return service.getUniqueMachineFamilies();
    }

    @GetMapping("/machine-category-options")
    public List<String> getUniqueMachineCategories() {
        return service.getUniqueMachineCategories();
    }

    @GetMapping("/get-all-ccns")
    public List<String> getAllCcns() {
        return service.getAllCcns();
    }

    @GetMapping("/by-ccn")
    public ResponseEntity<KickoffForm> getByCcn(@RequestParam String ccn) {
        return service.getByCcn(ccn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KickoffForm> getByIdEndpoint(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
