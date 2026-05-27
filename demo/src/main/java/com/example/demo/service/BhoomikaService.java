package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BhoomikaStatusDto;
import com.example.demo.model.BhoomikaStatus;
import com.example.demo.repository.BhoomikaStatusRepository;



@Service
public class BhoomikaService {
     @Autowired
    private BhoomikaStatusRepository bhoomikaStatusRepository;

    public void saveStatus(BhoomikaStatusDto bhoomikaStatusDto) {
        // Map the StatusForm to an entity and save it
        BhoomikaStatus bhoomikaStatus2 = new BhoomikaStatus();
        // Set properties from statusForm to statusEntity
        bhoomikaStatus2.setDate(bhoomikaStatusDto.getDate());
        bhoomikaStatus2.setReference(bhoomikaStatusDto.getReference());
        bhoomikaStatus2.setBlcPo(bhoomikaStatusDto.getBlcPo());
        bhoomikaStatus2.setBlcPoDate(bhoomikaStatusDto.getBlcPoDate());
        bhoomikaStatus2.setCcn(bhoomikaStatusDto.getCcn());
        bhoomikaStatus2.setMachineName(bhoomikaStatusDto.getMachineName());
        bhoomikaStatus2.setAssemblyCode(bhoomikaStatusDto.getAssemblyCode());
        bhoomikaStatus2.setInwardQty(bhoomikaStatusDto.getInwardQty());
        bhoomikaStatus2.setInwardBy(bhoomikaStatusDto.getInwardBy());
        bhoomikaStatus2.setStage(bhoomikaStatusDto.getStage());
        bhoomikaStatus2.setKitRecordDate(bhoomikaStatusDto.getKitRecordDate());
        bhoomikaStatus2.setKitAcknowledgeBy(bhoomikaStatusDto.getKitAcknowledgeBy());
        bhoomikaStatus2.setKitAcknowledgeDate(bhoomikaStatusDto.getKitAcknowledgeDate());
        bhoomikaStatus2.setAssyStartDate(bhoomikaStatusDto.getAssyStartDate());
        bhoomikaStatus2.setBhomikaEngineer(bhoomikaStatusDto.getBhomikaEngineer());
        bhoomikaStatus2.setLiveStatus(bhoomikaStatusDto.getLiveStatus());
        bhoomikaStatus2.setBlcStatus(bhoomikaStatusDto.getBlcStatus());
        bhoomikaStatus2.setInvoiceNumber(bhoomikaStatusDto.getInvoiceNumber());
        bhoomikaStatus2.setInvoiceDate(bhoomikaStatusDto.getInvoiceDate());
        

        // Save the entity
        bhoomikaStatusRepository.save(bhoomikaStatus2);
    }



}
