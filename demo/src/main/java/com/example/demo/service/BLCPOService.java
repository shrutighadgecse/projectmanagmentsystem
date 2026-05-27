package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BLCPO;
import com.example.demo.repository.BLCPORepository;



@Service
public class BLCPOService {

    @Autowired
    private BLCPORepository blcpoRepository;

    public List<String> findSuggestions(String query) {
        List<String> suggestions = blcpoRepository.findByBlcPoContaining(query);
        System.out.println("Suggestions found: " + suggestions);
        return suggestions;
    }
    

    public void saveBLCPO(String blcPo) {
        BLCPO newBLCPO = new BLCPO();
        newBLCPO.setBlcPo(blcPo);
        blcpoRepository.save(newBLCPO);
    }
}
