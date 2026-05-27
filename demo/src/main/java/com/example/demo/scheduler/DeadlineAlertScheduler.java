package com.example.demo.scheduler;

import com.example.demo.model.MachineCcnProject;
import com.example.demo.repository.MachineCcnProjectRepository;
import com.example.demo.service.DeadlineAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DeadlineAlertScheduler {

    private static final Logger logger = Logger.getLogger(DeadlineAlertScheduler.class.getName());

    @Autowired
    private MachineCcnProjectRepository repository;

    @Autowired
    private DeadlineAlertService alertService;

    // List of all date fields that can have deadlines
    private static final String[] DATE_FIELDS = {
        "actualOaGenerationDate",
        "actualCpOaGenerationDate",
        "actualAdvPaymentRecdDate",
        "actualBalPaymentRecdDate",
        "actualCpLayoutApprovalDate",
        "actualOptionalCpLayoutApprovalDate",
        "actualMachineLayoutApprovalDate",
        "actualRoomLayoutApprovalDate",
        "actualDispatchClearanceRecdDate",
        "actualCommercialClearanceDate",
        "actualDqReleaseDate",
        "actualPpcBasicMaterialDate",
        "actualPpcOptionalMaterialDate",
        "actualCpDesignReleaseDate",
        "actualMachineCpManufacturingDate",
        "actualMachineOptionalCpManufacturingDate",
        "actualMachineBasicCompletion",
        "actualMachineCustomizationCompletion",
        "actualAssyStartDate",
        "actualIfatStdCpDate",
        "actualIfatCustomerCpDate",
        "actualPmrSubmissionDate",
        "actualProductReceiptDate",
        "actualPackagingReceiptDate",
        "actualIfatProtocolReleaseDate",
        "actualCfatDate",
        "actualComplianceDate",
        "actualPackingClearanceRecdDate",
        "actualBasicMaterialHandedOverAssyDate",
        "actualOptionalMaterialHandedOverAssyDate",
        "actualBasicMaterialReceiptDate",
        "actualCustomizationMaterialReceiptDate",
        "actualHandedToDispatchDate",
        "actualDispatchDate",
        "actualPpcBasicDate",
        "actualPpcOptionalDate",
        "actualLoaBasic",
        "actualLoaOptional",
        "additionalNewAssemblyAttachedAfterCfatDate",
        "mcnShortfallClosureDate",
        "actualPpcNewMaterialDateAfterCfat"
    };

    /**
     * Scheduled task to check deadlines daily at 9 AM
     * Cron expression: "0 0 9 * * *" (second minute hour day month day-of-week)
     */
    @Scheduled(cron = "0 0 9 * * *")
    public void checkDeadlinesAndSendAlerts() {
        logger.info("Starting daily deadline check...");

        LocalDate today = LocalDate.now();
        List<MachineCcnProject> allProjects = repository.findAll();

        int alertsSent = 0;

        for (MachineCcnProject project : allProjects) {
            for (String dateField : DATE_FIELDS) {
                try {
                    if (shouldSendAlertForField(project, dateField, today)) {
                        sendAlertForField(project, dateField, today);
                        alertsSent++;
                    }
                } catch (Exception e) {
                    logger.severe("Error processing field " + dateField + " for CCN " + project.getCcn() + ": " + e.getMessage());
                }
            }
        }

        logger.info("Daily deadline check completed. Alerts sent: " + alertsSent);
    }

    /**
     * Check if an alert should be sent for a specific field
     */
    private boolean shouldSendAlertForField(MachineCcnProject project, String dateField, LocalDate today) {
        try {
            // Get the date value
            LocalDate deadlineDate = getDateFieldValue(project, dateField);
            if (deadlineDate == null) {
                return false;
            }

            // Get final flag
            Boolean isFinal = getFinalFlagValue(project, dateField);

            // Get alert sent flag
            Boolean alertSent = getAlertSentFlagValue(project, dateField);

            // Get last alert date
            LocalDate lastAlertDate = getLastAlertDateValue(project, dateField);

            // Check if alert should be sent
            return alertService.shouldSendAlert(deadlineDate, today, isFinal, alertSent, lastAlertDate);

        } catch (Exception e) {
            logger.warning("Error checking alert conditions for field " + dateField + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Send alert for a specific field and update tracking fields
     */
    private void sendAlertForField(MachineCcnProject project, String dateField, LocalDate today) {
        try {
            // Get the deadline date
            LocalDate deadlineDate = getDateFieldValue(project, dateField);

            // Send the alert
            alertService.sendDeadlineAlert(project.getCcn(), dateField, deadlineDate, today);

            // Update tracking fields
            setAlertSentFlagValue(project, dateField, true);
            setLastAlertDateValue(project, dateField, today);

            // Save the project
            repository.save(project);

            logger.info("Alert sent and tracking updated for CCN: " + project.getCcn() + ", field: " + dateField);

        } catch (Exception e) {
            logger.severe("Error sending alert for field " + dateField + " on CCN " + project.getCcn() + ": " + e.getMessage());
        }
    }

    /**
     * Get date field value using reflection
     */
    private LocalDate getDateFieldValue(MachineCcnProject project, String fieldName) throws Exception {
        Field field = MachineCcnProject.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (LocalDate) field.get(project);
    }

    /**
     * Get final flag value using reflection
     */
    private Boolean getFinalFlagValue(MachineCcnProject project, String dateField) throws Exception {
        String finalFieldName = dateField + "Final";
        Field field = MachineCcnProject.class.getDeclaredField(finalFieldName);
        field.setAccessible(true);
        return (Boolean) field.get(project);
    }

    /**
     * Get alert sent flag value using reflection
     */
    private Boolean getAlertSentFlagValue(MachineCcnProject project, String dateField) throws Exception {
        String alertFieldName = dateField + "AlertSent";
        Field field = MachineCcnProject.class.getDeclaredField(alertFieldName);
        field.setAccessible(true);
        return (Boolean) field.get(project);
    }

    /**
     * Get last alert date value using reflection
     */
    private LocalDate getLastAlertDateValue(MachineCcnProject project, String dateField) throws Exception {
        String alertDateFieldName = dateField + "LastAlertDate";
        Field field = MachineCcnProject.class.getDeclaredField(alertDateFieldName);
        field.setAccessible(true);
        return (LocalDate) field.get(project);
    }

    /**
     * Set alert sent flag value using reflection
     */
    private void setAlertSentFlagValue(MachineCcnProject project, String dateField, Boolean value) throws Exception {
        String alertFieldName = dateField + "AlertSent";
        Field field = MachineCcnProject.class.getDeclaredField(alertFieldName);
        field.setAccessible(true);
        field.set(project, value);
    }

    /**
     * Set last alert date value using reflection
     */
    private void setLastAlertDateValue(MachineCcnProject project, String dateField, LocalDate value) throws Exception {
        String alertDateFieldName = dateField + "LastAlertDate";
        Field field = MachineCcnProject.class.getDeclaredField(alertDateFieldName);
        field.setAccessible(true);
        field.set(project, value);
    }
}