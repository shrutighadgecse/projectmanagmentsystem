package com.example.demo.service;

import com.example.demo.model.OtpVerification;
import com.example.demo.repository.OtpVerificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class OtpServiceTest {

    @Autowired
    private OtpService otpService;

    @Autowired
    private OtpVerificationRepository otpRepository;

    @Test
    @Transactional
    public void testMultipleOtpRequestsForSameEmail() {
        String email = "duplicate@test.com";
        // first request
        String firstOtp = otpService.requestOtp(email);
        // fetch latest record for this email only
        OtpVerification record = otpRepository.findLatestByEmailid(email)
                                         .orElseThrow();
        Assertions.assertEquals(email, record.getEmailid());
        Assertions.assertEquals(firstOtp, record.getOtp());
        Assertions.assertFalse(record.isUsed());

        // second request should not throw and should update the same row
        String secondOtp = otpService.requestOtp(email);
        OtpVerification record2 = otpRepository.findLatestByEmailid(email)
                                          .orElseThrow();
        Assertions.assertEquals(email, record2.getEmailid());
        Assertions.assertNotEquals(firstOtp, secondOtp, "New OTP should be different");
        Assertions.assertEquals(secondOtp, record2.getOtp(), "Record should have been updated");
    }
}
