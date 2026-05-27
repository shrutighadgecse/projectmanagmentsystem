package com.example.demo.service;

import com.example.demo.model.OtpVerification;
import com.example.demo.repository.OtpVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private OtpVerificationRepository otpRepository;

    private static final int OTP_VALIDITY_MINUTES = 10;
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generate a random 6-digit OTP
     */
    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    /**
     * Request OTP for email
     * - Generates new OTP
     * - Saves to database with 10-minute expiry
     * - Returns the OTP string
     */
    public String requestOtp(String emailid) {
        emailid = emailid.trim().toLowerCase();
        String otp = generateOtp();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plusMinutes(OTP_VALIDITY_MINUTES);

        // Look for an existing record so that we don't violate any unique
        // constraint on emailid.  Earlier versions of the schema had a
        // unique index on the email column, causing a failure when the same
        // address requested a second OTP.  We now update the existing row
        // if one is found.
        Optional<OtpVerification> existing = otpRepository.findLatestByEmailid(emailid);
        OtpVerification otpVerification;
        if (existing.isPresent()) {
            otpVerification = existing.get();
            otpVerification.setOtp(otp);
            otpVerification.setCreatedAt(now);
            otpVerification.setExpiryTime(expiryTime);
            otpVerification.setUsed(false);
        } else {
            otpVerification = new OtpVerification(emailid, otp, now, expiryTime);
        }

        otpRepository.save(otpVerification);
        return otp;
    }

    /**
     * Verify OTP
     * - Checks if OTP exists
     * - Checks if OTP is not expired
     * - Checks if OTP hasn't been used yet
     */
    public boolean verifyOtp(String emailid, String otp) {
        emailid = emailid.trim().toLowerCase();
        otp = otp.trim();
        
        Optional<OtpVerification> otpOptional = otpRepository.findByEmailidAndOtp(emailid, otp);
        
        if (otpOptional.isEmpty()) {
            return false;
        }
        
        OtpVerification otpVerification = otpOptional.get();
        
        // Check if OTP is expired
        if (otpVerification.isExpired()) {
            return false;
        }
        
        // Check if OTP has already been used
        if (otpVerification.isUsed()) {
            return false;
        }
        
        return true;
    }

    /**
     * Mark OTP as used after successful reset
     */
    public void markOtpAsUsed(String emailid, String otp) {
        emailid = emailid.trim().toLowerCase();
        otp = otp.trim();
        
        Optional<OtpVerification> otpOptional = otpRepository.findByEmailidAndOtp(emailid, otp);
        
        if (otpOptional.isPresent()) {
            OtpVerification otpVerification = otpOptional.get();
            otpVerification.setUsed(true);
            otpRepository.save(otpVerification);
        }
    }

    /**
     * Check if email has a valid (non-expired, non-used) OTP
     */
    public boolean hasValidOtp(String emailid) {
        emailid = emailid.trim().toLowerCase();
        
        Optional<OtpVerification> otpOptional = otpRepository.findLatestByEmailid(emailid);
        
        if (otpOptional.isEmpty()) {
            return false;
        }
        
        OtpVerification otpVerification = otpOptional.get();
        return otpVerification.isValid();
    }

    /**
     * Get remaining time for OTP (in minutes)
     */
    public long getRemainingMinutes(String emailid) {
        emailid = emailid.trim().toLowerCase();
        
        Optional<OtpVerification> otpOptional = otpRepository.findLatestByEmailid(emailid);
        
        if (otpOptional.isEmpty()) {
            return 0;
        }
        
        OtpVerification otpVerification = otpOptional.get();
        
        if (otpVerification.isExpired()) {
            return 0;
        }
        
        return java.time.temporal.ChronoUnit.MINUTES.between(
            LocalDateTime.now(),
            otpVerification.getExpiryTime()
        );
    }

    /**
     * Get remaining time for OTP in seconds
     */
    public long getRemainingSeconds(String emailid) {
        emailid = emailid.trim().toLowerCase();
        
        Optional<OtpVerification> otpOptional = otpRepository.findLatestByEmailid(emailid);
        
        if (otpOptional.isEmpty()) {
            return 0;
        }
        
        OtpVerification otpVerification = otpOptional.get();
        
        if (otpVerification.isExpired()) {
            return 0;
        }
        
        return java.time.temporal.ChronoUnit.SECONDS.between(
            LocalDateTime.now(),
            otpVerification.getExpiryTime()
        );
    }
}
