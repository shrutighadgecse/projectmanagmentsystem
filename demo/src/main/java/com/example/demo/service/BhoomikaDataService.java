package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BhoomikaForm;
import com.example.demo.model.FormData;
import com.example.demo.repository.BhoomikaDataRepository;
import com.example.demo.repository.FormDataRepository;

import jakarta.transaction.Transactional;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class BhoomikaDataService {

    @Autowired
    private BhoomikaDataRepository bhoomikaDataRepository;

    @Autowired
    private FormDataRepository formDataRepository;
    
    @Transactional
    public BhoomikaForm saveBhoomikaForm(BhoomikaForm bhoomikaForm) {
        
        return bhoomikaDataRepository.save(bhoomikaForm);
    }

    public Optional<BhoomikaForm> findById(Long id) {
        return bhoomikaDataRepository.findById(id);
    }
    public boolean referenceExists(String reference) {
        return bhoomikaDataRepository.existsByReference(reference);
    }


  
    public List<BhoomikaForm> findByBlcStatus(String status) {
        return bhoomikaDataRepository.findByBlcStatus(status);
    }

    public List<BhoomikaForm> findByBlcStatusNot(String status) {
        return bhoomikaDataRepository.findByBlcStatusNot(status);
    }
    public List<BhoomikaForm> findAll() {
        return bhoomikaDataRepository.findAll();
    }

    public Optional<FormData> findLocationsByAssemblyCode(String assemblyCode) {
    return formDataRepository.findByAssemblyCode(assemblyCode);
}

    @Transactional
    public BhoomikaForm updateBhoomikaForm(String referenceId, BhoomikaForm updatedForm) {
        Optional<BhoomikaForm> existingFormOpt = bhoomikaDataRepository.findByReference(referenceId);
        if (existingFormOpt.isPresent()) {
            BhoomikaForm existingForm = existingFormOpt.get();
            
            // Update fields as necessary
            existingForm.setKitFormDate(updatedForm.getKitFormDate());
            existingForm.setCurrentDateTime(updatedForm.getCurrentDateTime());
            existingForm.setBlcPo(updatedForm.getBlcPo());
            existingForm.setBlcPoDate(updatedForm.getBlcPoDate());
            existingForm.setCcn(updatedForm.getCcn());
            existingForm.setMachineName(updatedForm.getMachineName());
            existingForm.setAssemblyCode(updatedForm.getAssemblyCode());
            existingForm.setStage(updatedForm.getStage());
            existingForm.setInwardBy(updatedForm.getInwardBy());
            existingForm.setInwardQty(updatedForm.getInwardQty());
            existingForm.setDescription(updatedForm.getDescription());
            existingForm.setKitAcknowledgeBy(updatedForm.getKitAcknowledgeBy());
            existingForm.setKitAcknowledgeDate(updatedForm.getKitAcknowledgeDate());
            existingForm.setAssyStartDate(updatedForm.getAssyStartDate());
            existingForm.setBhomikaEngineer(updatedForm.getBhomikaEngineer());
            existingForm.setLiveStatus(updatedForm.getLiveStatus());
            existingForm.setAssyEndDate(updatedForm.getAssyEndDate());
            existingForm.setInvoiceNumber(updatedForm.getInvoiceNumber());
            existingForm.setInvoiceDate(updatedForm.getInvoiceDate());
            existingForm.setHoldDate(updatedForm.getHoldDate());
            existingForm.setHoldRemark(updatedForm.getHoldRemark());
            existingForm.setCloseDate(updatedForm.getCloseDate());
            existingForm.setCloseRemark(updatedForm.getCloseRemark());
            existingForm.setBlcStatus(updatedForm.getBlcStatus());
            existingForm.setKmLocation(updatedForm.getKmLocation());
            existingForm.setLimbodagriLocation(updatedForm.getLimbodagriLocation());
            
            
  // Check if BLC status is being changed to "Closed"
  if ("Closed".equals(updatedForm.getBlcStatus())) {
    existingForm.setLiveStatus("Closed"); // Set live status to Closed
}
            
           
        // Handle hold and close remarks
        if ("Closed".equals(updatedForm.getBlcStatus())) {
            existingForm.setCloseRemark(updatedForm.getCloseRemark());
            existingForm.setCloseDate(updatedForm.getCloseDate());
            existingForm.setHoldRemark(null); // Clear hold remark if closing
        } else if ("Hold".equals(updatedForm.getBlcStatus())) {
            existingForm.setHoldRemark(updatedForm.getHoldRemark());
            existingForm.setCloseRemark(null); // Clear close remark if holding
        }


        // Generate QR code if BLC status is closed
        if ("Closed".equals(updatedForm.getBlcStatus())) {
            String qrData = generateQRCodeData(existingForm);
            generateQRCode(qrData, referenceId); // Save QR code image
            existingForm.setQrCode(qrData); // Update QR code data
        }

        existingForm.setQrCode(updatedForm.getQrCode()); // Update QR code data

            // Save the updated form
            return bhoomikaDataRepository.save(existingForm);
        }
        return null; // Return null if the form does not exist
    }

    private String generateQRCodeData(BhoomikaForm form) {
        return (
                """
                Reference: %s
                Kit Form Date: %s
                BLC PO: %s
                BLC PO Date: %s
                CCN: %s
                Machine Name: %s
                Stage : %s
                Assembly Code: %s
                Inward By: %s
                Inward Quantity: %d
                Description: %s
                Kit Acknowledge By: %s
                Kit Acknowledge Date: %s
                Assy Start Date: %s
                Bhomika Engineer: %s
                Live Status: %s
                BLC Status : %s
                Assy End Date: %s
                Invoice Number: %s
                Invoice Date: %s
                Hold Date: %s
                Hold Remark: %s
                Close Date: %s
                Close Remark: %s
                KM Location: %s
                Limbodagri Location: %s""").formatted(
                form.getReference(),
                form.getKitFormDate(),
                form.getBlcPo(),
                form.getBlcPoDate(),
                form.getCcn(),
                form.getMachineName(),
                form.getStage(),
                form.getAssemblyCode(),
                form.getInwardBy(),
                form.getInwardQty(),
                form.getDescription(),
                form.getKitAcknowledgeBy(),
                form.getKitAcknowledgeDate(),
                form.getAssyStartDate(),
                form.getBhomikaEngineer(),
                form.getLiveStatus(),
                form.getBlcStatus(),
                form.getAssyEndDate(),
                form.getInvoiceNumber(),
                form.getInvoiceDate(),
                form.getHoldDate(),
                form.getHoldRemark(),
                form.getCloseDate(),
                form.getCloseRemark(),
                form.getKmLocation(),
                form.getLimbodagriLocation()
        );
    }
    
    private void generateQRCode(String data, String referenceId) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            String filePath = "/actual/path/to/save/qrcodes/" + referenceId + ".png"; // Update this path
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Path.of(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public BhoomikaForm findByReference(String referenceId) {
        throw new UnsupportedOperationException("Unimplemented method 'findByReference'");
    }

  
    
}