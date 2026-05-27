package com.example.demo.service;

import com.example.demo.dto.MachineProjectResponseDTO;
import com.example.demo.model.KickoffForm;
import com.example.demo.model.MachineProject;
import com.example.demo.model.PurchaseOrder;
import com.example.demo.repository.KickoffFormRepository;
import com.example.demo.repository.MachineProjectRepository;
import com.example.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Service
public class MachineProjectService {

    @Autowired
    private MachineProjectRepository machineProjectRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private KickoffFormRepository kickoffFormRepository;

    private static final String UPLOAD_DIR = "uploads/";
    private static final Logger logger = Logger.getLogger(MachineProjectService.class.getName());
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MachineProjectResponseDTO getByCcn(String ccn) {
        if (ccn == null || ccn.trim().isEmpty()) {
            throw new IllegalArgumentException("CCN cannot be null or empty");
        }

        try {
            PurchaseOrder po = purchaseOrderRepository.findAll().stream()
                    .filter(p -> ccn.equalsIgnoreCase(p.getCcn()))
                    .findFirst().orElse(null);

            KickoffForm kickoff = kickoffFormRepository.findAll().stream()
                    .filter(k -> ccn.equalsIgnoreCase(k.getCcn()))
                    .findFirst().orElse(null);

            MachineProject machineProject = machineProjectRepository.findByCcn(ccn).orElse(null);
            
            // If machineProject doesn't exist, create new one with CCN and populate from kickoff
            if (machineProject == null && kickoff != null) {
                machineProject = new MachineProject();
                machineProject.setCcn(ccn);
                machineProject.setCcnStatus("LIVE"); // Default status
                machineProject = populateFromKickoff(machineProject, kickoff);
            }

            return new MachineProjectResponseDTO(machineProject, po, kickoff);
        } catch (Exception e) {
            logger.severe("Error fetching data for CCN: " + ccn + " - " + e.getMessage());
            throw new RuntimeException("Error fetching machine project data", e);
        }
    }

    /**
     * Populate plan dates from Kickoff form to MachineProject
     */
    private MachineProject populateFromKickoff(MachineProject mp, KickoffForm kickoff) {
        try {
            if (mp == null || kickoff == null) return mp;

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Map all plan dates from kickoff to machine project
            mp.setPlanCommercialClearanceDate(parseDate(kickoff.getCommercial_clearance(), df));
            mp.setPlanAdvPaymentRecdDate(parseDate(kickoff.getAdvance_payment_receipt_date(), df));
            mp.setPlanBalPaymentRecdDate(parseDate(kickoff.getBalance_payment_receipt_date(), df));
            mp.setPlanMachineLayoutApprovalDate(parseDate(kickoff.getMachine_layout_approvals(), df));
            mp.setPlanRoomLayoutApprovalDate(parseDate(kickoff.getRoom_layout_approvals(), df));
            mp.setPlanCpLayoutApprovalDate(parseDate(kickoff.getCp_layout_approvals(), df));
            mp.setPlanOptionalCpLayoutApprovalDate(parseDate(kickoff.getOptional_cp_layout_approval_date(), df));
            mp.setPlanOaGenerationDate(parseDate(kickoff.getOa_generation_date(), df));
            mp.setPlanCpOaGenerationDate(parseDate(kickoff.getCp_oa_genration_date(), df));
            mp.setPlanDispatchClearanceRecdDate(parseDate(kickoff.getDispatch_clearance(), df));
            mp.setPlanLoaBasicReleaseDate(parseDate(kickoff.getLoa_basic(), df));
            mp.setPlanLoaOptionalReleaseDate(parseDate(kickoff.getLoa_optional(), df));
            mp.setPlanDqReleaseDate(parseDate(kickoff.getDq_release(), df));
            mp.setPlanIfatProtocolReleaseDate(parseDate(kickoff.getIfat_protocol_release(), df));
            mp.setPlanPmrSubmissionDate(parseDate(kickoff.getPmr_genration_submission_date(), df));
            mp.setPlanProductReceiptDate(parseDate(kickoff.getProducts_receipt_date(), df));
            mp.setPlanPackagingReceiptDate(parseDate(kickoff.getPackaging_receipt_date(), df));
            mp.setPlanComplianceDate(parseDate(kickoff.getCompliance(), df));
            mp.setPlanPackingClearanceRecdDate(parseDate(kickoff.getCompliance(), df)); // Use compliance date
            mp.setPlanPpcBasicMaterialDate(parseDate(kickoff.getPpc_basic_date(), df));
            mp.setPlanPpcOptionalMaterialDate(parseDate(kickoff.getPpc_optional_date(), df));
            mp.setPlanCpDesignReleaseDate(parseDate(kickoff.getCp_design_release_date(), df));
            mp.setPlanMachineCpManufacturingDate(parseDate(kickoff.getCp_manufacturing_date(), df));
            mp.setPlanMachineOptionalCpManufacturingDate(parseDate(kickoff.getOptional_cp_manufacturing_date(), df));
            mp.setPlanBasicMaterialHandedOverAssyDate(parseDate(kickoff.getBasic_matl_handed_over_Assy_date(), df));
            mp.setPlanOptionalMaterialHandedOverAssyDate(parseDate(kickoff.getOptional_matl_handed_over_Assy_date(), df));
            mp.setPlanAssyStartDate(parseDate(kickoff.getAssy_start_date(), df));
            mp.setPlanMachineBasicCompletion(parseDate(kickoff.getMachine_manufacturing_basic(), df));
            mp.setPlanMachineCustomizationCompletion(parseDate(kickoff.getMachine_manufacturing_customisation(), df));
            mp.setPlanIfatStdCpDate(parseDate(kickoff.getIfat_std_cp(), df));
            mp.setPlanIfatCustomerCpDate(parseDate(kickoff.getIfat_customer_cp(), df));
            mp.setPlanCfatDate(parseDate(kickoff.getCfat(), df));
            mp.setPlanHandedToDispatchDate(parseDate(kickoff.getHanded_dispatch_date(), df));
            mp.setPlanDispatchDate(parseDate(kickoff.getPlanned_dispatch_date(), df));

        } catch (Exception e) {
            logger.warning("Error populating plan dates from kickoff: " + e.getMessage());
        }
        return mp;
    }

    /**
     * Parse date string with fallback handling
     */
    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(dateStr.trim(), formatter);
        } catch (Exception e) {
            logger.warning("Could not parse date: " + dateStr);
            return null;
        }
    }

    /**
     * Calculate machine percentage based on task completed and std task
     */
    public Double calculateMachinePercentage(MachineProject machineProject) {
        if (machineProject == null) return 0.0;
        if (machineProject.getStdTaskOfMachine() == null || machineProject.getStdTaskOfMachine() == 0) return 0.0;
        if (machineProject.getTaskCompleted() == null) return 0.0;

        double percentage = (machineProject.getTaskCompleted() * 100.0) / machineProject.getStdTaskOfMachine();
        return Math.min(100.0, Math.max(0.0, percentage));
    }

    /**
     * Check if all steps are complete for CCN status
     */
    public boolean areAllStepsComplete(MachineProject machineProject) {
        if (machineProject == null) return false;
        
        // Check if critical fields from each step are filled
        // This is a basic check - can be extended based on actual requirements
        return machineProject.getDnNumber() != null
                && machineProject.getSalesOrderNumber() != null
                && machineProject.getMcnNumber() != null
                && machineProject.getActualCommercialClearanceDate() != null
                && machineProject.getInvoiceNumber() != null
                && machineProject.getActualDispatchDate() != null;
    }

    @Transactional
    public MachineProject save(MachineProject machineProject, MultipartFile cpImage, 
                              MultipartFile machineImageStores, MultipartFile machineImageAssembly) throws IOException {
        
        // Validate CCN
        if (machineProject.getCcn() == null || machineProject.getCcn().trim().isEmpty()) {
            throw new IllegalArgumentException("CCN is mandatory and cannot be empty");
        }

        try {
            // Handle file uploads
            if (cpImage != null && !cpImage.isEmpty()) {
                String cpImagePath = saveFile(cpImage, "cp_" + machineProject.getCcn());
                machineProject.setCpImagePath(cpImagePath);
            }
            if (machineImageStores != null && !machineImageStores.isEmpty()) {
                String storesPath = saveFile(machineImageStores, "stores_" + machineProject.getCcn());
                machineProject.setMachineImageStoresPath(storesPath);
            }
            if (machineImageAssembly != null && !machineImageAssembly.isEmpty()) {
                String assemblyPath = saveFile(machineImageAssembly, "assembly_" + machineProject.getCcn());
                machineProject.setMachineImageAssemblyPath(assemblyPath);
            }

            // Calculate machine percentage
            machineProject.setMachinePercentage(calculateMachinePercentage(machineProject));

            // Update last saved date
            machineProject.setFormLastSavedDate(LocalDate.now());

            // If all steps complete, mark CCN status as checkable (but keep LIVE unless user closes)
            if (areAllStepsComplete(machineProject)) {
                // Keep existing status or default to LIVE
                if (machineProject.getCcnStatus() == null) {
                    machineProject.setCcnStatus("LIVE");
                }
            } else {
                // Ensure at least LIVE status
                if (machineProject.getCcnStatus() == null) {
                    machineProject.setCcnStatus("LIVE");
                }
            }

            logger.info("Saving machine project for CCN: " + machineProject.getCcn());
            return machineProjectRepository.save(machineProject);

        } catch (IOException e) {
            logger.severe("File upload error: " + e.getMessage());
            throw new IOException("File upload failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.severe("Save error: " + e.getMessage());
            throw new RuntimeException("Save failed: " + e.getMessage(), e);
        }
    }

    private String saveFile(MultipartFile file, String prefix) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filename = prefix + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.write(filePath, file.getBytes());
        logger.info("File saved: " + filename);
        return filename;
    }
}