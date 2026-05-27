package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class DeadlineAlertService {

    private static final Logger logger = Logger.getLogger(DeadlineAlertService.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    // Department email mapping
    private static final Map<String, String> DEPARTMENT_EMAILS = new HashMap<>();

    static {
        // Configure department email addresses
        DEPARTMENT_EMAILS.put("SALES", "sales@company.com");
        DEPARTMENT_EMAILS.put("PPC", "ppc@company.com");
        DEPARTMENT_EMAILS.put("DESIGN", "design@company.com");
        DEPARTMENT_EMAILS.put("CP_CELL", "cpcell@company.com");
        DEPARTMENT_EMAILS.put("ASSEMBLY", "assembly@company.com");
        DEPARTMENT_EMAILS.put("STORES", "stores@company.com");
        DEPARTMENT_EMAILS.put("DISPATCH", "dispatch@company.com");
        DEPARTMENT_EMAILS.put("QA", "qa@company.com");
    }

    // Date field to department mapping
    private static final Map<String, String> DATE_FIELD_DEPARTMENTS = new HashMap<>();

    static {
        // Map each date field to its responsible department
        DATE_FIELD_DEPARTMENTS.put("actualOaGenerationDate", "SALES");
        DATE_FIELD_DEPARTMENTS.put("actualCpOaGenerationDate", "SALES");
        DATE_FIELD_DEPARTMENTS.put("actualAdvPaymentRecdDate", "SALES");
        DATE_FIELD_DEPARTMENTS.put("actualBalPaymentRecdDate", "SALES");

        DATE_FIELD_DEPARTMENTS.put("actualCpLayoutApprovalDate", "DESIGN");
        DATE_FIELD_DEPARTMENTS.put("actualOptionalCpLayoutApprovalDate", "DESIGN");
        DATE_FIELD_DEPARTMENTS.put("actualMachineLayoutApprovalDate", "DESIGN");
        DATE_FIELD_DEPARTMENTS.put("actualRoomLayoutApprovalDate", "DESIGN");

        DATE_FIELD_DEPARTMENTS.put("actualDispatchClearanceRecdDate", "DISPATCH");
        DATE_FIELD_DEPARTMENTS.put("actualCommercialClearanceDate", "SALES");

        DATE_FIELD_DEPARTMENTS.put("actualDqReleaseDate", "QA");

        DATE_FIELD_DEPARTMENTS.put("actualPpcBasicMaterialDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualPpcOptionalMaterialDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualCpDesignReleaseDate", "DESIGN");

        DATE_FIELD_DEPARTMENTS.put("actualMachineCpManufacturingDate", "CP_CELL");
        DATE_FIELD_DEPARTMENTS.put("actualMachineOptionalCpManufacturingDate", "CP_CELL");
        DATE_FIELD_DEPARTMENTS.put("actualMachineBasicCompletion", "CP_CELL");
        DATE_FIELD_DEPARTMENTS.put("actualMachineCustomizationCompletion", "CP_CELL");

        DATE_FIELD_DEPARTMENTS.put("actualAssyStartDate", "ASSEMBLY");
        DATE_FIELD_DEPARTMENTS.put("actualIfatStdCpDate", "QA");
        DATE_FIELD_DEPARTMENTS.put("actualIfatCustomerCpDate", "QA");

        DATE_FIELD_DEPARTMENTS.put("actualPmrSubmissionDate", "QA");
        DATE_FIELD_DEPARTMENTS.put("actualProductReceiptDate", "STORES");
        DATE_FIELD_DEPARTMENTS.put("actualPackagingReceiptDate", "STORES");
        DATE_FIELD_DEPARTMENTS.put("actualIfatProtocolReleaseDate", "QA");
        DATE_FIELD_DEPARTMENTS.put("actualCfatDate", "QA");
        DATE_FIELD_DEPARTMENTS.put("actualComplianceDate", "QA");
        DATE_FIELD_DEPARTMENTS.put("actualPackingClearanceRecdDate", "DISPATCH");

        DATE_FIELD_DEPARTMENTS.put("actualBasicMaterialHandedOverAssyDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualOptionalMaterialHandedOverAssyDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualBasicMaterialReceiptDate", "STORES");
        DATE_FIELD_DEPARTMENTS.put("actualCustomizationMaterialReceiptDate", "STORES");

        DATE_FIELD_DEPARTMENTS.put("actualHandedToDispatchDate", "ASSEMBLY");
        DATE_FIELD_DEPARTMENTS.put("actualDispatchDate", "DISPATCH");

        DATE_FIELD_DEPARTMENTS.put("actualPpcBasicDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualPpcOptionalDate", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualLoaBasic", "PPC");
        DATE_FIELD_DEPARTMENTS.put("actualLoaOptional", "PPC");

        DATE_FIELD_DEPARTMENTS.put("additionalNewAssemblyAttachedAfterCfatDate", "ASSEMBLY");
        DATE_FIELD_DEPARTMENTS.put("mcnShortfallClosureDate", "DESIGN");
        DATE_FIELD_DEPARTMENTS.put("actualPpcNewMaterialDateAfterCfat", "PPC");
    }

    /**
     * Send deadline alert email for a specific date field
     */
    public void sendDeadlineAlert(String ccn, String dateFieldName, LocalDate deadlineDate, LocalDate today) {
        try {
            String department = DATE_FIELD_DEPARTMENTS.get(dateFieldName);
            if (department == null) {
                logger.warning("No department mapping found for date field: " + dateFieldName);
                return;
            }

            String emailAddress = DEPARTMENT_EMAILS.get(department);
            if (emailAddress == null) {
                logger.warning("No email address configured for department: " + department);
                return;
            }

            // Create email content
            String subject = "URGENT: Deadline Alert - CCN " + ccn + " - " + formatFieldName(dateFieldName);
            String body = buildEmailBody(ccn, dateFieldName, deadlineDate, today, department);

            // Send email
            sendEmail(emailAddress, subject, body);

            logger.info("Deadline alert sent to " + department + " (" + emailAddress + ") for CCN: " + ccn + ", field: " + dateFieldName);

        } catch (Exception e) {
            logger.severe("Failed to send deadline alert for CCN: " + ccn + ", field: " + dateFieldName + ", error: " + e.getMessage());
        }
    }

    /**
     * Send email using JavaMailSender
     */
    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("system@company.com"); // Configure from address

            mailSender.send(message);
        } catch (Exception e) {
            logger.severe("Failed to send email to " + to + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Build email body content
     */
    private String buildEmailBody(String ccn, String dateFieldName, LocalDate deadlineDate, LocalDate today, String department) {
        StringBuilder body = new StringBuilder();
        body.append("URGENT DEADLINE ALERT\n\n");
        body.append("CCN: ").append(ccn).append("\n");
        body.append("Field: ").append(formatFieldName(dateFieldName)).append("\n");
        body.append("Deadline Date: ").append(deadlineDate).append("\n");
        body.append("Current Date: ").append(today).append("\n");
        body.append("Days Overdue: ").append(java.time.temporal.ChronoUnit.DAYS.between(deadlineDate, today)).append("\n\n");
        body.append("Department: ").append(department).append("\n\n");
        body.append("Please take immediate action to address this deadline.\n\n");
        body.append("This is an automated notification from the Machine CCN Project Management System.");

        return body.toString();
    }

    /**
     * Format field name for display (convert camelCase to readable text)
     */
    private String formatFieldName(String fieldName) {
        // Remove "actual" prefix and convert camelCase to readable text
        String displayName = fieldName.replace("actual", "").replace("Date", "");

        // Add spaces before capital letters
        displayName = displayName.replaceAll("([a-z])([A-Z])", "$1 $2");

        // Capitalize first letter
        if (!displayName.isEmpty()) {
            displayName = displayName.substring(0, 1).toUpperCase() + displayName.substring(1);
        }

        return displayName.trim();
    }

    /**
     * Get department for a date field
     */
    public String getDepartmentForField(String dateFieldName) {
        return DATE_FIELD_DEPARTMENTS.get(dateFieldName);
    }

    /**
     * Get email address for a department
     */
    public String getEmailForDepartment(String department) {
        return DEPARTMENT_EMAILS.get(department);
    }

    /**
     * Check if a date field should trigger an alert
     */
    public boolean shouldSendAlert(LocalDate deadlineDate, LocalDate today, Boolean isFinal, Boolean alertAlreadySent, LocalDate lastAlertDate) {
        // Don't send if already final
        if (Boolean.TRUE.equals(isFinal)) {
            return false;
        }

        // Don't send if deadline not crossed
        if (deadlineDate == null || !today.isAfter(deadlineDate)) {
            return false;
        }

        // Send if no alert sent yet
        if (!Boolean.TRUE.equals(alertAlreadySent)) {
            return true;
        }

        // Send daily if still overdue (but not more than once per day)
        if (lastAlertDate == null || !lastAlertDate.equals(today)) {
            return true;
        }

        return false;
    }
}