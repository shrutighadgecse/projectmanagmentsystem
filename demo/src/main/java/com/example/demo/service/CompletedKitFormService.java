package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CompletedKitForm;
import com.example.demo.model.KitForm;
import com.example.demo.repository.CompletedKitFormRepository;

@Service
public class CompletedKitFormService {
    @Autowired
    private CompletedKitFormRepository completedKitFormRepository;

    public void saveCompletedKitForm(KitForm kitForm) {
        CompletedKitForm completedKitForm = new CompletedKitForm();
        completedKitForm.setReferenceId(kitForm.getReferenceId());
        completedKitForm.setBlcPo(kitForm.getBlcPo());
        completedKitForm.setBlcPoDate(kitForm.getBlcPoDate());
        completedKitForm.setCcn(kitForm.getCcn());
        completedKitForm.setAssemblyCode(kitForm.getAssemblyCode());
        completedKitForm.setStage(kitForm.getStage());
        completedKitForm.setMachineName(kitForm.getMachineName());
        completedKitForm.setStatus(kitForm.getStatus());
        completedKitForm.setInwardBy(kitForm.getInwardBy());
        completedKitForm.setInwardQty(kitForm.getInwardQty());
        completedKitForm.setDescription(kitForm.getDescription());
        completedKitForm.setDateTime(kitForm.getDateTime());
        completedKitForm.setCompletionDate(LocalDateTime.now()); // Set current date and time

        completedKitFormRepository.save(completedKitForm);
    }
}