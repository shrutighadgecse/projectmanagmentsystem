package com.example.demo.repository;

import com.example.demo.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    
    /**
     * Find the latest OTP record by email (case-insensitive)
     */
    @Query("SELECT o FROM OtpVerification o WHERE LOWER(o.emailid) = LOWER(:emailid) " +
           "ORDER BY o.createdAt DESC LIMIT 1")
    Optional<OtpVerification> findLatestByEmailid(@Param("emailid") String emailid);
    
    /**
     * Find OTP by emailid and OTP code (case-insensitive email)
     */
    @Query("SELECT o FROM OtpVerification o WHERE LOWER(o.emailid) = LOWER(:emailid) AND o.otp = :otp")
    Optional<OtpVerification> findByEmailidAndOtp(@Param("emailid") String emailid, @Param("otp") String otp);
}
