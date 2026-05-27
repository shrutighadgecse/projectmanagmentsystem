package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ccn;
import com.example.demo.model.InwardBy;
import com.example.demo.repository.CcnRepository;
import com.example.demo.repository.InwardByRepsository;
@Service
public class DropdownService2 {



    @Autowired
    private InwardByRepsository inwardByRepsository;

    @Autowired
    private CcnRepository ccnRepsository;

    public List<String> getCcnOptions() {
        return ccnRepsository.findAll().stream()
                .map(Ccn::getCcnName)
                .collect(Collectors.toList());
    }
    public void addCcnOption(String name) {
        if (name != null && !name.trim().isEmpty() && !ccnRepsository.existsByCcnNameIgnoreCase(name)) {
            Ccn newCcn = new Ccn(name.trim());
            ccnRepsository.save(newCcn);
        } else {
            throw new IllegalArgumentException("Duplicate or invalid CCN name");
        }
    }
    

    public List<String> getInwardByOptions() {
        return inwardByRepsository.findAll().stream()
                .map(InwardBy::getInwardBy)
                .collect(Collectors.toList());
    }

    public void addInwardByOption(String name) {
        if (!inwardByRepsository.existsByInwardByIgnoreCase(name)) {
            InwardBy newInwardBy = new InwardBy();
            newInwardBy.setInwardBy(name); // Set correct field
            inwardByRepsository.save(newInwardBy);
        }
    }
}
