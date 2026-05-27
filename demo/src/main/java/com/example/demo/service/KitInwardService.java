package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KitInwardRepsitory;

@Service
public class KitInwardService {
    @Autowired
private KitInwardRepsitory kitInwardRepsitory;

public boolean isBLCPOUnique(String blcPo) {
    return !kitInwardRepsitory.existsByBlcPo(blcPo);
}


}

