package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:noreply@imaproject.com}")
    private String senderEmail;

    /**
     * Send OTP to user's email
     */
    public boolean sendOtpEmail(String recipientEmail, String otp) {
        try {
            if (mailSender == null) {
                logger.warn("Mail sender not configured. OTP would have been sent to: {} with code: {}", 
                           recipientEmail, otp);
                return true; // For development, we'll return true even without actual sending
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(recipientEmail);
            helper.setSubject("Password Reset OTP - IMA Project Management System");

            String htmlContent = buildOtpEmailContent(otp, recipientEmail);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("OTP email sent successfully to: {}", recipientEmail);
            return true;

        } catch (MessagingException e) {
            logger.error("Failed to send OTP email to: {}", recipientEmail, e);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error while sending OTP email to: {}", recipientEmail, e);
            return false;
        }
    }

    /**
     * Send password reset confirmation email
     */
    public boolean sendPasswordResetConfirmationEmail(String recipientEmail, String employeeName) {
        try {
            if (mailSender == null) {
                logger.warn("Mail sender not configured. Confirmation would have been sent to: {}", recipientEmail);
                return true;
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(recipientEmail);
            helper.setSubject("Password Reset Successful - IMA Project Management System");

            String htmlContent = buildConfirmationEmailContent(employeeName);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("Password reset confirmation email sent to: {}", recipientEmail);
            return true;

        } catch (MessagingException e) {
            logger.error("Failed to send confirmation email to: {}", recipientEmail, e);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error while sending confirmation email to: {}", recipientEmail, e);
            return false;
        }
    }

    /**
     * Build HTML content for OTP email
     */
    private String buildOtpEmailContent(String otp, String email) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "    <style>" +
               "        body { font-family: Arial, sans-serif; background-color: #f5f5f5; }" +
               "        .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
               "        .header { background-color: #3E8DA8; color: white; padding: 20px; text-align: center; border-radius: 8px 8px 0 0; }" +
               "        .content { padding: 30px 20px; text-align: center; }" +
               "        .otp-box { background-color: #f9f9f9; border: 2px solid #3E8DA8; border-radius: 8px; padding: 20px; margin: 20px 0; }" +
               "        .otp-text { font-size: 32px; font-weight: bold; color: #3E8DA8; letter-spacing: 5px; }" +
               "        .validity { color: #666; font-size: 14px; margin-top: 10px; }" +
               "        .footer { background-color: #f5f5f5; padding: 15px; text-align: center; font-size: 12px; color: #999; border-radius: 0 0 8px 8px; }" +
               "        .warning { color: #d9534f; font-size: 12px; margin-top: 20px; }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <div class='container'>" +
               "        <div class='header'>" +
               "            <h2>IMA Project Management System</h2>" +
               "        </div>" +
               "        <div class='content'>" +
               "            <h3>Password Reset Request</h3>" +
               "            <p>Your password reset OTP is:</p>" +
               "            <div class='otp-box'>" +
               "                <div class='otp-text'>" + otp + "</div>" +
               "            </div>" +
               "            <div class='validity'>" +
               "                ⏱️ This OTP is valid for 10 minutes" +
               "            </div>" +
               "            <p style='margin-top: 20px; color: #666;'>" +
               "                If you didn't request a password reset, please ignore this email or contact support." +
               "            </p>" +
               "            <div class='warning'>" +
               "                ⚠️ Do not share this OTP with anyone. We will never ask for your OTP." +
               "            </div>" +
               "        </div>" +
               "        <div class='footer'>" +
               "            <p>This is an automated email. Please do not reply to this message.</p>" +
               "            <p>&copy; 2026 IMA Project Management System. All rights reserved.</p>" +
               "        </div>" +
               "    </div>" +
               "</body>" +
               "</html>";
    }

    /**
     * Build HTML content for password reset confirmation email
     */
    private String buildConfirmationEmailContent(String employeeName) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "    <style>" +
               "        body { font-family: Arial, sans-serif; background-color: #f5f5f5; }" +
               "        .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
               "        .header { background-color: #28a745; color: white; padding: 20px; text-align: center; border-radius: 8px 8px 0 0; }" +
               "        .content { padding: 30px 20px; text-align: center; }" +
               "        .success-icon { font-size: 48px; margin: 20px 0; }" +
               "        .message { color: #28a745; font-size: 16px; font-weight: bold; margin: 20px 0; }" +
               "        .details { background-color: #f9f9f9; border-left: 4px solid #28a745; padding: 15px; text-align: left; margin: 20px 0; border-radius: 4px; }" +
               "        .next-steps { text-align: left; margin: 20px 0; }" +
               "        .next-steps ol { color: #666; }" +
               "        .next-steps li { margin: 8px 0; }" +
               "        .footer { background-color: #f5f5f5; padding: 15px; text-align: center; font-size: 12px; color: #999; border-radius: 0 0 8px 8px; }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <div class='container'>" +
               "        <div class='header'>" +
               "            <h2>IMA Project Management System</h2>" +
               "        </div>" +
               "        <div class='content'>" +
               "            <div class='success-icon'>✅</div>" +
               "            <h3>Password Reset Successful</h3>" +
               "            <div class='message'>" +
               "                Your password has been successfully reset!" +
               "            </div>" +
               "            <div class='details'>" +
               "                <p><strong>Recipient:</strong> " + (employeeName != null ? employeeName : "User") + "</p>" +
               "                <p><strong>Time:</strong> " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss")) + "</p>" +
               "            </div>" +
               "            <div class='next-steps'>" +
               "                <h4>What to do next:</h4>" +
               "                <ol>" +
               "                    <li>Return to the login page</li>" +
               "                    <li>Enter your email and new password</li>" +
               "                    <li>You're all set! Enjoy full access to IMA Project Management System</li>" +
               "                </ol>" +
               "            </div>" +
               "            <p style='margin-top: 20px; color: #666;'>" +
               "                If you did not perform this action, please contact support immediately." +
               "            </p>" +
               "        </div>" +
               "        <div class='footer'>" +
               "            <p>This is an automated email. Please do not reply to this message.</p>" +
               "            <p>&copy; 2026 IMA Project Management System. All rights reserved.</p>" +
               "        </div>" +
               "    </div>" +
               "</body>" +
               "</html>";
    }
}
