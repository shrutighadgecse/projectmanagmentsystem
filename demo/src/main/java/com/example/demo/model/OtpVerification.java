package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verification", indexes = {
    @Index(name = "idx_emailid", columnList = "emailid"),
    @Index(name = "idx_otp", columnList = "otp")
})
public class OtpVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 65)
    private String emailid;

    @Column(nullable = false, length = 10)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiryTime;

    @Column(nullable = false)
    private boolean isUsed;

    // Constructors
    public OtpVerification() {
        this.isUsed = false;
    }

    public OtpVerification(String emailid, String otp, LocalDateTime createdAt, LocalDateTime expiryTime) {
        this.emailid = emailid;
        this.otp = otp;
        this.createdAt = createdAt;
        this.expiryTime = expiryTime;
        this.isUsed = false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    // Check if OTP has expired
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryTime);
    }

    // Check if OTP is still valid (not expired and not used)
    public boolean isValid() {
        return !isExpired() && !isUsed;
    }
}
